package com.hwy.shipyard.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hwy.shipyard.enums.ApplicationStateEnum;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 申请单
 */
@Data
public class Application implements Serializable {

    /**
     * 申请单ID 若一次申请了多种货物，以此为搜索索引
     */
    private String applicationId;

    /**
     * 船名
     */
    private String shipName;

    @Override
    public String toString() {
        return "Application{" +
                "applicationId='" + applicationId + '\'' +
                ", shipName='" + shipName + '\'' +
                ", applicationTime=" + applicationTime +
                ", applicationAddress='" + applicationAddress + '\'' +
                ", applicationProposer='" + applicationProposer + '\'' +
                ", applicationRemark='" + applicationRemark + '\'' +
                ", applicationPre='" + applicationPre + '\'' +
                ", applicationField0='" + applicationField0 + '\'' +
                ", applicationField1='" + applicationField1 + '\'' +
                ", applicationField2='" + applicationField2 + '\'' +
                ", applicationField3='" + applicationField3 + '\'' +
                '}';
    }

    /**
     * 申请时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date applicationTime;

    /**
     * 申请地址
     */
    private String applicationAddress;

    /**
     * 申请人
     */
    private String applicationProposer;

    /**
     * 申请单状态
     */
    private int applicationState = ApplicationStateEnum.UNCHECK0.getCode();

    /**
     * 申请单备注
     */
    private String applicationRemark;

    /**
     * 前一条记录
     */
    private String applicationPre;

    /**
     * 申请单校验位
     */
    private String applicationCheck;

    /**
     * 预留字段
     */
    private String applicationField0;
    private String applicationField1;
    private String applicationField2;
    private String applicationField3;

    /**
     * 排序id，用于获取上一条记录，做主键使用
     */
    @JsonIgnore
    private int sortId;



}
