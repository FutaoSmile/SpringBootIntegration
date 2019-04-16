package com.futao.springbootdemo.annotation.impl.aop;

import com.futao.springbootdemo.annotation.Role;
import com.futao.springbootdemo.foundation.LogicException;
import com.futao.springbootdemo.model.entity.User;
import com.futao.springbootdemo.model.enums.UserRoleEnum;
import com.futao.springbootdemo.model.system.ErrorMessage;
import com.futao.springbootdemo.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 用户角色权限拦截
 *
 * @author futao
 * Created on 2018-12-13.
 */
@Aspect
@Component
public class RoleSimpleInterceptor {

    @Resource
    private UserService userService;

    /**
     * 切入点
     */
    @Pointcut("@within(com.futao.springbootdemo.annotation.Role)||@annotation(com.futao.springbootdemo.annotation.Role)")
    public void pointCut() {

    }

    /**
     * 进入方法之前执行
     *
     * @param point
     */
    @Before("@within(annotation)||@annotation(annotation)")
    public void checkUserRole(JoinPoint point, Role annotation) {
        User user = userService.currentLoginUser();
//        //注解打在方法上
//        Role annotation = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(Role.class);
//        if (annotation == null) {
//            //注解打在类上
//            annotation = (Role) point.getSignature().getDeclaringType().getAnnotation(Role.class);
//        }
        if (annotation != null) {
            if (!Arrays.asList(annotation.value()).contains(UserRoleEnum.value(user.getRole()))) {
                throw LogicException.le(ErrorMessage.LogicErrorMessage.ROLE_NOT_ALLOW);
            }
        }
    }

}