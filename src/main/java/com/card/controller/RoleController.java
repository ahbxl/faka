package com.card.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.card.entity.Role;
import com.card.entity.vo.PageVO;
import com.card.entity.vo.Result;
import com.card.entity.vo.RoleVO;
import com.card.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/saveOrUpdate")
    public Result<Object> saveOrUpdate(@RequestBody Role role) {
        List<Role> roles = roleService.lambdaQuery().eq(Role::getName, role.getName())
                .ne(role.getId() != null, Role::getId, role.getId())
                .list();
        if (CollectionUtil.isNotEmpty(roles)) {
            return Result.fail("已存在该角色名称");
        }
        roleService.saveOrUpdate(role);
        return Result.success();
    }

    @PostMapping("/removeByIds")
    public Result<Object> update(@RequestBody PageVO pageVO) {
        roleService.removeByIds(pageVO.getIds());
        return Result.success();
    }

    @PostMapping("/getById")
    public Result<Object> getById(@RequestBody Role role) {
        return Result.success(roleService.getById(role.getId()));
    }

    @PostMapping("/selectPage")
    public Result<Object> selectPage(@RequestBody RoleVO roleVO) {
        IPage<Role> roleIPage = roleService.lambdaQuery()
                .eq(null != roleVO.getParentId(), Role::getParentId, roleVO.getParentId())
                .like(Role::getName, roleVO.getName())
                .page(new Page<>(roleVO.getPageNum(), roleVO.getPageSize()));
        return Result.success(roleIPage);
    }
}