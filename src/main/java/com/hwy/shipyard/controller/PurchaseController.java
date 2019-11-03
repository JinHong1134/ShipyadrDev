package com.hwy.shipyard.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hwy.shipyard.converter.PurchaseFormToPurchaseConverter;
import com.hwy.shipyard.dataobject.Purchase;
import com.hwy.shipyard.dataobject.PurchaseDetail;
import com.hwy.shipyard.enums.PurchaseStateEnum;
import com.hwy.shipyard.enums.ResultEnum;
import com.hwy.shipyard.exception.PurchaseException;
import com.hwy.shipyard.form.PurchaseDetailForm;
import com.hwy.shipyard.form.PurchaseForm;
import com.hwy.shipyard.service.PurchaseDetailService;
import com.hwy.shipyard.service.impl.PurchaseDetailServiceImpl;
import com.hwy.shipyard.service.impl.PurchaseServiceImpl;
import com.hwy.shipyard.utils.KeyUtils;
import com.hwy.shipyard.utils.ResultVOUtils;
import com.hwy.shipyard.vo.PurchaseVO;
import com.hwy.shipyard.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: shipyard
 * @author: huangwenyu
 * @create: 2019-08-24
 */

@RestController
@RequestMapping("/purchase")
@Slf4j
public class PurchaseController {

    @Autowired
    private PurchaseServiceImpl purchaseService;

    @Autowired
    private PurchaseDetailServiceImpl purchaseDetailService;

    @PostMapping("/apply/create")
    public ResultVO<Map<String, String>> createApply(@Valid PurchaseForm purchaseForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            //TODO 打印日志
            throw new PurchaseException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        String id = KeyUtils.genUniqueKey();
        Purchase purchase = PurchaseFormToPurchaseConverter.convert(purchaseForm);
        purchase.setPurchaseId(id);
        int result = purchaseService.createPurchase(purchase);
        if (result > 0) {
            Map<String, String> map = new HashMap<>();
            map.put("applicationId", id);
            return ResultVOUtils.success(map);
        }
        return ResultVOUtils.error();
    }

    @GetMapping("/apply/select")
    public ResultVO<List<Purchase>> selectApply(@RequestParam(value = "state") int state,
                                           @RequestParam(value = "pageNum") int pageNum,
                                           @RequestParam(value = "pageSize") int pageSize) {
        if (state <= PurchaseStateEnum.All.getCode() && state >= PurchaseStateEnum.UNCHECK.getCode()) {
            PageInfo<Purchase> purchasePageInfo = purchaseService.findByState(state, pageNum, pageSize);
            List<Purchase> purchaseList = purchasePageInfo.getList();
            return ResultVOUtils.success(purchaseList);
        } else {
            throw new PurchaseException(ResultEnum.PARAM_ERROR.getCode(),ResultEnum.PARAM_ERROR.getMsg());
        }
    }


    @GetMapping("/apply/detail")
    public ResultVO<List<PurchaseVO>> findOne(@RequestParam(value = "purchaseId") String purchaseId) {
        Purchase purchase = purchaseService.findPurchaseById(purchaseId);
        String plan = purchase.getPurchasePlan();
        List<PurchaseVO> purchaseVOList = new ArrayList<>();
        Gson gson = new Gson();
        //TODO 异常日志打印
        purchaseVOList = gson.fromJson(plan, new TypeToken<List<PurchaseVO>>(){}.getType());
        return ResultVOUtils.success(purchaseVOList);
    }

    @GetMapping("apply/check")
    public ResultVO checkApply(@RequestParam(value = "id") String purchaseId,
                          @RequestParam(value = "state") int purchaseState) {
        int result = purchaseService.updateState(purchaseId, purchaseState);
        if (result > 0) {
            return ResultVOUtils.success();
        } else {
            return  ResultVOUtils.error();
        }
    }

    @PostMapping("detail/insert")
    public ResultVO<Map<String, String>> insertDetail(@Valid PurchaseDetailForm purchaseDetailForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO 打印日志
            throw new PurchaseException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        String id = KeyUtils.genUniqueKey();
        PurchaseDetail purchaseDetail = new PurchaseDetail();
        BeanUtils.copyProperties(purchaseDetailForm, purchaseDetail);
        purchaseDetail.setPurchaseDetailId(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        purchaseDetail.setCreateTime(timestamp);
        int result = purchaseDetailService.insertByObject(purchaseDetail);
        if (result > 0) {
            Map<String, String> map = new HashMap<>();
            map.put("id", id);
            return ResultVOUtils.success(map);
        }
        return ResultVOUtils.error();
    }

    @GetMapping("detail/select")
    public ResultVO<List<PurchaseDetail>> selectPurchaseDetail(@RequestParam(value = "pageNum") int pageNum,
                                                               @RequestParam(value = "pageSize") int pageSize) {
        PageInfo<PurchaseDetail> purchaseDetailPageInfo = purchaseDetailService.findAll(pageNum, pageSize);
        List<PurchaseDetail> purchaseDetailList = purchaseDetailPageInfo.getList();
        return ResultVOUtils.success(purchaseDetailList);
    }


    @GetMapping("detail/check")
    public ResultVO<Map<String, String>> checkDetail() {
        String result = purchaseDetailService.check();
        Map<String, String> map = new HashMap<>();
        map.put("id", result);
        return ResultVOUtils.success(map);
    }
}
