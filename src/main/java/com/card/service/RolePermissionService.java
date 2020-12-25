package com.card.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.dao.RolePermissionDao;
import com.card.entity.RolePermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RolePermissionService extends ServiceImpl<RolePermissionDao, RolePermission>  {
}
