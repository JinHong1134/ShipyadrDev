package com.hwy.shipyard.controller;




import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.hwy.shipyard.dataobject.UpdatePwd;
import com.hwy.shipyard.dataobject.UserRole;
import com.hwy.shipyard.service.UserService;
import com.hwy.shipyard.dataobject.Role;
import com.hwy.shipyard.dataobject.User;

import com.hwy.shipyard.utils.JsonData;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

   /* //更新用户,更新所有字段
    @PostMapping("update")
    public Object updateUser(@RequestBody User user){
        List<Role> roles = user.getRoleList();
        user.setUserPassword(new SimpleHash("md5",user.getUserPassword(),"fkn",2).toString());
        try{
            userService.delUserRole(user.getUserId());
            for (Role role : roles){
                userService.addUserRole(user.getUserId(),role.getRoleId(),user.getUserPosition());
            }
            return userService.updateUserRoot(user);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }

    }*/

    @PostMapping("update/info")
    public Object updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }


    /**
     * 重置用户权限
     * @param userRoles
     * @return
     */
    @PostMapping("update/role")
    public Object updateUserRole(@RequestBody String requestJson) {
        Gson gson = new Gson();

        //将json字符串转换为JsonObject对象
        JsonObject jsonObject = new JsonParser().parse(requestJson).getAsJsonObject();


        //拿出list集合
        JsonArray list = jsonObject.getAsJsonArray("roleList");

        List<UserRole> userRoles = gson.fromJson(list, new TypeToken<List<UserRole>>() {
        }.getType());
        try {
            for (UserRole userRole : userRoles) {
                userService.delUserRole(userRole.getUserId());
                userService.addUserRole(userRole.getUserId(), userRole.getRoleId(), userRole.getRoleDescription());
            }
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
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

    @PostMapping("/update/pwd")
    public Object updateUserPwd(@RequestBody UpdatePwd updatePwd) {
        return userService.updateUserPwd(updatePwd.getUserId(),updatePwd.getOldUserPassword(),updatePwd.getNewUserPassword());
    }

    @PostMapping("/update/info/root")
    public Object updateUserPwd(@RequestBody User user) {
        return userService.updateUserRoot(user);
    }




}
