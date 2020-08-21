package com.card.controller;

import com.card.command.IdsCommand;
import com.card.command.exportfile.ExportFileCommand;
import com.card.entity.domain.ExportFile;
import com.card.entity.vo.ResultVO;
import com.card.enu.ExportFileState;
import com.card.service.CustomMultiThreadingService;
import com.card.service.ExportFileService;
import com.card.util.RandomUtil;
import com.card.util.ResultVOUtil;
import com.card.util.SecurityUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

    /**
     * 分页展示文件列表
     * 需要管理员权限
     *
     * @param pageNum  页数
     * @param pageSize 页大小
     * @param command
     * @return
     */
    @PostMapping("/exportFileListByPage/{pageNum}/{pageSize}")
    public ResultVO<Object> exportFileListByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody ExportFileCommand command) {
        return ResultVOUtil.success(exportFileService.findByPage(pageNum, pageSize, command));
    }

    /**
     * 将excel下载到本地
     * 需要管理员权限
     *
     * @param id id
     * @return
     */
    @GetMapping("/downloadExportFile/{id}")
    public ResultVO<Object> downloadExportFile(@PathVariable("id") Long id) {
        ExportFile exportFile = exportFileService.selectById(id);
        if (exportFile == null) {
            return ResultVOUtil.success("未查询到文件信息");
        }
        if (!SecurityUtil.getCurrentUser().getId().equals(exportFile.getCreator())) {
            return ResultVOUtil.success("你没有权限删除");
        }
        exportFileService.downloadExportFile(exportFile);
        return ResultVOUtil.success();
    }

    /**
     * 删除文件
     * 需要管理员权限
     *
     * @param idsCommand 批量删除的文件id集合
     * @return
     */
    @GetMapping("/downloadExportFile")
    public ResultVO<Object> deleteExportFile(@RequestBody IdsCommand idsCommand) {
        ArrayList<Long> ids = Lists.newArrayList();
        for (Long id : idsCommand.getIds()) {
            ExportFile exportFile = exportFileService.selectById(id);
            if (exportFile == null) {
                log.info("未查询到文件信息");
            } else if (!SecurityUtil.getCurrentUser().getId().equals(exportFile.getCreator())) {
                log.info("你没有权限删除");
            } else {
                ids.add(id);
                // 从服务器上删除文件
                try {
                    File f = new File(exportFile.getPath());
                    if (f.exists()) {
                        f.delete();
                    }
                } catch (Exception e) {
                    log.error("文件内容读取异常，文件:" + e.getMessage());
                }
            }
        }
        // 从数据库中删除文件信息
        exportFileService.deleteExportFile(ids);
        return ResultVOUtil.success();
    }

    /**
     * 在服务器生成excel
     * 需要管理员权限
     *
     * @param exportFileCommand 查询条件对象
     * @return
     */
    @PostMapping("/generateExportFile")
    public ResultVO<Object> generateExportFile(@RequestBody ExportFileCommand exportFileCommand) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String endTime = simpleDateFormat.format(exportFileCommand.getStartTime());
        String startTime = simpleDateFormat.format(exportFileCommand.getEndTime());
        String fileName = startTime + "至" + endTime + "卡密数据" + RandomUtil.getStringRandom(4) + ".xlsx";
        // 插入到数据库，状态值为正在生成
        ExportFile exportFile = exportFileService.saveExportFile(fileName, path + fileName, ExportFileState.Downloading.getValue());
        // 新建线程生成需要导出的文件到服务器/data/faka/exportFile文件夹下
        customMultiThreadingService.executeAysncCardExport(exportFileCommand.getStartTime(), exportFileCommand.getEndTime(), exportFile);
        return ResultVOUtil.success();
    }
}