package com.card.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.card.entity.RolePermission;
import com.card.entity.vo.Result;
import com.card.entity.vo.RolePermissionVO;
import com.card.service.RolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/rolePermission")
public class RolePermissionController {
    @Autowired
    private RolePermissionService rolePermissionService;

    @PostMapping("/saveOrUpdate")
    public Result<Object> saveOrUpdate(@RequestBody RolePermission rolePermission) {
        rolePermission.setId(null);
        List<RolePermission> rolePermissions = rolePermissionService.lambdaQuery().eq(RolePermission::getRoleId, rolePermission.getRoleId())
                .eq(RolePermission::getPermissionId, rolePermission.getPermissionId()).list();
        if (CollectionUtil.isNotEmpty(rolePermissions)) {
            return Result.fail("授权已存在");
        }
        rolePermissionService.saveOrUpdate(rolePermission);
        return Result.success();
    }

    @PostMapping("/removeByIds")
    public Result<Object> removeByIds(@RequestBody RolePermissionVO rolePermissionVO) {
        rolePermissionService.removeByIds(rolePermissionVO.getIds());
        return Result.success();
    }
}
