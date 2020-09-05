package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.card.command.category.CategoryFindCommand;
import com.card.dao.CategoryDao;
import com.card.entity.domain.Category;
import com.card.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> selectAll() {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name").orderBy(false, true, "state");
        return categoryDao.selectList(queryWrapper);
    }

    @Override
    public Category selectById(Long id) {
        return categoryDao.selectById(id);
    }

    @Override
    public IPage<Category> selectByPage(Integer pageNum, Integer pageSize, CategoryFindCommand command) {
        Page<Category> categoryPage = new Page<>(pageNum, pageSize);
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(command.getName())) {
            wrapper.like("name", command.getName());
        }
        if (null != command.getState()) {
            wrapper.eq("state", command.getState());
        }
        if (null != command.getStartTime() && null != command.getEndTime()) {
            wrapper.between("create_time", command.getStartTime(), command.getEndTime());
        }
        return categoryDao.selectPage(categoryPage, wrapper);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        categoryDao.categoryDeleteByIds(ids);
    }

    @Override
    public void updateById(Long id, Category category) {
        categoryDao.categoryUpdateById(id, category);
    }

    @Override
    public void insert(Category category) {
        categoryDao.categoryInsert(category);
    }

    @Override
    public List<Category> findAllExceptSelf(Long id) {
        return categoryDao.findAllExceptSelf(id);
    }
}