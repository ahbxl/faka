package com.card.controller;

import com.card.command.IdsCommand;
import com.card.command.product.ProductFindCommand;
import com.card.entity.domain.Product;
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
    @PostMapping("/selectOne/{id}")
    public ResultVO<Object> selectOne(@PathVariable("id") Long id) {
        return ResultVOUtil.success(productService.selectOne(id));
    }

    /**
     * 查看指定分类的产品
     *
     * @param categoryId 分类的id
     * @return
     */
    @PostMapping("/selectByCategoryId/{categoryId}")
    public ResultVO<Object> selectByCategoryId(@PathVariable("categoryId") Integer categoryId) {
        return ResultVOUtil.success(productService.selectByCategoryId(categoryId));
    }

    /**
     * 分页查询产品信息
     * 需要管理员权限
     *
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @param command
     * @return
     */
    @PostMapping("/admin/selectByPage/{pageNum}/{pageSize}")
    public ResultVO<Object> selectByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody ProductFindCommand command) {
        return ResultVOUtil.success(productService.selectByPage(pageNum, pageSize, command));
    }

    /**
     * 批量删除产品
     * 需要管理员权限
     *
     * @param command ids
     * @return
     */
    @PostMapping("/admin/deleteByIds")
    public ResultVO<Object> deleteByIds(@RequestBody IdsCommand command) {
        command.validate();
        productService.deleteByIds(command);
        return ResultVOUtil.success();
    }

    /**
     * 通过id修改产品信息
     * 需要管理员权限
     *
     * @param id      id
     * @param product 产品对象
     * @return
     */
    @PostMapping("/admin/updateById/{id}")
    public ResultVO<Object> updateById(@PathVariable("id") Long id, @RequestBody Product product) {
        Product productById = productService.selectOne(id);
        if (productById == null) {
            ResultVOUtil.fail("不存在该产品信息");
        }
        productService.updateById(id, product);
        return ResultVOUtil.success();
    }

    /**
     * 添加产品
     * 需要管理员权限
     *
     * @param product 产品对象
     * @return
     */
    @PostMapping("/admin/insert")
    public ResultVO<Object> insert(@RequestBody Product product) {
        product.validate();
        productService.insert(product);
        return ResultVOUtil.success();
    }
}