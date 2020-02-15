package com.xiaochen.beatles.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.xiaochen.beatles.config.AlipayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class ShaXiangController {
    /**
     * 支付宝请求交易
     *
     * @param
     * @return
     */
    @RequestMapping("/shapays")
    @ResponseBody
    public String pay() {
        log.info("infoMsg:--- 支付宝请求交易开始");

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        //AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        String moony = String.valueOf(System.currentTimeMillis()).substring(8);

        model.setOutTradeNo(System.currentTimeMillis()+"");
        model.setSubject("Iphone6 16G");
        model.setTotalAmount(moony);
        model.setSubject("beatles");
        model.setBody("guxin : "+moony);
       request.setBizModel(model);
        String form = "";
        try {
            //调用SDK生成表单
            form = alipayClient.pageExecute(request).getBody();
            System.out.println(form);
            return form;
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return null;
    }
}
