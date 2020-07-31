package com.card.service;

import com.card.entity.domain.Card;

import java.util.List;

public interface CardService {
    void cardDeleteByIds(List<Long> ids);

    void cardUpdateById(Long id, Card card);

    void cardInsert(Card card);
}
