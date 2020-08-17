package com.card.controller;

import com.card.service.CardService;
import com.card.service.CustomMultiThreadingService;
import com.card.service.ExportFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @Autowired
    private ExportFileService exportFileService;

    @Autowired
    private CustomMultiThreadingService customMultiThreadingService;
}
