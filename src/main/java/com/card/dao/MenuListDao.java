package com.card.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.card.command.menulist.MenuListACommand;
import com.card.command.menulist.MenuListBCommand;
import com.card.entity.domain.MenuList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuListDao extends BaseMapper<MenuList> {
    List<MenuListBCommand> findByParent(@Param("parent") Long parent);
    List<MenuListACommand> findByParentNull();
}