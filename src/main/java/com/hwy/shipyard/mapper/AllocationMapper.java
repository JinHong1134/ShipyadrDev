package com.hwy.shipyard.mapper;

import com.hwy.shipyard.dataobject.Allocation;
import com.hwy.shipyard.dataobject.AllocationDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author honghong
 * @version 1.0
 * @date 2019/9/2 22:17
 */
public interface AllocationMapper {

    //添加调拨单
    @Insert("INSERT INTO warehouse_allocation(allocation_id,warehouse_deliver_id,warehouse_deliver,warehouse_entry_id,warehouse_entry,operator_name,allocation_time,remark,field,allocation_state) " +
            "VALUES(#{allocationId},#{warehouseEntryId},#{warehouseDeliver},#{warehouseEntryId},#{warehouseEntry},#{operatorName},#{allocationTime},#{remark},#{field},#{allocationState})")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn="id")
    int addAllocation(Allocation allocation);

    //调拨单列表
    @Select("SELECT * FROM warehouse_allocation")
    List<Allocation> getAllocationAll();

    //根据调拨单单号查找调拨单
    @Select("SELECT * FROM warehouse_allocation WHERE allocation_id = #{allocationId}")
    Allocation getByAllocationId(String allocationId);

    //根据调拨单单号查询明细
    @Select("SELECT * FROM warehouse_allocation_detail WHERE allocation_id = #{allocationId}")
    List<AllocationDetail> getDetailByAllocationId(String allocationId);

    //添加明细单]
    @Insert("INSERT INTO warehouse_allocation_detail(allocation_id,product_id,product_name,product_unit,product_quantity,product_specification,remark,field) " +
            "VALUES(#{allocationId},#{productId},#{productName},#{productUnit},#{productQuantity},#{productSpecification},#{remark},#{field})")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn="id")
    int addAllocationDetail(AllocationDetail allocationDetail);

    //更新调拨单id
    @Update("UPDATE warehouse_allocation SET allocation_check_bits = #{allocationCheckBits} WHERE id = #{id}")
    int updateCheck(String allocationCheckBits,int id);

    //更新调拨明细单id
    @Update("UPDATE warehouse_allocation_detail SET allocation_detail_check_bits = #{allocationDetailCheckBits} WHERE id = #{id}")
    int updateDetailCheck(String allocationDetailCheckBits,int id);

    //根据自增id获取调拨单，校验
    @Select("SELECT * FROM warehouse_allocation WHERE id = #{id}")
    Allocation getAllocationById(int id);

    //根据自增id获取调拨明细单，校验
    @Select("SELECT * FROM warehouse_allocation_detail WHERE id = #{id}")
    AllocationDetail getAllocationDetailById(int id);

    //获取调拨单数量
    @Select("SELECT count(0) count FROM  warehouse_allocation")
    Integer getAllocationCount();
    @Select("SELECT count(0) FROM warehouse_allocation_detail")
    Integer getAllocationDetailCount();


    @Select("SELECT count(0) FROM warehouse_allocation WHERE allocation_state = 0")
    Integer getAllocation0();

    @Update("UPDATE warehouse_allocation SET allocation_state = #{allocationState} WHERE allocation_id = #{allocationId}")
    int updateState(int allocationState, String allocationId);

    @Select("SELECT * FROM warehouse_allocation ORDER BY id DESC LIMIT 1")
    Allocation getLast();

    @Select("SELECT * FROM warehouse_allocation_detail ORDER BY id DESC LIMIT 1")
    AllocationDetail getDetailLast();

}
