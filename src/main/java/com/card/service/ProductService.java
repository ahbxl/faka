package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.card.command.IdsCommand;
import com.card.command.product.ProductFindCommand;
import com.card.entity.domain.Category;
import com.card.entity.domain.Product;

import java.util.List;

public interface ProductService {
    Product selectOne(Long id);

    List<Product> selectByCategoryId(Integer categoryId);

    void insert(Product product);

    void updateById(Long id, Product product);

    void deleteByIds(IdsCommand command);

    IPage<Product> selectByPage(Integer pageNum, Integer pageSize, ProductFindCommand command);

    List<Product> selectAll();
}