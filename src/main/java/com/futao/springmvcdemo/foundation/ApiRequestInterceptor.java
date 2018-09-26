package com.futao.springmvcdemo.foundation;

import org.aspectj.lang.annotation.*;

/**
 * @author futao
 * Created on 2018/9/25.
 * MethodInterceptor
 * Springboot中使用切面aop
 */
//@Aspect
//@Component
//@Order(1)
public class ApiRequestInterceptor {

    //@Pointcut("@within(org.springframework.web.bind.annotation.RestController) && @annotation(org.springframework.web.bind.annotation.RestController)")
    @Pointcut("execution(public * com.futao.springmvcdemo.controller.*.*(..))")
//        @Pointcut("execution(public * com.futao.springmvcdemo.controller..* (..)) && @annotation(org.springframework.web.bind.annotation.RestController)")
    public void pointCut() {
    }

    /**
     * 方法执行前
     */
    @Before(value = "pointCut()")
    public void before() {
        System.out.println("@Before");
    }

    /**
     * 方法执行后
     */
    @After(value = "pointCut()")
    public void after() {
        System.out.println("@After");
    }

    @Around(value = "pointCut()")
    public void advice() {
        System.out.println("@Around");
    }

    @AfterReturning(value = "pointCut()")
    public void afterReturn() {
        System.out.println("@AfterReturning");
    }


    @AfterThrowing(value = "pointCut()")
    public void afterThrow() {
        System.out.println("@AfterThrow");
    }
}
