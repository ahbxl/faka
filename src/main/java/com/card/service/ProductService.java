package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.card.entity.Product;
import com.card.entity.User;
import com.card.entity.vo.ProductVO;

import java.util.List;

public interface ProductService extends IService<Product> {
    Product selectOne(Long id);

    List<Product> selectByCategoryId(Integer categoryId);

    void insert(Product product);

    void deleteBatchIds(List<Long> ids);

    IPage<Product> selectPage(ProductVO productVO);

    Product selectById(Long id);
}