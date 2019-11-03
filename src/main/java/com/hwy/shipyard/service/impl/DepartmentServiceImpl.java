package com.hwy.shipyard.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwy.shipyard.dataobject.Department;
import com.hwy.shipyard.mapper.DepartmentMapper;
import com.hwy.shipyard.service.DepartmentService;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper depMapper;

    @Override
    public Object getAllDepartment(Integer pageNum, Integer pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Department> allDep = depMapper.getAllDepartment();
            PageInfo<Department> departmentPageInfo = new PageInfo<>(allDep);
            return JsonData.buildSuccess(departmentPageInfo,"查询全部成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("查询全部失败");
        }
    }

    @Override
    public Object addDepartment(Department department) {
        try {
            depMapper.addDepartment(department);
            return JsonData.buildSuccess(null,"增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("增加失败");
        }
    }

    @Override
    public Object delDepartment(String departmentId) {
        try {
            depMapper.delDepartment(departmentId);
            return JsonData.buildSuccess(null,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("删除失败");
        }
    }

    @Override
    public Object updateDepartment(Department department) {
        try {
            depMapper.updateDepartment(department);
            return JsonData.buildSuccess(null,"更新成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("更新失败");
        }
    }
}
