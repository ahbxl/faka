package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.card.entity.User;
import com.card.entity.vo.UserVO;

import java.util.List;

public interface UserService {
    IPage<User> selectPage(UserVO userVO);

    void updateById(User user);

    void deleteById(Long id);

    void insert(User user);

    User selectByUsernameAndPassword(UserVO userVO);

    Integer countByUsername(String username);

    User selectById(Long id);

    void deleteBatchIds(List<Long> ids);
}