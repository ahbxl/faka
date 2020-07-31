package com.card.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.card.entity.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductDao extends BaseMapper<Product> {
    void productInsert(@Param("product") Product product);

    void productDeleteByIds(@Param("ids") List<Long> ids);

    void productUpdateById(@Param("product") Product product);
}