package com.hwy.shipyard.dataobject;

import lombok.Data;

/**
 * @author JinHong
 * @version 1.0
 * @Name
 * @date 2019/12/6 1:51
 */
@Data
public class UpdatePwd {
    private String userId;

    private String oldUserPassword;

    private String newUserPassword;
}
