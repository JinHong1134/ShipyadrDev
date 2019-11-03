package com.hwy.shipyard.service;

import com.hwy.shipyard.dataobject.MaintenanceRequest;

public interface MaintenanceRequestService {
    Object getAll(Integer pageNum, Integer pageSize);

    Object findById(String requestId);

    void add(MaintenanceRequest maintenanceRequest);

    Object check();

    Object updatePass(String requestId);

    Object updateNotPass(String requestId);
}
