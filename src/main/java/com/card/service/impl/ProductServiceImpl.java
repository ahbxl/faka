package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.card.dao.CardDao;
import com.card.dao.ProductDao;
import com.card.entity.domain.Card;
import com.card.entity.domain.Product;
import com.card.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private CardDao cardDao;

    @Override
    public Product findOne(Integer id) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        wrapper.select("id", "name", "price");
        return productDao.selectOne(wrapper);
    }

    @Override
    public List<Product> findByCategoryId(Integer categoryId) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id", categoryId);
        wrapper.select("id", "name").orderBy(false,true,"state");
        return productDao.selectList(wrapper);
    }

    @Override
    public Integer countCardByProductId(Long productId) {
        QueryWrapper<Card> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Card::getProductId, productId);
        return cardDao.selectCount(queryWrapper);
    }
}
