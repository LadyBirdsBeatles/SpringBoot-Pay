package com.xiaochen.beatles.service.impl;

import com.xiaochen.beatles.mapper.FlowMapper;
import com.xiaochen.beatles.mapper.OrdersMapper;
import com.xiaochen.beatles.pojo.Flow;
import com.xiaochen.beatles.pojo.Orders;
import com.xiaochen.beatles.service.OrdersService;
import com.xiaochen.beatles.util.OrderStatusEnum;
import com.xiaochen.beatles.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private FlowMapper flowMapper;


    @Override
    public void saveOrder(Orders order) {
        ordersMapper.insert(order);
    }

    @Override
    public Orders getOrderById(String orderId) {
        return ordersMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public void updateOrderStatus(String orderId, String alpayFlowNum, String paidAmount) {
        String flowid = (String.valueOf(Utils.outTradeNo()));
        Orders order = getOrderById(orderId);
        String productId = order.getProductId();
        Integer buyCounts = order.getBuyCounts();

        if (order.getOrderStatus().equals(OrderStatusEnum.WAIT_PAY.key)) {
            order = new Orders();
            order.setId(orderId);
            order.setOrderStatus(OrderStatusEnum.PAID.key);
            order.setPaidTime(new Date());
            order.setPaidAmount(paidAmount);
            int i = ordersMapper.updateByPrimaryKeySelective(order);
            System.out.println(i);
            /**
             * 获取商品id和购买个数
             */
            Orders orderById = getOrderById(orderId);

            /**
             * 增加流水
             */
            //String flowId = sid.nextShort();
            // flow.setId(flowId);
            Flow flow = new Flow();
            flow.setId(flowid);
            flow.setFlowNum(alpayFlowNum);
            flow.setBuyCounts(buyCounts);
            flow.setCreateTime(new Date());
            flow.setOrderNum(orderId);
            flow.setPaidAmount(paidAmount);
            flow.setPaidMethod(1);
            flow.setProductId(productId);
            int insert = flowMapper.insert(flow);
        }

    }

}
