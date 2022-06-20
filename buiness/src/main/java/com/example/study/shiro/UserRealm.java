package com.example.study.shiro;

import com.example.study.entiy.User;
import com.example.study.service.UserService;
import com.example.study.util.Md5Utils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRealm extends AuthorizingRealm {

    //授权
    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("admin");
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String userName = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null)
        {
            password = new String(upToken.getPassword());
        }else {
            throw new RuntimeException("用户密码为空");
        }
        User user=null;
        try {
           user  = userService.loginByUserName(userName);
        }catch (Exception e){
            throw new AuthenticationException(e.getMessage());
        }
        String pwd = user.getPwd();
        String oldPwd = Md5Utils.md5(password, user.getSalt());
        if(!pwd.equals(oldPwd)){
            try {
                throw new PasswordException("密码错误");
            } catch (PasswordException e) {
                e.printStackTrace();
            }
        }

        return new SimpleAuthenticationInfo(user,password,getName());
    }
}
