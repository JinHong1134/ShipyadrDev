package com.hwy.shipyard.mapper;

import com.hwy.shipyard.dataobject.RequestReport;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RequestReportMapper {

    //查询所有
    @Select("select * from request_report")
    List<RequestReport> getAll();

    //新增
    @Insert("insert into request_report (request_id,maintenance_man,equipment_name,maintenance_detail," +
            "report_date,report_field0,report_field1,report_field2,report_field3,ship_name) values " +
            "(#{requestId},#{maintenanceMan},#{equipmentName},#{maintenanceDetail},#{reportDate}," +
            "#{reportField0},#{reportField1},#{reportField2},#{reportField3},#{shipName})")
    @Options(useGeneratedKeys = true, keyProperty = "sortId")
    void add(RequestReport requestReport);

    //根据计划单编号查询
    @Select("select * from request_report where request_id=#{requestId}")
    RequestReport getById(String requestId);

    @Select("select * from request_report where sort_id=#{sortId}")
    RequestReport getBySId(int sortId);

    @Update("update request_report set report_pre=#{reportPre},report_check=#{reportCheck} where sort_id=#{sortId}")
    void update(String reportPre, String reportCheck, int sortId);

    @Select("select count(*) as count from request_report")
    Integer getCount();

    @Select("SELECT * FROM request_report ORDER BY sort_id DESC LIMIT 1")
    RequestReport getLast();
}