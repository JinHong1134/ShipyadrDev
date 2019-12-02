package com.hwy.shipyard.dataobject;


import lombok.Data;

/**
 * 角色权限关联表
 */
@Data
public class RoleAuthorization {

    /**
     * 关联表id，主键
     */
    private int id;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限id
     */
    private Integer authorizationId;


}
