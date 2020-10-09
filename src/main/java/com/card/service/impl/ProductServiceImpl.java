package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.card.command.IdsCommand;
import com.card.command.product.ProductFindCommand;
import com.card.dao.CardDao;
import com.card.dao.ProductDao;
import com.card.entity.domain.Product;
import com.card.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
        productDao.productInsert(product);
    }

    @Override
    public void updateById(Long id, Product product) {
        productDao.productUpdateById(id, product);
    }

    @Override
    public void deleteByIds(IdsCommand command) {
        productDao.productDeleteByIds(command.getIds());
    }

    @Override
    public IPage<Product> selectByPage(Integer pageNum, Integer pageSize, ProductFindCommand command) {
        Page<Product> productPage = new Page<>(pageNum, pageSize);
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(command.getName())) {
            wrapper.like("name", command.getName());
        }
        if (null != command.getState()) {
            wrapper.eq("state", command.getState());
        }
        if (null != command.getCategoryId()) {
            wrapper.eq("category_id", command.getCategoryId());
        }
        if (null != command.getStartTime() && null != command.getEndTime()) {
            wrapper.between("create_time", command.getStartTime(), command.getEndTime());
        }
        wrapper.orderByDesc("create_time");
        return productDao.selectPage(productPage, wrapper);
    }

    @Override
    public List<Product> selectAll() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name").orderBy(false, true, "state");
        return productDao.selectList(queryWrapper);
    }
}