package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.card.dao.MenuListDao;
import com.card.entity.MenuList;
import com.card.entity.vo.MenuListVO;
import com.card.service.MenuListService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MenuListServiceImpl implements MenuListService {
    @Autowired
    private MenuListDao menuListDao;

    @Override
    public IPage<MenuList> selectPage(MenuListVO menuListVO) {
        Page<MenuList> menuListPage = new Page<>(menuListVO.getPageNum(), menuListVO.getPageSize());
        QueryWrapper<MenuList> queryWrapper = new QueryWrapper<>();
        if (null != menuListVO.getRoleId()) {
            queryWrapper.eq("role_id", menuListVO.getRoleId());
        }
        if (null != menuListVO.getParentId()) {
            queryWrapper.eq("parent_id", menuListVO.getParentId());
        }
        if (StringUtils.isNotBlank(menuListVO.getName())) {
            queryWrapper.like("name", menuListVO.getName());
        }
        if (null != menuListVO.getState()) {
            queryWrapper.eq("state", menuListVO.getState());
        }
        if (null != menuListVO.getStartTime() && null != menuListVO.getEndTime()) {
            queryWrapper.between("create_time", menuListVO.getStartTime(), menuListVO.getEndTime());
        }
        queryWrapper.orderByDesc("create_time");
        return menuListDao.selectPage(menuListPage, queryWrapper);
    }
}