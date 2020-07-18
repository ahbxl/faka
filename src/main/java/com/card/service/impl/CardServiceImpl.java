package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.card.dao.CardDao;
import com.card.entity.domain.Card;
import com.card.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CardServiceImpl implements CardService {
    @Autowired
    private CardDao cardDao;

    @Override
    public Integer countByproductId(Integer productId) {
        QueryWrapper<Card> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id", productId);
        return cardDao.selectCount(wrapper);
    }
}
