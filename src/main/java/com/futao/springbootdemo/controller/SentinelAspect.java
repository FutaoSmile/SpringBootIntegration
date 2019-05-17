package com.futao.springbootdemo.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.futao.springbootdemo.model.system.ErrorMessage;
import com.lazyer.foundation.foundation.exception.LogicException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author futao
 * Created on 2019-01-14.
 */
@Component
@Aspect
public class SentinelAspect {

    @Pointcut("@annotation(com.futao.springbootdemo.controller.SentinelAnn)")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("66666");
    }

    public void initSentinelFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("niubi");
        //限流阈值类型
        rule.setGrade(1);
        //限流阈值，表示每秒钟通过n次请求
        rule.setCount(2);
        //将定义好的rule放在List中
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        initSentinelFlowRules();
        System.out.println(StringUtils.repeat("-", 100));
        Entry entry = null;
        try {
            entry = SphU.entry("niubi");
            return point.proceed();
        } catch (BlockException e1) {
            throw LogicException.le(ErrorMessage.LogicErrorMessage.VISIT_TOO_FREQUENTLY);
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
    }
}
