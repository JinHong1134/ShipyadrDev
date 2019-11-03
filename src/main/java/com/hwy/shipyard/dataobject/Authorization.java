package com.hwy.shipyard.dataobject;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限表
 */
@Data
public class Authorization implements Serializable {
    /**
     *权限id，整形，主键
     */
    private int authorizationId;

    /**
     * 权限名称
     */
    private String authorizationName;

    /**
     * 权限url，暂留
     */
    private String authorizationUrl;

    /**
     * 权限描述
     */
    private String authorizationDescription;

}
