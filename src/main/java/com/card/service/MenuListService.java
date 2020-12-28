package com.card.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.dao.MenuListDao;
import com.card.entity.MenuList;
import com.card.entity.vo.MenuListVO;
import com.card.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MenuListService extends ServiceImpl<MenuListDao, MenuList> {
    @Autowired
    private MenuListDao menuListDao;
    @Autowired
    private RoleService roleService;

    public IPage<MenuList> selectPage(MenuListVO menuListVO) {
        IPage<MenuList> menuListIPage = lambdaQuery().in(MenuList::getRoleId, roleService.selectRoles(SecurityUtil.getCurrentUser().getRoleId(), true))
                .like(StringUtils.isNotBlank(menuListVO.getName()), MenuList::getName, menuListVO.getName())
                .eq(null != menuListVO.getState(), MenuList::getState, menuListVO.getState())
                .between(null != menuListVO.getStartTime() && null != menuListVO.getEndTime(), MenuList::getCreateTime, menuListVO.getStartTime(), menuListVO.getEndTime())
                .orderByAsc(MenuList::getParentId).last(",create_time desc")
                .page(new Page<>(menuListVO.getPageNum(), menuListVO.getPageSize()));
        return menuListIPage;
    }

    /**
     * 根据角色返回角色树
     *
     * @param roleId
     * @return
     */
    public List<MenuList> selectList(Long roleId) {
        List<MenuList> menuLists = lambdaQuery().eq(MenuList::getParentId, null)
                .in(roleId != null, MenuList::getRoleId, roleService.selectRoles(roleId, true))
                .list();
        for (MenuList menuList : menuLists) {
            selectByParentId(menuList.getId());
        }
        return menuLists;
    }

    /**
     * 查询下级菜单,不包括自己
     *
     * @param parentId
     * @return
     */
    public List<MenuList> selectByParentId(Long parentId) {
        List<MenuList> list = lambdaQuery().eq(MenuList::getParentId, parentId).list();
        if (CollectionUtil.isEmpty(list)) return null;
        for (MenuList menuList : list) {
            List<MenuList> menuLists = selectByParentId(menuList.getParentId());
            menuList.setMenuListList(menuLists);
        }
        return list;
    }
}