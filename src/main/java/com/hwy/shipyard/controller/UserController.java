package com.hwy.shipyard.controller;




import com.hwy.shipyard.dataobject.User;
import com.hwy.shipyard.dataobject.UserQuery;

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
        return userService.addUser(user);
    }

    //删除用户
    @GetMapping("del")
    public Object delUser(String userId){
        return userService.delUser(userId);
    }

    //更新用户,更新所有字段
    @PostMapping("update")
    public Object updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    /**
     * 登录接口
     * @param userQuery
     * @param request
     * @param response
     * @return
     */
    @PostMapping("login")
    public JsonData login(@RequestBody UserQuery userQuery, HttpServletRequest request, HttpServletResponse response){

        Subject subject = SecurityUtils.getSubject();
        userQuery.setUserPassword(new SimpleHash("md5",userQuery.getUserPassword(),"fkn",2).toString());
        Map<String,Object> info = new HashMap<>();
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userQuery.getUserId(), userQuery.getUserPassword());
            subject.login(usernamePasswordToken);
            info.put("msg","登录成功");
            info.put("session_id", subject.getSession().getId());
            return JsonData.buildSuccess(info);
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("账号或者密码错误");
        }
    }

    @GetMapping("admin")
    public Object admin(){
        return JsonData.buildSuccess();
    }


}
