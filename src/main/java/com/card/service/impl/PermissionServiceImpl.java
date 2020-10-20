package com.card.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.dao.CardDao;
import com.card.dao.PermissionDao;
import com.card.entity.Card;
import com.card.entity.Permission;
import com.card.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission> implements PermissionService {
}
