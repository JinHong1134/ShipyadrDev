package com.hwy.shipyard.dataobject;



import lombok.Data;

import java.io.Serializable;


/**
 * 入库明细表
 */
@Data
public class WarehouseEntryDetail implements Serializable {
    /**
     * 自增主键
     */
    private int warehouseEntryDetailId;

    /**
     * 入库单单号
     */
    private String warehouseEntryId;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品规格
     */
    private String productSpecification;

    /**
     * 入库数量
     */
    private int entryQuantity;

    /**
     * 产品单位
     */
    private String productUnit;


    /**
     * 产品存放位置
     */
    private String location;

    /**
     * 校验位
     */
    private String warehouseEntryDetailCheckBits;

    /**
     * 预留字段
     */
    private String warehouseEntryDetailField0;



    @Override
    public String toString() {
        return  warehouseEntryDetailId +
                warehouseEntryId +
                productId +
                productName +
                entryQuantity +
                productUnit +
                location +
                warehouseEntryDetailField0 ;
    }
}
