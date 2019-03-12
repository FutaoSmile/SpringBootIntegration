//package com.futao.springbootdemo.foundation.shiro;
//
//import com.futao.springbootdemo.dao.UserDao;
//import com.futao.springbootdemo.model.entity.User;
//import com.futao.springbootdemo.service.impl.UserServiceImpl;
//import com.futao.springbootdemo.utils.CommonUtilsKt;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//
//import javax.annotation.Resource;
//
//
///**
// * @author futao
// * Created on 2018-12-13.
// */
//public class ShiroRealmConfiguration extends AuthorizingRealm {
//
//    @Resource
//    private UserDao userDao;
//
//    /**
//     * 认证
//     *
//     * @param token
//     * @return
//     * @throws AuthenticationException
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        if (token.getPrincipal() == null) {
//            return null;
//        }
//        //用户名
//        String mobile = (String) token.getPrincipal();
//        //密码
//        String password = new String((char[]) token.getCredentials());
//        //密码加密
////        password = CommonUtilsKt.md5(password + UserServiceImpl.PWD_SALT);
//        //查询DB
//        User user = userDao.getUserByMobileAndPwd(mobile, password);
//        return null != user ? new SimpleAuthenticationInfo(mobile, password, getName()) : null;
//    }
//
//    /**
//     * 授权
//     *
//     * @param principals
//     * @return
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        User user = (User) principals.getPrimaryPrincipal();
//        user.getRoles().forEach(role ->
//                {
//                    authorizationInfo.addRole(role.getRoleName());
//                    role.getPermissions().forEach(permission -> authorizationInfo.addStringPermission(permission.getPermissionName()));
//                }
//        );
//        return authorizationInfo;
//    }
//
//}
