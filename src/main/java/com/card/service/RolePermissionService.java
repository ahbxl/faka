package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.dao.RolePermissionDao;
import com.card.entity.RolePermission;
import com.card.entity.vo.RolePermissionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RolePermissionService extends ServiceImpl<RolePermissionDao, RolePermission> {
    public IPage<RolePermission> selectPage(RolePermissionVO rolePermissionVO) {
        IPage<RolePermission> rolePermissionIPage = lambdaQuery()
                .page(new Page<>(rolePermissionVO.getPageNum(), rolePermissionVO.getPageSize()));
        return rolePermissionIPage;
    }
}
