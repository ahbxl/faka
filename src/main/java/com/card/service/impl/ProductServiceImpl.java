package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.dao.CardDao;
import com.card.dao.ProductDao;
import com.card.entity.Card;
import com.card.entity.Product;
import com.card.entity.vo.ProductVO;
import com.card.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Product selectOne(Long id) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        wrapper.select("id", "name", "price", "category_id", "state");
        return productDao.selectOne(wrapper);
    }

    @Override
    public List<Product> selectByCategoryId(Integer categoryId) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id", categoryId);
        wrapper.select("id", "name").orderBy(false, true, "state");
        return productDao.selectList(wrapper);
    }


    @Override
    public void insert(Product product) {
        productDao.insert(product);
    }

    @Override
    public void deleteBatchIds(List<Long> ids) {
        productDao.deleteBatchIds(ids);
    }

    @Override
    public IPage<Product> selectPage(ProductVO productVO) {
        Page<Product> productPage = new Page<>(productVO.getPageNum(), productVO.getPageSize());
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(productVO.getName())) {
            wrapper.like("name", productVO.getName());
        }
        if (null != productVO.getState()) {
            wrapper.eq("state", productVO.getState());
        }
        if (null != productVO.getCategoryId()) {
            wrapper.eq("category_id", productVO.getCategoryId());
        }
        if (null != productVO.getStartTime() && null != productVO.getEndTime()) {
            wrapper.between("create_time", productVO.getStartTime(), productVO.getEndTime());
        }
        wrapper.orderByDesc("create_time");
        return productDao.selectPage(productPage, wrapper);
    }
}