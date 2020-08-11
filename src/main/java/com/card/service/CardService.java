package com.card.service;

import com.card.command.export.CardExport;
import com.card.entity.domain.Card;

import java.util.List;

public interface CardService {
    void cardDeleteByIds(List<Long> ids);

    void cardUpdateById(Long id, Card card);

    void cardInsert(Card card);

    List<CardExport> cardExportFindByStateAndTime(Integer state, Long startTime, Long endTime);
}
