package com.card.security;

import com.card.entity.User;
import com.card.entity.vo.UserVO;
import com.card.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("=========执行授权=========");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("=========执行认证=========");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        UserVO userVO = new UserVO();
        userVO.setUsername(usernamePasswordToken.getUsername());
        userVO.setPassword(String.valueOf(usernamePasswordToken.getPassword()));
        User user = userService.selectByUsernameAndPassword(userVO);
        if (user == null) {
            return null;
        }
        ((UsernamePasswordToken) authenticationToken).setRememberMe(true);
        return new SimpleAuthenticationInfo(user, String.valueOf(usernamePasswordToken.getPassword()), getName());
    }
}