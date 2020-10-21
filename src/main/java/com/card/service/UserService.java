package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.card.entity.User;
import com.card.entity.vo.UserVO;

import java.util.List;

public interface UserService extends IService<User> {
    IPage<User> selectPage(UserVO userVO);

    void insert(User user);

    User selectByUsernameAndPassword(UserVO userVO);

    Integer countByUsername(String username);

    User selectById(Long id);

    void deleteBatchIds(List<Long> ids);

    List<Long> selectIdsByParentId(Long parentId);
}