package com.hwy.shipyard.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.hwy.shipyard.dataobject.User;
import com.hwy.shipyard.mapper.RoleMapper;
import com.hwy.shipyard.mapper.UserMapper;
import com.hwy.shipyard.service.UserService;
import com.hwy.shipyard.utils.JsonData;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
        return userMapper.getUserById(userId);

    }

    @Override
    public Object getUserByUserName(String userName) {
        try {
            return JsonData.buildSuccess(userMapper.getUserByUsername(userName), 0);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("失败",-1);
        }
    }


    @Override
    public Object addUser(User user) {
        String password = user.getUserPassword();
        user.setUserPassword(new SimpleHash("md5",password,"fkn",2).toString());
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
    public Object updateUser(User user) {
        try {
            userMapper.updateUser(user);
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("失败",-1);
        }
    }



    /*@Override
    public User getUserByUsername(String username) {

        User user = userMapper.getUserByUsername(username);

        //用户的角色集合
        List<Role> roleList =  roleMapper.findRoleListByUserId(user.getId());

        user.setRoleList(roleList);

        return user;
    }
     */
}
