package com.futao.springbootdemo.other.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import com.futao.springbootdemo.foundation.shiro.CustomShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * @author futao
 * Created on 2018/11/15.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ShiroTest {


    //    SimpleAccountRealm realm = new SimpleAccountRealm();
//    JdbcRealm realm = new JdbcRealm();

    CustomShiroRealm realm = new CustomShiroRealm();

    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/springmvcdemo?useUnicode=true&characterEncoding=utf8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456789");
        return druidDataSource;
    }

    @Before
    public void addUser() {
//        realm.addAccount("futao", "123456", "admin");
//        realm.setDataSource(druidDataSource());
//        realm.setAuthenticationQuery("select * from futao_user where username = ?");
        //jdbcRealm默认不查询权限数据，需要手动开启
//        realm.setPermissionsLookupEnabled(true);
//        realm.setPermissionsQuery("");
//        realm.setUserRolesQuery("");
    }

    /**
     * Shiro认证
     */
    @Test
    public void test28() {
        //设置SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //设置数据源Realm
        securityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "82320d5140d5efb1762e27edb606922b");
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        //主体提交认证请求
        subject.login(usernamePasswordToken);
        System.out.println("权限admin：" + subject.hasRole("admin"));
        System.out.println(subject.isAuthenticated());
    }

}
