package com.card.service;

import com.card.entity.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product findOne(Integer id);

    List<Product> findByCategoryId(Integer categoryId);
}
