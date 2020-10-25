package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.dao.CardDao;
import com.card.entity.Card;
import com.card.entity.vo.CardVO;
import com.card.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CardServiceImpl extends ServiceImpl<CardDao, Card> implements CardService {
    @Autowired
    private CardDao cardDao;


    @Override
    public Card selectById(Long id) {
        return cardDao.selectById(id);
    }

    @Override
    public void insert(Card card) {
        cardDao.insert(card);
    }

    @Override
    public IPage<Card> selectPage(CardVO cardVO) {
        Page<Card> cardPage = new Page<>(cardVO.getPageNum(), cardVO.getPageSize());
        QueryWrapper<Card> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != cardVO.getProductId(), "product_id", cardVO.getProductId());
        queryWrapper.like(StringUtils.isNotBlank(cardVO.getContent()), "content", cardVO.getContent());
        queryWrapper.eq(null != cardVO.getState(), "state", cardVO.getState());
        queryWrapper.between(null != cardVO.getStartTime() && null != cardVO.getEndTime(), "create_time", cardVO.getStartTime(), cardVO.getEndTime());
        queryWrapper.orderByDesc("create_time");
        return cardDao.selectPage(cardPage, queryWrapper);
    }

    @Override
    public Integer countByProductId(Long productId) {
        QueryWrapper<Card> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        return cardDao.selectCount(queryWrapper);
    }

    @Override
    public Card selectOne(Long id) {
        QueryWrapper<Card> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        wrapper.select("id", "content", "state", "product_id", "creator");
        return cardDao.selectOne(wrapper);
    }

    @Override
    public void deleteBatchIds(List<Long> ids) {
        cardDao.deleteBatchIds(ids);
    }
}