package com.futao.springmvcdemo.foundation.shiro;

import com.futao.springmvcdemo.dao.UserDao;
import com.futao.springmvcdemo.model.entity.User;
import com.futao.springmvcdemo.service.UserService;
import com.futao.springmvcdemo.service.impl.UserServiceImpl;
import com.futao.springmvcdemo.utils.CommonUtilsKt;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;


/**
 * @author futao
 * Created on 2018-12-13.
 */
public class ShiroRealmConfiguration extends AuthorizingRealm {

    @Resource
    private UserService userService;
    @Resource
    private UserDao userDao;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        authorizationInfo.addRoles(userDao.getUserRoleList());
//        authorizationInfo.addObjectPermissions();
        return null;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token.getPrincipal() == null) {
            return null;
        }
        String mobile = (String) token.getPrincipal();
        String password = CommonUtilsKt.md5(new String((char[]) token.getCredentials()) + UserServiceImpl.PWD_SALT);
        User user = userDao.getUserByMobileAndPwd(mobile, password);
        return null != user ? new SimpleAuthenticationInfo(mobile, password, getName()) : null;
    }

//
//    @Bean
//    public ShiroFilterFactoryBean shirFilter(org.apache.shiro.mgt.SecurityManager securityManager) {
//        System.out.println("ShiroConfiguration.shirFilter()");
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//        //注意过滤器配置顺序 不能颠倒
//        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
//        filterChainDefinitionMap.put("/logout", "logout");
//        // 配置不会被拦截的链接 顺序判断
//        filterChainDefinitionMap.put("/static/**", "anon");
//        filterChainDefinitionMap.put("/ajaxLogin", "anon");
//        filterChainDefinitionMap.put("/login", "anon");
//        filterChainDefinitionMap.put("/**", "authc");
//        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
//        shiroFilterFactoryBean.setLoginUrl("/unauth");
//        // 登录成功后要跳转的链接
////        shiroFilterFactoryBean.setSuccessUrl("/index");
//        //未授权界面;
////        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }
}
