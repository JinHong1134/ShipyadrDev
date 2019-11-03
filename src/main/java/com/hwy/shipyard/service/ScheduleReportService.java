package com.hwy.shipyard.service;

import com.hwy.shipyard.dataobject.ScheduleReport;

public interface ScheduleReportService {

    Object getAll(Integer pageNum, Integer pageSize);

    Object findById(String scheduleId);

    void add(ScheduleReport scheduleReport);

    Object check();
}
