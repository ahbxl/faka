package com.card.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.card.command.category.CategoryCommand;
import com.card.command.category.CategoryIdsCommand;
import com.card.entity.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryDao extends BaseMapper<Category> {
    void categoryDeleteByIds(@Param("list") List<Long> ids);

    void categoryUpdateById(@Param("command") CategoryCommand command);

    void categoryInsert(@Param("ids") List<CategoryCommand> commands);
}