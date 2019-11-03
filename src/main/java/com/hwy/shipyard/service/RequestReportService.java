package com.hwy.shipyard.service;

import com.hwy.shipyard.dataobject.RequestReport;

public interface RequestReportService {

    Object getAll(Integer pageNum, Integer pageSize);

    Object findById(String scheduleId);

    void add(RequestReport requestReport);

    Object check();
}
