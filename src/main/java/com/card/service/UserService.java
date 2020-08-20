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

    IPage<Category> categoryFindByPage(Integer pageNum, Integer pageSize, CategoryFindCommand command);

    void categoryDeleteByIds(IdsCommand command);

    void categoryUpdateById(Long id, Category category);

    void categoryInsert(Category category);

    void productInsert(Product product);

    void productUpdateById(Long id, Product product);

    void productDeleteByIds(IdsCommand command);

    IPage<Product> productFindByPage(Integer pageNum, Integer pageSize, ProductFindCommand command);
}