package com.card.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.dao.CategoryDao;
import com.card.entity.Category;
import com.card.entity.vo.CategoryVO;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService extends ServiceImpl<CategoryDao, Category> {
    @Autowired
    private CategoryDao categoryDao;

    public Category selectById(Long id) {
        return categoryDao.selectById(id);
    }

    public IPage<Category> selectPage(CategoryVO categoryVO) {
        return lambdaQuery().like(StrUtil.isNotBlank(categoryVO.getName()), Category::getName, categoryVO.getName())
                .eq(null != categoryVO.getState(), Category::getState, categoryVO.getState())
                .eq(null != categoryVO.getParentId(), Category::getParentId, categoryVO.getParentId())
                .between(null != categoryVO.getStartTime() && null != categoryVO.getEndTime(), Category::getCreateTime, categoryVO.getStartTime(), categoryVO.getCreateTime())
                .orderByDesc(Category::getCreateTime)
                .page(new Page<>(categoryVO.getPageNum(), categoryVO.getPageSize()));
    }

    /**
     * 查询子集分类,返回集合结构
     *
     * @param id
     * @return
     */
    public List<Category> selectCategorys(Long id, Boolean bool) {
        List<Category> categories = Lists.newArrayList();
        if (bool) categories.add(lambdaQuery().eq(Category::getId, id).one());
        selectByParentId(id, categories);
        return categories;
    }

    /**
     * 递归查询子集,不包含自己,返回嵌套结构
     *
     * @param parentId
     * @return
     */
    public List<Category> selectByParentId(Long parentId) {
        List<Category> list = lambdaQuery().eq(Category::getParentId, parentId).list();
        list.forEach(category -> category.setCategorys(selectByParentId(category.getId())));
        return list;
    }

    /**
     * 查询子集,不包含自己,返回集合结构
     *
     * @param parentId
     * @param categories
     * @return
     */
    public List<Category> selectByParentId(Long parentId, List<Category> categories) {
        List<Category> list = lambdaQuery().eq(Category::getParentId, parentId).list();
        if (CollectionUtil.isNotEmpty(list)) {
            categories.addAll(list);
            list.forEach(category -> categories.addAll(selectByParentId(category.getId(), categories)));
        }
        return categories;
    }
}