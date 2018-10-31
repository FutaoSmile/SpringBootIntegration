package com.futao.springmvcdemo;

import com.alibaba.fastjson.parser.ParserConfig;
import com.futao.springmvcdemo.annotation.EnableEntity;
import com.futao.springmvcdemo.model.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Map;

/**
 * @author futao
 * ServletComponentScan 开启servlet和filter
 * springboot项目在启动的时候如果抛出了Exception的话会导致项目启动失败，且异常信息不会打印出来
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.futao.springmvcdemo.dao")
//@EnableCaching
@EnableScheduling
@EnableAsync
@EnableAspectJAutoProxy
//@EnableElasticsearchRepositories(basePackages = "com.futao.springmvcdemo")
@EnableEntity
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
        Map<String, User> stringUserMap = context.getBeansOfType(User.class);
        System.out.println(stringUserMap.keySet() + "-------");
        /**
         * redis反序列化
         * 开启fastjson反序列化的autoType
         */
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
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
    }

}
