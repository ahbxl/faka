package com.card.controller;

import com.card.command.category.CategoryFindCommand;
import com.card.entity.vo.ResultVO;
import com.card.service.CategoryService;
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

    /**
     * 查看所有的分类信息
     *
     * @return
     */
    @PostMapping("/findAll")
    public ResultVO<Object> findAll() {
        return ResultVOUtil.success(categoryService.findAll());
    }
}