package com.card.controller;

import com.card.command.exportfile.ExportFileCommand;
import com.card.entity.vo.ResultVO;
import com.card.service.ExportFileService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/exportFile")
public class ExportFileController {
    @Autowired
    private ExportFileService exportFileService;

    @PostMapping("/exportFileListByPage/{pageNum}/{pageSize}")
    public ResultVO<Object> exportFileListByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody ExportFileCommand command) {
        return ResultVOUtil.success(exportFileService.findByPage(pageNum, pageSize, command));
    }
}
