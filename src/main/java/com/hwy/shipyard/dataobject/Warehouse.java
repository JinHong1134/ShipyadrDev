package com.hwy.shipyard.dataobject;


import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 11347
 * 仓库表
 */

@Data
public class Warehouse {

    /**
     * 仓库id，主键
     */
    private String warehouseId;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 仓库地址
     */
    private String warehouseAddress;

    /**
     * 仓库管理员
     */
    private String warehouseAdministrator;

    /**
     * 仓库面积
     */
    private BigDecimal warehouseArea;

    /**
     * 仓库状态
     */
    private String warehouseState;

    /**
     * 仓库备注
     */
    private String warehouseRemark;

    private String warehouseField0;
}
