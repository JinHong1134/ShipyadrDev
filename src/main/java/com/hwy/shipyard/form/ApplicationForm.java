package com.hwy.shipyard.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ApplicationForm {

    @NotEmpty(message = "申请单编号必填")
    private String applicationId;


    @NotEmpty(message = "船名必填")
    private String shipName;

    @NotEmpty(message = "申请地址必填")
    private String applicationAddress;

    @NotEmpty(message = "申请人必填")
    private String applicationProposer;


    private String applicationRemark;
}
