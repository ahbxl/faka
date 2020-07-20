package com.card.service;

import com.card.entity.domain.Product;

import java.util.List;

public interface ProductService {
    Product findOne(Integer id);

    List<Product> findByCategoryId(Integer categoryId);
}
