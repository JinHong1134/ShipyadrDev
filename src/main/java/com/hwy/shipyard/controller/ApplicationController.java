package com.hwy.shipyard.controller;


import com.hwy.shipyard.dataobject.Application;
import com.hwy.shipyard.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("application")
public class ApplicationController {

    @Autowired
    private ApplicationService service;

    //查看所有申请单
    @GetMapping("/all")
    public Object getAll(Integer pageNum, Integer pageSize){
        return service.getAll(pageNum,pageSize);
    }

    //根据申请单id查询
    @GetMapping("/find/id")
    public Object findById(String applicationId){
        return service.findById(applicationId);
    }

    //审核之后，更新申请单
    @PostMapping("/update")
    public Object  update(@ModelAttribute Application application){
        return service.updateState(application.getApplicationState(),application.getApplicationId());
    }

    //更新申请单状态为审核通过但未发货
    @PostMapping("/update/pass/one")
    public Object updatePassOne(String applicationId){
        return service.updatePassOne(applicationId);
    }

    //更新申请单状态为审核通过并已经发货
    @PostMapping("/update/pass/two")
    public Object updatePassTwo(String applicationId){
        return service.updatePassTwo(applicationId);
    }

    //更新工单状态为审核未过
    @PostMapping("/update/pass/not")
    public Object updateNotPass(String applicationId){
        return service.updateNotPass(applicationId);
    }



}
