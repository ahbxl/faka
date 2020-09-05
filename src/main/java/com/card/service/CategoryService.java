package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.card.command.category.CategoryFindCommand;
import com.card.entity.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> selectAll();

    Category selectById(Long id);

    IPage<Category> selectByPage(Integer pageNum, Integer pageSize, CategoryFindCommand command);

    void deleteByIds(List<Long> ids);

    void updateById(Long id, Category category);

    void insert(Category category);

    List<Category> findAllExceptSelf(Long id);
}