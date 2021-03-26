package com.hwy.shipyard.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

//产品表
@Data
public class Product implements Serializable {

    /**
     * 产品编号
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 库存
     */
    private String productStock;

    /**
     * 单位
     */
    private String productUnit;


    /**
     * 产品规格
     */
    private String productSpecification;

    /**
     * 产品状态
     */
    private int productState;

    /**
     * 存放地址
     */
    private String location;

    /**
     * 预留字段
     */
    private String productField0;
    private String productField1;
    private String productField2;
    private String productField3;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作员
     */
    private String operator;

    /**
     * 操作时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date operationTime;

}
