package com.hwy.shipyard.controller;

import com.hwy.shipyard.service.DepartmentService;
import com.hwy.shipyard.dataobject.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys")
public class DepartmentController {
    @Autowired
    private DepartmentService depService;

    //查看所有部门
    @GetMapping("/department/all")
    public Object getAll(Integer pageNum, Integer pageSize){
        return depService.getAllDepartment(pageNum,pageSize);
    }

    //增加
    @PostMapping("/department/add")
    public Object addDepartment(@RequestBody Department department){
        return depService.addDepartment(department);
    }

    //删除
    @GetMapping("/department/del")
    public Object delDepartment(String departmentId){
        return depService.delDepartment(departmentId);
    }

    //更新
    @PostMapping("/department/update")
    public Object updateDepartment(@RequestBody Department department){
        return depService.updateDepartment(department);
    }
}
