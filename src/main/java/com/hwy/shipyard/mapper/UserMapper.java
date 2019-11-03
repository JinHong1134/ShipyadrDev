package com.hwy.shipyard.mapper;


import com.hwy.shipyard.dataobject.User;
import com.hwy.shipyard.dataobject.Warehouse;
import com.hwy.shipyard.dataobject.WarehouseDeliver;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface UserMapper {

    //获得所有用户信息
    @Select("select * from user")
    List<User> getAllUser();

    @Select("select count(*) as count from user")
    Integer getCount();

    @Select("select * from user where user_name = #{userName}")
    User getUserByUsername(@Param("userName") String userName);

    @Select("SELECT * FROM user WHERE user_id = #{userId}")
    User getUserById(@Param("userId") String userId);

    @Select("SELECT * FROM user where user_name = #{userName} and user_password = #{userPassword}")
    User login(@Param("userName") String userName, @Param("userPassword") String userPassword);

    @Insert("INSERT INTO user VALUES(#{userId},#{userPassword},#{userName},#{userSex},#{userBirthday},#{userAddress},#{userDepartment},#{userPosition},#{userPhone},#{userEmail},#{userRemark},#{userState})")
    int addUser(User user);

    @Delete("DELETE FROM user WHERE user_id = #{userId} ")
    int delUser(String userId);

    @Update("UPDATE user SET user_password=#{userPassword},"+
            "user_name=#{userName}," +
            "user_sex=#{userSex}," +
            "user_birthday=#{userBirthday}," +
            "user_address=#{userAddress}," +
            "user_department=#{userDepartment}," +
            "user_position=#{userPosition}," +
            "user_phone=#{userPhone}," +
            "user_email=#{userEmail}," +
            "user_remark=#{userRemark}," +
            "user_state=#{userState} " +
            "WHERE user_id = #{userId}" )
    int updateUser(User user);
}
