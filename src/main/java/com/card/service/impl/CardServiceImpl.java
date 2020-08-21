package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.card.command.exportfile.CardExport;
import com.card.dao.CardDao;
import com.card.entity.domain.Card;
import com.card.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CardServiceImpl implements CardService {
    @Autowired
    private CardDao cardDao;

    @Override
    public void cardDeleteByIds(List<Long> ids) {
        cardDao.cardDeleteByIds(ids);
    }

    @Override
    public void cardUpdateById(Long id, Card card) {
        cardDao.cardUpdateById(id, card);
    }

    @Override
    public void cardInsert(Card card) {
        cardDao.cardInsert(card);
    }

    @Override
    public List<CardExport> cardExportFindByStateAndTime(Integer state, Long startTime, Long endTime) {
        return cardDao.cardExportFindByStateAndTime(state, startTime, endTime);
    }

    @Override
    public Integer countByProductId(Long productId) {
        QueryWrapper<Card> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Card::getProductId, productId);
        return cardDao.selectCount(queryWrapper);
    }
}