package com.hwy.shipyard.enums;

import lombok.Getter;

@Getter
public enum ApplicationStateEnum {

    UNCHECK0(0, "The application hasn't been checked"),
    CHECKED1(1, "The application has been checked,but the goods have not been shipped"),
    CHECKED3(3,"The application was rejected"),
    CHECKED2(2,"The application has been checked and the goods have been shipped")
    ;

    private int code;

    private String message;

    ApplicationStateEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


}
