package com.hwy.shipyard.controller;





import com.hwy.shipyard.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 校验功能
 */
@RequestMapping("check")
@RestController
public class CheckController {
    @Autowired
    private WarehouseEntryService warehouseEntryService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationDetailService applicationDetailService;

    @Autowired
    private RequestReportService requestReportService;

    @Autowired
    private MaintenanceRequestService maintenanceRequestService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleReportService scheduleReportService;

    @Autowired
    private WarehouseDeliverService warehouseDeliverService;

    @Autowired
    private AllocationService allocationService;

    //校验入库单
    @GetMapping("/warehouse/entry")
    public Object checkEntry(){
        return warehouseEntryService.checkEntry();
    }

    //校验入库明细单

    @GetMapping("/warehouse/entry/detail")
    public  Object checkEntryDetail(){
        return warehouseEntryService.checkEntryDetail();
    }

    //校验出库单
    @GetMapping("/warehouse/deliver")
    public Object checkDeliver(){return warehouseDeliverService.checkDeliver();}

    //校验出库明细单
    @GetMapping("/warehouse/deliver/detail")
    public Object checkDeliverDetail(){return warehouseDeliverService.checkDetail();}


    /**
     * @author ssp
     * @return
     */
    //校验申请单
    @GetMapping("/application")
    public Object checkApplication() {
        return applicationService.check();
    }


    //校验申请明细单
    @GetMapping("/application/detail")
    public Object checkApplicationDetail() {
        return applicationDetailService.check();
    }


    //校验维修申请单
    @GetMapping("/maintenance/request")
    public Object checkMaintenanceRequest(){
        return maintenanceRequestService.check();
    }



    //校验申请完成报告单
    @GetMapping("/request/report")
    public Object checkRequestReport(){
        return requestReportService.check();
    }


    //校验维修计划单
    @GetMapping("/schedule")
    public Object checkSchedule(){
        return scheduleService.check();
    }


    //校验计划完成报告单
    @GetMapping("/schedule/report")
    public Object checkScheduleReport() {
        return scheduleReportService.check();
    }

    //校验调拨单
    @GetMapping("/warehouse/allocation")
    public Object checkAllocation(){
        return allocationService.checkAllocation();
    }

    //校验调拨明细单
    @GetMapping("/warehouse/allocation/detail")
    public Object checkAllocationDetail(){
        return allocationService.checkAllocationDetail();
    }
}
