package com.card.controller;


import com.card.entity.Product;
import com.card.entity.vo.ProductVO;
import com.card.entity.vo.Result;
import com.card.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 通过id查询产品信息
     *
     * @param productVO
     * @return
     */
    @PostMapping("/selectOne")
    public Result<Object> selectOne(@RequestBody ProductVO productVO) {
        return Result.success(productService.selectOne(productVO.getId()));
    }

    /**
     * 查看指定分类的产品
     *
     * @param productVO
     * @return
     */
    @PostMapping("/selectByCategoryId")
    public Result<Object> selectByCategoryId(@RequestBody ProductVO productVO) {
        return Result.success(productService.selectByCategoryId(productVO.getCategoryId()));
    }

    /**
     * 分页查询产品信息
     * 需要管理员权限
     *
     * @param productVO
     * @return
     */
    @PostMapping("/selectPage")
    public Result<Object> selectPage(@RequestBody ProductVO productVO) {
        return Result.success(productService.selectPage(productVO));
    }

    /**
     * 批量删除产品
     * 需要管理员权限
     *
     * @param productVO
     * @return
     */
    @PostMapping("/deleteBatchIds")
    @RequiresPermissions({"product:delete]"})
    public Result<Object> deleteBatchIds(@RequestBody ProductVO productVO) {
        productService.deleteBatchIds(productVO.getIds());
        return Result.success();
    }

    /**
     * 通过id修改产品信息
     * 需要管理员权限
     *
     * @param product
     * @return
     */
    @PostMapping("/updateById")
    @RequiresPermissions({"product:update"})
    public Result<Object> updateById(@RequestBody Product product) {
        Product productById = productService.selectOne(product.getId());
        if (productById == null) {
            Result.fail("不存在该产品信息");
        }
        productService.updateById(product);
        return Result.success();
    }

    /**
     * 添加产品
     * 需要管理员权限
     *
     * @param product 产品对象
     * @return
     */
    @PostMapping("/insert")
    @RequiresPermissions({"product:add"})
    public Result<Object> insert(@RequestBody Product product) {
        productService.insert(product);
        return Result.success();
    }
}