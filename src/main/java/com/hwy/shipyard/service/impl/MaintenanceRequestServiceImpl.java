package com.hwy.shipyard.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwy.shipyard.dataobject.MaintenanceRequest;
import com.hwy.shipyard.enums.RequestStateEnum;
import com.hwy.shipyard.mapper.MaintenanceRequestMapper;
import com.hwy.shipyard.service.MaintenanceRequestService;
import com.hwy.shipyard.utils.EncryptUtils;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaintenanceRequestServiceImpl implements MaintenanceRequestService {
    @Autowired
    private MaintenanceRequestMapper mapper;

    @Override
    public Object getAll(Integer pageNum, Integer pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<MaintenanceRequest> maintenanceRequests = mapper.getAll();
            PageInfo<MaintenanceRequest> maintenanceRequestPageInfo = new PageInfo<>(maintenanceRequests);
            return JsonData.buildSuccess(maintenanceRequestPageInfo,"查询全部成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("查询全部失败");
        }
    }

    @Override
    public Object findById(String requestId) {
        try {
            MaintenanceRequest maintenanceRequest = mapper.getById(requestId);
            List<MaintenanceRequest> list = new ArrayList<>();
            list.add(maintenanceRequest);
            return JsonData.buildSuccess(list,"查询成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("查询失败");
        }
    }

    @Override
    public void add(MaintenanceRequest maintenanceRequest) {
        mapper.add(maintenanceRequest);
        //1.获取前一条记录的序号
        int preSortId = maintenanceRequest.getSortId()-1;

        //2.判断是否为第一条记录
        if (preSortId!=0){
            //3.创建前一条记录的对象
            MaintenanceRequest preRequest = mapper.getBySId(preSortId);
            //4.获取其校验位的值
            String preCheck = preRequest.getRequestCheck();
            //5.创建当前纪录的对象
            MaintenanceRequest nowRequest = mapper.getBySId(preSortId+1);
            //5.5.将preCheck设置进去
            nowRequest.setRequestPre(preCheck);
            //6.加密数据
            String check = EncryptUtils.saltEncrypt(nowRequest.toString(),"fkn");
            //7.更新当前记录
            mapper.update(preCheck,check,preSortId+1);
        }else {
            //4.前一条记录的加密位为空
            //5.创建当前纪录的对象
            MaintenanceRequest nowRequest = mapper.getBySId(preSortId+1);
            //6.加密数据
            String check = EncryptUtils.saltEncrypt(nowRequest.toString(),"fkn");
            //7.更新当前记录
            mapper.update(null,check,preSortId+1);
        }
    }

    @Override
    public Object check() {
        int i = 2;//从2号开始
        int count = mapper.getCount()+i-1;

        while (true) {
            if (i==count-1){
                return JsonData.buildSuccess(null,2);
            }
            MaintenanceRequest maintenanceRequest = mapper.getBySId(i);

            String s1 = maintenanceRequest.getRequestCheck();

            String s2 = EncryptUtils.saltEncrypt(maintenanceRequest.toString(), "fkn");

            if (s1.equals(s2)) {
                i++;
            } else {
                return JsonData.buildSuccess(maintenanceRequest, "编号为" + maintenanceRequest.getRequestId() + "的记录与预期不符");
            }

        }

    }

    @Override
    public Object updatePass(String requestId) {

        MaintenanceRequest maintenanceRequest = mapper.getById(requestId);

        maintenanceRequest.setRequestState(RequestStateEnum.CHECKED1.getCode());
        try {
            mapper.updateState(requestId, maintenanceRequest.getRequestState());
            return JsonData.buildSuccess(null,"工单状态已更新");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("工单状态更新失败");
        }
    }

    @Override
    public Object updateNotPass(String requestId) {
        MaintenanceRequest maintenanceRequest = mapper.getById(requestId);

        maintenanceRequest.setRequestState(RequestStateEnum.CHECKED2.getCode());
        try {
            mapper.updateState(requestId, maintenanceRequest.getRequestState());
            return JsonData.buildSuccess(null,"工单状态已更新");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("工单状态更新失败");
        }
    }
}
