package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.card.command.category.CategoryCommand;
import com.card.command.category.CategoryIdsCommand;
import com.card.entity.domain.Admin;
import com.card.entity.domain.Category;

public interface AdminService {
    Admin findByUsernameAndPassword(String username, String password);

    Integer countByUsername(String username);

    Admin findAdminById(Long id);

    Admin findByUsername(String username);

    IPage<Category> categoryFindByPage(Integer pageNum, Integer pageSize, CategoryCommand command);

    void categoryDeleteByIds(CategoryIdsCommand command);

    void categoryUpdateById(CategoryCommand command);

    void categoryInsert(CategoryCommand command);
}