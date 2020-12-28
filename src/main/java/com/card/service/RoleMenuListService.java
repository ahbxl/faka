package com.card.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.dao.ProductDao;
import com.card.dao.RoleMenuListDao;
import com.card.entity.Product;
import com.card.entity.RoleMenuList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleMenuListService  extends ServiceImpl<RoleMenuListDao, RoleMenuList> {
}
