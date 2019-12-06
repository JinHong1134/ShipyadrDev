package com.hwy.shipyard.mapper;

import com.hwy.shipyard.dataobject.Purchase;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;


/**
 * @program: shipyard
 * @author: huangwenyu
 * @create: 2019-08-24
 */
public interface PurchaseMapper {

    /**
     * 查询订单申请，通过id
     * @param purchaseId 订单id
     * @return 一个订单对象
     */
    @Select("select * from purchase where purchase_id = #{purchaseId}")
    @Results(value = {
            @Result(column = "purchase_id", property = "purchaseId"),
            @Result(column = "purchase_title", property = "purchaseTitle"),
            @Result(column = "operator_name", property = "operatorName"),
            @Result(column = "department", property = "department"),
            @Result(column = "warehouse", property = "warehouse"),
            @Result(column = "note", property = "note"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "purchase_plan", property = "purchasePlan"),
            @Result(column = "purchase_state", property = "purchaseState"),
            @Result(column = "purchase_field0", property = "purchaseField0"),
            @Result(column = "purchase_field1", property = "purchaseField1"),
            @Result(column = "purchase_field2", property = "purchaseField2"),
            @Result(column = "purchase_field3", property = "purchaseField3")
    })
    Purchase findById(String purchaseId);

    /**
     * 查询所有
     * @return
     */
    @Select("select * from purchase")
    @Results(value = {
            @Result(column = "purchase_id", property = "purchaseId"),
            @Result(column = "purchase_title", property = "purchaseTitle"),
            @Result(column = "operator_name", property = "operatorName"),
            @Result(column = "department", property = "department"),
            @Result(column = "warehouse", property = "warehouse"),
            @Result(column = "note", property = "note"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "purchase_plan", property = "purchasePlan"),
            @Result(column = "purchase_state", property = "purchaseState"),
            @Result(column = "purchase_field0", property = "purchaseField0"),
            @Result(column = "purchase_field1", property = "purchaseField1"),
            @Result(column = "purchase_field2", property = "purchaseField2"),
            @Result(column = "purchase_field3", property = "purchaseField3")
    })
    List<Purchase> findAll();
    /**
     * 查找订单申请，通过订单申请创建的时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 订单列表
     */
    @Select("select * from purchase where purchase_id = #{purchaseId}")
    @Results(value = {
            @Result(column = "purchase_id", property = "purchaseId"),
            @Result(column = "purchase_title", property = "purchaseTitle"),
            @Result(column = "operator_name", property = "operatorName"),
            @Result(column = "department", property = "department"),
            @Result(column = "warehouse", property = "warehouse"),
            @Result(column = "note", property = "note"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "purchase_plan", property = "purchasePlan"),
            @Result(column = "purchase_state", property = "purchaseState"),
            @Result(column = "purchase_field0", property = "purchaseField0"),
            @Result(column = "purchase_field1", property = "purchaseField1"),
            @Result(column = "purchase_field2", property = "purchaseField2"),
            @Result(column = "purchase_field3", property = "purchaseField3")
    })
    List<Purchase> findByTime(Date startTime, Date endTime);

    /**
     * 通过业务员姓名进行查找
     * @param operatorName 业务员姓名
     * @return 订单列表
     */
    @Select("select * from purchase where operator_name = #{operatorName}")
    @Results(value = {
            @Result(column = "purchase_id", property = "purchaseId"),
            @Result(column = "purchase_title", property = "purchaseTitle"),
            @Result(column = "operator_name", property = "operatorName"),
            @Result(column = "department", property = "department"),
            @Result(column = "warehouse", property = "warehouse"),
            @Result(column = "note", property = "note"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "purchase_plan", property = "purchasePlan"),
            @Result(column = "purchase_state", property = "purchaseState"),
            @Result(column = "purchase_field0", property = "purchaseField0"),
            @Result(column = "purchase_field1", property = "purchaseField1"),
            @Result(column = "purchase_field2", property = "purchaseField2"),
            @Result(column = "purchase_field3", property = "purchaseField3")
    })
    List<Purchase> findByOperator(String operatorName);

    @Select("select * from purchase where purchase_state = #{purchaseState}")
    @Results(value = {
            @Result(column = "purchase_id", property = "purchaseId"),
            @Result(column = "purchase_title", property = "purchaseTitle"),
            @Result(column = "operator_name", property = "operatorName"),
            @Result(column = "department", property = "department"),
            @Result(column = "warehouse", property = "warehouse"),
            @Result(column = "note", property = "note"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "purchase_plan", property = "purchasePlan"),
            @Result(column = "purchase_state", property = "purchaseState"),
            @Result(column = "purchase_field0", property = "purchaseField0"),
            @Result(column = "purchase_field1", property = "purchaseField1"),
            @Result(column = "purchase_field2", property = "purchaseField2"),
            @Result(column = "purchase_field3", property = "purchaseField3")
    })
    List<Purchase> findByState(int purchaseState);

    /**
     * 创建新的订单申请
     * @param purchase 创建的订单
     * @return 创建状态
     */
    @Insert("insert into purchase (purchase_id, purchase_title, operator_name, department, warehouse, note," +
            "purchase_plan, purchase_state, purchase_field0," +
            "purchase_field1, purchase_field2, purchase_field3) values (#{purchaseId}, #{purchaseTitle}, " +
            "#{operatorName}, #{department}, #{warehouse}, #{note}, #{purchasePlan}, #{purchaseState}, " +
            "#{purchaseField0}, #{purchaseField1}, #{purchaseField2}, #{purchaseField3})")
    int insertByObject(Purchase purchase);

    @Delete("delete from purchase where purchase_id = #{purchaseId}")
    int deleteById(String purchaseId);

    @Update("update purchase set purchase_state = #{purchaseState} where purchase_id = #{purchaseId}")
    int updateById(@Param("purchaseId") String purchaseId, @Param("purchaseState") Integer purchaseState);

    @Select("SELECT count(0) FROM purchase WHERE purchase_state = 0")
    Integer getStateNum();

}
