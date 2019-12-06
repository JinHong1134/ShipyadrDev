package com.hwy.shipyard.mapper;

import com.hwy.shipyard.dataobject.ApplicationDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ApplicationDetailMapper {


    @Insert("insert into application_detail " +
            "(application_detail_id,application_id," +
            "product_id,product_name,product_unit," +
            "product_quantity," +
            "application_detail_field0,application_detail_field1," +
            "application_detail_field2,application_detail_field3)values" +
            "(#{applicationDetailId},#{applicationId}," +
            "#{productId},#{productName}," +
            "#{productUnit},#{productQuantity}," +
            "#{applicationDetailField0}" +
            ",#{applicationDetailField1},#{applicationDetailField2},#{applicationDetailField3})")
    @Options(useGeneratedKeys = true, keyProperty = "sortId")
    void add(ApplicationDetail detail);

    @Select("select * from application_detail")
    List<ApplicationDetail> getAll();

    @Select("select * from application_detail where application_id = #{applicationId}")
    List<ApplicationDetail> getById(String applicationId);

    //跟新pre和check
    @Update("update application_detail set application_detail_pre=#{applicationDetailPre},application_detail_check=#{applicationDetailCheck} where sort_id=#{sortId}")
    void update(String applicationDetailPre,String applicationDetailCheck,int sortId);

    @Select("select * from application_detail where sort_id = #{sortId}")
    ApplicationDetail getBySId(int sortId);

    @Select("select count(*) count from application_detail")
    Integer getCount();

    @Select("SELECT * FROM application_detail ORDER BY sort_id DESC LIMIT 1")
    ApplicationDetail getLast();

}
