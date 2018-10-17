package com.futao.springmvcdemo.foundation.configuration;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Hibernate Validator配置类
 *
 * @author futao
 * Created on 2018/9/23-20:00.
 */
@Configuration
public class HibernateValidatorConfiguration {

    /**
     * JSR和Hibernate validator的校验只能对Object的属性进行校验
     * 不能对单个的参数进行校验
     * spring 在此基础上进行了扩展
     * 添加了MethodValidationPostProcessor拦截器
     * 可以实现对方法参数的校验
     *
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator());
        return processor;
    }

    @Bean
    public static Validator validator() {
        return Validation
                .byProvider(HibernateValidator.class)
                .configure()
                //快速返回模式，有一个验证失败立即返回错误信息
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();
    }
//
//    public static <T> void validate(T obj) {
//        Set<ConstraintViolation<T>> constraintViolations = validator().validate(obj);
//        if (constraintViolations.size() > 0) {
//            throw LogicException.le(constraintViolations.iterator().next().getMessage());
//        }
//    }
}
