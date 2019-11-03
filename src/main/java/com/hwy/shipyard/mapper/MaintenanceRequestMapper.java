package com.hwy.shipyard.mapper;

import com.hwy.shipyard.dataobject.MaintenanceRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MaintenanceRequestMapper {

    @Select("select * from maintenance_request")
    List<MaintenanceRequest> getAll();

    @Insert("insert into maintenance_request (request_id,ship_name," +
            "equipment_name,operator_name,request_state,request_date," +
            "department,note,request_field0,request_field1,request_field2,request_field3) values" +
            "(#{requestId},#{shipName},#{equipmentName},#{operatorName},#{requestState},#{requestDate}," +
            "#{department},#{note},#{requestField0},#{requestField1},#{requestField2},#{requestField3})")
    @Options(useGeneratedKeys = true, keyProperty = "sortId")
    void add(MaintenanceRequest maintenanceRequest);

    //根据工单编号查询
    @Select("select * from maintenance_request where request_id = #{requestId}")
    MaintenanceRequest getById(String requestId);

    //根据排序编号查询
    @Select("select * from maintenance_request where sort_id = #{sortId}")
    MaintenanceRequest getBySId(int sortId);

    //更新pre check
    @Update("update maintenance_request set request_pre=#{requestPre},request_check=#{requestCheck} where sort_id=#{sortId}")
    void update(String requestPre,String requestCheck,int sortId);

    @Select("select count(*) as count from maintenance_request")
    Integer getCount();

    @Update("update maintenance_request set request_state=#{requestState} where request_id=#{requestId}")
    void updateState(String requestId,int requestState);
}
