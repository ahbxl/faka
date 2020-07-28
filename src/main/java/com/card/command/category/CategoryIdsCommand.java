package com.card.command.category;

import com.card.entity.vo.ResultVO;
import com.card.util.ResultVOUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CategoryIdsCommand {
    private List<Long> ids;

    public ResultVO<Object> validate() {
        if (0 == ids.size()) {
            return ResultVOUtil.fail("ids不能为空");
        }
        return ResultVOUtil.success();
    }
}