package com.hwy.shipyard.service;

import com.github.pagehelper.PageInfo;
import com.hwy.shipyard.dataobject.Department;

public interface DepartmentService {
    Object getAllDepartment(Integer pageNum, Integer pageSize);

    Object addDepartment(Department department);

    Object delDepartment(String departmentId);

    Object updateDepartment(Department department);
}
