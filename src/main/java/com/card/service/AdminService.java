package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.card.command.category.CategoryFindCommand;
import com.card.command.category.CategoryIdsCommand;
import com.card.entity.domain.Admin;
import com.card.entity.domain.Category;
import com.card.entity.domain.Product;

import java.util.List;

public interface AdminService {
    Admin findByUsernameAndPassword(String username, String password);

    Integer countByUsername(String username);

    Admin findAdminById(Long id);

    Admin findByUsername(String username);

    IPage<Category> categoryFindByPage(Integer pageNum, Integer pageSize, CategoryFindCommand command);

    void categoryDeleteByIds(CategoryIdsCommand command);

    void categoryUpdateById(Category command);

    void categoryInsert(Category command);

    void productInsert(Product command);
}