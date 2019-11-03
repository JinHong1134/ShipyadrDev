package com.hwy.shipyard.enums;

import lombok.Getter;

@Getter
public enum  ScheduleStateEnum {

    UNCHECK(0, "The application hasn't been checked"),
    CHECKED1(1, "The application has been approved"),
    CHECKED2(2,"The application has not been approved")
    ;

    private int code;

    private String message;

    ScheduleStateEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
