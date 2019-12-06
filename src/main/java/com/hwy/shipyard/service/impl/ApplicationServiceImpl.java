package com.hwy.shipyard.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwy.shipyard.dataobject.Application;
import com.hwy.shipyard.enums.ApplicationStateEnum;
import com.hwy.shipyard.mapper.ApplicationMapper;
import com.hwy.shipyard.service.ApplicationService;
import com.hwy.shipyard.utils.EncryptUtils;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationMapper mapper;

    @Override
    public Object getAll(Integer pageNum, Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<Application> allApp = mapper.getAll();
            PageInfo<Application> applicationPageInfo = new PageInfo<>(allApp);
            return JsonData.buildSuccess(applicationPageInfo, "查询全部成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("查询全部失败");
        }
    }

    @Override
    public void add(Application application) {
        //1.
        mapper.add(application);
        //1.获取前一条记录的序号
        int preSortId = application.getSortId() - 1;

        //2.判断是否为第一条记录
        if (preSortId != 0) {
            //3.创建前一条记录的对象
            Application preApplication = mapper.getBySId(preSortId);
            //4.获取其校验位的值
            String preCheck = preApplication.getApplicationCheck();
            //5.创建当前纪录的对象
            Application nowApplication = mapper.getBySId(preSortId + 1);
            //5.5.将preCheck设置进去
            nowApplication.setApplicationPre(preCheck);
            //6.加密数据
            String check = EncryptUtils.saltEncrypt(nowApplication.toString(), "fkn");
            //7.更新当前记录
            mapper.update(preCheck, check, preSortId + 1);
        } else {
            //4.前一条记录的加密位为空
            //5.创建当前纪录的对象
            Application nowApplication = mapper.getBySId(preSortId + 1);
            //6.加密数据
            String check = EncryptUtils.saltEncrypt(nowApplication.toString(), "fkn");
            //7.更新当前记录
            mapper.update(null, check, preSortId + 1);
        }
    }

    @Override
    public Object findById(String applicationId) {
        try {
            Application application = mapper.getById(applicationId);
            List<Application> list = new ArrayList<>();
            list.add(application);
            return JsonData.buildSuccess(list, "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("查询失败");
        }
    }

    @Override
    public Object updateState(int applicationState, String applicationId) {
        try {
            int sortId = mapper.getById(applicationId).getSortId();
            mapper.updateState(applicationState, sortId);
            return JsonData.buildSuccess(null, "申请单状态已更新");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("申请单状态更新失败");
        }
    }

    @Override
    public Object check() {
        int last = mapper.getLast().getSortId();
            int i = last;
            int count = mapper.getCount();

            while (true) {
                if (last-i==count-1){
                    return JsonData.buildSuccess(null,2);
                }
                try {
                    Application application = mapper.getBySId(i);

                    String s1 = application.getApplicationCheck();

                    String s2 = EncryptUtils.saltEncrypt(application.toString(), "fkn");

                    if (s1.equals(s2)) {
                        i--;
                    } else {
                        return JsonData.buildSuccess(application, "编号为" + application.getApplicationId() + "的记录与预期不符");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return JsonData.buildError("编号为"+mapper.getBySId(i).getApplicationId()+"前一条记录被删除",mapper.getBySId(i).getApplicationId(),0);

                }
            }
    }

    @Override
    public Object getStateNum() {
        try{
            return JsonData.buildSuccess(mapper.getStateNum());
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("查找失败");
        }
    }

    @Override
    public Object updatePassOne(String applicationId) {
        Application application = mapper.getById(applicationId);
        application.setApplicationState(ApplicationStateEnum.CHECKED1.getCode());
        try{
            mapper.updateState(application.getApplicationState(),application.getSortId());
            return JsonData.buildSuccess(null,"表单状态已更新");
        }catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("表单状态更新失败");
        }
    }

    @Override
    public Object updatePassTwo(String applicationId){
        Application application = mapper.getById(applicationId);

        application.setApplicationState(ApplicationStateEnum.CHECKED2.getCode());
        try{
            mapper.updateState(application.getApplicationState(),application.getSortId());
            return JsonData.buildSuccess(null,"表单状态已更新");
        }catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("表单状态更新失败");
        }
    }

    @Override
    public Object updateNotPass(String applicationId){
        Application application = mapper.getById(applicationId);

        application.setApplicationState(ApplicationStateEnum.CHECKED3.getCode());
        try{
            mapper.updateState(application.getApplicationState(),application.getSortId());
            return JsonData.buildSuccess(null,"表单状态已更新");
        }catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("表单状态更新失败");
        }
    }
}
