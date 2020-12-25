package com.card.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.dao.PermissionDao;
import com.card.entity.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PermissionService extends ServiceImpl<PermissionDao, Permission>  {
}
