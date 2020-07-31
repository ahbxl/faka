package com.card.command.alipay;

import com.card.entity.vo.ResultVO;
import com.card.util.ResultVOUtil;
import lombok.Data;

@Data
public class AliPayCommand {
    private String subject;
    private String outTradeNo;
    private String totalAmount;

    public ResultVO<Object> validate() {
        if (subject == null) {
            return ResultVOUtil.success("subject不能为空");
        }
        if (outTradeNo == null) {
            return ResultVOUtil.success("outTradeNo不能为空");
        }
        if (totalAmount == null) {
            return ResultVOUtil.success("totalAmount不能为空");
        }
        return null;
    }
}