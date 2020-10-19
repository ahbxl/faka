package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.card.entity.Product;
import com.card.entity.vo.ProductVO;

import java.util.List;

public interface ProductService {
    Product selectOne(Long id);

    List<Product> selectByCategoryId(Integer categoryId);

    void insert(Product product);

    void updateById(Product product);

    void deleteBatchIds(List<Long> ids);

    IPage<Product> selectPage(ProductVO productVO);
}