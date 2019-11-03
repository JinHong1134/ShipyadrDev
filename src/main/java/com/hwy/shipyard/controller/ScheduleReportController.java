package com.hwy.shipyard.controller;

import com.hwy.shipyard.dataobject.Schedule;
import com.hwy.shipyard.dataobject.ScheduleReport;
import com.hwy.shipyard.service.ScheduleReportService;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule/report")
public class ScheduleReportController {
    @Autowired
    private ScheduleReportService service;

    //查询所有的工单
    @GetMapping("/all")
    public Object findAll(Integer pageNum, Integer pageSize){
        return service.getAll(pageNum,pageSize);
    }

    //根据工单编号查询
    @GetMapping("/id")
    public Object findById(String scheduleId){
        return service.findById(scheduleId);
    }

    //增加工单计划
    @PostMapping("/add")
    public Object add(ScheduleReport scheduleReport){
        try {
            service.add(scheduleReport);
            return JsonData.buildSuccess(null,"增加成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("增加失败");
        }
    }
}