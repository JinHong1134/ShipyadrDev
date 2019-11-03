package com.hwy.shipyard.dataobject;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 出库单
 */
@Data
public class WarehouseDeliver {

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 出库单单号
     */
    private String warehouseDeliverId;

    /**
     *仓库Id
     */
    private String warehouseId;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 出库时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date deliverTime;

    /**
     * 船舶名称
     */
    private String shipName;

    /**
     * 配送地址
     */
    private String address;

    /**
     * 业务员
     */
    private String operatorName;

    /**
     * 出库单状态
     * 0：正常出库单
     * 1：异常出库单
     */
    private int deliverState;

    /**
     * 备注
     */
    private String remark;

    /**
     * 校验位
     */
    private String warehouseDeliverCheckBits;

    /**
     * 预留字段
     */
    private String warehouseEntryField0;

    @Override
    public String toString() {
        return
                id +
                        warehouseDeliverId +
                        warehouseId +
                        warehouseName +
                        deliverTime +
                        shipName +
                        address +
                        operatorName +
                        remark +
                        warehouseEntryField0 ;
    }
}
