package com.card.security;

import com.card.entity.Permission;
import com.card.entity.RolePermission;
import com.card.entity.User;
import com.card.entity.vo.UserVO;
import com.card.service.PermissionService;
import com.card.service.RolePermissionService;
import com.card.service.UserService;
import com.card.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("=========执行授权=========");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<RolePermission> rolePermissions = rolePermissionService.lambdaQuery().eq(RolePermission::getRoleId, SecurityUtil.getCurrentUser().getRoleId()).list();
        Set<Permission> set = new HashSet<>();
        for (RolePermission rolePermission : rolePermissions) {
            List<Permission> permissions = permissionService.lambdaQuery().eq(Permission::getId, rolePermission.getPermissionId()).list();
            set.addAll(permissions);
        }
        authorizationInfo.addStringPermissions(set.stream().map(Permission::getName).collect(Collectors.toList()));
//        authorizationInfo.addRole();
        return authorizationInfo;
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