package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.card.command.category.CategoryFindCommand;
import com.card.command.category.CategoryIdsCommand;
import com.card.dao.AdminDao;
import com.card.dao.CategoryDao;
import com.card.dao.ProductDao;
import com.card.entity.domain.Admin;
import com.card.entity.domain.Category;
import com.card.entity.domain.Product;
import com.card.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public Admin findByUsernameAndPassword(String username, String password) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Admin::getUsername, username).eq(Admin::getPassword, password);
        return adminDao.selectOne(queryWrapper);
    }

    @Override
    public Integer countByUsername(String username) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Admin::getUsername, username);
        return adminDao.selectCount(queryWrapper);
    }

    @Override
    public Admin findAdminById(Long id) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return adminDao.selectOne(queryWrapper);
    }

    @Override
    public Admin findByUsername(String username) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Admin::getUsername, username);
        return adminDao.selectOne(queryWrapper);
    }

    @Override
    public IPage<Category> categoryFindByPage(Integer pageNum, Integer pageSize, CategoryFindCommand command) {
        Page<Category> categoryPage = new Page<>(pageNum, pageSize);
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(command.getName())) {
            wrapper.eq("name", command.getName());
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
    public void categoryDeleteByIds(CategoryIdsCommand command) {
        categoryDao.categoryDeleteByIds(command.getIds());
    }

    @Override
    public void categoryUpdateById(Category command) {
        categoryDao.categoryUpdateById(command);
    }

    @Override
    public void categoryInsert(Category command) {
        categoryDao.categoryInsert(command);
    }

    @Override
    public void productInsert(Product command) {
        productDao.productInsert(command);
    }
}