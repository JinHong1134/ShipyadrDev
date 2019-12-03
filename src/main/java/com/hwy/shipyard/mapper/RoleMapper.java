package com.hwy.shipyard.mapper;


import com.hwy.shipyard.dataobject.Role;
import com.hwy.shipyard.dataobject.UserRole;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface RoleMapper {

    @Select("SELECT ur.role_id as id, " +
            "r.role_description as role_description, " +
            "r.role_name as role_name " +
            "FROM user_role ur LEFT JOIN role r ON ur.role_id = r.role_id " +
            "WHERE ur.user_id = #{userId} ")
    @Results(
             value = {
                     @Result(id=true,property = "roleId",column = "id"),
                     @Result(property = "roleDescription",column = "role_description"),
                     @Result(property = "roleName",column = "role_name"),
                     @Result(property = "permissionList",column = "id",
                     many = @Many(select = "com.hwy.shipyard.mapper.AuthorizationMapper.findPermissionListByRoleId", fetchType = FetchType.DEFAULT)
                     )
            }
    )
    List<Role> findRoleListByUserId(@Param("userId") String userId);


    //更新用户权限
    @Insert("INSERT INTO user_role(user_id,role_id,role_description) VALUES(#{userId},#{roleId},#{userRoleDescription}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn="id")
    int addUserRole(String userId,int roleId,String userRoleDescription);

    //删除用户所有权限
    @Delete("DELETE FROM user_role WHERE user_id = #{userId}")
    int deleteUserRole(String userId);
}
