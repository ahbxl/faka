package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.card.command.IdsCommand;
import com.card.command.category.CategoryFindCommand;
import com.card.command.product.ProductFindCommand;
import com.card.entity.domain.Admin;
import com.card.entity.domain.Category;
import com.card.entity.domain.Product;

public interface AdminService {
    Admin findByUsernameAndPassword(String username, String password);

    Integer countByUsername(String username);

    Admin findAdminById(Long id);

    Admin findByUsername(String username);

    IPage<Category> categoryFindByPage(Integer pageNum, Integer pageSize, CategoryFindCommand command);

    void categoryDeleteByIds(IdsCommand command);

    void categoryUpdateById(Long id, Category category);

    void categoryInsert(Category category);

    void productInsert(Product product);

    void productUpdateById(Long id, Product product);

    void productDeleteByIds(IdsCommand command);

    IPage<Product> productFindByPage(Integer pageNum, Integer pageSize, ProductFindCommand command);
}