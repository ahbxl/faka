package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.card.entity.Category;
import com.card.entity.User;
import com.card.entity.vo.CategoryVO;

import java.util.List;

public interface CategoryService extends IService<Category> {

    Category selectById(Long id);

    IPage<Category> selectPage(CategoryVO categoryVO);

    void deleteBatchIds(List<Long> ids);

    void insert(Category category);
}