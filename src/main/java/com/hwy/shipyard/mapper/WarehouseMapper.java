package com.hwy.shipyard.mapper;

import com.hwy.shipyard.dataobject.Warehouse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WarehouseMapper {

    /**
     * 新增仓库
     * @param warehouse
     * @return 0成功 -1失败
     */
    @Insert("INSERT INTO warehouse " +
            "VALUES(#{warehouseId},#{warehouseName},#{warehouseAddress},#{warehouseAdministrator},#{warehouseArea},#{warehouseState},#{warehouseRemark},#{warehouseField0})")
    int addWarehouse(Warehouse warehouse);

    /**
     * 查询所有仓库
     * @return Warehouse列表
     */
    @Select("SELECT * FROM warehouse")
    List<Warehouse> getAllWarehouse();

    /**
     * 删除仓库
     * @param warehouseId
     * @return 0成功 -1失败
     */
    @Delete("DELETE FROM warehouse WHERE warehouse_id = #{warehouseId}")
    int delWarehouse(@Param("warehouseId")String warehouseId);
}
