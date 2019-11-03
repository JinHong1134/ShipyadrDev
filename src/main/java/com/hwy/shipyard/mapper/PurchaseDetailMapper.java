package com.hwy.shipyard.mapper;

import com.hwy.shipyard.dataobject.PurchaseDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PurchaseDetailMapper {

    @Select("select * from purchase_detail")
    @Results(value = {
            @Result(column = "purchase_detail_id", property = "purchaseDetailId"),
            @Result(column = "purchase_id", property = "purchaseId"),
            @Result(column = "product_id", property = "productId"),
            @Result(column = "product_name", property = "productName"),
            @Result(column = "product_quantity", property = "productQuantity"),
            @Result(column = "product_unit", property = "productUnit"),
            @Result(column = "factory_name", property = "factoryName"),
            @Result(column = "reserve_time", property = "reserveTime"),
            @Result(column = "delivery_time", property = "deliveryTime"),
            @Result(column = "product_price", property = "productPrice"),
            @Result(column = "total_price", property = "totalPrice"),
            @Result(column = "warehouse_id", property = "warehouseId"),
            @Result(column = "warehouse_name", property = "warehouseName"),
            @Result(column = "purchase_detail_pre", property = "purchaseDetailPre"),
            @Result(column = "purchase_detail_check_bits", property = "purchaseDetailCheckBits"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "purchaser", property = "purchaser"),
            @Result(column = "purchase_detail_field0", property = "purchase_detail_field0"),
            @Result(column = "purchase_detail_field1", property = "purchase_detail_field1"),
            @Result(column = "purchase_detail_field2", property = "purchase_detail_field2"),
            @Result(column = "purchase_detail_field3", property = "purchase_detail_field3"),
    })
    List<PurchaseDetail> findAll();

    @Insert("insert into purchase_detail (purchase_detail_id, purchase_id, product_id, product_name," +
            "product_quantity, product_unit, factory_name, reserve_time, delivery_time, product_price, total_price," +
            "warehouse_id, warehouse_name, purchase_detail_pre, purchase_detail_check_bits, create_time, purchaser," +
            "purchase_detail_field0, purchase_detail_field1, purchase_detail_field2, purchase_detail_field3) values" +
            "(#{purchaseDetailId}, #{purchaseId}, #{productId}, #{productName}, #{productQuantity}, #{productUnit}," +
            "#{factoryName}, #{reserveTime}, #{deliveryTime}, #{productPrice}, #{totalPrice}, #{warehouseId}," +
            "#{warehouseName}, #{purchaseDetailPre}, #{purchaseDetailCheckBits}, #{createTime}, #{purchaser}, " +
            "#{purchaseDetailField0}, #{purchaseDetailField1}, #{purchaseDetailField2}, #{purchaseDetailField3})")
    int insertByObject(PurchaseDetail purchaseDetail);

    @Select("select * from purchase_detail order by create_time desc limit 1")
    @Results(value = {
            @Result(column = "purchase_detail_id", property = "purchaseDetailId"),
            @Result(column = "purchase_id", property = "purchaseId"),
            @Result(column = "product_id", property = "productId"),
            @Result(column = "product_name", property = "productName"),
            @Result(column = "product_quantity", property = "productQuantity"),
            @Result(column = "product_unit", property = "productUnit"),
            @Result(column = "factory_name", property = "factoryName"),
            @Result(column = "reserve_time", property = "reserveTime"),
            @Result(column = "delivery_time", property = "deliveryTime"),
            @Result(column = "product_price", property = "productPrice"),
            @Result(column = "total_price", property = "totalPrice"),
            @Result(column = "warehouse_id", property = "warehouseId"),
            @Result(column = "warehouse_name", property = "warehouseName"),
            @Result(column = "purchase_detail_pre", property = "purchaseDetailPre"),
            @Result(column = "purchase_detail_check_bits", property = "purchaseDetailCheckBits"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "purchaser", property = "purchaser"),
            @Result(column = "purchase_detail_field0", property = "purchase_detail_field0"),
            @Result(column = "purchase_detail_field1", property = "purchase_detail_field1"),
            @Result(column = "purchase_detail_field2", property = "purchase_detail_field2"),
            @Result(column = "purchase_detail_field3", property = "purchase_detail_field3"),
    })
    PurchaseDetail findLast();

    @Select("select * from purchase_detail where purchase_detail_id = #{purchaseDetailId}")
    @Results(value = {
            @Result(column = "purchase_detail_id", property = "purchaseDetailId"),
            @Result(column = "purchase_id", property = "purchaseId"),
            @Result(column = "product_id", property = "productId"),
            @Result(column = "product_name", property = "productName"),
            @Result(column = "product_quantity", property = "productQuantity"),
            @Result(column = "product_unit", property = "productUnit"),
            @Result(column = "factory_name", property = "factoryName"),
            @Result(column = "reserve_time", property = "reserveTime"),
            @Result(column = "delivery_time", property = "deliveryTime"),
            @Result(column = "product_price", property = "productPrice"),
            @Result(column = "total_price", property = "totalPrice"),
            @Result(column = "warehouse_id", property = "warehouseId"),
            @Result(column = "warehouse_name", property = "warehouseName"),
            @Result(column = "purchase_detail_pre", property = "purchaseDetailPre"),
            @Result(column = "purchase_detail_check_bits", property = "purchaseDetailCheckBits"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "purchaser", property = "purchaser"),
            @Result(column = "purchase_detail_field0", property = "purchase_detail_field0"),
            @Result(column = "purchase_detail_field1", property = "purchase_detail_field1"),
            @Result(column = "purchase_detail_field2", property = "purchase_detail_field2"),
            @Result(column = "purchase_detail_field3", property = "purchase_detail_field3"),
    })
    PurchaseDetail findById(String purchaseDetailId);
}
