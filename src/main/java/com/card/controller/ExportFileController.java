package com.card.controller;

import com.card.entity.ExportFile;
import com.card.entity.vo.ExportFileVO;
import com.card.entity.vo.ResultVO;
import com.card.enu.ExportFileState;
import com.card.service.CustomMultiThreadingService;
import com.card.service.ExportFileService;
import com.card.util.RandomUtil;
import com.card.util.ResultVOUtil;
import com.card.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
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

    /**
     * 分页展示文件列表
     * 需要管理员权限
     *
     * @param exportFileVO
     * @return
     */
    @PostMapping("/admin/selectPage")
    public ResultVO<Object> selectPage(@RequestBody ExportFileVO exportFileVO) {
        return ResultVOUtil.success(exportFileService.selectPage(exportFileVO));
    }

    /**
     * 将excel下载到本地
     * 需要管理员权限
     *
     * @param exportFileVO
     * @return
     */
    @GetMapping("/downloadExportFile}")
    public ResultVO<Object> downloadExportFile(@RequestBody ExportFileVO exportFileVO) {
        ExportFile exportFile = exportFileService.selectById(exportFileVO.getId());
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
     * @param exportFileVO
     * @return
     */
    @GetMapping("/admin/deleteBatchIds")
    public ResultVO<Object> deleteBatchIds(@RequestBody ExportFileVO exportFileVO) {
        for (Long id : exportFileVO.getIds()) {
            ExportFile exportFile = exportFileService.selectById(id);
            if (exportFile == null) {
                log.info("未查询到文件信息");
            } else if (!SecurityUtil.getCurrentUser().getId().equals(exportFile.getCreator())) {
                log.info("你没有权限删除");
            } else {
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
        exportFileService.deleteBatchIds(exportFileVO.getIds());
        return ResultVOUtil.success();
    }

    /**
     * 在服务器生成excel
     * 需要管理员权限
     *
     * @param exportFileVO
     * @return
     */
    @PostMapping("/generateExportFile")
    public ResultVO<Object> generateExportFile(@RequestBody ExportFileVO exportFileVO) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String endTime = simpleDateFormat.format(exportFileVO.getStartTime());
        String startTime = simpleDateFormat.format(exportFileVO.getEndTime());
        String fileName = startTime + "至" + endTime + "卡密数据" + RandomUtil.getStringRandom(4) + ".xlsx";
        // 插入到数据库，状态值为正在生成
        ExportFile exportFile = new ExportFile();
        exportFile.setName(fileName);
        exportFile.setPath(path);
        exportFile.setState(ExportFileState.Downloading.getValue());
        Integer insert = exportFileService.insert(exportFile);
        // 新建线程生成需要导出的文件到服务器/data/faka/exportFile文件夹下
        customMultiThreadingService.executeAysncCardExport(exportFileVO.getStartTime(), exportFileVO.getEndTime(), exportFileService.selectById(Long.valueOf(insert)));
        return ResultVOUtil.success();
    }
}