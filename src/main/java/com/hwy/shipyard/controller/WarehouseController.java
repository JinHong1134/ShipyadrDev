package com.hwy.shipyard.controller;


import com.hwy.shipyard.dataobject.Warehouse;

import com.hwy.shipyard.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("/add")
    public Object addWarehouse(@RequestBody Warehouse warehouse){
        return warehouseService.addWarehouse(warehouse);
    }

    @GetMapping("/list")
    public Object getAll(int pageNum,int pageSize){
        return warehouseService.getAll(pageNum,pageSize);
    }


    @GetMapping("/del")
    public Object delWarehouse(String warehouseId){
        return warehouseService.delWarehouse(warehouseId);
    }


}
