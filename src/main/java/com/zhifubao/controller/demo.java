package com.zhifubao.controller;

import com.zhifubao.entity.vo.ResultVO;
import com.zhifubao.util.ResultVOUtil;
import com.zhifubao.util.qrcodejsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class demo {
    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "demo";
    }

    @RequestMapping("/qrcode")
    @ResponseBody
    public ResultVO<Object> qrcode() {
        return ResultVOUtil.success(qrcodejsUtil.qrcode("iphone", "123465789", "0.01"));
    }
}