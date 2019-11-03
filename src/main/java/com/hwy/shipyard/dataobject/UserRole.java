package com.hwy.shipyard.dataobject;

import lombok.Data;

/**
 * 用户角色关联表
 */
@Data
public class UserRole {
    /**
     * id，主键
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 用户角色描述
     */
    private String userRoleDescription;
}
