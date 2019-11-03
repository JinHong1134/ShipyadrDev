package com.hwy.shipyard.controller;

import com.hwy.shipyard.dataobject.MaintenanceRequest;
import com.hwy.shipyard.service.MaintenanceRequestService;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/maintenance")
public class MaintenanceRequestController {
    @Autowired
    private MaintenanceRequestService service;

    //查询所有的维修申请单
    @GetMapping("/all")
    public Object findAll(Integer pageNum, Integer pageSize){
        return service.getAll(pageNum,pageSize);
    }

    //根据工单编号查询
    @GetMapping("/id")
    public Object findById(String requestId){
        return service.findById(requestId);
    }

    //增加工单计划
    @PostMapping("/add")
    public Object add(@ModelAttribute MaintenanceRequest maintenanceRequest){
        try {
            service.add(maintenanceRequest);
            return JsonData.buildSuccess(null,"增加成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("增加失败");
        }
    }

    //更新状态为审核通过
    @PostMapping("/update/pass")
    public Object updatePass(String requestId){
        return service.updatePass(requestId);
    }

    //更新状态为审核未通过
    @PostMapping("/update/not")
    public Object updateNotPass(String requestId){
        return service.updateNotPass(requestId);
    }

}
