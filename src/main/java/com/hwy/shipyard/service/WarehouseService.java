package com.hwy.shipyard.service;


import com.hwy.shipyard.dataobject.Warehouse;

public interface WarehouseService {

    /**
     * 添加仓库
     * @param warehouse
     * @return 0成功 -1失败
     */
    Object addWarehouse(Warehouse warehouse);

    /**
     * 查询所有仓库信息
     * @param pageNum 查询页数
     * @param pageSize 每页展示记录条数
     * @return 0成功  -1失败
     */
    Object getAll(int pageNum,int pageSize);

    /**
     * 删除仓库
     * @param warehouseId
     * @return 0成功  -1失败
     */
    Object delWarehouse(String warehouseId);
}
