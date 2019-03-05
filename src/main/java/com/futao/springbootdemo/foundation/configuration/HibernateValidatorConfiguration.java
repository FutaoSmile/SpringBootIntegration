package com.futao.springbootdemo.foundation.configuration;

import com.futao.springbootdemo.foundation.LogicException;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Properties;
import java.util.Set;

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
        processor.setValidator(getValidator());
        return processor;
    }


    /**
     * 手动触发校验，可直接在需要校验对象的地方调用这个方法
     * 对于重写了message的提示信息，直接返回该message
     * 对于没有重写message的系统默认提示信息，返回某个字段-违反了某个规则的message格式返回
     *
     * @param obj
     */
    public static void validate(Object obj) {
        Set<ConstraintViolation<Object>> constraintViolations = localValidatorFactoryBean.validate(obj);
        if (constraintViolations.size() > 0) {
            String message = constraintViolations.iterator().next().getMessage();
            throw LogicException.le(message.contains("_") ? message : "notSet" + constraintViolations.iterator().next().getPropertyPath() + " " + message);
        }
    }


    /**
     * 获取Validator
     *
     * @return
     * @throws Exception
     */
    @Bean
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(getMessageSource());
        validator.setProviderClass(HibernateValidator.class);
        Properties properties = new Properties();
        //设置快速返回模式
        properties.setProperty("hibernate.validator.fail_fast", "true");
        validator.setValidationProperties(properties);
        localValidatorFactoryBean = validator;
        return validator;
    }


    /**
     * 获取Validator的简单写法，但是这种方式无法设置messageSource，无法实现国际化
     *
     * @return
     */
    private static Validator validator() {
        return Validation
                .byProvider(HibernateValidator.class)
                .configure()
                //快速返回模式，有一个验证失败立即返回错误信息
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();
    }


    /**
     * 获取国际化资源
     *
     * @return
     */
    private ResourceBundleMessageSource getMessageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        resourceBundleMessageSource.setBasenames("i18n/errorMessage");
        return resourceBundleMessageSource;
    }

    private static LocalValidatorFactoryBean localValidatorFactoryBean;


}
