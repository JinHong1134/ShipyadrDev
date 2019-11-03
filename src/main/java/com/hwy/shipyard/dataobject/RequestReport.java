package com.hwy.shipyard.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

//维修申请单对应的完成报告单
@Data
public class RequestReport implements Serializable {

    private String requestId;

    private String maintenanceMan;

    private String equipmentName;

    private String maintenanceDetail;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date reportDate;

    private String reportPre;

    private String reportCheck;

    private String reportField0;
    private String reportField1;
    private String reportField2;
    private String reportField3;

    private String shipName;

    private int sortId;

    @Override
    public String toString() {
        return "RequestReport{" +
                "requestId='" + requestId + '\'' +
                ", maintenanceMan='" + maintenanceMan + '\'' +
                ", equipmentName='" + equipmentName + '\'' +
                ", maintenanceDetail='" + maintenanceDetail + '\'' +
                ", reportDate=" + reportDate +
                ", reportPre='" + reportPre + '\'' +
                ", reportField0='" + reportField0 + '\'' +
                ", reportField1='" + reportField1 + '\'' +
                ", reportField2='" + reportField2 + '\'' +
                ", reportField3='" + reportField3 + '\'' +
                ", shipName='" + shipName + '\'' +
                ", sortId=" + sortId +
                '}';
    }
}
