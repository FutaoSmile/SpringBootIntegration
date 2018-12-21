package com.futao.springmvcdemo;

import com.alibaba.fastjson.parser.ParserConfig;
import com.futao.springmvcdemo.annotation.EnableEntity;
import com.futao.springmvcdemo.model.entity.SystemInformation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Timestamp;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

/**
 * @author futao
 * ServletComponentScan 开启servlet和filter
 * springboot项目在启动的时候如果抛出了Exception的话会导致项目启动失败，且异常信息不会打印出来
 * <p>
 * The @EnableRedisHttpSession annotation
 * creates a Spring Bean with the name of springSessionRepositoryFilter that implements Filter.
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.futao.springmvcdemo.dao")      //会将包里面的接口当做mapper配置，之后可以自动引入mapper类
//@EnableCaching
@EnableScheduling
@EnableAsync
@EnableAspectJAutoProxy
//@EnableElasticsearchRepositories(basePackages = "com.futao.springmvcdemo")
@EnableEntity
//@EnableWebSocketMessageBroker
@EnableTransactionManagement
//@EnableRedisHttpSession
public class SpringmvcdemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        /**
         * 添加elasticsearch之后发生异常的解决方案
         * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
         * 解决netty冲突后初始化client时还会抛出异常
         * java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4]
         */
        System.setProperty("es.set.netty.runtime.available.processors", "false");

        ConfigurableApplicationContext context = SpringApplication.run(SpringmvcdemoApplication.class, args);
        /**
         * redis反序列化
         * 开启fastjson反序列化的autoType
         */
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
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
    }

}
