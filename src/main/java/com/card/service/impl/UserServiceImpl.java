package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.card.command.IdsCommand;
import com.card.command.category.CategoryFindCommand;
import com.card.command.product.ProductFindCommand;
import com.card.command.user.UserCommand;
import com.card.dao.CategoryDao;
import com.card.dao.ProductDao;
import com.card.dao.UserDao;
import com.card.entity.domain.Category;
import com.card.entity.domain.Product;
import com.card.entity.domain.User;
import com.card.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public IPage<User> findByPage(Integer pageNum, Integer pageSize, UserCommand userCommand) {
        Page<User> userPage = new Page<>(pageNum, pageSize);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (StringUtils.isEmpty(userCommand.getUsername())) {
            wrapper.like("username", userCommand.getUsername());
        }
        if (StringUtils.isEmpty(userCommand.getEmail())) {
            wrapper.like("email", userCommand.getEmail());
        }
        if (userCommand.getState() != null) {
            wrapper.eq("state", userCommand.getState());
        }
        return userDao.selectPage(userPage, wrapper);
    }

    @Override
    public void updateById(Long id, User user) {
        userDao.updateById(id, user);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, username).eq(User::getPassword, password);
        return userDao.selectOne(queryWrapper);
    }

    @Override
    public Integer countByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, username);
        return userDao.selectCount(queryWrapper);
    }

    @Override
    public User findUserById(Long id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return userDao.selectOne(queryWrapper);
    }

    @Override
    public User findByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, username);
        return userDao.selectOne(queryWrapper);
    }

    @Override
    public IPage<Category> categoryFindByPage(Integer pageNum, Integer pageSize, CategoryFindCommand command) {
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
    public void categoryDeleteByIds(IdsCommand command) {
        categoryDao.categoryDeleteByIds(command.getIds());
    }

    @Override
    public void categoryUpdateById(Long id, Category category) {
        categoryDao.categoryUpdateById(id, category);
    }

    @Override
    public void categoryInsert(Category category) {
        categoryDao.categoryInsert(category);
    }

    @Override
    public void productInsert(Product product) {
        productDao.productInsert(product);
    }

    @Override
    public void productUpdateById(Long id, Product product) {
        productDao.productUpdateById(id, product);
    }

    @Override
    public void productDeleteByIds(IdsCommand command) {
        productDao.productDeleteByIds(command.getIds());
    }

    @Override
    public IPage<Product> productFindByPage(Integer pageNum, Integer pageSize, ProductFindCommand command) {
        Page<Product> productPage = new Page<>(pageNum, pageSize);
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(command.getName())) {
            wrapper.like("name", command.getName());
        }
        if (null != command.getState()) {
            wrapper.eq("state", command.getState());
        }
        if (null != command.getCategoryId()) {
            wrapper.eq("category_id", command.getCategoryId());
        }
        if (null != command.getStartTime() && null != command.getEndTime()) {
            wrapper.between("create_time", command.getStartTime(), command.getEndTime());
        }
        return productDao.selectPage(productPage, wrapper);
    }
}