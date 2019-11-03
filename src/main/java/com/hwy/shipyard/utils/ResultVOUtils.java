package com.hwy.shipyard.utils;

import com.hwy.shipyard.vo.ResultVO;

/**
 * @program: shipyard
 * @author: huangwenyu
 * @create: 2019-08-24
 */
public class ResultVOUtils {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("success");
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg("error");
        return resultVO;
    }
}
