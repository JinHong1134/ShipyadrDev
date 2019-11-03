package com.hwy.shipyard.mapper;

import com.hwy.shipyard.dataobject.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProductMapper {

    //查询所有
    @Select("select * from product")
    List<Product> getAll();

    //增加
    @Insert("insert into product (product_id,product_name,product_type,warehouse_name," +
            "product_unit,product_stock,product_state,location,product_field0,product_field1," +
            "product_field2,product_field3,remark,operator,operation_time) values(#{productId}," +
            "#{productName},#{productType}," +
            "#{warehouseName},#{productUnit},#{productStock},#{productState},#{location}," +
            "#{productField0},#{productField1},#{productField2},#{productField3},#{remark},#{operator},#{operationTime})")
    void add(Product product);

    //根据产品名称查询
    @Select("select * from product where product_name=#{productName}")
    Product findByName(String productName);

    //根据产品id查询
    @Select("select * from product where product_id=#{productId}")
    Product findById(String productId);

    //更新产品库存
    @Update("update product set product_stock=#{productStock},product_state=#{productState}," +
            "warehouse_name=#{warehouseName},location=#{location},product_field0=#{productField0}," +
            "product_field1=#{productField1},product_field2=#{productField2},product_field3=#{productField3}," +
            "remark=#{remark},operator=#{operator},operation_time=#{operationTime} " +
            "where product_id=#{productId}")
    void update(Product product);

    //删除
    @Delete("delete from product where product_id=#{productId}")
    void delete(String productId);
}
