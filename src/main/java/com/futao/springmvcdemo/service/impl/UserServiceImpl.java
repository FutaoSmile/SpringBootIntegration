package com.futao.springmvcdemo.service.impl;

import com.futao.springmvcdemo.dao.UserDao;
import com.futao.springmvcdemo.foundation.LogicException;
import com.futao.springmvcdemo.model.entity.User;
import com.futao.springmvcdemo.model.enums.User_Status;
import com.futao.springmvcdemo.model.system.*;
import com.futao.springmvcdemo.service.MailService;
import com.futao.springmvcdemo.service.UUIDService;
import com.futao.springmvcdemo.service.UserService;
import com.futao.springmvcdemo.utils.CommonUtilsKt;
import com.futao.springmvcdemo.utils.PageResultUtils;
import com.futao.springmvcdemo.utils.ThreadLocalUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.futao.springmvcdemo.utils.TimeUtilsKt.currentTimeStamp;

/**
 * @author futao
 * Created on 2018/9/20-15:16.
 * Spring事务超时 = 事务开始时到最后一个Statement创建时时间 + 最后一个Statement的执行时超时时间（即其queryTimeout）。所以在在执行Statement之外的超时无法进行事务回滚。
 * 参考：https://blog.csdn.net/qq_18860653/article/details/79907984
 */
@Transactional(isolation = Isolation.DEFAULT, timeout = Constant.SERVICE_TIMEOUT_TIME, rollbackFor = Exception.class)
@Service
public class UserServiceImpl implements UserService {
    /**
     * 密码加盐
     */
    private static final String pwdSalt = "nobug666";
    @Resource
    private ThreadLocalUtils threadLocalUtils;

    @Autowired
    private UserDao userDao;

    @Resource
    private MailService mailService;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public User currentUser() {
        return (User) threadLocalUtils.get();
    }

    @Override
    public User getUserByMobile(String mobile) {
        return userDao.getUserByMobile(mobile);
    }

    @Override
    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

    @Value("${futao.registerMailCodeExpireSecond}")
    public int registerMailCodeExpireSecond;

    @Override
    public void registerByEmail(String username, String password, int age, String mobile, String email, String address, String verifyCode, int sex) {
        //1.查询该手机号有没有被注册
//        User userInfo = userDao.getUserByMobile(mobile);

        //检查验证码
        Object o = redisTemplate.opsForValue().get(RedisKeySet.gen(RedisKeySet.registerEmailCode, email));
        //检查是否过期
        if (o == null) {
            throw LogicException.le(ErrorMessage.VERIFY_CODE_EXPIRED);
        }
        //检查是否正确
        if (!verifyCode.equals(o.toString())) {
            throw LogicException.le(ErrorMessage.VERIFY_CODE_ERROR);
        }

        //检查该邮箱是否已经被注册
        if (userDao.getNormalUserByEmail(email, User_Status.Normal.getCode()) != null) {
            throw LogicException.le(ErrorMessage.EMAIL_ALREADY_EXIST);
        }
        //更新账号信息与状态
        userDao.registerByEmail(username, CommonUtilsKt.md5(password + pwdSalt), age, mobile, address, User_Status.Normal.getCode(), sex, email);
    }

    /**
     * 用户登录
     *
     * @param mobile
     * @param password
     * @param request
     * @return
     */
    @Override
    public User login(String mobile, String password, HttpServletRequest request) {
        String md5Pwd = CommonUtilsKt.md5(password);
        User user = userDao.getUserByMobileAndPwd(mobile, md5Pwd);
        if (ObjectUtils.allNotNull(user)) {
            HttpSession session = request.getSession();
            session.setAttribute(Constant.LOGIN_USER_SESSION_KEY, String.valueOf(user.getId()));
            session.setMaxInactiveInterval(Constant.SESSION_INVALIDATE_SECOND);
            return user;
        } else {
            throw LogicException.le(ErrorMessage.MOBILE_OR_PWD_ERROR);
        }
    }

    @Override
    public User userNameLogin(User user, HttpServletRequest request) {
        String md5Pwd = CommonUtilsKt.md5(user.getPassword());
        User byUserNameAndPwd = userDao.getUserByUserNameAndPwd(user.getUsername(), md5Pwd);

        if (ObjectUtils.allNotNull(byUserNameAndPwd)) {
            HttpSession session = request.getSession();
            session.setAttribute(Constant.LOGIN_USER_SESSION_KEY, String.valueOf(user.getId()));
            session.setMaxInactiveInterval(Constant.SESSION_INVALIDATE_SECOND);
            return byUserNameAndPwd;
        } else {
            throw LogicException.le(ErrorMessage.MOBILE_OR_PWD_ERROR);
        }
    }

    @Override
    @Cacheable(value = "user")
    public List<User> list(String mobile, int pageNum, int pageSize, String orderBy) {
        PageResultUtils<User> pageResultUtils = new PageResultUtils<>();
        String sql = pageResultUtils.createCriteria(User.class.getSimpleName())
                .orderBy(orderBy)
                .page(pageNum, pageSize)
                .getSql();
        return userDao.list(sql);
    }

    @Override
    public int total() {
        return userDao.total("futao_user");
    }

    /**
     * 发送注册邮件验证码
     *
     * @param email
     */
    @Override
    public void sendRegisterEmailVerifyCode(String email) {
        //1.检查该邮箱是否已经被注册
        if (userDao.getNormalUserByEmail(email, User_Status.Normal.getCode()) != null) {
            throw LogicException.le(ErrorMessage.EMAIL_ALREADY_EXIST);
        }

        //2.预注册，用户表生成一条数据，存储email
        Timestamp currentTimeStamp = currentTimeStamp();
        userDao.preRegister(UUIDService.get(), email, User_Status.Pre_Register.getCode(), currentTimeStamp, currentTimeStamp);
        //3.判断是否已经发送了邮件且未过期
        if (redisTemplate.opsForValue().get(RedisKeySet.gen(RedisKeySet.registerEmailCode, email)) != null) {
            throw LogicException.le(ErrorMessage.EMAIL_ALREADY_SEND);
        }
        String verifyCode = CommonUtilsKt.numVerifyCode(6);

        //4.通过消息队列发送注册邮件
        MailMSingle mailM = new MailMSingle();
        mailM.setTo(email);
        mailM.setSubject("快乐的网站 | 注册验证码");
        mailM.setContent("您的验证码是" + verifyCode);
        mailM.setCc("1185172056@qq.com");

        mailService.sendSimpleEmail(new String[]{mailM.getTo()}, new String[]{mailM.getCc()}, mailM.getSubject(), mailM.getContent());
        //5.将验证码存入redis环境，控制有效期
        redisTemplate.opsForValue().set(RedisKeySet.gen(RedisKeySet.registerEmailCode, email), verifyCode, registerMailCodeExpireSecond, TimeUnit.SECONDS);

    }
}
