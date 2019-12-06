package com.hwy.shipyard.controller;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.hwy.shipyard.service.WarehouseEntryService;
import com.hwy.shipyard.dataobject.WarehouseEntry;
import com.hwy.shipyard.dataobject.WarehouseEntryDetail;
import com.hwy.shipyard.mapper.ProductMapper;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse/entry")
public class WarehouseEntryController {
    @Autowired
    WarehouseEntryService warehouseEntryService;

    @Autowired
    ProductMapper productMapper;


    @GetMapping("/list")
    public Object getAll( Integer pageNum, Integer pageSize){
        try {
            return warehouseEntryService.getWarehouseEntry(pageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("查找失败");
        }
    }

    @GetMapping("/detail")
    public Object getDetail(String warehouseEntryId,Integer pageNum, Integer pageSize){
        try {
            return warehouseEntryService.getWarehouseEntryDetail(warehouseEntryId,pageNum,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("查找明细失败");
        }
    }

    @Transactional
    @PostMapping("/add")
    public Object addEntry(@RequestBody String requestJson){
/*        try {
            warehouseEntryService.addWarehouseEntry(warehouseEntry);
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }*/



        Gson gson = new Gson();

        //将json字符串转换为JsonObject对象
        JsonObject jsonObject = new JsonParser().parse(requestJson).getAsJsonObject();

        JsonObject entryObject = jsonObject.getAsJsonObject("warehouseEntry");
        WarehouseEntry warehouseEntry = gson.fromJson(entryObject,new TypeToken<WarehouseEntry>() {
        }.getType());
        //拿出list集合
        JsonArray list = jsonObject.getAsJsonArray("list");
        //System.out.println(list);
        //明细转换
        List<WarehouseEntryDetail> warehouseEntryDetails = gson.fromJson(list, new TypeToken<List<WarehouseEntryDetail>>() {
        }.getType());

        //开始写入数据库
        try {
            warehouseEntryService.addWarehouseEntry(warehouseEntry);
            for (WarehouseEntryDetail warehouseEntryDetail : warehouseEntryDetails) {
                warehouseEntryService.addWarehouseEntryDetail(warehouseEntryDetail);
                productMapper.updateProductNum(warehouseEntryDetail.getProductId(),warehouseEntryDetail.getEntryQuantity(),warehouseEntry.getWarehouseName());
            }
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    @GetMapping("/id")
    public Object getEntryById(String warehouseEntryId){
        return warehouseEntryService.getWarehouseEntryById(warehouseEntryId);
    }

}
