package com.hwy.shipyard.controller;

import com.hwy.shipyard.service.RequestReportService;
import com.hwy.shipyard.dataobject.RequestReport;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request/report")
public class RequestReportController {
    @Autowired
    private RequestReportService service;

    //查询所有的工单
    @GetMapping("/all")
    public Object findAll(Integer pageNum, Integer pageSize){
        return service.getAll(pageNum,pageSize);
    }

    //根据工单编号查询
    @GetMapping("/id")
    public Object findById(String requestId){
        return service.findById(requestId);
    }

    //增加工单申请完成表
    @PostMapping("/add")
    public Object add(RequestReport requestReport){
        try {
            service.add(requestReport);
            return JsonData.buildSuccess(null,"增加成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("增加失败");
        }
    }
}
