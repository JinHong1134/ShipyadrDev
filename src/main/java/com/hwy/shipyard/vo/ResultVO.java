package com.hwy.shipyard.vo;

import lombok.Data;

/**
 * @program: shipyard
 * @author: huangwenyu
 * @create: 2019-08-24
 */
@Data
public class ResultVO<T> {

    private int code;

    private String msg;

    private T data;
}
