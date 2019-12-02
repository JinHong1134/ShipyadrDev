package com.hwy.shipyard.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.hwy.shipyard.dataobject.Product;
import com.hwy.shipyard.dataobject.WarehouseDeliver;
import com.hwy.shipyard.dataobject.WarehouseDeliverDetail;
import com.hwy.shipyard.mapper.ProductMapper;
import com.hwy.shipyard.service.WarehouseDeliverService;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("warehouse/deliver")
public class WarehouseDeliverController {

    @Autowired
    private WarehouseDeliverService warehouseDeliverService;

    @Autowired
    ProductMapper productMapper;

    @GetMapping("/list")
    public Object getAll(int pageNum,int pageSize){
        return warehouseDeliverService.getAll(pageNum,pageSize);
    }

    @GetMapping("/detail")
    public Object getDetail(String warehouseDeliverId,int pageNum,int pageSize){
        return warehouseDeliverService.getDetail(warehouseDeliverId,pageNum,pageSize);
    }


    @Transactional
    @PostMapping("/add")
    public Object addWarehouseDeliver(@RequestBody String requestJson){
        Gson gson = new Gson();

        //将json字符串转换为JsonObject对象
        JsonObject jsonObject = new JsonParser().parse(requestJson).getAsJsonObject();

        JsonObject deliverObject = jsonObject.getAsJsonObject("warehouseDeliver");
        WarehouseDeliver warehouseDeliver = gson.fromJson(deliverObject,new TypeToken<WarehouseDeliver>() {
        }.getType());
        //拿出list集合
        JsonArray list = jsonObject.getAsJsonArray("list");
        //System.out.println(list);
        //明细转换
        List<WarehouseDeliverDetail> warehouseEntryDetails = gson.fromJson(list, new TypeToken<List<WarehouseDeliverDetail>>() {
        }.getType());
        for (WarehouseDeliverDetail warehouseDeliverDetail:warehouseEntryDetails){
            if(warehouseDeliverDetail.getDeliverQuantity() < productMapper.getProductNum(warehouseDeliverDetail.getProductId(),warehouseDeliver.getWarehouseId()))
                return JsonData.buildError(warehouseDeliverDetail.getProductName()+"产品库存不足",-2);
        }

        try {
            warehouseDeliverService.addDeliver(warehouseDeliver);
            for (WarehouseDeliverDetail warehouseDeliverDetail:warehouseEntryDetails){
                warehouseDeliverService.addDetail(warehouseDeliverDetail);
                productMapper.updateProductNum(warehouseDeliverDetail.getProductId(),-warehouseDeliverDetail.getDeliverQuantity(),warehouseDeliver.getWarehouseId());
            }
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    //根据出库单单号查找出库单
    @GetMapping("/id")
    public Object getDeliverById(String warehouseDeliverId){
        return warehouseDeliverService.getDeliverById(warehouseDeliverId);
    }
}
