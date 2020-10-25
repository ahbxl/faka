package com.card.controller;

import com.card.entity.Category;
import com.card.entity.vo.CategoryVO;
import com.card.entity.vo.ResultVO;
import com.card.service.CategoryService;
import com.card.service.UserService;
import com.card.util.ResultVOUtil;
import com.card.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    /**
     * 分页查询分类信息
     * 需要管理员权限
     *
     * @param categoryVO
     * @return
     */
    @PostMapping("/token/selectPage")
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
    @PostMapping("/token/deleteBatchIds")
    public ResultVO<Object> deleteBatchIds(@RequestBody CategoryVO categoryVO) {
        List<Long> longs = userService.selectIdsByParentId(SecurityUtil.getCurrentUser().getId());
        ArrayList<Long> list = new ArrayList<>();
        for (Long id : categoryVO.getIds()) {
            Category category = categoryService.selectById(id);
            if (category != null && longs.contains(category.getCreator())) {
                list.add(category.getId());
            }
        }
        categoryService.deleteBatchIds(list);
        log.info("用户{}删除了{}", SecurityUtil.getCurrentUser().getId(), list);
        return ResultVOUtil.success();
    }

    /**
     * 通过id修改分类信息
     * 需要管理员权限
     *
     * @param category
     * @return
     */
    @PostMapping("/token/updateById")
    public ResultVO<Object> updateById(@RequestBody Category category) {
        Category category1 = categoryService.selectById(category.getId());
        if (category1 == null) {
            ResultVOUtil.fail("不存在该卡密信息");
        }
        List<Long> longs = userService.selectIdsByParentId(SecurityUtil.getCurrentUser().getId());
        if (!longs.contains(category.getCreator())) {
            ResultVOUtil.fail("你暂无权限查看");
        }
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
    @PostMapping("/token/insert")
    public ResultVO<Object> insert(@RequestBody Category category) {
        categoryService.insert(category);
        return ResultVOUtil.success();
    }
}