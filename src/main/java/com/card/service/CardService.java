package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.card.entity.Card;
import com.card.entity.User;
import com.card.entity.vo.CardVO;

import java.util.List;

public interface CardService extends IService<Card> {
    Card selectById(Long id);

    void insert(Card card);

    IPage<Card> selectPage(CardVO cardVO);

    Integer countByProductId(Long productId);

    Card selectOne(Long id);

    void deleteBatchIds(List<Long> ids);
}
