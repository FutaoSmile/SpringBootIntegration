package com.futao.springmvcdemo.service.impl

import com.futao.springmvcdemo.foundation.LogicException
import com.futao.springmvcdemo.model.system.ErrorMessage
import com.futao.springmvcdemo.service.KotlinTestService
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.stereotype.Service

/**
 * @author futao
 * Created on ${date}.
 * 实现Condition接口，重写matches()方法，不需要打@Service注解，否则matches无效
 * matches()返回为true时Spring会实例化这个类
 * 使用：
 *  可以在其他类上打上@Condition(K::class)注解，会根据K类是否被实例化再来实例化自己
 *
 */
@Service
open class KotlinTestServiceImpl : Condition, KotlinTestService {

    val logger = LoggerFactory.getLogger(KotlinTestServiceImpl::class.java)!!
    /**
     * Determine if the condition matches.
     * @param context the condition context
     * @param metadata metadata of the [class][org.springframework.core.type.AnnotationMetadata]
     * or [method][org.springframework.core.type.MethodMetadata] being checked
     * @return `true` if the condition matches and the component can be registered,
     * or `false` to veto the annotated component's registration
     */
    override fun matches(context: ConditionContext?, metadata: AnnotatedTypeMetadata?): Boolean {
        val osName = context!!.environment.getProperty("os.name")
        logger.info("osName$osName")
        return true
    }

    override fun t(): String {
        return "test"
    }


    override fun exception() {
        throw LogicException.le(ErrorMessage.LOGIC_EXCEPTION)
    }
}