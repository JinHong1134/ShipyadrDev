package com.hwy.shipyard.service.impl;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwy.shipyard.dataobject.Allocation;
import com.hwy.shipyard.dataobject.User;
import com.hwy.shipyard.dataobject.WarehouseDeliver;
import com.hwy.shipyard.dataobject.WarehouseDeliverDetail;
import com.hwy.shipyard.mapper.WarehouseDeliverMapper;
import com.hwy.shipyard.service.WarehouseDeliverService;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.hwy.shipyard.utils.EncryptUtils.saltEncrypt;

@Service
public class WarehouseDeliverImpl implements WarehouseDeliverService {


    @Autowired
    WarehouseDeliverMapper warehouseDeliverMapper;

    @Override
    public Object addDeliver(WarehouseDeliver warehouseDeliver) {
        try {
            warehouseDeliverMapper.addDeliver(warehouseDeliver);
            String preHash;
           if (warehouseDeliver.getId() == 1 ){
               preHash = "fkn";
           }else {
               preHash = warehouseDeliverMapper.getDeliver(warehouseDeliver.getId() - 1).getWarehouseDeliverCheckBits();
           }
            String str = warehouseDeliver.toString() + preHash;
            String checkBits = saltEncrypt(str, "fkn");
            warehouseDeliverMapper.updateCheck(warehouseDeliver.getId(), checkBits);
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }


    @Override
    public Object addDetail(WarehouseDeliverDetail warehouseDeliverDetail) {
        try {
            warehouseDeliverMapper.addDeliverDetail(warehouseDeliverDetail);
            String preHash;
            if (warehouseDeliverDetail.getId() ==1){
                preHash = "fkn";
            }else {
                preHash = warehouseDeliverMapper.getDetailById(warehouseDeliverDetail.getId() - 1).getWarehouseDeliverDetailCheckBits();
            }
            String str = warehouseDeliverDetail.toString() + preHash;
            String checkBits = saltEncrypt(str, "fkn");
            warehouseDeliverMapper.updateDetailCheck(warehouseDeliverDetail.getId(), checkBits);
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }

    }

    @Override
    public Object getAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<WarehouseDeliver> warehouseDelivers = warehouseDeliverMapper.getAll();
            PageInfo<WarehouseDeliver> warehouseDeliverPageInfo = new PageInfo<>(warehouseDelivers);
            return JsonData.buildSuccess(warehouseDeliverPageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    @Override
    public Object getDetail(String warehouseDeliverId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<WarehouseDeliverDetail> warehouseDeliverDetailList = warehouseDeliverMapper.getDeliverDetail(warehouseDeliverId);
            PageInfo<WarehouseDeliverDetail> warehouseDeliverDetailPageInfo = new PageInfo<>(warehouseDeliverDetailList);
            return JsonData.buildSuccess(warehouseDeliverDetailPageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    //校验出库单
    @Override
    public Object checkDeliver() {
        int i = 12;
        int count = warehouseDeliverMapper.getDeliverCount() + i;
        while (true) {
            if (i == count - 1) {
                return JsonData.buildSuccess(null, 2);
            }
            WarehouseDeliver warehouseDeliver = warehouseDeliverMapper.getDeliver(i);
            String checkBits = warehouseDeliver.getWarehouseDeliverCheckBits();
            WarehouseDeliver warehouseDeliver1 = warehouseDeliverMapper.getDeliver(i - 1);
            String pre = warehouseDeliver1.getWarehouseDeliverCheckBits();
            String check = saltEncrypt(warehouseDeliver.toString() + pre, "fkn");
            if (checkBits.equals(check)) {
                i++;
            } else {
                return JsonData.buildSuccess(warehouseDeliverMapper.getDeliver(i));
            }
        }
    }

    //校验出库明细单
    @Override
    public Object checkDetail() {
        int i = 12;
        int count = warehouseDeliverMapper.getDeliverDetailCount() + i;
        while (true) {
            if (i == count - 1) {
                return JsonData.buildSuccess(null, 2);
            }
            WarehouseDeliverDetail warehouseDeliverDetail = warehouseDeliverMapper.getDetailById(i);
            String checkBits = warehouseDeliverDetail.getWarehouseDeliverDetailCheckBits();
            WarehouseDeliverDetail warehouseDeliverDetail1 = warehouseDeliverMapper.getDetailById(i - 1);
            String pre = warehouseDeliverDetail1.getWarehouseDeliverDetailCheckBits();
            String check = saltEncrypt(warehouseDeliverDetail.toString() + pre, "fkn");
            if (checkBits.equals(check)) {
                i++;
            } else {
                return JsonData.buildSuccess(warehouseDeliverMapper.getDetailById(i));
            }
        }
    }

    //根据出库单id查询出库单
    @Override
    public Object getDeliverById (String warehouseDeliverId){
        try {
            List<WarehouseDeliver> warehouseDeliverArrayList = new ArrayList<>();
            warehouseDeliverArrayList.add(warehouseDeliverMapper.getDeliverById(warehouseDeliverId));
            return JsonData.buildSuccess(warehouseDeliverArrayList);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }
}