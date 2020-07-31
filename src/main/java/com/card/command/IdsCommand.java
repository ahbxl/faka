package com.card.command;

import com.card.entity.vo.ResultVO;
import com.card.util.ResultVOUtil;
import lombok.Data;

import java.util.List;

@Data
public class IdsCommand {
    private List<Long> ids;

    public ResultVO<Object> validate() {
        if (0 == ids.size()) {
            return ResultVOUtil.fail("ids不能为空");
        }
        return null;
    }
}
