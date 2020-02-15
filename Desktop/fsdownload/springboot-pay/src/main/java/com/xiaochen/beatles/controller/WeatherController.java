package com.xiaochen.beatles.controller;


import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xiaochen.beatles.config.AlipayConfig;
import com.xiaochen.beatles.pojo.ChenAlipayBean;
import com.xiaochen.beatles.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class WeatherController {
    @Resource
    private WeatherService weatherService;


    @RequestMapping("/weather")
    @ResponseBody
    public String show() {
        String weather = weatherService.weather();
        System.out.println(weather);
        return weather;
    }

    @RequestMapping("/pay")
    @ResponseBody
    public String alipaycontroller() {

        ChenAlipayBean alipayBean = new ChenAlipayBean();
        alipayBean.setOut_trade_no("10005");
        alipayBean.setSubject("甲壳虫");
        alipayBean.setTotal_amount("500");
        alipayBean.setBody("价格 :" + alipayBean.getTotal_amount());
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
        // 2、设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        // 封装参数
        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
        // 3、请求支付宝进行付款，并获取支付结果
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
            // 返回付款信息
            return result;
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        // 返回付款信息
        return null;
    }
}
