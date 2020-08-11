package com.card.controller;

import com.card.command.exportfile.ExportFileCommand;
import com.card.entity.vo.ResultVO;
import com.card.service.CardService;
import com.card.service.CustomMultiThreadingService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @Autowired
    private CustomMultiThreadingService customMultiThreadingService;

    /**
     * 将卡密信息导出的异步任务
     *
     * @return
     */
    @GetMapping("/cardExport")
    public ResultVO<Object> cardExport(@RequestBody ExportFileCommand command) {
        customMultiThreadingService.executeAysncCardExport(command.getStartTime(), command.getStartTime());
        return ResultVOUtil.success();
    }
}
