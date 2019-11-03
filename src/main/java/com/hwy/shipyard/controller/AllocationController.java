package com.hwy.shipyard.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.hwy.shipyard.dataobject.Allocation;
import com.hwy.shipyard.dataobject.AllocationDetail;
import com.hwy.shipyard.service.AllocationService;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author honghong
 * @version 1.0
 * @date 2019/9/3 20:47
 */

@RestController
@RequestMapping("warehouse/allocation")
public class AllocationController {

    @Autowired
    private AllocationService allocationService;


    @PostMapping("/add")
    public Object addAllocationDetail(@RequestBody String json) {
        Gson gson = new Gson();
        //将json字符串转换为JsonObject对象
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonObject allocationObject = jsonObject.getAsJsonObject("allocation");
        //拿出list集合
        JsonArray list = jsonObject.getAsJsonArray("list");
        //转换调拨单类型
        Allocation allocation = gson.fromJson(allocationObject,new TypeToken<Allocation>(){}.getType());
        List<AllocationDetail> allocationDetailList = gson.fromJson(list, new TypeToken<List<AllocationDetail>>() {
        }.getType());
        try {
            allocationService.addAllocation(allocation);
            for (AllocationDetail allocationDetail : allocationDetailList) {
                allocationService.addAllocationDetail(allocationDetail);
            }
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    @GetMapping("/list")
    public Object getAllocationAll(int pageNum,int pageSize){
        return allocationService.getAllAllocation(pageNum,pageSize);
    }

    @GetMapping("/detail")
    public Object getAllocationDetail(String allocationId,int pageNum,int pageSize){
        return allocationService.getAllocationDetail(allocationId,pageNum,pageSize);
    }

    @GetMapping("/id")
    public Object getAllocationById(String allocationId){
        return allocationService.getAllocationById(allocationId);
    }



}