package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.card.command.user.UserCommand;
import com.card.entity.domain.User;

import java.util.List;

public interface UserService {
    IPage<User> selectByPage(Integer pageNum, Integer pageSize, UserCommand userCommand);

    void updateById(Long id, User user);

    void deleteById(Long id);

    void insert(User user);

    User selectByUsernameAndPassword(String username, String password);

    Integer countByUsername(String username);

    User selectById(Long id);

    User findByUsername(String username);

    void deleteByIds(List<Long> ids);
}