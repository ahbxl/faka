package com.card.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.card.entity.Card;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface CardDao extends BaseMapper<Card> {
    List<Card> selectByStateAndTime(@Param("state") Integer state, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
