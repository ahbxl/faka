package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.dao.UserDao;
import com.card.entity.User;
import com.card.entity.vo.UserVO;
import com.card.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public IPage<User> selectPage(UserVO userVO) {
        Page<User> userPage = new Page<>(userVO.getPageNum(), userVO.getPageSize());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userVO.getUsername())) {
            wrapper.like("username", userVO.getUsername());
        }
        if (StringUtils.isNotBlank(userVO.getEmail())) {
            wrapper.like("email", userVO.getEmail());
        }
        if (StringUtils.isNotBlank(userVO.getQq())) {
            wrapper.like("qq", userVO.getQq());
        }
        if (StringUtils.isNotBlank(userVO.getPhone())) {
            wrapper.like("phone", userVO.getPhone());
        }
        if (StringUtils.isNotBlank(userVO.getEmail())) {
            wrapper.like("email", userVO.getEmail());
        }
        if (null != userVO.getState()) {
            wrapper.eq("state", userVO.getState());
        }
        if (null != userVO.getRoleId()) {
            wrapper.eq("role_id", userVO.getRoleId());
        }
        if (null != userVO.getParentId()) {
            wrapper.eq("parent_id", userVO.getParentId());
        }
        if (null != userVO.getStartTime() && null != userVO.getEndTime()) {
            wrapper.between("create_time", userVO.getStartTime(), userVO.getEndTime());
        }
        wrapper.orderByDesc("create_time");
        return userDao.selectPage(userPage, wrapper);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public User selectByUsernameAndPassword(UserVO userVO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, userVO.getUsername()).eq(User::getPassword, userVO.getPassword());
        return userDao.selectOne(queryWrapper);
    }

    @Override
    public Integer countByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userDao.selectCount(queryWrapper);
    }

    @Override
    public User selectById(Long id) {
        return userDao.selectById(id);
    }

    @Override
    public void deleteBatchIds(List<Long> ids) {
        userDao.deleteBatchIds(ids);
    }
}