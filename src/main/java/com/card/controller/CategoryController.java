package com.card.controller;

import com.card.command.IdsCommand;
import com.card.command.category.CategoryFindCommand;
import com.card.entity.domain.Category;
import com.card.entity.vo.ResultVO;
import com.card.service.CategoryService;
import com.card.service.UserService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    /**
     * 查看所有的分类信息
     *
     * @return
     */
    @PostMapping("/findAll")
    public ResultVO<Object> findAll() {
        return ResultVOUtil.success(categoryService.findAll());
    }

    @PostMapping("/category/findByPage/{pageNum}/{pageSize}")
    public ResultVO<Object> categoryFindByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody CategoryFindCommand command) {
        return ResultVOUtil.success(userService.categoryFindByPage(pageNum, pageSize, command));
    }

    @PostMapping("/category/deleteByIds")
    public ResultVO<Object> categoryDeleteByIds(@RequestBody IdsCommand command) {
        command.validate();
        userService.categoryDeleteByIds(command);
        return ResultVOUtil.success();
    }

    @PostMapping("/category/updateById/{id}")
    public ResultVO<Object> categoryUpdateById(@PathVariable("id") Long id, @RequestBody Category category) {
        userService.categoryUpdateById(id, category);
        return ResultVOUtil.success();
    }

    @PostMapping("/category/insert")
    public ResultVO<Object> categoryInsert(@RequestBody Category command) {
        command.validate();
        userService.categoryInsert(command.doBuild());
        return ResultVOUtil.success();
    }
}