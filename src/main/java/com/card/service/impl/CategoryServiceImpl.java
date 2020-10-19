package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.card.dao.CategoryDao;
import com.card.entity.Category;
import com.card.entity.vo.CategoryVO;
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
    public Category selectById(Long id) {
        return categoryDao.selectById(id);
    }

    @Override
    public IPage<Category> selectPage(CategoryVO categoryVO) {
        Page<Category> categoryPage = new Page<>(categoryVO.getPageNum(), categoryVO.getPageSize());
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(categoryVO.getName())) {
            wrapper.like("name", categoryVO.getName());
        }
        if (null != categoryVO.getState()) {
            wrapper.eq("state", categoryVO.getState());
        }
        if (null != categoryVO.getParentId()) {
            wrapper.eq("parent_id", categoryVO.getParentId());
        }
        if (null != categoryVO.getStartTime() && null != categoryVO.getEndTime()) {
            wrapper.between("create_time", categoryVO.getStartTime(), categoryVO.getEndTime());
        }
        wrapper.orderByDesc("create_time");
        return categoryDao.selectPage(categoryPage, wrapper);
    }

    @Override
    public void deleteBatchIds(List<Long> ids) {
        categoryDao.deleteBatchIds(ids);
    }

    @Override
    public void updateById(Category category) {
        categoryDao.updateById(category);
    }

    @Override
    public void insert(Category category) {
        categoryDao.insert(category);
    }
}