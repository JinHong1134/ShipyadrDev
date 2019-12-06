package com.hwy.shipyard.controller;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.hwy.shipyard.service.ApplicationDetailService;
import com.hwy.shipyard.service.ApplicationService;
import com.hwy.shipyard.dataobject.Application;
import com.hwy.shipyard.dataobject.ApplicationDetail;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application/detail")
public class ApplicationDetailController {

    @Autowired
    private ApplicationDetailService service;

    @Autowired
    private ApplicationService applicationService;

    //增加明细单，一张申请单对应多张明细单
    @PostMapping("/add")
    public Object add(@RequestBody String jsonList)  {
        Gson gson = new Gson();
        //将json字符串转为jsonObject对象
        JsonObject jsonObject = new JsonParser().parse(jsonList).getAsJsonObject();
        JsonObject applicationObject = jsonObject.getAsJsonObject("application");

        //拿出list集合
        JsonArray list = jsonObject.getAsJsonArray("list");

        //转换申请单类型
        Application application = gson.fromJson(applicationObject,new TypeToken<Application>(){}.getType());
        //System.out.println(application);
        applicationService.add(application);

        List<ApplicationDetail> applicationDetailList = gson.fromJson(list, new TypeToken<List<ApplicationDetail>>(){}.getType());
        //System.out.println(applicationDetailList);
        try {
            //循环写入数据库
            for (ApplicationDetail applicationDetail:applicationDetailList){
                //System.out.println(applicationDetail);
                service.add(applicationDetail);
            }
            return JsonData.buildSuccess(null,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("添加失败");
        }

    }


    //通过申请单id查找对应的所有的明细单
    @GetMapping("/id")
    public Object findById(String applicationId){
        return service.findById(applicationId);
    }


}
