package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.card.entity.Category;
import com.card.entity.vo.CategoryVO;

import java.util.List;

public interface CategoryService {

    Category selectById(Long id);

    IPage<Category> selectPage(CategoryVO categoryVO);

    void deleteBatchIds(List<Long> ids);

    void updateById(Category category);

    void insert(Category category);
}