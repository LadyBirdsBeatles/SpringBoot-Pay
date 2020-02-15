package com.xiaochen.beatles.mapper;

import com.xiaochen.beatles.pojo.Flow;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FlowMapper {
    int countByExample(Flow flow);

    int deleteByExample(Flow flow);

    int deleteByPrimaryKey(String id);

    @Insert(value = "INSERT INTO flow(id,flow_num,order_num,product_id,paid_amount,paid_method,buy_counts,create_time)" + "VALUE(#{id},#{flowNum},#{orderNum},#{productId},#{paidAmount},#{paidMethod},#{buyCounts},#{createTime})")
    int insert(Flow record);

    /**
     * 选择性插入
     *
     * @param record
     * @return
     */
    @InsertProvider(type = FlowDynamicSqlProvider.class, method = "insertSelective")
    int insertSelective(Flow record);

    List<Flow> selectByExample(Flow flow);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "flowNum", column = "flow_num"),
            @Result(property = "orderNum", column = "order_num"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "paidAmount", column = "paid_amount"),
            @Result(property = "paidMethod", column = "paid_method"),
            @Result(property = "buyCounts", column = "buy_counts"),
            @Result(property = "createTime", column = "create_time")
    })
    @Select(value = "select *from flow where id=#{id}")
    Flow selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Flow record, @Param("example") Flow flow);

    int updateByExample(@Param("record") Flow record, @Param("example") Flow flow);

    /**
     * @param record
     * @return
     */
    @UpdateProvider(type = FlowDynamicSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Flow record);

    int updateByPrimaryKey(Flow record);
}