package com.card.controller;


import com.card.entity.Product;
import com.card.entity.vo.ProductVO;
import com.card.entity.vo.Result;
import com.card.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 通过id查询产品信息
     *
     * @param id 主键
     * @return
     */
    @PostMapping("/token/selectOne/{id}")
    public Result<Object> selectOne(@PathVariable("id") Long id) {
        return Result.success(productService.selectOne(id));
    }

    /**
     * 查看指定分类的产品
     *
     * @param categoryId 分类的id
     * @return
     */
    @PostMapping("/token/selectByCategoryId/{categoryId}")
    public Result<Object> selectByCategoryId(@PathVariable("categoryId") Integer categoryId) {
        return Result.success(productService.selectByCategoryId(categoryId));
    }

    /**
     * 分页查询产品信息
     * 需要管理员权限
     *
     * @param productVO
     * @return
     */
    @PostMapping("/token/selectPage")
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
    @PostMapping("/token/deleteBatchIds")
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
    @PostMapping("/token/updateById")
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
    @PostMapping("/token/insert")
    public Result<Object> insert(@RequestBody Product product) {
        productService.insert(product);
        return Result.success();
    }
}