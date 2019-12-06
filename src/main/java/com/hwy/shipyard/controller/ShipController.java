package com.hwy.shipyard.controller;

import com.hwy.shipyard.service.ShipService;
import com.hwy.shipyard.dataobject.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys")
public class ShipController {
    @Autowired
    private ShipService shipService;

    //查看所有船
    @GetMapping("/ship/all")
    public Object AllShipInfo(Integer pageNum, Integer pageSize){
        return shipService.getAllShip(pageNum,pageSize);
    }

    //根据名称查找船
    @GetMapping("/ship/name")
    public Object ShipInfo(String shipName){
        return shipService.getShipByName(shipName);
    }

    //增加船
    @PostMapping("/ship/add")
    public Object addShip(@RequestBody Ship ship){
        return shipService.addShip(ship);
    }

    //删除
    @GetMapping("/ship/del")
    public Object delShip(String shipId){
        return shipService.delShip(shipId);
    }

    //更新
    @PostMapping("/ship/update")
    public Object updateShip(@RequestBody Ship ship){
        return shipService.update(ship);
    }


}
