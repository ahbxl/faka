package com.card.controller;

import com.card.entity.Category;
import com.card.entity.vo.CategoryVO;
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
     * 分页查询分类信息
     * 需要管理员权限
     *
     * @param categoryVO
     * @return
     */
    @PostMapping("/admin/selectPage")
    public ResultVO<Object> selectPage(@RequestBody CategoryVO categoryVO) {
        return ResultVOUtil.success(categoryService.selectPage(categoryVO));
    }

    /**
     * 批量删除分类
     * 需要管理员权限
     *
     * @param categoryVO
     * @return
     */
    @PostMapping("/admin/deleteBatchIds")
    public ResultVO<Object> deleteBatchIds(@RequestBody CategoryVO categoryVO) {
        categoryService.deleteBatchIds(categoryVO.getIds());
        return ResultVOUtil.success();
    }

    /**
     * 通过id修改分类信息
     * 需要管理员权限
     *
     * @param category
     * @return
     */
    @PostMapping("/admin/updateById")
    public ResultVO<Object> updateById(@RequestBody Category category) {
        categoryService.updateById(category);
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
    public ResultVO<Object> insert(@RequestBody Category category) {
        categoryService.insert(category);
        return ResultVOUtil.success();
    }

    /**
     * 通过id查询分类信息
     *
     * @param categoryVO
     * @return
     */
    @PostMapping("/admin/selectOne")
    public ResultVO<Object> selectOne(@RequestBody CategoryVO categoryVO) {
        return ResultVOUtil.success(categoryService.selectById(categoryVO.getId()));
    }
}