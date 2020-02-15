package com.xiaochen.beatles.mapper;


import com.xiaochen.beatles.pojo.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdersMapper {
    int countByExample(Orders orders);

    int deleteByExample(Orders orders);

    int deleteByPrimaryKey(String id);

    /**
     * 添加订单
     *
     * @param record
     * @return
     */
    @Insert(value = "INSERT INTO orders(id,order_num,order_status,order_amount,paid_amount,product_id,buy_counts,create_time,paid_time)" + "VALUE(#{id},#{orderNum},#{orderStatus},#{orderAmount},#{paidAmount},#{productId},#{buyCounts},now(),now())")
    int insert(Orders record);

    @InsertProvider(type = OrdersDynamicSqlProvider.class, method = "insertSelective")
    int insertSelective(Orders record);

    List<Orders> selectByExample(Orders orders);

    /**
     * 根据订单id查询
     *
     * @param id
     * @return
     */
    @Results({
            @Result(property = "orderNum", column = "order_num"),
            @Result(property = "orderStatus", column = "order_status"),
            @Result(property = "orderAmount", column = "order_amount"),
            @Result(property = "paidAmount", column = "paid_amount"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "buyCounts", column = "buy_counts"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "paidTime", column = "paid_time"),
    })
    @Select(value = "select *from orders where id=#{id}")
    Orders selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") Orders orders);

    int updateByExample(@Param("record") Orders record, @Param("example") Orders orders);

    /**
     * 更新订单状态
     *
     * @param record
     * @return
     */
    @UpdateProvider(type = OrdersDynamicSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
}