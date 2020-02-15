package com.xiaochen.beatles.mapper;


import com.xiaochen.beatles.pojo.Pay;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PayMapper {
    /**
     * 添加账单
     *
     * @param pay
     * @return
     */
    @Insert(value = "INSERT INTO pay(pay_money,pay_date,pay_source)" + "Values(#{payMoney},#{payDate},DATABASE())")
    public boolean addPay(Pay pay);

    /**
     * 根据id 查询账单
     *
     * @param payId
     * @return
     */
    @Results({
            @Result(property = "payId", column = "pay_id"),
            @Result(property = "payMoney", column = "pay_money"),
            @Result(property = "payDate", column = "pay_date"),
            @Result(property = "paySources", column = "pay_source")
    })
    @Select(value = "select pay_id,pay_money,pay_date,pay_source from pay where pay_id=#{payId}")
    public Pay queryPayid(@Param("payId") long payId);

    /**
     * 所有账单
     *
     * @return
     */
    @Results(
            id = "payId", value = {
            @Result(property = "payId", column = "pay_id"),
            @Result(property = "payMoney", column = "pay_money"),
            @Result(property = "payDate", column = "pay_date"),
            @Result(property = "paySources", column = "pay_source")
    })
    @Select(value = "select *from  pay")
    public List<Pay> queryPay();

    /**
     * 删除账单
     *
     * @param payId
     * @return
     */
    @Delete(value = "delete from pay where pay_id=#{payId}")
    public boolean delPay(@Param(value = "payId") long payId);
}
