package com.card.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.card.entity.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
}