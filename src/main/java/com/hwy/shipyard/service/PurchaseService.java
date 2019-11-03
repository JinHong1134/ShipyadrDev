package com.hwy.shipyard.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.hwy.shipyard.dataobject.Purchase;
import org.springframework.stereotype.Service;


import java.util.Date;

/**
 * @program: shipyard
 * @author: huangwenyu
 * @create: 2019-08-24
 */
public interface PurchaseService {

    /**
     * 创建订单
     * @param purchase
     * @return
     */
    int createPurchase(Purchase purchase);

    /**
     * 通过id查找订单
     * @param purchaseId
     * @return
     */
    Purchase findPurchaseById(String purchaseId);

    /**
     * 通过时间查找订单
     * @param startTime
     * @param endTime
     * @return
     */
    Page<Purchase> findByTime(Date startTime, Date endTime, int pageNum, int pageSize);

    /**
     * 通过业务员姓名查询订单
     * @param operatorName
     * @return
     */
    Page<Purchase> findByName(String operatorName, int pageNum, int pageSize);

    /**
     * 删除订单
     * @param purchaseId
     * @return
     */
    int deleteById(String purchaseId);

    /**
     * 通过状态查询订单，0是为审核，1是审核过，3是全部订单
     * @param state 状态
     * @param pageNum 第几页
     * @param pageSize 页面大小
     * @return 查询的页面
     */
    PageInfo<Purchase> findByState(int state, int pageNum, int pageSize);

    /**
     * 更新订单状态
     * @param purchaseId
     * @param purchaseState
     * @return
     */
    int updateState(String purchaseId, int purchaseState);
}
