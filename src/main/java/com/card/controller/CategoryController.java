package com.card.controller;

import com.card.entity.vo.ResultVO;
import com.card.service.CategoryService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/findAll")
    @ResponseBody
    public ResultVO<Object> findAll() {
        return ResultVOUtil.success(categoryService.findAll());
    }
}
