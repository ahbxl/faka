package com.card.controller;

import com.card.entity.Category;
import com.card.entity.vo.ResultVO;
import com.card.service.CategoryService;
import com.card.util.ResultVOUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
        return ResultVOUtil.success(categoryService.selectAll());
    }

    @PostMapping("/findAllExceptSelf")
    public ResultVO<Object> findAllExceptSelf(@RequestBody Category category) {
        return ResultVOUtil.success(categoryService.findAllExceptSelf(category.getId()));
    }

    /**
     * 分页查询分类信息
     * 需要管理员权限
     *
     * @param pageNum
     * @param pageSize
     * @param command
     * @return
     */
    @PostMapping("/admin/findByPage/{pageNum}/{pageSize}")
    public ResultVO<Object> categoryFindByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody CategoryFindCommand command) {
        return ResultVOUtil.success(categoryService.selectByPage(pageNum, pageSize, command));
    }

    /**
     * 批量删除分类
     * 需要管理员权限
     *
     * @param command id的集合
     * @return
     */
    @PostMapping("/admin/deleteByIds")
    public ResultVO<Object> categoryDeleteByIds(@RequestBody IdsCommand command) {
        ArrayList<Long> ids = Lists.newArrayList();
        for (Long id : command.getIds()) {
            Category category = categoryService.selectById(id);
            if (category == null) {
                log.info("不存在该分类:" + id);
            } else {
                ids.add(category.getId());
            }
        }
        categoryService.deleteByIds(ids);
        return ResultVOUtil.success();
    }

    /**
     * 通过id修改分类信息
     * 需要管理员权限
     *
     * @param id
     * @param category
     * @return
     */
    @PostMapping("/admin/updateById/{id}")
    public ResultVO<Object> categoryUpdateById(@PathVariable("id") Long id, @RequestBody Category category) {
        if (null != category.getParent())
            if (null == categoryService.selectById(category.getParent())) return ResultVOUtil.fail("不存在该父级编号");
        categoryService.updateById(id, category);
        return ResultVOUtil.success();
    }

    /**
     * 添加分类信息
     * 需要管理员权限
     *
     * @param category
     * @return
     */
    @PostMapping("/admin/insert")
    public ResultVO<Object> categoryInsert(@RequestBody Category category) {
        category.validate();
        categoryService.insert(category.doBuild());
        return ResultVOUtil.success();
    }

    /**
     * 通过id查询分类信息
     *
     * @param id
     * @return
     */
    @PostMapping("/admin/selectById/{id}")
    public ResultVO<Object> selectById(@PathVariable("id") Long id) {
        return ResultVOUtil.success(categoryService.selectById(id));
    }
}