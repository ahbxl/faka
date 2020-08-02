package com.card.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.card.entity.domain.AliPayConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AliPayConfigDao extends BaseMapper<AliPayConfig> {
    void updateById(@Param("id") Long id, @Param("aliPayConfig") AliPayConfig aliPayConfig);
}
