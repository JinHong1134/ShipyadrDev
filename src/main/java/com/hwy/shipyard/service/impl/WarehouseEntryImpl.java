package com.hwy.shipyard.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwy.shipyard.service.WarehouseEntryService;
import com.hwy.shipyard.dataobject.WarehouseEntry;
import com.hwy.shipyard.dataobject.WarehouseEntryDetail;
import com.hwy.shipyard.mapper.WarehouseEntryMapper;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.hwy.shipyard.utils.EncryptUtils.saltEncrypt;

@Service
public class WarehouseEntryImpl implements WarehouseEntryService {

    @Autowired
    WarehouseEntryMapper warehouseEntryMapper;


    @Override
    public Object addWarehouseEntry(WarehouseEntry warehouseEntry) {
        /*String preHash;
        try {
            //加密处理
            try {
                WarehouseEntry warehouseEntry1 = warehouseEntryMapper.getNewWarehouseEntry();
                preHash = warehouseEntry1.getWarehouseEntryCheckBits();
            } catch (Exception e) {
                preHash = "JinHong";
            }
            String str = warehouseEntry.toString() + preHash;
            String checkBits = saltEncrypt(str, "fkn");
            warehouseEntry.setWarehouseEntryCheckBits(checkBits);
            Map map = SignatureRsa.jdkRSA(checkBits);
            boolean bool= (boolean) map.get("bool");
            String signature = (String) map.get("signature");
            warehouseEntry.setSignature(signature);
            //System.out.println(warehouseEntry.getSignature()+warehouseEntry.getWarehouseEntryCheckBits());
            if(bool==true){
                warehouseEntryMapper.addEntry(warehouseEntry);
                return JsonData.buildSuccess();
            }else return JsonData.buildError();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("添加失败");
        }*/
        try {
            warehouseEntryMapper.addEntry(warehouseEntry);
            String preHash;
            if (warehouseEntry.getId() == 1 ){
                preHash = "fkn";
            }else {
                preHash = warehouseEntryMapper.getEntry(warehouseEntry.getId() - 1).getWarehouseEntryCheckBits();
            }
            String str = warehouseEntry.toString() + preHash;
            String checkBits = saltEncrypt(str, "fkn");
            warehouseEntryMapper.updateEntry(checkBits,warehouseEntry.getId());
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    @Override
    public Object addWarehouseEntryDetail(WarehouseEntryDetail warehouseEntryDetail) {
        //加密字符串
        try {
            warehouseEntryMapper.addEntryDetail(warehouseEntryDetail);
            String preHash;
            if(warehouseEntryDetail.getWarehouseEntryDetailId() ==1) {
                preHash = "preHash";
            }else {
                preHash = warehouseEntryMapper.getWarehouseEntryDetail(warehouseEntryDetail.getWarehouseEntryDetailId() - 1).getWarehouseEntryDetailCheckBits();
            }
            String str = warehouseEntryDetail.toString() + preHash;
            String checkBits = saltEncrypt(str, "fkn");
            warehouseEntryMapper.updateDetailCheck(checkBits, warehouseEntryDetail.getWarehouseEntryDetailId());
            return JsonData.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    @Override
    public Object getWarehouseEntry(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<WarehouseEntry> allEntry = warehouseEntryMapper.getAll();
            PageInfo<WarehouseEntry> userPageInfo = new PageInfo<>(allEntry);
            return JsonData.buildSuccess(userPageInfo, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("失败", -1);
        }
    }

    //获取入库单的详细信息，考虑到信息量可能较大，做分页处理
    @Override
    public Object getWarehouseEntryDetail(String warehouseEntryId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<WarehouseEntryDetail> allEntryDetail = warehouseEntryMapper.getDetail(warehouseEntryId);
            PageInfo<WarehouseEntryDetail> userPageInfo = new PageInfo<>(allEntryDetail);
            return JsonData.buildSuccess(userPageInfo, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("失败", -1);
        }
    }

    @Override
    public Object getWarehouseEntryById(String warehouseEntryId) {
        try {
            List<WarehouseEntry> warehouseEntries = new ArrayList<>();
            warehouseEntries.add(warehouseEntryMapper.getEntryById(warehouseEntryId));
            return JsonData.buildSuccess(warehouseEntries);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError();
        }
    }

    //校验入库单，正确返回0，发生篡改返回第一条被篡改的记录
    @Override
    public Object checkEntry() {
        WarehouseEntry warehouseEntryLast = warehouseEntryMapper.getLast();
        int last = warehouseEntryLast.getId();
        int count = warehouseEntryMapper.getEntryCount();
        int i =last;
        while(true){
            if (last-i == count -1) return JsonData.buildSuccess(null,2);
            try {
                WarehouseEntry warehouseEntry = warehouseEntryMapper.getEntry(i);
                String checkBits = warehouseEntry.getWarehouseEntryCheckBits();
                WarehouseEntry warehouseEntry1 = warehouseEntryMapper.getEntry(i - 1);
                String pre = warehouseEntry1.getWarehouseEntryCheckBits();
                String check = saltEncrypt(warehouseEntry.toString() + pre, "fkn");
                if (check.equals(checkBits)) {
                    i--;
                } else {
                    return JsonData.buildSuccess(warehouseEntryMapper.getEntry(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return JsonData.buildError();
            }
        }

    }

    @Override
    public Object checkEntryDetail() {
        int last = warehouseEntryMapper.getDetailLast().getWarehouseEntryDetailId();
        int i =last;
        int count = warehouseEntryMapper.getEntryDetailCount();
        while(true){
            if (last-i == count-1){
                return JsonData.buildSuccess(null,2);
            }
            try {
                WarehouseEntryDetail warehouseEntryDetail = warehouseEntryMapper.getWarehouseEntryDetail(i);
                String checkBits = warehouseEntryDetail.getWarehouseEntryDetailCheckBits();
                WarehouseEntryDetail warehouseEntryDetail1 = warehouseEntryMapper.getWarehouseEntryDetail(i - 1);
                String pre = warehouseEntryDetail1.getWarehouseEntryDetailCheckBits();
                String check = saltEncrypt(warehouseEntryDetail.toString() + pre, "fkn");
                if (check.equals(checkBits)) {
                    i--;
                } else {
                    return JsonData.buildSuccess(warehouseEntryMapper.getWarehouseEntryDetail(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return JsonData.buildError();
            }
        }

    }



}
