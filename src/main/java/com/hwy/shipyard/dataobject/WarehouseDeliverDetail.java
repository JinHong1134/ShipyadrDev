package com.hwy.shipyard.dataobject;

import lombok.Data;

import java.math.BigInteger;

/**
 * 出库明细单
 */
@Data
public class WarehouseDeliverDetail {

    /**
     * 自增主键
     */
    private int id;

    /**
     * 出库单单号
     */
    private String warehouseDeliverId;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 出库数量
     */
    private int deliverQuantity;

    /**
     * 产品规格
     */
    private String productSpecification;

    /**
     * 产品单位
     */
    private String productUnit;

    /**
     * 校验位
     */
    private String warehouseDeliverDetailCheckBits;

    /**
     * 预留字段
     */
    private String warehouseDeliverDetailField0;

    @Override
    public String toString() {
        return id +
                warehouseDeliverId  +
                productId  +
                productName  +
                deliverQuantity +
                productUnit  +
                warehouseDeliverDetailField0 ;
    }
}
