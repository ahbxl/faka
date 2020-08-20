package com.card.controller;

import com.card.command.IdsCommand;
import com.card.command.product.ProductFindCommand;
import com.card.entity.domain.Product;
import com.card.entity.vo.ResultVO;
import com.card.service.ProductService;
import com.card.service.UserService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    /**
     * 查看指定产品的信息
     *
     * @param id 主键
     * @return
     */
    @PostMapping("/findOne/{id}")
    public ResultVO<Object> findOne(@PathVariable("id") Integer id) {
        return ResultVOUtil.success(productService.findOne(id));
    }

    /**
     * 查看指定分类的产品
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

    @PostMapping("/product/findByPage/{pageNum}/{pageSize}")
    public ResultVO<Object> productFindByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody ProductFindCommand command) {
        return ResultVOUtil.success(userService.productFindByPage(pageNum, pageSize, command));
    }

    @PostMapping("/product/deleteByIds")
    public ResultVO<Object> productDeleteByIds(@RequestBody IdsCommand command) {
        command.validate();
        userService.productDeleteByIds(command);
        return ResultVOUtil.success();
    }

    @PostMapping("/product/updateById/{id}")
    public ResultVO<Object> categoryUpdateById(@PathVariable("id") Long id, @RequestBody Product product) {
        userService.productUpdateById(id, product);
        return ResultVOUtil.success();
    }

    @PostMapping("/product/insert")
    public ResultVO<Object> productInsert(@RequestBody Product product) {
        product.validate();
        userService.productInsert(product.doBuild());
        return ResultVOUtil.success();
    }
}