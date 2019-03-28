package com.futao.springbootdemo.foundation.shiro;

import com.futao.springbootdemo.dao.UserDao;
import com.futao.springbootdemo.model.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * 自定义shiroRealm
 *
 * @author futao
 * Created on 2018-12-13.
 */
@Component
public class CustomShiroRealm extends AuthorizingRealm {

    @Resource
    private UserDao userDao;

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
        //用户名
        String mobile = (String) token.getPrincipal();
        //密码
        String password = new String((char[]) token.getCredentials());
        //密码加密
//        password = CommonUtilsKt.md5(password + UserServiceImpl.PWD_SALT);
        //查询DB
        User user = userDao.getUserByMobileAndPwd(mobile, password);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(mobile, password, getName());
        //加盐
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("nobug666"));
        return null != user ? authenticationInfo : null;
    }

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
//        user.getRoles().forEach(role ->
//                {
//                    authorizationInfo.addRole(role.getRoleName());
//                    role.getPermissions().forEach(permission -> authorizationInfo.addStringPermission(permission.getPermissionName()));
//                }
//        );
        return authorizationInfo;
    }

}
