package com.card.security;

import com.card.entity.AuthorizedUser;
import com.card.entity.domain.Admin;
import com.card.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private AdminService adminService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("=========执行授权=========");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("=========执行认证=========");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        Admin admin = adminService.findByUsernameAndPassword(usernamePasswordToken.getUsername(), String.valueOf(usernamePasswordToken.getPassword()));
        if (admin == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(findByPrincipal(usernamePasswordToken), String.valueOf(usernamePasswordToken.getPassword()), getName());
    }

    public AuthorizedUser findByPrincipal(UsernamePasswordToken usernamePasswordToken) {
        Admin admin = adminService.findByUsername(usernamePasswordToken.getUsername());
        return new AuthorizedUser(admin.getId(), admin.getUsername(), null);
    }
}