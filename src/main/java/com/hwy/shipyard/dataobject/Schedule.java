package com.hwy.shipyard.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hwy.shipyard.enums.ScheduleStateEnum;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

//维修计划工单
@Data
public class Schedule implements Serializable {

    /**
     * 工单编号
     */
    private String scheduleId;

    /**
     * 船名
     */
    private String shipName;

    /**
     * 设备名称
     */
    private String equipmentName;

    /**
     * 检修类型
     */
    private int maintenanceType;

    /**
     * 检修周期
     */
    private String period;

    /**
     * 上次检修时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date lastTime;

    /**
     * 运行时间
     */
    private String runTime;

    /**
     * 计划维修时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date scheduleTime;

    /**
     * 本次计划内容
     */
    private String schedulePlan;

    /**
     * 业务员
     */
    private String OperatorName;

    /**
     * 部门
     */
    private String department;

    /**
     * 申请日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date applicationTime;

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId='" + scheduleId + '\'' +
                ", shipName='" + shipName + '\'' +
                ", equipmentName='" + equipmentName + '\'' +
                ", maintenanceType=" + maintenanceType +
                ", period='" + period + '\'' +
                ", lastTime=" + lastTime +
                ", runTime='" + runTime + '\'' +
                ", scheduleTime=" + scheduleTime +
                ", schedulePlan='" + schedulePlan + '\'' +
                ", OperatorName='" + OperatorName + '\'' +
                ", department='" + department + '\'' +
                ", applicationTime=" + applicationTime +
                ", schedulePre='" + schedulePre + '\'' +
                ", scheduleField0='" + scheduleField0 + '\'' +
                ", scheduleField1='" + scheduleField1 + '\'' +
                ", scheduleField2='" + scheduleField2 + '\'' +
                ", scheduleField3='" + scheduleField3 + '\'' +
                ", sortId=" + sortId +
                '}';
    }

    /**
     * 工单状态
     */
    private int scheduleState = ScheduleStateEnum.UNCHECK.getCode();

    /**
     * 上一条记录的加密值
     */
    private String schedulePre;

    /**
     * 加密校验位
     */
    private String scheduleCheck;

    /**
     * 预留字段
     */
    private String scheduleField0;
    private String scheduleField1;
    private String scheduleField2;
    private String scheduleField3;

    /**
     * 排序编号，做主键
     */
    private int sortId;

}
