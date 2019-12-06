package com.hwy.shipyard.utils;

import com.hwy.shipyard.dataobject.PurchaseDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HashUtilsTest {

    @Test
    public void genHash() {
        PurchaseDetail purchaseDetail = new PurchaseDetail();
        purchaseDetail.setPurchaseDetailId("123456");
        purchaseDetail.setPurchaseDetailId("abcd");
        String result = HashUtils.genHash(purchaseDetail, null);
        System.out.println(result);
    }
}