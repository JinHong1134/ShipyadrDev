package com.hwy.shipyard.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.hwy.shipyard.dataobject.Role;
import com.hwy.shipyard.dataobject.User;
import com.hwy.shipyard.mapper.RoleMapper;
import com.hwy.shipyard.mapper.UserMapper;
import com.hwy.shipyard.service.UserService;
import com.hwy.shipyard.utils.JsonData;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    //pageNum 查询第pageNum页，pageSize 一页展示pageSize条数据
    @Override
    public Object getAllUser(Integer pageNum, Integer pageSize) {
        //分页设置
        PageHelper.startPage(pageNum,pageSize);
        try {
            List<User> allUser = userMapper.getAllUser();
            PageInfo<User> userPageInfo = new PageInfo<>(allUser);
            return JsonData.buildSuccess(userPageInfo,0);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("失败",-1);
        }
    }

    @Override
    public User getUserById(String userId) {
        User user = userMapper.getUserById(userId);
        //用户的角色集合
        List<Role> roleList =  roleMapper.findRoleListByUserId(user.getUserId());
        user.setRoleList(roleList);

        return user;

    }

    @Override
    public Object getUserByUserName(String userName) {
        try {
            User user = userMapper.getUserByUsername(userName);
            List<User> users = new ArrayList<>();
            users.add(user);
            return JsonData.buildSuccess(users);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("失败",-1);
        }
    }


    @Override
    public Object addUser(User user) {
        try {
            userMapper.addUser(user);

            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("失败",-1);
        }
    }

    @Override
    public Object delUser(String userId) {
        try {
            userMapper.delUser(userId);
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("失败",-1);
        }
    }

    @Override
    public Object updateUserRoot(User user) {
        try {
            userMapper.updateUserRoot(user);
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("失败",-1);
        }
    }



    @Override
    public User getUserRoleByUsername(String username) {

        User user = userMapper.getUserByUsername(username);

        //用户的角色集合
        List<Role> roleList =  roleMapper.findRoleListByUserId(user.getUserId());

        user.setRoleList(roleList);

        return user;
    }

    @Override
    public Object addUserRole(String userId,int roleId,String userPosition) {
        try {
            roleMapper.addUserRole(userId,roleId,userPosition);
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    @Override
    public Object delUserRole(String userId) {
        try{
            roleMapper.deleteUserRole(userId);
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    @Override
    public Object updateUserInfo(User user) {
        try{
            userMapper.updateUserAdmin(user);
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    @Override
    public Object updateUserPwd(String userId, String oldUserPassword, String newUserPassword) {
        oldUserPassword = new SimpleHash("md5",oldUserPassword,"fkn",2).toString();
        newUserPassword = new SimpleHash("md5",newUserPassword,"fkn",2).toString();
        String pwd = userMapper.getUserById(userId).getUserPassword();
        System.out.println(oldUserPassword+"  "+newUserPassword);
        if(oldUserPassword.equals(pwd)) {
            try {
                userMapper.updateUserPwd(userId, oldUserPassword, newUserPassword);
                return JsonData.buildSuccess();
            } catch (Exception e) {
                e.printStackTrace();
                return JsonData.buildError();
            }
        }else
            return JsonData.buildSuccess("密码不一致",1);
    }
}
