package com.hwy.shipyard.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author honghong
 * @version 1.0
 * @date 2019/9/2 21:58
 */

@Data
public class Allocation {

    /**
     * 自增主键
     */
    @JsonIgnore
    private Integer id;

    /**
     * 调拨单单号
     */
    private String allocationId;

    /**
     * 出库仓库
     */
    private String warehouseDeliver;

    /**
     * 出库仓库ID
     */
    private String warehouseDeliverId;

    /**
     * 入库仓库ID
     */
    private String warehouseEntryId;

    /**
     * 入库仓库
     */
    private String warehouseEntry;

    /**
     * 入库时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date allocationTime;

    /**
     * 操作人员名称
     */
    private String operatorName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 校验位
     */
    private String allocationCheckBits;

    /**
     * 预留字段
     */
    private String field;

    @Override
    public String toString() {
        return  id +
                allocationId +
                warehouseDeliver +
                warehouseEntry +
                operatorName +
                allocationTime+
                remark +
                field ;
    }
}
