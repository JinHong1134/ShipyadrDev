package com.hwy.shipyard.mapper;

import com.hwy.shipyard.dataobject.WarehouseDeliver;
import com.hwy.shipyard.dataobject.WarehouseDeliverDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface WarehouseDeliverMapper {
    //添加出库单
    @Insert("INSERT INTO warehouse_deliver(warehouse_deliver_id,warehouse_id,warehouse_name,deliver_time,ship_name,address,operator_name,deliver_state,remark,warehouse_deliver_field0)" +
            "VALUES(#{warehouseDeliverId},#{warehouseId},#{warehouseName},#{deliverTime},#{shipName},#{address},#{operatorName},#{deliverState},#{remark},#{warehouseEntryField0})")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn="id")
    int addDeliver(WarehouseDeliver warehouseDeliver);

    //添加出库明细单
    @Insert("INSERT INTO warehouse_deliver_detail(warehouse_deliver_id,product_id,product_name,product_unit,deliver_quantity,product_specification,warehouse_deliver_detail_field0)" +
            "VALUES(#{warehouseDeliverId},#{productId},#{productName},#{productUnit},#{deliverQuantity},#{productSpecification},#{warehouseDeliverDetailField0})")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn="id")
    int addDeliverDetail(WarehouseDeliverDetail warehouseDeliverDetail);

    //获取所有出库单
    @Select("SELECT * FROM warehouse_deliver")
    List<WarehouseDeliver> getAll();

    //根据出库单号获取出库单
    //@Select("SELECT * FROM warehouse_deliver WHERE warehouse_deliver_id = #{warehouseDeliverId}")
    //int getDeliver(@Param("warehouseDeliverId") String warehouseDeliverId);

    @Select("SELECT * FROM warehouse_deliver WHERE warehouse_deliver_id = #{warehouseDeliverId}")
    WarehouseDeliver getDeliverById(String warehouseDeliverId);
    //根据自增id获取明细单
    @Select("SELECT * FROM warehouse_deliver_detail WHERE id = #{id}")
    WarehouseDeliverDetail getDetailById(int id);

    //获取相应明细单
    @Select("SELECT * FROM warehouse_deliver_detail WHERE warehouse_deliver_id = #{warehouseDeliverId}")
    List<WarehouseDeliverDetail> getDeliverDetail(@Param("warehouseDeliverId") String warehouseDeliverId);

    //根据ID获取出库单
    @Select("SELECT * FROM warehouse_deliver WHERE id = #{id}")
    WarehouseDeliver getDeliver(@Param("id") int id);

    //更新校验位
    @Update("UPDATE warehouse_deliver SET warehouse_deliver_check_bits = #{warehouseDeliverCheckBits} WHERE id=#{id}")
    int updateCheck(int id,String warehouseDeliverCheckBits);

    //更新明细单校验位
    @Update("UPDATE warehouse_deliver_detail SET warehouse_deliver_detail_check_bits = #{warehouseDeliverDetailCheckBits} WHERE id = #{id}")
    int updateDetailCheck(int id,String warehouseDeliverDetailCheckBits);


    /**
     * 获取列表行数
     * @return
     */
    @Select("SELECT COUNT(0) count FROM warehouse_deliver")
    Integer getDeliverCount();
    @Select("SELECT COUNT(0) count FROM warehouse_deliver_detail")
    Integer getDeliverDetailCount();

    @Select("SELECT * FROM warehouse_deliver_detail ORDER BY id DESC LIMIT 1")
    WarehouseDeliverDetail getDetailLast();

    @Select("SELECT * FROM warehouse_deliver ORDER BY id DESC LIMIT 1")
    WarehouseDeliver getLast();

}
