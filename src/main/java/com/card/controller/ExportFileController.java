package com.card.controller;

import com.card.entity.ExportFile;
import com.card.entity.vo.ExportFileVO;
import com.card.entity.vo.Result;
import com.card.enu.ExportFileState;
import com.card.security.utils.SecurityUtil;
import com.card.service.CustomMultiThreadingService;
import com.card.service.ExportFileService;
import com.card.service.UserService;
import com.card.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/exportFile")
public class ExportFileController {
    @Value("${export.file.path}")
    private String path;
    @Autowired
    private ExportFileService exportFileService;

    @Autowired
    private CustomMultiThreadingService customMultiThreadingService;

    @Autowired
    private UserService userService;

    /**
     * 分页展示文件列表
     * 需要管理员权限
     *
     * @param exportFileVO
     * @return
     */
    @PostMapping("/selectPage")
    public Result<Object> selectPage(@RequestBody ExportFileVO exportFileVO) {
        return Result.success(exportFileService.selectPage(exportFileVO));
    }

    /**
     * 将excel下载到本地
     * 需要管理员权限
     *
     * @param exportFileVO
     * @return
     */
    @PostMapping("/downloadExportFile")
    public void downloadExportFile(@RequestBody ExportFileVO exportFileVO) {
        ExportFile exportFile = exportFileService.getById(exportFileVO.getId());
        if (exportFile == null) throw new RuntimeException("不存在该文件");
        List<Long> longs = userService.selectUserIds(SecurityUtil.getCurrentUser().getId(), true);
        if (!longs.contains(exportFile.getCreator())) throw new RuntimeException("你没有权限");
        exportFileService.downloadExportFile(exportFile);
    }

    /**
     * 删除文件
     *
     * @param exportFileVO
     * @return
     */
    @PostMapping("/removeById")
    public Result<Object> removeById(@RequestBody ExportFileVO exportFileVO) {
        List<Long> longs = userService.selectUserIds(SecurityUtil.getCurrentUser().getId(), true);
        ExportFile exportFile = exportFileService.getById(exportFileVO.getId());
        if (exportFile != null && longs.contains(exportFile.getCreator())) {
            // 从服务器上删除文件
            try {
                File f = new File(exportFile.getPath());
                if (f.exists()) {
                    boolean delete = f.delete();
                    if (delete) log.info("用户{}删除文件{}", SecurityUtil.getCurrentUser().getId(), exportFileVO.getPath());
                }
            } catch (Exception e) {
                log.error("文件内容读取异常，文件:" + e.getMessage());
            }
        }
        // 从数据库中删除文件信息
        boolean remove = exportFileService.removeById(exportFileVO.getId());
        if (remove) log.info("用户{}删除记录{}", SecurityUtil.getCurrentUser().getId(), exportFileVO.getId());
        return Result.success();
    }

    /**
     * 在服务器生成excel
     * 需要管理员权限
     *
     * @param exportFileVO
     * @return
     */
    @PostMapping("/generateExportFile")
    public Result<Object> generateExportFile(@RequestBody ExportFileVO exportFileVO) {
        // 如果不传时间默认近七天
        Date start = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(start);
        c.add(Calendar.DAY_OF_MONTH, -7);
        start = c.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String endTime = simpleDateFormat.format(exportFileVO.getEndTime() == null ? new Date() : exportFileVO.getEndTime());
        String startTime = simpleDateFormat.format(exportFileVO.getStartTime() == null ? start : exportFileVO.getStartTime());
        // 随机生成文件名，防止重复
        String fileName = startTime + "至" + endTime + "卡密数据" + RandomUtils.getStringRandom(4) + ".xlsx";
        // 插入到数据库，状态值为正在生成
        ExportFile exportFile = new ExportFile();
        exportFile.setName(fileName);
        exportFile.setPath(path + fileName);
        exportFile.setCreator(SecurityUtil.getCurrentUser().getId());
        exportFile.setState(ExportFileState.Downloading.getValue());
        exportFileService.saveOrUpdate(exportFile);
        // 新建线程生成需要导出的文件到服务器/data/faka/exportFile文件夹下
        customMultiThreadingService.executeAysncCardExport(exportFileVO.getStartTime(), exportFileVO.getEndTime(), exportFile);
        return Result.success();
    }
}