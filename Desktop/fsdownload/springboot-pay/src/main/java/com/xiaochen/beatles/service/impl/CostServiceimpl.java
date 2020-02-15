package com.xiaochen.beatles.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaochen.beatles.mapper.PayMapper;
import com.xiaochen.beatles.pojo.Pay;
import com.xiaochen.beatles.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CostServiceimpl implements CostService {
    @Autowired
    private PayMapper payMapper;

    @Override
    public boolean addPay(String data) {
        JSONObject jsonObject = JSON.parseObject(data);
        Pay pay = new Pay();
        pay.setPayMoney(jsonObject.getLong("payMoney"));
        pay.setPayDate(new Date());
        return payMapper.addPay(pay);
    }

    @Override
    public long queryPayid(long payId) {
        Pay pay = payMapper.queryPayid(payId);
        return pay.getPayMoney();
    }

    @Override
    public  List<Pay>  queryPay() {
        return payMapper.queryPay();
    }

    @Override
    public boolean delPay(long payId) {
        return payMapper.delPay(payId);
    }
}
