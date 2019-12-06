package com.hwy.shipyard.converter;

import com.hwy.shipyard.dataobject.Purchase;
import com.hwy.shipyard.form.PurchaseForm;

/**
 * @program: shipyard
 * @author: huangwenyu
 * @create: 2019-08-24
 */

public class PurchaseFormToPurchaseConverter {

    public static Purchase convert(PurchaseForm purchaseForm) {

        Purchase purchase = new Purchase();

        purchase.setDepartment(purchaseForm.getDepartment());
        purchase.setOperatorName(purchaseForm.getName());
        purchase.setNote(purchaseForm.getNote());
        purchase.setPurchasePlan(purchaseForm.getPlan());
        purchase.setPurchaseTitle(purchaseForm.getTitle());
        purchase.setWarehouse(purchaseForm.getWarehouse());

        return purchase;
    }
}
