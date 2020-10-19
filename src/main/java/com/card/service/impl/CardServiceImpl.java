package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.card.command.IdsCommand;
import com.card.command.card.CardFindCommand;
import com.card.command.exportfile.CardExport;
import com.card.dao.CardDao;
import com.card.entity.Card;
import com.card.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public IPage<Card> selectByPage(Integer pageNum, Integer pageSize, CardFindCommand command) {
        Page<Card> categoryPage = new Page<>(pageNum, pageSize);
        QueryWrapper<Card> wrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(command.getContent())) {
            wrapper.like("content", command.getContent());
        }
        if (null != command.getState()) {
            wrapper.eq("state", command.getState());
        }
        if (null != command.getStartTime() && null != command.getEndTime()) {
            wrapper.between("create_time", command.getStartTime(), command.getEndTime());
        }
        return cardDao.selectPage(categoryPage, wrapper);
    }

    @Override
    public void updateById(Long id, Card card) {
        cardDao.updateById(id, card);
    }

    @Override
    public Card selectOne(Long id) {
        QueryWrapper<Card> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        wrapper.select("id", "content", "state", "product_id");
        return cardDao.selectOne(wrapper);
    }

    @Override
    public void deleteByIds(IdsCommand command) {
        cardDao.cardDeleteByIds(command.getIds());
    }
}