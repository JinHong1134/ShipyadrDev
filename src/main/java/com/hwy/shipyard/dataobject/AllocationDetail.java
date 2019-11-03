package com.hwy.shipyard.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author honghong
 * @version 1.0
 * @date 2019/9/2 21:00
 */

@Data
public class AllocationDetail {

    /***
     * 自增id，主键
     */
    @JsonIgnore
    private Integer id;

    /**
     * 调拨单单号
     */
    private String allocationId;

    /**
     * 产品编号
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品单位
     */
    private String productUnit;

    /**
     * 产品规格
     */
    private String productSpecification;

    /**
     * 产品数量
     */
    private int productQuantity;

    /**
     * 备注
     */
    private String remark;

    /**
     * 调拨明细单校验位
     */
    private String allocationDetailCheckBits;

    /**
     *预留字段
     */
    private String field;

    @Override
    public String toString() {
        return
                id +
                allocationId +
                productId +
                productName +
                productUnit +
                productQuantity +
                remark +
                field ;
    }
}
