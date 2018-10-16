package com.futao.springmvcdemo.service.impl;

import com.futao.springmvcdemo.dao.UserDao;
import com.futao.springmvcdemo.foundation.LogicException;
import com.futao.springmvcdemo.model.entity.User;
import com.futao.springmvcdemo.model.entity.constvar.ErrorMessage;
import com.futao.springmvcdemo.model.system.SystemConfig;
import com.futao.springmvcdemo.service.UUIDService;
import com.futao.springmvcdemo.service.UserService;
import com.futao.springmvcdemo.utils.CommonUtilsKt;
import com.futao.springmvcdemo.utils.PageResultUtils;
import com.futao.springmvcdemo.utils.ThreadLocalUtils;
import lombok.val;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

import static com.futao.springmvcdemo.utils.TimeUtilsKt.currentTimeStamp;

/**
 * @author futao
 * Created on 2018/9/20-15:16.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private ThreadLocalUtils threadLocalUtils;

    @Autowired
    private UserDao userDao;

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

    @Override
    public boolean register(String username, String age, String mobile, String email, String address) {
        User user1 = new User();
        user1.setUsername("1");
        user1.setAge(age);
        user1.setMobile(mobile);
        user1.setEmail(email);
        user1.setAddress(address);

        //1.查询该手机号有没有被注册
        User userInfo = userDao.getUserByMobile(mobile);
        //2.已经被注册返回注册失败，已经被注册的提示
        if (ObjectUtils.allNotNull(userInfo)) {
            throw LogicException.le(ErrorMessage.MOBILE_ALREADY_REGISTER);
        } else {
            //3.未被注册，进行注册,返回注册成功的提示
            Timestamp currentTimeStamp = currentTimeStamp();
            int count = userDao.addUser(UUIDService.get(), username, age, mobile, email, address, currentTimeStamp, currentTimeStamp);
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        }
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
            session.setAttribute(SystemConfig.LOGIN_USER_SESSION_KEY, String.valueOf(user.getId()));
            session.setMaxInactiveInterval(SystemConfig.SESSION_INVALIDATE_SECOND);
            return user;
        } else {
            throw LogicException.le(ErrorMessage.MOBILE_OR_PWD_ERROR);
        }
    }

    @Override
    public List<User> list(String mobile, int pageNum, int pageSize, String orderBy) {
        PageResultUtils<User> pageResultUtils = new PageResultUtils<>();
        final val sql = pageResultUtils.createCriteria("futao_" + User.class.getSimpleName())
                                       .orderBy(orderBy)
                                       .page(pageNum, pageSize)
                                       .getSql();
        return userDao.list(sql);
    }

    @Override
    public int total() {
        return userDao.total("futao_user");
    }
}
