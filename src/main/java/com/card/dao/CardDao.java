package com.card.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.card.command.order.OrderCommand;
import com.card.entity.domain.Card;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CardDao extends BaseMapper<Card> {
    void cardDeleteByIds(@Param("list") List<Long> ids);

    void cardUpdateById(@Param("command") OrderCommand command);

    void cardInsert(@Param("commands") List<OrderCommand> commands);
}
