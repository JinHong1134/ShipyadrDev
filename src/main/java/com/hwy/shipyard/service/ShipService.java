package com.hwy.shipyard.service;

import com.github.pagehelper.PageInfo;
import com.hwy.shipyard.dataobject.Ship;

public interface ShipService {
    Object getAllShip(Integer pageNum, Integer pageSize);

    Object getShipByIMO(String shipImoNumber);

    Object getShipByName(String shipName);

    Object addShip(Ship ship);

    Object delShip(String shipId);

    Object update(Ship ship);
}
