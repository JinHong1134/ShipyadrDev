package com.hwy.shipyard.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwy.shipyard.dataobject.Purchase;
import com.hwy.shipyard.service.PurchaseService;
import com.hwy.shipyard.enums.PurchaseStateEnum;
import com.hwy.shipyard.mapper.PurchaseMapper;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: shipyard
 * @author: huangwenyu
 * @create: 2019-08-24
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public int createPurchase(Purchase purchase) {
        int result = purchaseMapper.insertByObject(purchase);
        return result;
    }

    @Override
    public Purchase findPurchaseById(String purchaseId) {
        Purchase purchase = purchaseMapper.findById(purchaseId);
        return purchase;
    }

    @Override
    public Page<Purchase> findByTime(Date startTime, Date endTime, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<Purchase> findByName(String operatorName, int pageNum, int pageSize) {
        Page<Purchase> purchasePage = PageHelper.startPage(pageNum, pageSize);
        List<Purchase> purchaseList = purchaseMapper.findByOperator(operatorName);
        return purchasePage;
    }

    @Override
    public int deleteById(String purchaseId) {
        return purchaseMapper.deleteById(purchaseId);
    }


   @Override
    public PageInfo<Purchase> findByState(int state, int pageNum, int pageSize) {
        if (state == PurchaseStateEnum.All.getCode()) {
            PageHelper.startPage(pageNum, pageSize);
            List<Purchase> purchaseList = purchaseMapper.findAll();
            PageInfo<Purchase> purchasePageInfo = new PageInfo<Purchase>(purchaseList);
            return purchasePageInfo;
        } else if (state == PurchaseStateEnum.UNCHECK.getCode()) {
            PageHelper.startPage(pageNum, pageSize);
            List<Purchase> purchaseList = purchaseMapper.findByState(PurchaseStateEnum.UNCHECK.getCode());
            PageInfo<Purchase> purchasePageInfo = new PageInfo<Purchase>(purchaseList);
            return purchasePageInfo;
        } else if (state == PurchaseStateEnum.APPROVED.getCode()) {
            PageHelper.startPage(pageNum, pageSize);
            List<Purchase> purchaseList = purchaseMapper.findByState(PurchaseStateEnum.APPROVED.getCode());
            PageInfo<Purchase> purchasePageInfo = new PageInfo<Purchase>(purchaseList);
            return purchasePageInfo;
        } else if (state == PurchaseStateEnum.NOT_APPROVED.getCode()) {
            PageHelper.startPage(pageNum, pageSize);
            List<Purchase> purchaseList = purchaseMapper.findByState(PurchaseStateEnum.NOT_APPROVED.getCode());
            PageInfo<Purchase> purchasePageInfo = new PageInfo<Purchase>(purchaseList);
            return purchasePageInfo;
        } else {
            return null;
        }
    }


    @Override
    public int updateState(String purchaseId, int purchaseState) {
        return purchaseMapper.updateById(purchaseId, purchaseState);
    }

    @Override
    public Object getStateNum() {
        try{
            return JsonData.buildSuccess(purchaseMapper.getStateNum());
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("查找失败");
        }
    }


}

