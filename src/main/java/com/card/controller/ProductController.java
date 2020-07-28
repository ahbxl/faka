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

    @PostMapping("/findOne/{id}")
    public ResultVO<Object> findOne(@PathVariable("id") Integer id) {
        return ResultVOUtil.success(productService.findOne(id));
    }

    @PostMapping("/findByCategoryId/{categoryId}")
    public ResultVO<Object> findByCategoryId(@PathVariable("categoryId") Integer categoryId) {
        return ResultVOUtil.success(productService.findByCategoryId(categoryId));
    }
}