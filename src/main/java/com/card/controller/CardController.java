package com.card.controller;

import com.card.entity.vo.ResultVO;
import com.card.service.CardService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    /**
     * 通过productId计算产品的库存
     *
     * @param productId 产品的id
     * @return
     */
    @PostMapping("/countByProductId/{productId}")
    public ResultVO<Object> countByProductId(@PathVariable("productId") Long productId) {
        return ResultVOUtil.success(cardService.countByProductId(productId));
    }
}