package com.hwy.shipyard.service;

import com.hwy.shipyard.dataobject.Allocation;
import com.hwy.shipyard.dataobject.AllocationDetail;

/**
 * @author honghong
 * @version 1.0
 * @date 2019/9/3 9:14
 */
public interface AllocationService {

    /**
     * 增加调拨单
     */
    Object addAllocation(Allocation allocation);

    /**
     * 增加调拨明细单
     */
    Object addAllocationDetail(AllocationDetail allocationDetail);

    /**
     * 查询所有调拨单
     */
    Object getAllAllocation(int pageNum,int pageSize);

    /**
     * 根据调拨单号查找调拨单
     */
    Object getAllocationById(String allocationId);



    /**
     * 根据调拨单号查询明细
     */
    Object getAllocationDetail(String allocationId,int pageNum,int pageSize);

    /**
     * 更新调拨单校验位
     */
    Object updateAllocationCheck(String allocationCheckBits,int id);

    /**
     * 更新明细单校验位
     */
    Object updateAllocationDetailCheck(String allocationDetailCheckBits,int id);

    /**
     * 校验调拨单
     */
    Object checkAllocation();
    Object checkAllocationDetail();

    /**
     * 获取未审核调拨单数量
     * @param
     * @return
     */
    Object getStateNum();

    Object updateState(int state, String allocationId);
}
