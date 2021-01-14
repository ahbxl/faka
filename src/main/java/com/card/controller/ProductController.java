package com.card.controller;


import cn.hutool.core.util.StrUtil;
import com.card.entity.Category;
import com.card.entity.Product;
import com.card.entity.vo.ProductVO;
import com.card.entity.vo.Result;
import com.card.service.CategoryService;
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
    @Autowired
    private CategoryService categoryService;

    /**
     * 通过id查询产品信息
     *
     * @param productVO
     * @return
     */
    @PostMapping("/getById")
    public Result<Object> getById(@RequestBody ProductVO productVO) {
        Product product = productService.getById(productVO.getId());
        Category category = categoryService.getById(product.getCategoryId());
        product.setCategory(category);
        return Result.success(product);
    }

    /**
     * 查看指定分类的产品
     *
     * @param productVO
     * @return
     */
    @PostMapping("/selectByCategoryId")
    public Result<Object> selectByCategoryId(@RequestBody ProductVO productVO) {
        return Result.success(productService.selectByCategoryId(productVO));
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
     * 删除产品
     * 需要管理员权限
     *
     * @param productVO
     * @return
     */
    @PostMapping("/removeById")
    @RequiresPermissions({"product:delete]"})
    public Result<Object> removeById(@RequestBody ProductVO productVO) {
        productService.removeById(productVO.getId());
        return Result.success();
    }

    /**
     * 通过id修改产品信息
     * 需要管理员权限
     *
     * @param product
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @RequiresPermissions({"product:update"})
    public Result<Object> saveOrUpdate(@RequestBody Product product) {
        Integer count = productService.lambdaQuery().eq(StrUtil.isNotBlank(product.getName()), Product::getName, product.getName())
                .ne(product.getId() != null, Product::getId, product.getId()).count();
        if (count > 0) return Result.fail("该商品名称已存在");
        productService.saveOrUpdate(product);
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
        productService.saveOrUpdate(product);
        return Result.success();
    }
}