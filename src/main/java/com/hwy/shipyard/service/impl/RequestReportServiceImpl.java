package com.hwy.shipyard.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwy.shipyard.dataobject.RequestReport;
import com.hwy.shipyard.dataobject.ScheduleReport;
import com.hwy.shipyard.mapper.RequestReportMapper;
import com.hwy.shipyard.mapper.ScheduleReportMapper;
import com.hwy.shipyard.service.RequestReportService;
import com.hwy.shipyard.utils.EncryptUtils;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestReportServiceImpl implements RequestReportService {
    @Autowired
    private RequestReportMapper mapper;

    @Override
    public Object getAll(Integer pageNum, Integer pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<RequestReport> requestReports = mapper.getAll();
            PageInfo<RequestReport> requestReportPageInfo = new PageInfo<>(requestReports);
            return JsonData.buildSuccess(requestReportPageInfo,"查询全部成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("查询全部失败");
        }
    }

    @Override
    public Object findById(String requestId) {
        try {
            RequestReport requestReport = mapper.getById(requestId);
            List<RequestReport> list = new ArrayList<>();
            list.add(requestReport);
            return JsonData.buildSuccess(list,"查询成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("查询失败");
        }
    }

    @Override
    public void add(RequestReport requestReport) {
        mapper.add(requestReport);
        //1.获取前一条记录的序号
        int preSortId = requestReport.getSortId()-1;

        //2.判断是否为第一条记录
        if (preSortId!=0){
            //3.创建前一条记录的对象
            RequestReport preRequestReport = mapper.getBySId(preSortId);
            //4.获取其校验位的值
            String preCheck = preRequestReport.getReportCheck();
            //5.创建当前纪录的对象
            RequestReport nowRequestReport = mapper.getBySId(preSortId+1);
            //5.5.将preCheck设置进去
            nowRequestReport.setReportPre(preCheck);
            //6.加密数据
            String check = EncryptUtils.saltEncrypt(nowRequestReport.toString(),"fkn");
            //7.更新当前记录
            mapper.update(preCheck,check,preSortId+1);
        }else {
            //4.前一条记录的加密位为空
            //5.创建当前纪录的对象
            RequestReport nowRequestReport = mapper.getBySId(preSortId+1);
            //6.加密数据
            String check = EncryptUtils.saltEncrypt(nowRequestReport.toString(),"fkn");
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
            RequestReport requestReport = mapper.getBySId(i);

            String s1 = requestReport.getReportCheck();

            String s2 = EncryptUtils.saltEncrypt(requestReport.toString(), "fkn");

            if (s1.equals(s2)) {
                i++;
            } else {
                return JsonData.buildSuccess(requestReport, "编号为" + requestReport.getRequestId() + "的记录与预期不符");
            }

        }
    }
}
