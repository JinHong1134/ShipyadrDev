package com.hwy.shipyard.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwy.shipyard.dataobject.ApplicationDetail;
import com.hwy.shipyard.mapper.ApplicationDetailMapper;
import com.hwy.shipyard.service.ApplicationDetailService;
import com.hwy.shipyard.utils.EncryptUtils;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationDetailServiceImpl implements ApplicationDetailService {
    @Autowired
    private ApplicationDetailMapper mapper;

    @Override
    public Object getAll(Integer pageNum, Integer pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<ApplicationDetail> allAppDetail = mapper.getAll();
            PageInfo<ApplicationDetail> applicationDetailPageInfo = new PageInfo<>(allAppDetail);
            return JsonData.buildSuccess(applicationDetailPageInfo,"查询全部成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("查询全部失败");
        }
    }

    @Override
    public void add(ApplicationDetail detail) {
            mapper.add(detail);
        //1.获取前一条记录的序号
        int preSortId = detail.getSortId()-1;

        //2.判断是否为第一条记录
        if (preSortId!=0){
            //3.创建前一条记录的对象
            ApplicationDetail preApplicationDetail = mapper.getBySId(preSortId);
            //4.获取其校验位的值
            String preCheck = preApplicationDetail.getApplicationDetailCheck();
            //5.创建当前纪录的对象
            ApplicationDetail nowApplicationDetail = mapper.getBySId(preSortId+1);
            //5.5.将preCheck设置进去
            nowApplicationDetail.setApplicationDetailPre(preCheck);
            //6.加密数据
            String check = EncryptUtils.saltEncrypt(nowApplicationDetail.toString(),"fkn");
            //7.更新当前记录
            mapper.update(preCheck,check,preSortId+1);
        }else {
            //4.前一条记录的加密位为空
            //5.创建当前纪录的对象
            ApplicationDetail nowApplicationDetail = mapper.getBySId(preSortId+1);
            //6.加密数据
            String check = EncryptUtils.saltEncrypt(nowApplicationDetail.toString(),"fkn");
            //7.更新当前记录
            mapper.update(null,check,preSortId+1);
        }



    }

    @Override
    public Object findById(String applicationId) {
        try {
            List<ApplicationDetail> details = mapper.getById(applicationId);
            return JsonData.buildSuccess(details,"查询成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("查询失败");
        }
    }

    @Override
    public Object check() {
        int i = 4;//从23号开始
        int count = mapper.getCount()+i-1;

        while (true) {
            if (i==count-1){
                return JsonData.buildSuccess(null,2);
            }
            ApplicationDetail applicationDetail = mapper.getBySId(i);

            String s1 = applicationDetail.getApplicationDetailCheck();

            String s2 = EncryptUtils.saltEncrypt(applicationDetail.toString(), "fkn");

            if (s1.equals(s2)) {
                i++;
            } else {
                return JsonData.buildSuccess(applicationDetail, "编号为" + applicationDetail.getApplicationDetailId() + "的记录与预期不符，对应的申请单id为"+applicationDetail.getApplicationId());
            }

        }
    }


}
