package com.futao.springbootdemo.foundation.mq.active.ptp;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author futao
 * Created on 2019-06-04.
 */
@AllArgsConstructor
@Getter
public enum ActiveMqQueueEnum {
    /**
     * springboot-test-queue=测试Queue
     */
    TEST_QUEUE("springboot-test-queue", "测试Queue");

    private String queueName;
    private String desc;


    public static final String testQueue = "springboot-test-queue";

}
