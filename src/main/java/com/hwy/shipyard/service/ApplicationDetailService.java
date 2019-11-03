package com.hwy.shipyard.service;

import com.hwy.shipyard.dataobject.ApplicationDetail;
import org.springframework.web.bind.annotation.RestController;

public interface ApplicationDetailService {

    Object getAll(Integer pageNum, Integer pageSize);

    void add(ApplicationDetail detail);

    Object findById(String applicationId);

    Object check();

}
