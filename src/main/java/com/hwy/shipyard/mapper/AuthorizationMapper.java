package com.hwy.shipyard.mapper;


import com.hwy.shipyard.dataobject.Authorization;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AuthorizationMapper {

    /*
    @Select("SELECT a.authorization_id as id, a.authorization_name as name, a.authorization_url as url from  role_authorization ra " +
            "LEFT JOIN authorization a on ra.authorization_id=a.id " +
            "WHERE ra.role_id = #{roleId}")
    List<Authorization> findPermissionListByRoleId(@Param("roleId") int roleId);

     */
}
