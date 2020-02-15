package com.xiaochen.beatles.mapper;

import com.xiaochen.beatles.pojo.Orders;
import org.apache.ibatis.jdbc.SQL;

public class OrdersDynamicSqlProvider {
    /**
     * @param orders
     * @return
     */
    public String insertSelective(Orders orders) {
        return new SQL() {
            {
                INSERT_INTO("orders");
                if (orders.getOrderNum() != null) {
                    VALUES("order_num", "#{orderNum}");
                    //SET("name", person.getName());
                    //Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column 'Java' in 'field list'
                }
                if (orders.getOrderStatus() != null) {
                    VALUES("order_status", "#{orderStatus}");
                }
                if (orders.getOrderAmount() != null) {
                    VALUES("order_amount", "#{orderAmount}");
                }
                if (orders.getPaidAmount() != null) {
                    VALUES("paid_amount", "#{paidAmount}");
                }
                if (orders.getProductId() != null) {
                    VALUES("product_id", "#{productId}");
                }
                if (orders.getBuyCounts() != null) {
                    VALUES("buy_counts", "#{buyCounts}");
                }
                if (orders.getCreateTime() != null) {
                    VALUES("create_time", "#{createTime}");
                }
                if (orders.getPaidTime() != null) {
                    VALUES("paid_time", "#{paidTime}");
                }
            }
        }.toString();
    }

    /**
     * @return
     */
    public String updateByPrimaryKeySelective(Orders orders) {
        return new SQL() {
            {
                UPDATE("orders");
                if (orders.getOrderNum() != null) {
                    SET("order_num=#{orderNum}");
                    //SET("name", person.getName());
                    //Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column 'Java' in 'field list'
                }
                if (orders.getOrderStatus() != null) {
                    SET("order_status=#{orderStatus}");
                }
                if (orders.getOrderAmount() != null) {
                    SET("order_amount=#{orderAmount}");
                }
                if (orders.getPaidAmount() != null) {
                    SET("paid_amount=#{paidAmount}");
                }
                if (orders.getProductId() != null) {
                    SET("product_id=#{productId}");
                }
                if (orders.getBuyCounts() != null) {
                    SET("buy_counts=#{buyCounts}");
                }
                if (orders.getCreateTime() != null) {
                    SET("create_time=#{createTime}");
                }
                if (orders.getPaidTime() != null) {
                    SET("paid_time=#{paidTime}");
                }
                WHERE("id=#{id}");
            }
        }.toString();

    }

}
