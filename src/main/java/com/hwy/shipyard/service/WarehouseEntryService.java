package com.hwy.shipyard.service;

import com.hwy.shipyard.dataobject.WarehouseEntry;
import com.hwy.shipyard.dataobject.WarehouseEntryDetail;

public interface WarehouseEntryService {
    /**
     * 添加入库单
     * @param warehouseEntry
     * @return
     */
    Object addWarehouseEntry(WarehouseEntry warehouseEntry);
    Object addWarehouseEntryDetail(WarehouseEntryDetail warehouseEntryDetail);

    /**
     * 查询
     */
    Object getWarehouseEntry(Integer pageNum, Integer pageSize);
    Object getWarehouseEntryDetail(String warehouseEntryId, Integer pageNum, Integer pageSize);
    Object getWarehouseEntryById(String warehouseEntryId);



    /**
     * 校验
     */
    Object checkEntry();

    Object checkEntryDetail();
}
