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
}