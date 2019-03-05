package com.futao.springbootdemo.model.system;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;

/**
 * sentinel限流配置中心
 *
 * @author futao
 * Created on 2019-01-14.
 */
public enum SentinelResourceEnum {

    /**
     * api统计接口限流
     */
    STATISTIC_API_LIST(RuleConstant.FLOW_GRADE_QPS, 2),

    /**
     * enum统计接口限流
     */
    STATISTIC_ENUM(RuleConstant.FLOW_GRADE_QPS, 5);

    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 限流规则
     */
    private int ruleConstant;

    /**
     * 每秒通过请求次数
     */
    private int count;

    SentinelResourceEnum(int ruleConstant, int count) {
        this.resourceName = this.name();
        this.ruleConstant = ruleConstant;
        this.count = count;
    }

    public String getResourceName() {
        return resourceName;
    }

    public int getRuleConstant() {
        return ruleConstant;
    }

    public int getCount() {
        return count;
    }
}
