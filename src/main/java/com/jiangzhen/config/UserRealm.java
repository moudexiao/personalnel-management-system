package com.jiangzhen.config;

import com.jiangzhen.common.utils.JWTUtils;
import com.jiangzhen.dao.RoleDao;
import com.jiangzhen.po.RolePo;
import com.jiangzhen.po.UserPo;
import com.jiangzhen.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: zhaoyiming
 * @date: 2022-02-11 16:13
 */

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleDao roleDao;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权方法");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserPo user = (UserPo) principalCollection.getPrimaryPrincipal();
        RolePo role = roleDao.findById(user.getRoleId());
        authorizationInfo.addRole(role.getName());
        authorizationInfo.addStringPermission(null);
        return authorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证方法");
        //去数据库获取用户,密码
        String token = (String) authenticationToken.getCredentials();
        String username = JWTUtils.getUsername(token);

        if (username == null) {
            throw new AuthenticationException("token异常");
        }
        UserPo userBean = userService.findByUsername(username);

        if (!JWTUtils.verify(token, username, userBean.getPassword())) {
            throw new AuthenticationException("密码错误");
        }
        return new SimpleAuthenticationInfo(userBean, token, getName());
    }
}
