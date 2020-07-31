package com.card.controller;

import com.card.command.IdsCommand;
import com.card.command.category.CategoryFindCommand;
import com.card.command.product.ProductFindCommand;
import com.card.entity.domain.Category;
import com.card.entity.domain.Product;
import com.card.entity.vo.ResultVO;
import com.card.service.AdminService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/category/findByPage/{pageNum}/{pageSize}")
    public ResultVO<Object> categoryFindByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody CategoryFindCommand command) {
        return ResultVOUtil.success(adminService.categoryFindByPage(pageNum, pageSize, command));
    }

    @PostMapping("/category/deleteByIds")
    public ResultVO<Object> categoryDeleteByIds(@RequestBody IdsCommand command) {
        command.validate();
        adminService.categoryDeleteByIds(command);
        return ResultVOUtil.success();
    }

    @PostMapping("/category/updateById/{id}")
    public ResultVO<Object> categoryUpdateById(@PathVariable("id") Long id, @RequestBody Category category) {
        adminService.categoryUpdateById(id, category);
        return ResultVOUtil.success();
    }

    @PostMapping("/category/insert")
    public ResultVO<Object> categoryInsert(@RequestBody Category command) {
        command.validate();
        adminService.categoryInsert(command.doBuild());
        return ResultVOUtil.success();
    }

    @PostMapping("/product/findByPage/{pageNum}/{pageSize}")
    public ResultVO<Object> productFindByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody ProductFindCommand command) {
        return ResultVOUtil.success(adminService.productFindByPage(pageNum, pageSize, command));
    }

    @PostMapping("/product/deleteByIds")
    public ResultVO<Object> productDeleteByIds(@RequestBody IdsCommand command) {
        command.validate();
        adminService.productDeleteByIds(command);
        return ResultVOUtil.success();
    }

    @PostMapping("/product/updateById/{id}")
    public ResultVO<Object> categoryUpdateById(@PathVariable("id") Long id, @RequestBody Product product) {
        adminService.productUpdateById(id, product);
        return ResultVOUtil.success();
    }

    @PostMapping("/product/insert")
    public ResultVO<Object> productInsert(@RequestBody Product product) {
        product.validate();
        adminService.productInsert(product.doBuild());
        return ResultVOUtil.success();
    }
}