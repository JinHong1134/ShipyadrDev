package com.hwy.shipyard.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.hwy.shipyard.service.WarehouseService;
import com.hwy.shipyard.dataobject.Warehouse;
import com.hwy.shipyard.mapper.WarehouseMapper;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WarehouseImpl implements WarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public Object addWarehouse(Warehouse warehouse) {
        try {
            return JsonData.buildSuccess(warehouseMapper.addWarehouse(warehouse));
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    @Override
    public Object getAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        try {
            List<Warehouse> allWarehouse = warehouseMapper.getAllWarehouse();
            PageInfo<Warehouse> warehousePageInfo = new PageInfo<>(allWarehouse);
            return JsonData.buildSuccess(warehousePageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    @Override
    public Object delWarehouse(String warehouseId) {
        try {
            return JsonData.buildSuccess(warehouseMapper.delWarehouse(warehouseId));
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }
}
