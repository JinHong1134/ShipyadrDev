package com.hwy.shipyard.service;


import com.hwy.shipyard.dataobject.WarehouseDeliver;
import com.hwy.shipyard.dataobject.WarehouseDeliverDetail;
import org.springframework.stereotype.Service;


public interface WarehouseDeliverService  {

    /**
     * 添加出库单
     * @param warehouseDeliver
     * @return
     */
    Object addDeliver(WarehouseDeliver warehouseDeliver);

    /**
     * 添加出库明细单
     * @param warehouseDeliverDetail
     * @return
     */
    Object addDetail(WarehouseDeliverDetail warehouseDeliverDetail);

    /**
     * 查询所有出库单
     * @param pageNum
     * @param pageSize
     * @return List<WarehouseDeliverDetail>
     */
    Object getAll(int pageNum,int pageSize);

    /**
     * 根据出库单单号获取明细
     * @param warehouseDeliverId
     * @return
     */
    Object getDetail(String warehouseDeliverId,int PageNum,int pageSize);


    /**
     * 校验出库单
     */
    Object checkDeliver();

    /**
     * 校验出库明细单
     */
    Object checkDetail();

    /**
     * 根据出库单单号获取出库单
     * @param warehouseDeliverId
     * @return
     */
    Object getDeliverById(String warehouseDeliverId);
}
