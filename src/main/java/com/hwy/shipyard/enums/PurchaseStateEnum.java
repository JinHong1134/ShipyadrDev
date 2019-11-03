package com.hwy.shipyard.enums;

import lombok.Getter;

/**
 * @program: shipyard
 * @author: huangwenyu
 * @create: 2019-08-24
 */
@Getter
public enum PurchaseStateEnum {

    UNCHECK(0, "The application hasn't been checked"),
    APPROVED(1, "The application has been approved"),
    NOT_APPROVED(2, "The application has been not approved"),
    All(3, "all")
    ;

    private int code;

    private String message;

    PurchaseStateEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
