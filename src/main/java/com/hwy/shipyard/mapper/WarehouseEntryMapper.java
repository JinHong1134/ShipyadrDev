package com.hwy.shipyard.mapper;

import com.hwy.shipyard.dataobject.WarehouseDeliver;
import com.hwy.shipyard.dataobject.WarehouseEntry;
import com.hwy.shipyard.dataobject.WarehouseEntryDetail;
import org.apache.ibatis.annotations.*;


import java.util.List;

public interface WarehouseEntryMapper {
    /**
     * 入库表新增
     */

    @Insert("INSERT INTO warehouse_entry(warehouse_entry_id,warehouse_id,warehouse_name,entry_time,operator_name,remark,entry_state,warehouse_entry_check_bits,warehouse_entry_field0) " +
            "values(#{warehouseEntryId},#{warehouseId},#{warehouseName},#{entryTime},#{operatorName},#{remark},#{entryState},#{warehouseEntryCheckBits},#{warehouseEntryField0})")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn="id")
    int addEntry(WarehouseEntry warehouseEntry);

    @Insert("INSERT INTO warehouse_entry_detail(warehouse_entry_id,product_id,product_name,product_specification,entry_quantity,product_unit,product_specification,location,warehouse_entry_detail_field0) " +
            "VALUES(#{warehouseEntryId},#{productId},#{productName},#{productSpecification},#{entryQuantity},#{productUnit},#{productSpecification},#{location},#{warehouseEntryDetailField0})")
    @Options(useGeneratedKeys = true, keyProperty = "warehouseEntryDetailId",keyColumn="warehouse_entry_detail_id")
    int addEntryDetail(WarehouseEntryDetail warehouseEntryDetail);

    /**
     * 查找
     */
    @Select("SELECT * FROM warehouse_entry ")
    List<WarehouseEntry> getAll();

    @Select("SELECT * FROM warehouse_entry WHERE id = #{id}")
    WarehouseEntry getEntry(int id);

    @Select("SELECT * FROM warehouse_entry WHERE warehouse_entry_id = #{warehouseEntryId}")
    WarehouseEntry getEntryById(String warehouseEntryId);

    @Update("UPDATE warehouse_entry SET warehouse_entry_check_bits = #{warehouseEntryCheckBits} WHERE id = #{id}")
    void updateEntry(String warehouseEntryCheckBits,int id);

    @Select("SELECT * FROM warehouse_entry_detail WHERE warehouse_entry_id = #{warehouseEntryId}")
    List<WarehouseEntryDetail> getDetail(@Param("warehouseEntryId")String warehouseEntryId);


    @Select("SELECT * FROM warehouse_entry_detail WHERE warehouse_entry_detail_id = #{warehouseEntryDetailId}")
    WarehouseEntryDetail getWarehouseEntryDetail(int warehouseEntryDetailId);

    @Update("UPDATE warehouse_entry_detail SET warehouse_entry_detail_check_bits = #{warehouseEntryDetailCheckBits} WHERE warehouse_entry_detail_id = #{warehouseEntryDetailId}")
    void updateDetailCheck( String warehouseEntryDetailCheckBits,int warehouseEntryDetailId );


    @Select("SELECT COUNT(0) count FROM warehouse_entry")
    Integer getEntryCount();
    @Select("SELECT COUNT(0) count FROM warehouse_entry_detail")
    Integer getEntryDetailCount();
}
