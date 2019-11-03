package com.hwy.shipyard.mapper;

import com.hwy.shipyard.dataobject.Ship;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ShipMapper {

    @Select("select * from ship_info")
    List<Ship> getAllShip();

    @Select("select count(*) as count from ship_info")
    Integer getCount();

    @Select("select * from ship_info where ship_name = #{shipName}")
    Ship getShipByName(@Param("shipName") String shipName);

    @Select("SELECT * FROM user WHERE ship_imo_number = #{shipImoNumber}")
    Ship getShipByIMO(@Param("shipImoNumber") String shipImoNumber);

    @Insert("insert into ship_info values (#{shipImoNumber}," +
            "#{shipName},#{shipType},#{shipRegistration},#{shipNumber}," +
            "#{shipCallSign},#{shipGrade},#{shipRoute},#{shipField0}," +
            "#{shipField1},#{shipField2},#{shipField3})")
    void addShip(Ship ship);

    @Delete("delete from ship_info where ship_imo_number = #{shipImoNumber}")
    void delShip(String shipId);

    @Update("update ship_info set ship_type=#{shipType}," +
            "ship_registration=#{shipRegistration}," +
            "ship_name=#{shipName}," +
            "ship_number=#{shipNumber}," +
            "ship_callsign=#{shipCallSign}," +
            "ship_grade=#{shipGrade}," +
            "ship_route=#{shipRoute}," +
            "ship_field0=#{shipField0}," +
            "ship_field1=#{shipField1}," +
            "ship_field2=#{shipField2}," +
            "ship_field3=#{shipField3} " +
            "where ship_imo_number=#{shipImoNumber}")
    void update(Ship ship);
}

