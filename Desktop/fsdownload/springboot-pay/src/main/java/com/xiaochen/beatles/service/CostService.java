package com.xiaochen.beatles.service;


import com.xiaochen.beatles.pojo.Pay;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CostService {

    public boolean addPay(String pay);


    public long queryPayid(@Param("payId") long payId);


    public  List<Pay> queryPay();


    public boolean delPay(@Param(value = "payId") long payId);
}
