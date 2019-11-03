package com.hwy.shipyard.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @program: shipyard
 * @author: huangwenyu
 * @create: 2019-08-24
 */
@Data
public class PurchaseForm {

    @NotEmpty(message = "申请单标题必填")
    private String title;

    @NotEmpty(message = "申请人姓名必填")
    private String name;

    @NotEmpty(message = "申请部门必填")
    private String department;

    @NotEmpty(message = "申请仓库必填")
    private String warehouse;

    private String note;

    @NotEmpty(message = "申请计划必填")
    private String plan;

}
