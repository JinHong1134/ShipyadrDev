package com.hwy.shipyard.enums;

import lombok.Getter;

@Getter
public enum RequestStateEnum {
    UNCHECK(0, "The request hasn't been checked"),
    CHECKED1(1, "The request has been approved"),
    CHECKED2(2,"The request has not been approved")
    ;

    private int code;

    private String message;

    RequestStateEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
