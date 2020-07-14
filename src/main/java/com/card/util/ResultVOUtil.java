package com.card.util;

import com.card.entity.vo.ResultVO;

public class ResultVOUtil {
    public static ResultVO<Object> success(Object object) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(1);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO<Object> success() {
        return ResultVOUtil.success(null);
    }

    public static ResultVO<Object> fail() {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("失败");
        return resultVO;
    }
}
