package com.card.controller;


import com.card.entity.Product;
import com.card.entity.vo.ProductVO;
import com.card.entity.vo.ResultVO;
import com.card.service.ProductService;
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

    /**
     * 通过id查询产品信息
     *
     * @param id 主键
     * @return
     */
    @PostMapping("/token/selectOne/{id}")
    public ResultVO<Object> selectOne(@PathVariable("id") Long id) {
        return ResultVOUtil.success(productService.selectOne(id));
    }

    /**
     * 查看指定分类的产品
     *
     * @param categoryId 分类的id
     * @return
     */
    @PostMapping("/token/selectByCategoryId/{categoryId}")
    public ResultVO<Object> selectByCategoryId(@PathVariable("categoryId") Integer categoryId) {
        return ResultVOUtil.success(productService.selectByCategoryId(categoryId));
    }

    /**
     * 分页查询产品信息
     * 需要管理员权限
     *
     * @param productVO
     * @return
     */
    @PostMapping("/token/selectPage")
    public ResultVO<Object> selectPage(@RequestBody ProductVO productVO) {
        return ResultVOUtil.success(productService.selectPage(productVO));
    }

    /**
     * 批量删除产品
     * 需要管理员权限
     *
     * @param productVO
     * @return
     */
    @PostMapping("/token/deleteBatchIds")
    public ResultVO<Object> deleteBatchIds(@RequestBody ProductVO productVO) {
        productService.deleteBatchIds(productVO.getIds());
        return ResultVOUtil.success();
    }

    /**
     * 通过id修改产品信息
     * 需要管理员权限
     *
     * @param product
     * @return
     */
    @PostMapping("/token/updateById")
    public ResultVO<Object> updateById(@RequestBody Product product) {
        Product productById = productService.selectOne(product.getId());
        if (productById == null) {
            ResultVOUtil.fail("不存在该产品信息");
        }
        productService.updateById(product);
        return ResultVOUtil.success();
    }

    /**
     * 添加产品
     * 需要管理员权限
     *
     * @param product 产品对象
     * @return
     */
    @PostMapping("/token/insert")
    public ResultVO<Object> insert(@RequestBody Product product) {
        productService.insert(product);
        return ResultVOUtil.success();
    }
}