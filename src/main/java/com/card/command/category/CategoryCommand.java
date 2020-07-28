package com.card.command.category;

import com.card.entity.vo.ResultVO;
import com.card.util.ResultVOUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryCommand {
    private Long id;
    private String name;
    private Integer state;

    public ResultVO<Object> validate() {
        if (null == id) {
            return ResultVOUtil.fail("id不能为空");
        }
        return ResultVOUtil.success();
    }
}