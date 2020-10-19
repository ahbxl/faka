package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.card.command.IdsCommand;
import com.card.command.card.CardFindCommand;
import com.card.command.exportfile.CardExport;
import com.card.entity.Card;

import java.util.List;

public interface CardService {
    void cardDeleteByIds(List<Long> ids);

    void cardUpdateById(Long id, Card card);

    void cardInsert(Card card);

    List<CardExport> cardExportFindByStateAndTime(Integer state, Long startTime, Long endTime);

    Integer countByProductId(Long id);

    IPage<Card> selectByPage(Integer pageNum, Integer pageSize, CardFindCommand command);

    void updateById(Long id, Card card);

    Card selectOne(Long id);

    void deleteByIds(IdsCommand command);
}
