package com.hwy.shipyard.dataobject;

import lombok.Data;

/**
 *用户登录实体类
 */
@Data
public class UserQuery {

    /**
     * 账户ID
     */
    private String userId;

    /**
     * 账户密码
     */
    private String userPassword;

}
