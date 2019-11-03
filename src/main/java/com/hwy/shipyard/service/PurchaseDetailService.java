package com.hwy.shipyard.service;

import com.github.pagehelper.PageInfo;
import com.hwy.shipyard.dataobject.PurchaseDetail;
import org.springframework.stereotype.Service;

@Service
public interface PurchaseDetailService {

    /**
     * 新增详情
     * @param purchaseDetail 插入的详情
     * @return
     */
    int insertByObject(PurchaseDetail purchaseDetail);

    /**
     * 返回查询结果的分页
     * @param pageNum 页码
     * @param pageSize 页面大小
     * @return
     */
    PageInfo<PurchaseDetail> findAll(int pageNum, int pageSize);

    /**
     * 校验是否被篡改
     * @return 错误id
     */
    String check();


}
