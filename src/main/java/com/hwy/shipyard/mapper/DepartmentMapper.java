package com.hwy.shipyard.mapper;

import com.hwy.shipyard.dataobject.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DepartmentMapper {
    @Select("select * from department")
    List<Department> getAllDepartment();

    @Insert("insert into department values (#{departmentId},#{departmentName},#{departmentManager}," +
            "#{departmentPhone},#{departmentIntroduce}," +
            "#{departmentField0},#{departmentField1}," +
            "#{departmentField2},#{departmentField3})")
    void addDepartment(Department department);

    @Delete("delete from department where department_id=#{departmentId}")
    void delDepartment(String departmentId);

    @Update("update department set department_name=#{departmentName}," +
            "department_manager=#{departmentManager}," +
            "department_phone=#{departmentPhone}," +
            "department_introduce=#{departmentIntroduce}," +
            "department_field0=#{departmentField0}," +
            "department_field1=#{departmentField1}," +
            "department_field2=#{departmentField2}," +
            "department_field3=#{departmentField3}" +
            "where department_id=#{departmentId}")
    void updateDepartment(Department department);

    @Select("select count(*) as count from department")
    Integer getCount();

}
