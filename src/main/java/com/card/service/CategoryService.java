package com.card.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.dao.CategoryDao;
import com.card.entity.Category;
import com.card.entity.vo.CategoryVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService extends ServiceImpl<CategoryDao, Category>  {
    @Autowired
    private CategoryDao categoryDao;

    public Category selectById(Long id) {
        return categoryDao.selectById(id);
    }

    public IPage<Category> selectPage(CategoryVO categoryVO) {
        Page<Category> categoryPage = new Page<>(categoryVO.getPageNum(), categoryVO.getPageSize());
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.like(!StringUtils.isBlank(categoryVO.getName()), "name", categoryVO.getName());
        wrapper.eq(null != categoryVO.getState(), "state", categoryVO.getState());
        wrapper.eq(null != categoryVO.getParentId(), "parent_id", categoryVO.getParentId());
        wrapper.between(null != categoryVO.getStartTime() && null != categoryVO.getEndTime(), "create_time", categoryVO.getStartTime(), categoryVO.getEndTime());
        wrapper.orderByDesc("create_time");
        return categoryDao.selectPage(categoryPage, wrapper);
    }

    public void deleteBatchIds(List<Long> ids) {
        categoryDao.deleteBatchIds(ids);
    }

    public void insert(Category category) {
        categoryDao.insert(category);
    }
}