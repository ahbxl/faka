package com.card.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.card.entity.Category;
import com.card.entity.vo.CategoryVO;
import com.card.entity.vo.Result;
import com.card.security.utils.SecurityUtil;
import com.card.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 分页查询分类信息
     * 需要管理员权限
     *
     * @param categoryVO
     * @return
     */
    @PostMapping("/selectPage")
    public Result<Object> selectPage(@RequestBody CategoryVO categoryVO) {
        return Result.success(categoryService.selectPage(categoryVO));
    }

    /**
     * 单个删除分类
     * 需要管理员权限
     *
     * @param categoryVO
     * @return
     */
    @PostMapping("/removeById")
    public Result<Object> removeById(@RequestBody CategoryVO categoryVO) {
        List<Category> categories = categoryService.selectCategorys(categoryVO.getId(), false);
        if (CollectionUtil.isNotEmpty(categories)) return Result.fail("该分类下含有子集,不可以删除");
        boolean remove = categoryService.lambdaUpdate()
                .eq(Category::getCreator, SecurityUtil.getCurrentUser().getId())
                .eq(Category::getId, categoryVO.getId())
                .remove();
        if (remove) log.info("用户{}删除了{}", SecurityUtil.getCurrentUser().getId(), categoryVO.getId());
        return Result.success();
    }

    /**
     * 修改或者更新分类
     * 需要管理员权限
     *
     * @param category
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public Result<Object> saveOrUpdate(@RequestBody Category category) {
        Integer count = categoryService.lambdaQuery().eq(Category::getName, category.getName())
                .ne(category.getId() != null, Category::getId, category.getId())
                .count();
        if (count > 0) return Result.fail("该分类名称已存在");
        category.setCreator(category.getId() == null ? SecurityUtil.getCurrentUser().getId() : null);
        categoryService.saveOrUpdate(category);
        return Result.success();
    }

    @PostMapping("/select")
    public Result<Object> select(@RequestBody Category category) {
        List<Category> categories = categoryService.lambdaQuery()
                .ne(category.getId() != null, Category::getId, category.getId())
                .eq(Category::getCreator, SecurityUtil.getCurrentUser().getId())
                .list();
        return Result.success(categories);
    }

    @PostMapping("/getById")
    public Result<Object> getById(@RequestBody Category category) {
        Category one = categoryService.lambdaQuery().eq(Category::getCreator, SecurityUtil.getCurrentUser().getId())
                .eq(Category::getId, category.getId()).one();
        return Result.success(one);
    }
}