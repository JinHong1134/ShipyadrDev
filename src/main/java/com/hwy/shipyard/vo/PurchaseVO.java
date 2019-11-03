package com.hwy.shipyard.vo;

import lombok.Data;

import java.util.Date;

/**
 * @program: shipyard
 * @author: huangwenyu
 * @create: 2019-08-26
 */
@Data
public class PurchaseVO {

    private String productId;

    private String productName;

    private String productUnit;

    private String productSpecification;

    private int productQuantity;

    private String time;
}
