package com.testSystem.shiro.realm;

import com.testSystem.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class CustomAuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    /*
        权限接口，预留接口
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    /*
     身份认证验证密码是否正确，登录的时候会走这部分代码
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String userName =  usernamePasswordToken.getUsername();
        if(StringUtils.isEmpty(userName))
        {
            return null;
        }
        //查询密码
        String password = userService.getPasswordByUserName(userName);
        if (!StringUtils.isEmpty(password))
        {
            return new SimpleAuthenticationInfo(userName, password,
                    ByteSource.Util.bytes(userName), getName());
        }
        return null;
    }
}
