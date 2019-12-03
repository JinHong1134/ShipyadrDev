package com.hwy.shipyard.controller;




import com.hwy.shipyard.dataobject.Role;
import com.hwy.shipyard.dataobject.User;
import com.hwy.shipyard.dataobject.UserQuery;

import com.hwy.shipyard.dataobject.UserRole;
import com.hwy.shipyard.service.UserService;
import com.hwy.shipyard.utils.JsonData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("sys/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    //查找所有用户
    @RequestMapping("/list")
    public Object allUserInfo(Integer pageNum, Integer pageSize) {
        return userService.getAllUser(pageNum, pageSize);
    }

    //根据用户名查找用户
    @RequestMapping("/name")
    public Object UserInfoByName(String userName) {
        return userService.getUserByUserName(userName);
    }

    //增加用户
    @PostMapping("/add")
    public Object addUser(@RequestBody User user) {
        user.setUserPassword(new SimpleHash("md5",user.getUserPassword(),"fkn",2).toString());
        List<Role> roles = user.getRoleList();
        try{
            userService.addUser(user);
            for (Role role : roles){
                userService.addUserRole(user.getUserId(),role.getRoleId(),user.getUserPosition());
            }
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }

    }

    //删除用户
    @GetMapping("del")
    public Object delUser(String userId){
        userService.delUserRole(userId);
        return userService.delUser(userId);
    }

    //更新用户,更新所有字段
    @PostMapping("update")
    public Object updateUser(@RequestBody User user){
        List<Role> roles = user.getRoleList();
        try{
            userService.delUserRole(user.getUserId());
            for (Role role : roles){
                userService.addUserRole(user.getUserId(),role.getRoleId(),user.getUserPosition());
            }
            return userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }

    }


    /**
     * 重置用户权限
     * @param userRole
     * @return
     */
    @PostMapping("role/update")
    public Object updateUserRole(@RequestBody UserRole userRole){
        userService.delUserRole(userRole.getUserId());
        return userService.addUserRole(userRole.getUserId(),userRole.getRoleId(),userRole.getUserRoleDescription());
    }

    /**
     * 为用户赋予权限
     * @param userRole
     * @return
     */
    @PostMapping("role/add")
    public Object addUserRole(@RequestBody UserRole userRole){
        return userService.addUserRole(userRole.getUserId(),userRole.getRoleId(),userRole.getUserRoleDescription());
    }



}
