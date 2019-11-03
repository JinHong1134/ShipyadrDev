package com.hwy.shipyard.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hwy.shipyard.enums.RequestStateEnum;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

//维修申请单
@Data
public class MaintenanceRequest implements Serializable {

    /**
     * 维修申请单id
     */
    private String requestId;

    /**
     * 船名
     */
    private String shipName;

    @Override
    public String toString() {
        return "MaintenanceRequest{" +
                "requestId='" + requestId + '\'' +
                ", shipName='" + shipName + '\'' +
                ", equipmentName='" + equipmentName + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", requestState=" + requestState +
                ", requestDate=" + requestDate +
                ", requestPre='" + requestPre + '\'' +
                ", department='" + department + '\'' +
                ", note='" + note + '\'' +
                ", requestField0='" + requestField0 + '\'' +
                ", requestField1='" + requestField1 + '\'' +
                ", requestField2='" + requestField2 + '\'' +
                ", requestField3='" + requestField3 + '\'' +
                ", sortId=" + sortId +
                '}';
    }

    /**
     * 设备名称
     */
    private String equipmentName;

    /**
     * 业务员名称
     */
    private String operatorName;

    /**
     * 工单状态
     */
    private int requestState = RequestStateEnum.UNCHECK.getCode();

    /**
     * 申请日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date requestDate;

    /**
     * 前一记录的哈希
     */
    private String requestPre;

    /**
     *哈希
     */
    private String requestCheck;

    /**
     * 申请部门
     */
    private String department;

    /**
     * 申请原因
     */
    private String note;

    /**
     * 预留字段
     */
    private String requestField0;
    private String requestField1;
    private String requestField2;
    private String requestField3;

    /**
     * 排序id 主键
     */
    private int sortId;


}
