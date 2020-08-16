package com.card.controller;

import com.card.command.exportfile.ExportFileCommand;
import com.card.entity.domain.ExportFile;
import com.card.entity.vo.ResultVO;
import com.card.enu.ExportFileState;
import com.card.service.CustomMultiThreadingService;
import com.card.service.ExportFileService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

@RestController
@Slf4j
@RequestMapping("/exportFile")
public class ExportFileController {
    @Value("${export.file.path}")
    private String path;
    @Autowired
    private ExportFileService exportFileService;

    @Autowired
    private CustomMultiThreadingService customMultiThreadingService;

    @PostMapping("/exportFileListByPage/{pageNum}/{pageSize}")
    public ResultVO<Object> exportFileListByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody ExportFileCommand command) {
        return ResultVOUtil.success(exportFileService.findByPage(pageNum, pageSize, command));
    }

    @GetMapping("/downloadExportFile/{id}")
    public ResultVO<Object> downloadExportFile(@PathVariable("id") Long id) {
        exportFileService.downloadExportFile(id);
        return ResultVOUtil.success();
    }

    @PostMapping("/generateExportFile")
    public ResultVO<Object> generateExportFile(@RequestBody ExportFileCommand exportFileCommand) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String endTime = simpleDateFormat.format(exportFileCommand.getStartTime());
        String startTime = simpleDateFormat.format(exportFileCommand.getEndTime());
        String fileName = startTime + "至" + endTime + "卡密数据.xlsx";
        // 插入到数据库，状态值为正在生成
        ExportFile exportFile = exportFileService.saveExportFile(fileName, path + fileName, ExportFileState.Downloading.getValue());
        // 新建线程生成需要导出的文件到服务器/data/faka/exportFile文件夹下
        customMultiThreadingService.executeAysncCardExport(exportFileCommand.getStartTime(), exportFileCommand.getEndTime(), exportFile);
        return ResultVOUtil.success();
    }
}