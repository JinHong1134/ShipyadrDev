package com.hwy.shipyard.service;

import com.hwy.shipyard.dataobject.Role;
import com.hwy.shipyard.dataobject.User;
import com.hwy.shipyard.dataobject.UserRole;


public interface UserService {

    /**
     * 获得所有用户信息
     * @param pageNum 当前页数
     * @param pageSize 展示记录条数
     * @return
     */
    Object getAllUser(Integer pageNum, Integer pageSize);

    /**
     * 根据用户Id获得用户详细信息
     * @param userId 用户Id
     * @return
     */
    User getUserById(String userId);

    /**
     * 根据用户真实姓名获得用户信息
     * @param userName 用户真实姓名
     * @return
     */
    Object getUserByUserName(String userName);

    /**
     * 根据用户id获得用户
     * 授权认证所用
     * @param name
     * @return
     */

    /**
     * 添加用户
     * @param user user对象
     * @return 0：添加成功， -1：添加成功
     */
    Object addUser(User user);

    /**
     * 根据用户Id删除用户
     * @param userId 用户ID
     * @return 0:删除成功， -1：删除失败
     */
    Object delUser(String userId);

    /**
     * 更新用户
     * @param user User 对象
     * @return 0：更新成功， -1：更新失败
     */
    Object updateUser(User user);


    User getUserByUsername(String username);

    /**
     * 添加用户角色
     * @param userRole
     * @return 0 成功， -1 失败
     */
    Object addUserRole(UserRole userRole);

    Object delUserRole(String userId);

}
