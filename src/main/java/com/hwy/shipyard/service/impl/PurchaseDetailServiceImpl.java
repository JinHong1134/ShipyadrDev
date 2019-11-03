package com.hwy.shipyard.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwy.shipyard.dataobject.PurchaseDetail;
import com.hwy.shipyard.mapper.PurchaseDetailMapper;
import com.hwy.shipyard.service.PurchaseDetailService;
import com.hwy.shipyard.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: shipyard
 * @author: huangwenyu
 * @create: 2019-08-27
 */
@Service
public class PurchaseDetailServiceImpl implements PurchaseDetailService {

    @Autowired
    private PurchaseDetailMapper purchaseDetailMapper;

    @Override
    public int insertByObject(PurchaseDetail purchaseDetail) {

        //找到最后插入的一条记录
        PurchaseDetail purchaseDetailLast = purchaseDetailMapper.findLast();
        String preHash = null;
        //如果数据库不为空，就不是第一条插入
        if (purchaseDetailLast != null) {
           preHash = purchaseDetailLast.getPurchaseDetailCheckBits();
           String preId = purchaseDetailLast.getPurchaseDetailId();
           purchaseDetail.setPurchaseDetailPre(preId);
        }
        String hash = HashUtils.genHash(purchaseDetail, preHash);
        purchaseDetail.setPurchaseDetailCheckBits(hash);
        return purchaseDetailMapper.insertByObject(purchaseDetail);
    }

    @Override
    public PageInfo<PurchaseDetail> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PurchaseDetail> purchaseDetailList = purchaseDetailMapper.findAll();
        PageInfo<PurchaseDetail> purchaseDetailPageInfo = new PageInfo<>(purchaseDetailList);
        return purchaseDetailPageInfo;
    }

    @Override
    public String check() {
        PurchaseDetail purchaseDetail = purchaseDetailMapper.findLast();
        while (purchaseDetail != null) {
            String preId = purchaseDetail.getPurchaseDetailPre();
            PurchaseDetail purchaseDetailPre = purchaseDetailMapper.findById(preId);
            //空指针异常,数据库只有一条记录的情况
            if (preId == null) {
                String checkHash = HashUtils.genHash(purchaseDetail, null);
                if (!checkHash.equals(purchaseDetail.getPurchaseDetailCheckBits())) {
                    return purchaseDetail.getPurchaseDetailId();
                }
                return null;
            }
            //获得前一条的哈希
            String preHash = purchaseDetailPre.getPurchaseDetailCheckBits();
            //计算当前记录的哈希
            String checkHash = HashUtils.genHash(purchaseDetail, preHash);
            if (!checkHash.equals(purchaseDetail.getPurchaseDetailCheckBits())) {
                return purchaseDetail.getPurchaseDetailId();
            }
            //指向前一条记录
            purchaseDetail = purchaseDetailPre;
        }
        return null;
    }

}
