package com.hwy.shipyard.enums;


public enum ResultEnum {
    PARAM_ERROR(0, "parameter error")
    ;

    private int code;

    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
