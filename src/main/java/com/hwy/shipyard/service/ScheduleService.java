package com.hwy.shipyard.service;

import com.hwy.shipyard.dataobject.Schedule;

public interface ScheduleService {

    Object getAll(Integer pageNum, Integer pageSize);

    Object findById(String scheduleId);

    void add(Schedule schedule);

    Object check();

    Object updatePass(String scheduleId);

    Object updateNotPass(String scheduleId);

    Object getStateNum();

}
