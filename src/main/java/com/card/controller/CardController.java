package com.card.controller;

import com.card.command.IdsCommand;
import com.card.command.card.CardFindCommand;
import com.card.entity.domain.Card;
import com.card.entity.vo.ResultVO;
import com.card.service.CardService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 分页查询卡密内容
     * 需要管理员权限
     *
     * @param pageNum
     * @param pageSize
     * @param command
     * @return
     */
    @PostMapping("/admin/selectByPage/{pageNum}/{pageSize}")
    public ResultVO<Object> cardFindByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody CardFindCommand command) {
        return ResultVOUtil.success(cardService.selectByPage(pageNum, pageSize, command));
    }

    /**
     * 修改卡密信息
     * 需要管理员权限
     *
     * @param id
     * @param card
     * @return
     */
    @PostMapping("/admin/updateById/{id}")
    public ResultVO<Object> cardFindByPage(@PathVariable("id") Long id, @RequestBody Card card) {
        Card cardById = cardService.selectOne(id);
        if (cardById == null) {
            ResultVOUtil.fail("不存在该卡密信息");
        }
        cardService.updateById(id, card);
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
        card.validate();
        cardService.cardInsert(card);
        return ResultVOUtil.success();
    }

    /**
     * 通过id查询卡密信息
     *
     * @param id 主键
     * @return
     */
    @PostMapping("/admin/selectOne/{id}")
    public ResultVO<Object> selectOne(@PathVariable("id") Long id) {
        return ResultVOUtil.success(cardService.selectOne(id));
    }

    /**
     * 批量删除产品
     * 需要管理员权限
     *
     * @param command ids
     * @return
     */
    @PostMapping("/admin/deleteByIds")
    public ResultVO<Object> deleteByIds(@RequestBody IdsCommand command) {
        command.validate();
        cardService.deleteByIds(command);
        return ResultVOUtil.success();
    }
}