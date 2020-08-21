package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.card.command.IdsCommand;
import com.card.command.category.CategoryFindCommand;
import com.card.command.product.ProductFindCommand;
import com.card.command.user.UserCommand;
import com.card.entity.domain.Category;
import com.card.entity.domain.Product;
import com.card.entity.domain.User;

public interface UserService {
    IPage<User> findByPage(Integer pageNum, Integer pageSize, UserCommand userCommand);

    void updateById(Long id, User user);

    void deleteById(Long id);

    void insert(User user);

    User findByUsernameAndPassword(String username, String password);

    Integer countByUsername(String username);

    User findUserById(Long id);

    User findByUsername(String username);
}