package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.card.command.IdsCommand;
import com.card.command.product.ProductFindCommand;
import com.card.entity.domain.Product;

import java.util.List;

public interface ProductService {
    Product findOne(Integer id);

    List<Product> findByCategoryId(Integer categoryId);

    Integer countCardByProductId(Long id);

    void productInsert(Product product);

    void productUpdateById(Long id, Product product);

    void productDeleteByIds(IdsCommand command);

    IPage<Product> productFindByPage(Integer pageNum, Integer pageSize, ProductFindCommand command);
}