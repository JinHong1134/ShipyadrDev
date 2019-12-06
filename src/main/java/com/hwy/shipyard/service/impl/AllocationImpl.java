package com.hwy.shipyard.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwy.shipyard.service.AllocationService;
import com.hwy.shipyard.dataobject.Allocation;
import com.hwy.shipyard.dataobject.AllocationDetail;
import com.hwy.shipyard.mapper.AllocationMapper;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

import static com.hwy.shipyard.utils.EncryptUtils.saltEncrypt;

/**
 * @author honghong
 * @version 1.0
 * @date 2019/9/3 9:24
 */

@Service
public class AllocationImpl implements AllocationService {

    @Autowired
    private AllocationMapper allocationMapper;

    @Override
    public Object addAllocation(Allocation allocation) {
        try {

            String preHash;
            allocationMapper.addAllocation(allocation);
            if (allocation.getId() == null){
                preHash = "fkn";
            }else {
                preHash = allocationMapper.getAllocationById(allocation.getId()-1).getAllocationCheckBits();
            }
            String str = allocation.toString()+preHash;
            String checkBits = saltEncrypt(str,"fkn");
            allocationMapper.updateCheck(checkBits,allocation.getId());
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    @Override
    public Object addAllocationDetail(AllocationDetail allocationDetail) {
        try{
            String preCheck;
            if (allocationDetail.getId() == 1){
                preCheck = "fkn";
            }else {
                allocationMapper.addAllocationDetail(allocationDetail);
            }
            preCheck = allocationMapper.getAllocationDetailById(allocationDetail.getId()-1).getAllocationDetailCheckBits();
            String str = allocationDetail.toString()+preCheck;
            String checkBits = saltEncrypt(str,"fkn");
            allocationMapper.updateDetailCheck(checkBits,allocationDetail.getId());
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    //获取所有调拨单
    @Override
    public Object getAllAllocation(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        try{
            List<Allocation> allocationList = allocationMapper.getAllocationAll();
            PageInfo<Allocation> allocationPageInfo = new PageInfo<>(allocationList);
            return JsonData.buildSuccess(allocationPageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    @Override
    public Object getAllocationById(String allocationId) {
        try{
            List<Allocation> allocationList = new ArrayList<>();
            allocationList.add(allocationMapper.getByAllocationId(allocationId));
            return JsonData.buildSuccess(allocationList);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    @Override
    public Object getAllocationDetail(String allocationId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        try{
            List<AllocationDetail> allocationDetailList = allocationMapper.getDetailByAllocationId(allocationId);
            PageInfo<AllocationDetail> allocationDetailPageInfo = new PageInfo<>(allocationDetailList);
            return JsonData.buildSuccess(allocationDetailPageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    @Override
    public Object updateAllocationCheck(String allocationCheckBits, int id) {
        try{
            allocationMapper.updateCheck(allocationCheckBits,id);
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    @Override
    public Object updateAllocationDetailCheck(String allocationDetailCheckBits, int id) {
        try{
            allocationMapper.updateDetailCheck(allocationDetailCheckBits,id);
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    @Override
    public Object checkAllocation() {
        int last = allocationMapper.getLast().getId();
        int i = last;
        int count = allocationMapper.getAllocationCount();
        while (true){
            if (last - i== count-1){
                return JsonData.buildSuccess(null,2);
            }
            try {
                Allocation allocation = allocationMapper.getAllocationById(i);
                String checkBits = allocation.getAllocationCheckBits();
                Allocation allocation1 = allocationMapper.getAllocationById(i - 1);
                String pre = allocation1.getAllocationCheckBits();
                String check = saltEncrypt(allocation.toString()+pre, "fkn ");
                if (check.equals(checkBits)) {
                    i--;
                } else {
                    return JsonData.buildSuccess(allocationMapper.getAllocationById(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return JsonData.buildError("编号为"+allocationMapper.getAllocationById(i).getAllocationId()+"前一条记录被删除",allocationMapper.getAllocationById(i).getAllocationId(),0);
            }
        }
    }

    @Override
    public Object checkAllocationDetail() {
        int last =allocationMapper.getDetailLast().getId();
        int i = last;
        int count = allocationMapper.getAllocationDetailCount();
        while (true){
            if (last - i==count-1){
                return JsonData.buildSuccess(null,2);
            }
            try {
                AllocationDetail allocationDetail = allocationMapper.getAllocationDetailById(i);
                AllocationDetail allocationDetail1 = allocationMapper.getAllocationDetailById(i - 1);
                String pre = allocationDetail1.getAllocationDetailCheckBits();
                String checkBits = allocationDetail.getAllocationDetailCheckBits();
                String check = saltEncrypt(allocationDetail.toString()+pre, "fkn");
                if (check.equals(checkBits)) {
                    i--;
                } else {
                    return JsonData.buildSuccess(allocationMapper.getAllocationDetailById(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return JsonData.buildError("编号为"+allocationMapper.getAllocationById(i).getAllocationId()+"前一条记录被删除",allocationMapper.getAllocationById(i).getAllocationId(),0);
            }

        }
    }

    @Override
    public Object getStateNum() {
        try{
            return JsonData.buildSuccess(allocationMapper.getAllocation0());
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("查询失败");
        }
    }

    @Override
    public Object updateState(int state, String allocationId) {
        try{
            return JsonData.buildSuccess(allocationMapper.updateState(state,allocationId));
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("更新失败");
        }
    }
}
