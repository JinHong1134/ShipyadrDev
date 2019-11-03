package com.hwy.shipyard.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwy.shipyard.dataobject.Ship;
import com.hwy.shipyard.mapper.ShipMapper;
import com.hwy.shipyard.service.ShipService;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {
    @Autowired
    private ShipMapper shipMapper;

    @Override
    public Object getAllShip(Integer pageNum, Integer pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Ship> allShip = shipMapper.getAllShip();
            PageInfo<Ship> shipPageInfo = new PageInfo<>(allShip);
            return JsonData.buildSuccess(shipPageInfo,"查找全部成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("查找全部失败");
        }


    }

    @Override
    public Object getShipByIMO(String shipImoNumber) {
        try {
            Ship ship = shipMapper.getShipByIMO(shipImoNumber);
            List<Ship> list = new ArrayList<>();
            list.add(ship);
            return JsonData.buildSuccess(ship,"查找成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("查找失败");
        }
    }

    @Override
    public Object getShipByName(String shipName) {
        try {
            Ship ship = shipMapper.getShipByName(shipName);
            List<Ship> list = new ArrayList<>();
            list.add(ship);
            return JsonData.buildSuccess(list,"查找成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("查找失败");
        }
    }

    @Override
    public Object addShip(Ship ship){
        try {
            shipMapper.addShip(ship);
            return JsonData.buildSuccess(null,"增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("增加失败");
        }
    }

    @Override
    public Object delShip(String shipId){
        try {
            shipMapper.delShip(shipId);
            return JsonData.buildSuccess(null,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("删除失败");
        }

    }

    @Override
    public Object update(Ship ship){
        try {
            shipMapper.update(ship);
            return JsonData.buildSuccess(null,"更新成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("更新失败");
        }
    }
}
