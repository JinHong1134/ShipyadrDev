package com.hwy.shipyard.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: shipyard
 * @author: huangwenyu
 * @create: 2019-08-29
 */
//TODO
@Data
public class PurchaseDetailForm {

    @NotEmpty
    private String purchaseId;

    @NotEmpty
    private String productId;

    @NotEmpty
    private String productName;

    @DecimalMin(value = "0")
    private BigDecimal productQuantity;

    @NotEmpty
    private String productUnit;

    @NotEmpty
    private String factoryName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reserveTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deliveryTime;

    @DecimalMin(value = "0")
    private BigDecimal productPrice;

    @DecimalMin(value = "0")
    private BigDecimal totalPrice;

    @NotEmpty
    private String warehouseId;

    @NotEmpty
    private String warehouseName;

    @NotEmpty
    private String purchaser;

}
