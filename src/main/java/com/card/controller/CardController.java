package com.card.controller;

import com.card.entity.Card;
import com.card.entity.vo.CardVO;
import com.card.entity.vo.ResultVO;
import com.card.service.CardService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * @param cardVO
     * @return
     */
    @PostMapping("/countByProductId")
    public ResultVO<Object> countByProductId(@RequestBody CardVO cardVO) {
        return ResultVOUtil.success(cardService.countByProductId(cardVO.getProductId()));
    }

    /**
     * 分页查询卡密内容
     * 需要管理员权限
     *
     * @param cardVO
     * @return
     */
    @PostMapping("/admin/selectPage")
    public ResultVO<Object> selectPage(@RequestBody CardVO cardVO) {
        return ResultVOUtil.success(cardService.selectPage(cardVO));
    }

    /**
     * 修改卡密信息
     * 需要管理员权限
     *
     * @param card
     * @return
     */
    @PostMapping("/admin/updateById")
    public ResultVO<Object> updateById(@RequestBody Card card) {
        Card cardById = cardService.selectOne(card.getId());
        if (cardById == null) {
            ResultVOUtil.fail("不存在该卡密信息");
        }
        cardService.updateById(card);
        return ResultVOUtil.success();
    }

    /**
     * 添加卡密
     * 需要管理员权限
     *
     * @param card 卡密对象
     * @return
     */
    @PostMapping("/admin/insert")
    public ResultVO<Object> insert(@RequestBody Card card) {
        cardService.insert(card);
        return ResultVOUtil.success();
    }

    /**
     * 通过id查询卡密信息
     *
     * @param cardVO
     * @return
     */
    @PostMapping("/admin/selectOne")
    public ResultVO<Object> selectOne(@RequestBody CardVO cardVO) {
        return ResultVOUtil.success(cardService.selectOne(cardVO.getId()));
    }

    /**
     * 批量删除产品
     * 需要管理员权限
     *
     * @param cardVO
     * @return
     */
    @PostMapping("/admin/deleteBatchIds")
    public ResultVO<Object> deleteBatchIds(@RequestBody CardVO cardVO) {
        cardService.deleteBatchIds(cardVO.getIds());
        return ResultVOUtil.success();
    }
}