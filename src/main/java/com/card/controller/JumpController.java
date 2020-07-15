package com.card.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class JumpController {
    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "AliPay";
    }
}
