package com.futao.springmvcdemo.annotation.impl.aop;

import com.futao.springmvcdemo.annotation.Role;
import com.futao.springmvcdemo.foundation.LogicException;
import com.futao.springmvcdemo.model.entity.User;
import com.futao.springmvcdemo.model.enums.UserRoleEnum;
import com.futao.springmvcdemo.model.system.ErrorMessage;
import com.futao.springmvcdemo.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 用户角色权限拦截
 *
 * @author futao
 * Created on 2018-12-13.
 */
@Order(1)
@Aspect
@Component
public class RoleInterceptor {

    @Resource
    private UserService userService;

    /**
     * 切入点
     *
     * @Pointcut("@within(org.springframework.web.bind.annotation.RestController) && @annotation(org.springframework.web.bind.annotation.RestController)")
     * @Pointcut("execution(public * com.futao.springmvcdemo.controller.*.*(..))")
     * @Pointcut("execution(public * com.futao.springmvcdemo.controller..* (..)) && @annotation(org.springframework.web.bind.annotation.RestController)")
     */
    @Pointcut("@annotation(com.futao.springmvcdemo.annotation.Role)")
    public void pointCut() {

    }

    /**
     * 进入方法之前执行
     *
     * @param point
     */
    @Before("pointCut()")
    public void checkUserRole(JoinPoint point) {
        User user = userService.currentUser();
        //未登录
        if (user == null) {
            throw LogicException.le(ErrorMessage.LogicErrorMessage.NOT_LOGIN);
        }
        //注解打在方法上
        Role annotation = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(Role.class);
        if (annotation == null) {
            //注解打在类上
            annotation = (Role) point.getSignature().getDeclaringType().getAnnotation(Role.class);
        }
        if (annotation != null) {
            if (!Arrays.asList(annotation.value()).contains(UserRoleEnum.value(user.getRole()))) {
                throw LogicException.le(ErrorMessage.LogicErrorMessage.ROLE_NOT_ALLOW);
            }
        }
    }

//    @Around("pointCut()")
//    public void around(ProceedingJoinPoint point) throws Throwable {
//        System.out.println(StringUtils.repeat("-", 100));
//        point.proceed();
//    }

}