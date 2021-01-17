package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.dao.MenuListDao;
import com.card.dao.ProductDao;
import com.card.dao.RoleDao;
import com.card.dao.RoleMenuListDao;
import com.card.entity.MenuList;
import com.card.entity.Product;
import com.card.entity.RoleMenuList;
import com.card.entity.vo.RoleMenuListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoleMenuListService extends ServiceImpl<RoleMenuListDao, RoleMenuList> {
    @Autowired
    private MenuListDao menuListDao;
    @Autowired
    private RoleDao roleDao;

    public IPage<RoleMenuList> selectPage(RoleMenuListVO roleMenuListVO) {
        IPage<RoleMenuList> roleMenuListIPage = lambdaQuery().page(new Page<>(roleMenuListVO.getPageNum(), roleMenuListVO.getPageSize()));
        roleMenuListIPage.getRecords().forEach(rm -> {
            rm.setMenuList(menuListDao.selectById(rm.getMenuListId()));
            rm.setRole(roleDao.selectById(rm.getRoleId()));
        });
        return roleMenuListIPage;
    }
}
