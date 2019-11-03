package com.hwy.shipyard.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwy.shipyard.dataobject.Schedule;
import com.hwy.shipyard.enums.ScheduleStateEnum;
import com.hwy.shipyard.mapper.ScheduleMapper;
import com.hwy.shipyard.service.ScheduleService;
import com.hwy.shipyard.utils.EncryptUtils;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleMapper mapper;

    @Override
    public Object getAll(Integer pageNum, Integer pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Schedule> schedules = mapper.getAll();
            PageInfo<Schedule> schedulePageInfo = new PageInfo<>(schedules);
            return JsonData.buildSuccess(schedulePageInfo,"查询全部成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("查询全部失败");
        }
    }

    @Override
    public Object findById(String scheduleId) {
        try {
            Schedule schedule = mapper.getById(scheduleId);
            List<Schedule> list = new ArrayList<>();
            list.add(schedule);
            return JsonData.buildSuccess(list,"查询成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("查询失败");
        }
    }

    @Override
    public void add(Schedule schedule) {
        mapper.add(schedule);
        //1.获取前一条记录的序号
        int preSortId = schedule.getSortId()-1;

        //2.判断是否为第一条记录
        if (preSortId!=0){
            //3.创建前一条记录的对象
            Schedule preSchedule = mapper.getBySId(preSortId);
            //4.获取其校验位的值
            String preCheck = preSchedule.getScheduleCheck();
            //5.创建当前纪录的对象
            Schedule nowSchedule = mapper.getBySId(preSortId+1);
            //5.5.将preCheck设置进去
            nowSchedule.setSchedulePre(preCheck);
            //6.加密数据
            String check = EncryptUtils.saltEncrypt(nowSchedule.toString(),"fkn");
            //7.更新当前记录
            mapper.update(preCheck,check,preSortId+1);
        }else {
            //4.前一条记录的加密位为空
            //5.创建当前纪录的对象
            Schedule nowSchedule = mapper.getBySId(preSortId+1);
            //6.加密数据
            String check = EncryptUtils.saltEncrypt(nowSchedule.toString(),"fkn");
            //7.更新当前记录
            mapper.update(null,check,preSortId+1);
        }

    }

    @Override
    public Object check(){
        int i = 7;//从7号开始
        int count = mapper.getCount()+i-1;

        while (true) {
            if (i==count-1){
                return JsonData.buildSuccess(null,2);
            }
            Schedule schedule = mapper.getBySId(i);

            String s1 = schedule.getScheduleCheck();

            String s2 = EncryptUtils.saltEncrypt(schedule.toString(), "fkn");

            if (s1.equals(s2)) {
                i++;
            } else {
                return JsonData.buildSuccess(schedule, "编号为" + schedule.getScheduleId() + "的记录与预期不符");
            }

        }
    }

    //更新工单状态为审核通过
    @Override
    public Object updatePass(String scheduleId) {

        Schedule schedule = mapper.getById(scheduleId);

        schedule.setScheduleState(ScheduleStateEnum.CHECKED1.getCode());

        try {
            mapper.updateState(schedule.getScheduleId(), schedule.getScheduleState());
            return JsonData.buildSuccess(null,"工单状态已更新");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("更新失败");
        }
    }

    //更新工单状态为审核未通过
    @Override
    public Object updateNotPass(String scheduleId) {
        Schedule schedule = mapper.getById(scheduleId);

        schedule.setScheduleState(ScheduleStateEnum.CHECKED2.getCode());

        try {
            mapper.updateState(schedule.getScheduleId(), schedule.getScheduleState());
            return JsonData.buildSuccess(null,"工单状态已更新");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("更新失败");
        }

    }
}
