package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.card.dao.CategoryDao;
import com.card.entity.domain.Category;
import com.card.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> findAll() {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name");
        return categoryDao.selectList(queryWrapper);
    }
}
