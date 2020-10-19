package com.card.entity.export;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

@Data
@ExcelTarget("cardExport")
public class CardExport {
    @Excel(name = "id")
    private Long id;
    @Excel(name = "内容")
    private String content;
    @Excel(name = "商品名称")
    private String productName;
}