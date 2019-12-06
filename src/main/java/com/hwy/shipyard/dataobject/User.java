package com.hwy.shipyard.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户表
 */
@Data
public class User implements Serializable {

    /**
     *用户账号，主键
     */
    private String userId;

    /**
     *用户密码
     */

    private String userPassword;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户性别
     */
    private int userSex;

    /**
     * 用户生日日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date userBirthday;

    /**
     * 用户住址
     */
    private String userAddress;

    /**
     * 用户部门
     */
    private String userDepartment;

    /**
     * 用户职位
     */
    private String userPosition;

    /**
     * 用户联系电话
     */
    private String userPhone;

    /**
     * 用户电子邮箱
     */
    private String userEmail;

    /**
     * 用户备注
     */
    private String userRemark;

    /**
     * 用户备注
     */
    private int userState;

    /**
     * 用户拥有角色
     */
    private List<Role> roleList;


}
