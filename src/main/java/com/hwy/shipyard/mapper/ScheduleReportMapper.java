package com.hwy.shipyard.mapper;

import com.hwy.shipyard.dataobject.Schedule;
import com.hwy.shipyard.dataobject.ScheduleReport;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ScheduleReportMapper {

    //查询所有
    @Select("select * from schedule_report")
    List<ScheduleReport> getAll();

    //新增
    @Insert("insert into schedule_report (schedule_id,maintenance_man,equipment_name,equipment_state," +
            "report_date,report_field0,report_field1,report_field2,report_field3) values " +
            "(#{scheduleId},#{maintenanceMan},#{equipmentName},#{equipmentState},#{reportDate}," +
            "#{reportField0},#{reportField1},#{reportField2},#{reportField3})")
    @Options(useGeneratedKeys = true, keyProperty = "sortId")
    void add(ScheduleReport scheduleReport);

    //根据计划单编号查询
    @Select("select * from schedule_report where schedule_id=#{scheduleId}")
    ScheduleReport getById(String scheduleId);

    @Select("select * from schedule_report where sort_id=#{sortId}")
    ScheduleReport getBySId(int sortId);

    @Update("update schedule_report set report_pre=#{reportPre},report_check=#{reportCheck} where sort_id=#{sortId}")
    void update(String reportPre,String reportCheck,int sortId);

    @Select("select count(*) as count from schedule_report")
    Integer getCount();
}
