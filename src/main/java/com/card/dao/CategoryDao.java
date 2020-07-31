package com.card.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.card.entity.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryDao extends BaseMapper<Category> {
    void categoryDeleteByIds(@Param("ids") List<Long> ids);

    void categoryUpdateById(@Param("id") Long id, @Param("category") Category category);

    void categoryInsert(@Param("category") Category category);
}