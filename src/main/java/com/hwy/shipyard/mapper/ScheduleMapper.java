package com.hwy.shipyard.mapper;


import com.hwy.shipyard.dataobject.Schedule;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ScheduleMapper {

    @Select("select * from schedule")
    List<Schedule> getAll();

    //新增工单计划表
    @Insert("insert into schedule (schedule_id,ship_name,equipment_name,maintenance_type," +
            "period,last_time,run_time,schedule_time,schedule_plan,operator_name,schedule_state," +
            "schedule_field0,schedule_field1,schedule_field2,schedule_field3,department,application_time) values " +
            "(#{scheduleId},#{shipName},#{equipmentName},#{maintenanceType},#{period}," +
            "#{lastTime},#{runTime},#{scheduleTime},#{schedulePlan},#{operatorName},#{scheduleState}," +
            "#{scheduleField0},#{scheduleField1},#{scheduleField2},#{scheduleField3},#{department},#{applicationTime})")
    @Options(useGeneratedKeys = true, keyProperty = "sortId")
    void add(Schedule schedule);

    //根据工单编号查询
    @Select("select * from schedule where schedule_id = #{scheduleId}")
    Schedule getById(String scheduleId);

    //根据排序编号查询
    @Select("select * from schedule where sort_id = #{sortId}")
    Schedule getBySId(int sortId);

    //更新pre check
    @Update("update schedule set schedule_pre=#{schedulePre},schedule_check=#{scheduleCheck} where sort_id=#{sortId}")
    void update(String schedulePre,String scheduleCheck,int sortId);

    @Select("select count(*) as count from schedule")
    Integer getCount();

    //更新工单状态
    @Update("update schedule set schedule_state=#{scheduleState} where schedule_id=#{scheduleId}")
    void updateState(String scheduleId,int scheduleState);


}
