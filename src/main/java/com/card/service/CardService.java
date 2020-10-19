package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.card.entity.Card;
import com.card.entity.vo.CardVO;

import java.util.List;

public interface CardService {
    void cardDeleteByIds(List<Long> ids);

    void insert(Card card);

    IPage<Card> selectPage(CardVO cardVO);

    Integer countByProductId(Long productId);

    void updateById(Card card);

    Card selectOne(Long id);

    void deleteBatchIds(List<Long> ids);
}
