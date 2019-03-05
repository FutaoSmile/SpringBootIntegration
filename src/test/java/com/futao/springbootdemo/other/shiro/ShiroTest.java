package com.futao.springbootdemo.other.shiro;

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

/**
 * @author futao
 * Created on 2018/11/15.
 * ��ȫ���shiro������
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
        //1.securityManager��������
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(realm);

        //2.�����ύ��֤����
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("futao", "123456");
        //��¼
        subject.login(token);

        System.out.println(subject.isAuthenticated());

    }

}
