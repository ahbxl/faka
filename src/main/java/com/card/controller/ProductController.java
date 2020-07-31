package com.card.controller;

import com.card.entity.vo.ResultVO;
import com.card.service.ProductService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 查看指定产品的信息
     *
     * @param id 逐渐
     * @return
     */
    @PostMapping("/findOne/{id}")
    public ResultVO<Object> findOne(@PathVariable("id") Integer id) {
        return ResultVOUtil.success(productService.findOne(id));
    }

    /**
     * 查看知道分类的产品
     *
     * @param categoryId 分类的id
     * @return
     */
    @PostMapping("/findByCategoryId/{categoryId}")
    public ResultVO<Object> findByCategoryId(@PathVariable("categoryId") Integer categoryId) {
        return ResultVOUtil.success(productService.findByCategoryId(categoryId));
    }

    /**
     * 计算产品的库存
     *
     * @param productId 产品的id
     * @return
     */
    @PostMapping("/countCardByProductId/{productId}")
    public ResultVO<Object> countCardByProductId(@PathVariable("productId") Long productId) {
        return ResultVOUtil.success(productService.countCardByProductId(productId));
    }
}