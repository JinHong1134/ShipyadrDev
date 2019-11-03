package com.hwy.shipyard.controller;

import com.hwy.shipyard.dataobject.Schedule;
import com.hwy.shipyard.service.ScheduleService;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService service;

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
    public Object add(Schedule schedule){
        try {
            service.add(schedule);
            return JsonData.buildSuccess(null,"增加成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("增加失败");
        }
    }

    //更新工单状态为审核通过
    @PostMapping("/update/pass")
    public Object updatePass(String scheduleId){
        return service.updatePass(scheduleId);
    }

    //更新工单状态为审核未通过
    @PostMapping("/update/not")
    public Object updateNotPass(String scheduleId){
        return service.updateNotPass(scheduleId);
    }
}
