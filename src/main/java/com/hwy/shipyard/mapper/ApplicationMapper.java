package com.hwy.shipyard.mapper;

import com.hwy.shipyard.dataobject.Application;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ApplicationMapper {

    @Insert("insert into application(application_id,ship_name, " +
            "application_time,application_address,application_proposer," +
            "application_state,application_remark," +
            "application_field0,application_field1," +
            "application_field2,application_field3)" +
            " values (#{applicationId},#{shipName}," +
            "#{applicationTime}," +
            "#{applicationAddress},#{applicationProposer}," +
            "#{applicationState},#{applicationRemark}," +
            "#{applicationField0},#{applicationField1}," +
            "#{applicationField2},#{applicationField3})" )
    @Options(useGeneratedKeys = true, keyProperty = "sortId")
    void add(Application application);

    @Select("select * from application")
    List<Application> getAll();

    @Select("select * from application where application_id = #{applicationId}")
    Application getById(String applicationId);

    @Select("select * from application where sort_id = #{sortId}")
    Application getBySId(int sortId);


    //更新pre和check
    @Update("update application set application_pre = #{applicationPre},application_check=#{applicationCheck} where sort_id=#{sortId}")
    void update(String applicationPre,String applicationCheck,int sortId);

    //更新表单状态
    @Update("update application set application_state=#{applicationState} where sort_id=#{sortId}")
    void updateState(int applicationState,int sortId);

    @Select("select count(*) count from application")
    Integer getCount();

    @Select("SELECT count(*) from application WHERE application_state = 0")
    Integer getStateNum();

    @Select("SELECT * FROM application ORDER BY sort_id DESC LIMIT 1")
    Application getLast();
}
