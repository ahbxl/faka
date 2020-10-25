package com.card.controller;

import com.card.entity.Card;
import com.card.entity.Product;
import com.card.entity.vo.CardVO;
import com.card.entity.vo.ResultVO;
import com.card.service.CardService;
import com.card.service.ProductService;
import com.card.service.UserService;
import com.card.util.ResultVOUtil;
import com.card.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    /**
     * 通过productId计算产品的库存
     *
     * @param cardVO
     * @return
     */
    @PostMapping("/token/countByProductId")
    public ResultVO<Object> countByProductId(@RequestBody CardVO cardVO) {
        Product product = productService.selectById(cardVO.getProductId());
        if (null == product) {
            return ResultVOUtil.fail("不存在该产品");
        }
        return ResultVOUtil.success(cardService.countByProductId(cardVO.getProductId()));
    }

    /**
     * 分页查询卡密内容
     * 需要管理员权限
     *
     * @param cardVO
     * @return
     */
    @PostMapping("/token/selectPage")
    public ResultVO<Object> selectPage(@RequestBody CardVO cardVO) {
        return ResultVOUtil.success(cardService.selectPage(cardVO));
    }

    /**
     * 更新卡密信息
     * 需要管理员权限
     *
     * @param card
     * @return
     */
    @PostMapping("/token/updateById")
    public ResultVO<Object> updateById(@RequestBody Card card) {
        Card cardById = cardService.selectOne(card.getId());
        if (cardById == null) {
            ResultVOUtil.fail("不存在该卡密信息");
        }
        List<Long> longs = userService.selectIdsByParentId(SecurityUtil.getCurrentUser().getId());
        if (!longs.contains(card.getCreator())) {
            ResultVOUtil.fail("你暂无权限");
        }
        cardService.updateById(card);
        log.info("用户{}更新了卡密{}", SecurityUtil.getCurrentUser().getId(), card);
        return ResultVOUtil.success();
    }

    /**
     * 添加卡密
     * 需要管理员权限
     *
     * @param card 卡密对象
     * @return
     */
    @PostMapping("/token/insert")
    public ResultVO<Object> insert(@RequestBody Card card) {
        cardService.insert(card);
        log.info("用户{}添加了卡密{}", SecurityUtil.getCurrentUser().getId(), card);
        return ResultVOUtil.success();
    }

    /**
     * 通过id查询卡密信息
     *
     * @param cardVO
     * @return
     */
    @PostMapping("/token/selectOne")
    public ResultVO<Object> selectOne(@RequestBody CardVO cardVO) {
        Card cardById = cardService.selectById(cardVO.getId());
        if (cardById == null) {
            ResultVOUtil.fail("不存在该卡密信息");
        }
        List<Long> longs = userService.selectIdsByParentId(SecurityUtil.getCurrentUser().getId());
        if (!longs.contains(cardVO.getCreator())) {
            ResultVOUtil.fail("你暂无权限查看");
        }
        return ResultVOUtil.success(cardById);
    }

    /**
     * 批量删除产品
     * 需要管理员权限
     *
     * @param cardVO
     * @return
     */
    @PostMapping("/token/deleteBatchIds")
    public ResultVO<Object> deleteBatchIds(@RequestBody CardVO cardVO) {
        ArrayList<Long> list = new ArrayList<>();
        List<Long> longs = userService.selectIdsByParentId(SecurityUtil.getCurrentUser().getId());
        for (Long id : cardVO.getIds()) {
            Card card = cardService.selectById(id);
            if (card != null && longs.contains(cardVO.getCreator())) {
                list.add(card.getId());
            }
        }
        cardService.deleteBatchIds(list);
        log.info("用户{}删除了{}", SecurityUtil.getCurrentUser().getId(), list);
        return ResultVOUtil.success();
    }
}