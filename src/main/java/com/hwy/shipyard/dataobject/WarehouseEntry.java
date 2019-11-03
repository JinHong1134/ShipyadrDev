package com.hwy.shipyard.dataobject;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;



/**
 * 正常入库单
 */
@Data
public class WarehouseEntry {

    /**
     * 自增主键ID
     */
    private int id;

    /**
     * 入库单单号
     */
    private String warehouseEntryId;

    /**
     *仓库Id
     */
    private String warehouseId;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 入库时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date entryTime;

    /**
     * 业务员
     */
    private String operatorName;

    /**
     * 备注
     */
    private String remark;

    /**
     *入库单类型
     * 0：正常入库单
     * 1：异常入库单
     */
    private int entryState;

    /**
     * 校验位
     */
    private String warehouseEntryCheckBits;

    /**
     * 预留字段
     */
    private String warehouseEntryField0;
    /*private String warehouseEntryField1;
    private String warehouseEntryField2;
    private String warehouseEntryField3;
     */

    @Override
    public String toString() {
        return  id +
                warehouseEntryId +
                warehouseId +
                warehouseName +
                entryTime +
                operatorName +
                remark +
                warehouseEntryField0 ;
    }
}

