package com.hwy.shipyard.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色表
 */
@Data
public class Role implements Serializable {

    /**
     * 角色Id,主键
     */
    private int roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDescription;

    /**
     * 角色拥有权限
     */
    private List<Authorization> permissionList;




}
