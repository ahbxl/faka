package com.card.security;

import com.card.entity.*;
import com.card.entity.constant.SystemConstant;
import com.card.security.utils.SecurityUtil;
import com.card.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
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
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuListService menuListService;
    @Autowired
    private RoleMenuListService roleMenuListService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 执行授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 设置角色
        List<Role> roles = roleService.selectRoles(SecurityUtil.getCurrentUser().getId(), true);
        authorizationInfo.addRoles(roles.stream().map(Role::getName).collect(Collectors.toList()));
        List<RolePermission> rolePermissions = rolePermissionService.lambdaQuery().eq(RolePermission::getRoleId, SecurityUtil.getCurrentUser().getRoleId()).list();
        Set<Permission> set = new HashSet<>();
        for (RolePermission rolePermission : rolePermissions) {
            List<Permission> permissions = permissionService.lambdaQuery().eq(Permission::getId, rolePermission.getPermissionId()).list();
            set.addAll(permissions);
        }
        // 设置权限
        authorizationInfo.addStringPermissions(set.stream().map(Permission::getName).collect(Collectors.toList()));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        // 执行认证
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        User user = userService.selectByUsername(usernamePasswordToken.getUsername());
        // 查询不到用户或者用户状态为禁用
        if (user == null || user.getState() == 0) {
            return null;
        }

        // 设置用户的权限
        List<RoleMenuList> roleMenuLists = roleMenuListService.lambdaQuery().in(RoleMenuList::getRoleId, user.getRoleId()).list();
        List<Long> collect = roleMenuLists.stream().map(RoleMenuList::getMenuListId).collect(Collectors.toList());
        List<MenuList> menuLists = menuListService.lambdaQuery().in(MenuList::getId, collect).list();
        // 认证成功之后设置角色关联的菜单
        user.setMenuLists(menuLists);

        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(SystemConstant.slat), getName());
    }
}