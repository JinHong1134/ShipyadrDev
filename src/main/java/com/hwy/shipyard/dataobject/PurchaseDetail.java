package com.hwy.shipyard.dataobject;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class PurchaseDetail {

    /**
     * 订单详情表id
     */
    private String purchaseDetailId;

    /**
     * 订单申请表id
     */
    private String purchaseId;

    /**
     * 产品id
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品数量
     */
    private BigDecimal productQuantity;

    /**
     * 数量单位
     */
    private String productUnit;

    /**
     *供货商
     */
    private String factoryName;

    /**
     * 订货日期
     */
    private Date reserveTime;

    /**
     * 到货日期
     */
    private Date deliveryTime;

    /**
     * 产品单价
     */
    private BigDecimal productPrice;

    /**
     * 订单总价
     */
    private BigDecimal totalPrice;

    /**
     * 仓库id
     */
    private String warehouseId;

    /**
     * 仓库名字
     */
    private String warehouseName;

    /**
     * 上一条记录的哈希
     */
    private String purchaseDetailPre;

    /**
     * 校验位
     */
    private String purchaseDetailCheckBits;

    /**
     *采购明细单创建时间
     */
    private Timestamp createTime;

    /**
     * 采购人
     */
    private String purchaser;

    /**
     * 预留字段
     */
    private String purchaseDetailField0;

    /**
     * 预留字段
     */
    private String purchaseDetailField1;

    /**
     * 预留字段
     */
    private String purchaseDetailField2;

    /**
     * 预留字段
     */
    private String purchaseDetailField3;

    @Override
    public String toString() {
        return "PurchaseDetail{" +
                "purchaseDetailId='" + purchaseDetailId + '\'' +
                ", purchaseId='" + purchaseId + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productQuantity=" + productQuantity +
                ", productUnit='" + productUnit + '\'' +
                ", factoryName='" + factoryName + '\'' +
                ", reserveTime=" + reserveTime +
                ", deliveryTime=" + deliveryTime +
                ", productPrice=" + productPrice +
                ", totalPrice=" + totalPrice +
                ", warehouseId='" + warehouseId + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", purchaseDetailPre='" + purchaseDetailPre + '\'' +
                ", createTime=" + createTime +
                ", purchaser='" + purchaser + '\'' +
                ", purchaseDetailField0='" + purchaseDetailField0 + '\'' +
                ", purchaseDetailField1='" + purchaseDetailField1 + '\'' +
                ", purchaseDetailField2='" + purchaseDetailField2 + '\'' +
                ", purchaseDetailField3='" + purchaseDetailField3 + '\'' +
                '}';
    }
}
