package com.hwy.shipyard.mapper;


import com.hwy.shipyard.dataobject.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface RoleMapper {

    /*
    @Select("SELECT ur.role_id as id," +
            "r.name AS name, "+
            "r.description AS description " +
            "FROM  user_role ur LEFT JOIN role r ON ur.role_id = r.id " +
            "WHERE  ur.user_id = #{userId} ")
    @Results(
             value = {
                     @Result(id=true,property = "id",column = "id"),
                     @Result(property = "roleName",column = "role_name"),
                     @Result(property = "roleDescription",column = "role_description"),
                     @Result(property = "permissionList",column = "id",
                     many = @Many(select = "com.hwy.shipyard.mapper.AuthorizationMapper.findPermissionListByRoleId", fetchType = FetchType.DEFAULT)
                     )
            }
    )
    List<Role> findRoleListByUserId(@Param("userId") int userId);

     */

}
