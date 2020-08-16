package com.card.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.card.entity.domain.ExportFile;
import com.card.service.ExportFileService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    @Autowired
    private ExportFileService exportFileService;

    /**
     * 创建workbook,
     * 通过maplist填充Excel内容
     * 返回workbook
     * <p>
     * 进一步使用可以写入流,e.g.
     * FileOutputStream fos = new FileOutputStream(file);
     * workbook.write(fos);
     */
    public static Workbook mutiSheet(List<Map<String, Object>> mapListList) {
        Workbook workbook = null;
        workbook = ExcelExportUtil.exportExcel(mapListList, ExcelType.XSSF);
        return workbook;
    }

    public static Map<String, Object> createOneSheet(ExportParams exportParams, Class<?> clazz, List<?> data) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", exportParams);//new ExportParams("title"+i, "sheetName"+i, ExcelType.XSSF)
        map.put("entity", clazz);
        map.put("data", data);
        return map;
    }

    /**
     * 创建一个表格并填充内容
     * 返回map供工作簿使用
     */
    public static Map<String, Object> createOneSheet(String sheetName, String title, Class<?> clazz, List<?> data) {
        ExportParams exportParams = new ExportParams(title, sheetName, ExcelType.XSSF);
        return createOneSheet(exportParams, clazz, data);
    }

    /**
     * 下载excel
     *
     * @param mapListList
     * @param fileName
     * @return
     */
    public static void downFile(List<Map<String, Object>> mapListList, String fileName) {
        Workbook workbook = mutiSheet(mapListList);
    }
}