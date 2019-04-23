package com.futao.springbootdemo;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.parser.ParserConfig;
import com.futao.springbootdemo.annotation.EnableEntity;
import com.futao.springbootdemo.foundation.mq.rabbit.Receiver;
import com.futao.springbootdemo.model.entity.SystemInformation;
import com.futao.springbootdemo.model.system.SentinelResourceEnum;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

/**
 * ServletComponentScan 开启servlet和filter
 * springboot项目在启动的时候如果抛出了Exception的话会导致项目启动失败，且异常信息不会打印出来
 * <p>
 * The @EnableRedisHttpSession annotation
 * creates a Spring Bean with the name of springSessionRepositoryFilter that implements Filter.
 *
 * @author futao
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.futao.springbootdemo.dao")      //会将包里面的接口当做mapper配置，之后可以自动引入mapper类
@EnableCaching  //开启缓存
@EnableAspectJAutoProxy
//@EnableElasticsearchRepositories(basePackages = "com.futao.springbootdemo.dao")
@EnableEntity
//@EnableWebSocketMessageBroker
@EnableTransactionManagement//加上这个注解，使得支持事务
//@EnableRedisHttpSession
@EnableConfigurationProperties
public class SpringBootDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        /**
         * 添加elasticsearch之后发生异常的解决方案
         * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
         * 解决netty冲突后初始化client时还会抛出异常
         * java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4]
         */
        System.setProperty("es.set.netty.runtime.available.processors", "false");

        ConfigurableApplicationContext context = SpringApplication.run(SpringBootDemoApplication.class, args);

        /**
         * redis反序列化
         * 开启fastjson反序列化的autoType
         */
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        //初始化sentinel限流规则
        initSentinelFlowRules();
    }


    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("//\n" +
                "//                       _oo0oo_\n" +
                "//                      o8888888o\n" +
                "//                      88\" . \"88\n" +
                "//                      (| -_- |)\n" +
                "//                      0\\  =  /0\n" +
                "//                    ___/`---'\\___\n" +
                "//                  .' \\\\|     |// '.\n" +
                "//                 / \\\\|||  :  |||// \\\n" +
                "//                / _||||| -:- |||||- \\\n" +
                "//               |   | \\\\\\  -  /// |   |\n" +
                "//               | \\_|  ''\\---/''  |_/ |\n" +
                "//               \\  .-\\__  '-'  ___/-. /\n" +
                "//             ___'. .'  /--.--\\  `. .'___\n" +
                "//          .\"\" '<  `.___\\_<|>_/___.' >' \"\".\n" +
                "//         | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
                "//         \\  \\ `_.   \\_ __\\ /__ _/   .-` /  /\n" +
                "//     =====`-.____`.___ \\_____/___.-`___.-'=====\n" +
                "//                       `=---='//\n" +
                "//\n" +
                "//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "//\n" +
                "//               佛祖保佑         永无BUG\n" +
                "//");

//        System.out.println("Sending message...");
//        rabbitTemplate.convertAndSend(topicExchange, "foo.bar.baz", "Hello from RabbitMQ!");
//        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);

    }

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public SpringBootDemoApplication(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }


    /**
     * 初始化sentinel限流规则
     */
    public static void initSentinelFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        //资源名
        for (SentinelResourceEnum sentinelResource : SentinelResourceEnum.values()) {
            rule.setResource(sentinelResource.getResourceName());
            //限流阈值类型
            rule.setGrade(sentinelResource.getRuleConstant());
            //限流阈值，表示每秒钟通过n次请求
            rule.setCount(sentinelResource.getCount());
            //将定义好的rule放在List中
            rules.add(rule);
            System.out.println("加载限流规则" + rule);
        }
        FlowRuleManager.loadRules(rules);
    }


    @Scope(value = SCOPE_SINGLETON)
    @Bean
    public SystemInformation startTimestamp() {
        return new SystemInformation(new Timestamp(System.currentTimeMillis()));
    }


    /**
     * 开启spring-session redis管理session
     * We create a RedisConnectionFactory that connects Spring Session to the Redis Server
     *
     * @return
     */
//    @Bean
//    public LettuceConnectionFactory connectionFactory() {
//        return new LettuceConnectionFactory();
//    }
}
