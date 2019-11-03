package com.hwy.shipyard.dataobject;

import lombok.Data;

import java.io.Serializable;

//部门
@Data
public class Department implements Serializable {
    /**
     * 部门ID 主键
     */
    private String departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 部门负责人
     */
    private String departmentManager;

    /**
     * 部门电话
     */
    private String departmentPhone;

    /**
     * 部门介绍
     */
    private String departmentIntroduce;

    /**
     * 预留字段
     */
    private String departmentField0;
    private String departmentField1;
    private String departmentField2;
    private String departmentField3;

}
