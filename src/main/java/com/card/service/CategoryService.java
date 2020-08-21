package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.card.command.IdsCommand;
import com.card.command.category.CategoryFindCommand;
import com.card.entity.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category selectById(Long id);

    IPage<Category> categoryFindByPage(Integer pageNum, Integer pageSize, CategoryFindCommand command);

    void categoryDeleteByIds(List<Long> ids);

    void categoryUpdateById(Long id, Category category);

    void categoryInsert(Category category);
}