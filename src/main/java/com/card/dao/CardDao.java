package com.card.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.card.command.export.CardExport;
import com.card.entity.domain.Card;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CardDao extends BaseMapper<Card> {
    void cardDeleteByIds(@Param("ids") List<Long> ids);

    void cardUpdateById(@Param("id") Long id, @Param("card") Card card);

    void cardInsert(@Param("card") Card card);

    List<CardExport> cardExportFindByStateAndTime(@Param("state") Integer state, @Param("startTime") Long startTime, @Param("endTime") Long endTime);
}
