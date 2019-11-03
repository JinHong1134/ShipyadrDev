package com.hwy.shipyard.dataobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class Ship implements Serializable {
    /**
     *IMO编号，主键
     */
    private String shipImoNumber;

    /**
     * 船名
     */
    private String shipName;

    /**
     * 船舶类型
     */
    private int shipType;

    /**
     * 登记港口
     */
    private String shipRegistration;

    /**
     * 官方号码
     */
    private String shipNumber;

    /**
     * 船舶呼号
     */
    private String shipCallSign;

    /**
     * 船级编号
     */
    private String shipGrade;

    /**
     * 航线
     */
    private String shipRoute;

    /**
     * 预留字段
     */
    private String shipField0;
    private String shipField1;
    private String shipField2;
    private String shipField3;
}
