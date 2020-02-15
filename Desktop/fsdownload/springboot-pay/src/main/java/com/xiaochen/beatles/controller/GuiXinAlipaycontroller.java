package com.xiaochen.beatles.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AliosOpenAutoInfoQueryModel;
import com.alipay.api.domain.AlipayAssetPointPointprodPointlibQueryModel;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.xiaochen.beatles.config.AlipayConfig;
import com.xiaochen.beatles.pojo.Pay;
import com.xiaochen.beatles.service.CostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/pays")
public class GuiXinAlipaycontroller {
    @Resource
    private CostService costService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/hellos")
    public String hello(Model m) {
        m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));
        return "hello";
    }

    @RequestMapping("/addpay")
    @ResponseBody
    protected String addpay(String data) {
        boolean b = costService.addPay(data);
        return "0";
    }

    @RequestMapping("/delpay")
    @ResponseBody
    protected String addpay(long payid) {
        boolean b = costService.delPay(payid);
        return "0";
    }

    @RequestMapping("/querypayid")
    @ResponseBody
    protected long querypayid(long payid) {
        System.out.println(payid);
        long l = costService.queryPayid(payid);
        return l;
    }

    @RequestMapping("/querypay")
    @ResponseBody
    protected List querypay() {
        List<Pay> pays = costService.queryPay();
        return pays;
    }

    @RequestMapping("/pay")
    @ResponseBody
    protected String doPay() {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
        //创建API对应的request
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 返给用户的响应地址,回调结果
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        //在公共参数中设置回跳和通知地址
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //订单编号  10位随机数字组成
        Random random = new Random();
        int phone = 0;
        for (int i = 0; i < 10; i++) {
            phone = random.nextInt(50000);
        }
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel models = new AlipayTradeAppPayModel();

        String moony = String.valueOf(System.currentTimeMillis()).substring(8);
        models.setOutTradeNo(System.currentTimeMillis() + "");
        models.setTotalAmount(moony);
        models.setSubject("chenlin");
        models.setBody("商品价格: " + moony);
        models.setTimeoutExpress("1c");
        alipayRequest.setBizModel(models);
        System.out.println(models.toString());
        String s = JSON.toJSONString(models);
        System.out.println(s);
        String form = "";
        try {
            //调用SDK生成表单
            form = alipayClient.pageExecute(alipayRequest).getBody();
            return form;

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 支付宝请求交易
     *
     * @param
     * @return
     */
    @RequestMapping("/pays")
    @ResponseBody
    public String pay() {
        log.info("infoMsg:--- 支付宝请求交易开始");
        try {
            //获得初始化的AlipayClient
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
            //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            String moony = String.valueOf(System.currentTimeMillis()).substring(8);
            model.setOutTradeNo(System.currentTimeMillis() + "");
            model.setTotalAmount(moony);
            model.setSubject("chenlin");
            model.setBody("商品价格: " + moony);
            model.setTimeoutExpress("1c");
            alipayRequest.setBizModel(model);
            alipayRequest.setNotifyUrl(AlipayConfig.return_url);
            String form = "";
            //这里和普通的接口调用不同，使用的是sdkExecute
            form = alipayClient.sdkExecute(alipayRequest).getBody();
//
            return form;
        } catch (AlipayApiException e) {
            log.error("errorMsg:--- 支付宝请求交易失败" + e.getMessage());

        }
        return null;
    }
}

