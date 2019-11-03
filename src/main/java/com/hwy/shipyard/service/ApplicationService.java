package com.hwy.shipyard.service;

import com.hwy.shipyard.dataobject.Application;

public interface ApplicationService {

    Object getAll(Integer pageNum, Integer pageSize);

    void add(Application application);

    Object findById(String applicationId);

    Object updateState(int applicationState,String applicationId);

    Object updatePassOne(String applicationId);

    Object updatePassTwo(String applicationId);

    Object updateNotPass(String applicationId);

    Object check();



}
