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
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("pub")
public class PublicController {

    @Autowired
    UserService userService;

    @RequestMapping("need_login")
    public JsonData needLogin(){

        return JsonData.buildSuccess("温馨提示：请使用对应的账号登录",-2);

    }


    @RequestMapping("not_permit")
    public JsonData notPermit(){
        return JsonData.buildSuccess("温馨提示：拒绝访问，没权限",-3);
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

        User user = userService.getUserById(userQuery.getUserId());
        Map<String,Object> info = new HashMap<>();
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userQuery.getUserId(), userQuery.getUserPassword());

            subject.login(usernamePasswordToken);

            info.put("msg","登录成功");
            info.put("session_id", subject.getSession().getId());
            info.put("user",user.getUserName());
            info.put("userPosition",user.getUserPosition());
            request.getSession().setAttribute("userId",user.getUserId());
            return JsonData.buildSuccess(info);

        }catch (Exception e){
            e.printStackTrace();

            return JsonData.buildError("账号或者密码错误");

        }
    }


    @GetMapping("/info")
    public JsonData info(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        System.out.println(userId);
        if(userId == null) return JsonData.buildError("请登陆账号");
        User user = userService.getUserById(userId);
        return JsonData.buildSuccess(user);
    }



}
