package com.futao.springmvcdemo.annotation.interceptor.impl;

import com.alibaba.fastjson.JSON;
import com.futao.springmvcdemo.annotation.interceptor.LoginUser;
import com.futao.springmvcdemo.model.entity.User;
import com.futao.springmvcdemo.model.system.ErrorMessage;
import com.futao.springmvcdemo.model.system.RestResult;
import com.futao.springmvcdemo.model.system.Constant;
import com.futao.springmvcdemo.service.UserService;
import com.futao.springmvcdemo.utils.ThreadLocalUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author futao
 * Created on 2018/9/19-14:44.
 * 对请求标记了LoginUser的方法进行拦截
 */
@Component
public class LoginUserInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginUserInterceptor.class);

    @Resource
    private ThreadLocalUtils<User> threadLocalUtils;
    @Resource
    private UserService userService;

    /**
     * 在请求到达Controller之前进行拦截并处理
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            //注解在方法上
            LoginUser loginUserAnnotation = ((HandlerMethod) handler).getMethodAnnotation(LoginUser.class);
            //注解在类上
            LoginUser classLoginUserAnnotation = ((HandlerMethod) handler).getMethod().getDeclaringClass().getAnnotation(LoginUser.class);
            if (ObjectUtils.anyNotNull(loginUserAnnotation, classLoginUserAnnotation)) {
                HttpSession session = request.getSession(false);
                //session不为空
                if (ObjectUtils.allNotNull(session)) {
                    String loginUserId = (String) session.getAttribute(Constant.LOGIN_USER_SESSION_KEY);
                    if (ObjectUtils.allNotNull(loginUserId)) {
                        User currentUser = userService.getUserById(loginUserId);
                        System.out.println("当前登陆用户为：" + currentUser);
                        //将当前用户的信息存入threadLocal中
                        threadLocalUtils.set(currentUser);
                    } else {
                        System.out.println("用户不存在");
                        return false;
                    }
                } else {//session为空，用户未登录
                    RestResult restResult = new RestResult(false, "-1", ErrorMessage.NOT_LOGIN, ErrorMessage.NOT_LOGIN.substring(6));
                    response.getWriter().append(JSON.toJSONString(restResult));
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //释放threadLocal资源
        threadLocalUtils.remove();
    }
}
