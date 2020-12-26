package com.card.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.dao.RoleDao;
import com.card.entity.Role;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class RoleService extends ServiceImpl<RoleDao, Role> {
    @Autowired
    private RoleDao roleDao;

    public Set<Role> selectRolesByParentId(Set<Role> roles, Long id) {
        List<Role> list = lambdaQuery().eq(Role::getParentId, id).list();
        roles.addAll(list);
        for (Role role : list) {
            Set<Role> roles1 = selectRolesByParentId(roles, role.getId());
            roles.addAll(roles1);
        }
        return roles;
    }

    public List<Role> selectRoles(Long id, Boolean contain) {
        HashSet<Role> roles = new HashSet<>();
        if (contain) {
            roles.add(roleDao.selectById(id));
        }
        selectRolesByParentId(roles, id);
        return Lists.newArrayList(roles);
    }
}