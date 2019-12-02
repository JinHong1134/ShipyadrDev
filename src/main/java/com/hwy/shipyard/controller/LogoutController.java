package com.hwy.shipyard.controller;


import com.hwy.shipyard.utils.JsonData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * logout, 映射shiro自带的过滤器
 */
@RestController
public class LogoutController {


    @GetMapping("/logout")
    public JsonData findMyPlayRecord(){

        Subject subject = SecurityUtils.getSubject();
        if(subject.getPrincipals() != null ){

        }
        try {
            SecurityUtils.getSubject().logout();
            return JsonData.buildSuccess("logout成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("logout失败",-1);
        }
    }

}
