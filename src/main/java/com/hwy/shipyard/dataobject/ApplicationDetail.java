package com.hwy.shipyard.dataobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApplicationDetail implements Serializable {

    /**
     * 明细单id ，多种货物，以此为搜索索引
     */
    private String applicationDetailId;

    /**
     * 对应的申请单id
     */
    private String applicationId;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品单位
     */
    private String productUnit;

    /**
     * 产品数量
     */
    private int productQuantity;

    /**
     * 前一条记录的校验位
     */
    private String applicationDetailPre;

    /**
     * 明细单校验位
     */
    private String applicationDetailCheck;

    /**
     * 明细单预留字段
     */
    private String applicationDetailField0;
    private String applicationDetailField1;
    private String applicationDetailField2;
    private String applicationDetailField3;

    /**
     * 排序id，做主键
     */
    private int sortId;


    @Override
    public String toString() {
        return "ApplicationDetail{" +
                "applicationDetailId='" + applicationDetailId + '\'' +
                ", applicationId='" + applicationId + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productUnit='" + productUnit + '\'' +
                ", productQuantity=" + productQuantity +
                ", applicationDetailPre='" + applicationDetailPre + '\'' +
                ", applicationDetailField0='" + applicationDetailField0 + '\'' +
                ", applicationDetailField1='" + applicationDetailField1 + '\'' +
                ", applicationDetailField2='" + applicationDetailField2 + '\'' +
                ", applicationDetailField3='" + applicationDetailField3 + '\'' +
                '}';
    }
}
