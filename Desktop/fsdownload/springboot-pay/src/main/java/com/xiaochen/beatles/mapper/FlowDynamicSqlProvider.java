package com.xiaochen.beatles.mapper;

import com.xiaochen.beatles.pojo.Flow;
import org.apache.ibatis.jdbc.SQL;

public class FlowDynamicSqlProvider {
    /**
     * 动态插入flow
     *
     * @param flow
     * @return
     */
    public String insertSelective(Flow flow) {
        return new SQL() {
            {
                INSERT_INTO("flow");
                if (flow.getId() != null) {
                    VALUES("id", flow.getId());
                }
                if (flow.getFlowNum() != null) {
                    VALUES("flow_num", flow.getFlowNum());
                    //SET("flow_num=#{flowNum}");
                    //Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column 'Java' in 'field list'
                }
                if (flow.getOrderNum() != null) {
                    VALUES("order_num", flow.getOrderNum());
                }
                if (flow.getProductId() != null) {
                    VALUES("product_id", flow.getProductId());
                }
                if (flow.getPaidAmount() != null) {
                    VALUES("paid_amount", flow.getPaidAmount());
                }
                if (flow.getPaidMethod() != null) {
                    VALUES("paid_method", String.valueOf(flow.getPaidMethod()));
                }
                if (flow.getBuyCounts() != null) {
                    VALUES("buy_counts", String.valueOf(flow.getBuyCounts()));
                }
                if (flow.getCreateTime() != null) {
                    VALUES("create_time", String.valueOf(flow.getCreateTime()));
                }

            }
        }.toString();
    }

    public String updateByPrimaryKeySelective(Flow flow) {
        return new SQL() {
            {
                UPDATE("flow");
                if (flow.getFlowNum() != null) {
                    SET("flow_num", flow.getFlowNum());
                    //SET("flow_num=#{flowNum}");
                    //Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column 'Java' in 'field list'
                }
                if (flow.getOrderNum() != null) {
                    SET("order_num", flow.getOrderNum());
                }
                if (flow.getProductId() != null) {
                    SET("product_id", flow.getProductId());
                }
                if (flow.getPaidAmount() != null) {
                    SET("paid_amount", flow.getPaidAmount());
                }
                if (flow.getPaidMethod() != null) {
                    SET("paid_method", String.valueOf(flow.getPaidMethod()));
                }
                if (flow.getBuyCounts() != null) {
                    SET("buy_counts", String.valueOf(flow.getBuyCounts()));
                }
                if (flow.getCreateTime() != null) {
                    SET("create_time", String.valueOf(flow.getCreateTime()));
                }
                WHERE("id", flow.getId());
            }
        }.toString();
    }
}
