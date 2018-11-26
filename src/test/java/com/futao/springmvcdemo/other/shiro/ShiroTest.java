package com.futao.springmvcdemo.other.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author futao
 * Created on 2018/11/15.
 * 安全框架shiro测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ShiroTest {


    SimpleAccountRealm realm = new SimpleAccountRealm();
    JdbcRealm jdbcRealm = new JdbcRealm();


    @Resource
    private DruidDataSource dataSource;

    @Before
    public void addUser() {
//        realm.addAccount("futao", "123456");
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setAuthenticationQuery("");
        jdbcRealm.setPermissionsQuery("");
        jdbcRealm.setUserRolesQuery("");
    }

    @Test
    public void test28() {
        //1.securityManager构建环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(realm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("futao", "123456");
        //登录
        subject.login(token);

        System.out.println(subject.isAuthenticated());

    }

}
