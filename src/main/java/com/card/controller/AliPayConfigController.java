package com.card.controller;

import com.card.entity.domain.AliPayConfig;
import com.card.entity.vo.ResultVO;
import com.card.service.AliPayService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/aliPayConfig")
public class AliPayConfigController {
    @Autowired
    private AliPayService aliPayService;

    @PostMapping("/updateById/{id}")
    public ResultVO<Object> updateById(@PathVariable("id") Long id, @RequestBody AliPayConfig aliPayConfig) {
        aliPayService.updateById(id, aliPayConfig);
        return ResultVOUtil.success();
    }

    @PostMapping("/selectById/{id}")
    public ResultVO<Object> selectById(@PathVariable("id") Long id) {
        return ResultVOUtil.success(aliPayService.selectById(id));
    }
}