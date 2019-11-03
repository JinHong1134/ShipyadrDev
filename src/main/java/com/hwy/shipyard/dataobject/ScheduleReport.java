package com.hwy.shipyard.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

//维修计划单的完成报告表
@Data
public class ScheduleReport implements Serializable {

    /**
     * 排序id做主键
     */
    private int sortId;

    /**
     * 对应的计划表编号
     */
    private String scheduleId;


    /**
     * 维修员
     */
    private String maintenanceMan;

    /**
     * 设备名称
     */
    private String equipmentName;

    /**
     * 设备情况
     */
    private String equipmentState;

    /**
     * 维修日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date reportDate;

    /**
     * 上一条哈希
     */
    private String reportPre;

    /**
     * 当前哈希
     */
    private String reportCheck;

    @Override
    public String toString() {
        return "ScheduleReport{" +
                "sortId=" + sortId +
                ", scheduleId='" + scheduleId + '\'' +
                ", maintenanceMan='" + maintenanceMan + '\'' +
                ", equipmentName='" + equipmentName + '\'' +
                ", equipmentState='" + equipmentState + '\'' +
                ", reportDate=" + reportDate +
                ", reportPre='" + reportPre + '\'' +
                ", reportField0='" + reportField0 + '\'' +
                ", reportField1='" + reportField1 + '\'' +
                ", reportField2='" + reportField2 + '\'' +
                ", reportField3='" + reportField3 + '\'' +
                '}';
    }

    /**
     * 预留字段
     */
    private String reportField0;
    private String reportField1;
    private String reportField2;
    private String reportField3;


}
