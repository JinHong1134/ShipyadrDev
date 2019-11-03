package com.hwy.shipyard.dataobject;

import com.hwy.shipyard.enums.PurchaseStateEnum;
import lombok.Data;

import java.sql.Timestamp;


/**
 * @program: shipyard
 * @author: huangwenyu
 * @create: 2019-08-24
 */
@Data
public class Purchase {

    /**
     * 采购单编号，主键
     */
    private String purchaseId;

    /**
     * 申请单标题
     */
    private String purchaseTitle;

    /**
     * 业务员
     */
    private String operatorName;

    /**
     * 申请的部门
     */
    private String department;

    /**
     * 备注
     */
    private String note;

    /**
     * 采购计划
     */
    private String purchasePlan;

    /**
     * 申请仓库
     */
    private String warehouse;

    /**
     * 订单审核状态
     */
    private int purchaseState = PurchaseStateEnum.UNCHECK.getCode();

    /**
     * 订单创建时间
     */
    private Timestamp createTime;

    /**
     * 预留字段
     */
    private String purchaseField0;

    /**
     * 预留字段
     */
    private String purchaseField1;

    /**
     * 预留字段
     */
    private String purchaseField2;

    /**
     * 预留字段
     */
    private String purchaseField3;
}
