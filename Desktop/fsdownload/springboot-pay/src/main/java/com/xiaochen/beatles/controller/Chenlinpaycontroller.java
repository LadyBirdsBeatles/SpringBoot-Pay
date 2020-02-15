package com.xiaochen.beatles.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xiaochen.beatles.config.AlipayConfig;
import com.xiaochen.beatles.pojo.Orders;
import com.xiaochen.beatles.pojo.Product;
import com.xiaochen.beatles.service.OrdersService;
import com.xiaochen.beatles.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/pays")
public class Chenlinpaycontroller {
    @Autowired
    OrdersService orderService;
    @Autowired
    ProductService productService;

    @RequestMapping("/getpay")
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
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = String.valueOf(System.currentTimeMillis()).substring(5);
        String replace = UUID.randomUUID().toString().replace("-", "");
        //付款金额，必填
        String total_amount = String.valueOf(System.currentTimeMillis()).substring(9);
        //订单名称，必填
        String subject =String.valueOf(System.currentTimeMillis()).substring(8);

        //商品描述，可空
        String body = "商品价格: " + total_amount;
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";

        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"" + out_trade_no + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":\"" + total_amount + "\"," +
                "    \"subject\":\"" + subject + "\"," +
                "    \"body\":\"" + body + "\"," +
                "    \"timeout_express\":\"" + timeout_express + "\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2016092800619320\"" +
                "    }" +
                "  }");//填充业务参数

        String form = "";
        try {
            //调用SDK生成表单
            form = alipayClient.pageExecute(alipayRequest).getBody();

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return form;

    }

    /**
     * @Title: AlipayController.java
     * @Package com.sihai.controller
     * @Description: 支付宝同步通知页面
     * Copyright: Copyright (c) 2017
     * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
     * @author sihai
     * @date 2017年8月23日 下午8:51:01
     * @version V1.0
     */
    @RequestMapping(value = "/alipayReturnNotice")
    public ModelAndView alipayReturnNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {
        System.out.println("支付成功, 进入同步通知接口*******");
        log.info("支付成功, 进入同步通知接口...");
        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE); //调用SDK验证签名
        System.out.println(signVerified);

        ModelAndView mv = new ModelAndView("alipaySuccess");
        //——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {

            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");


            System.out.println("trade_no:" + trade_no + "<br/>out_trade_no:" + out_trade_no + "<br/>total_amount:" + total_amount);
            // 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水
            orderService.updateOrderStatus(out_trade_no, trade_no, total_amount);

            System.out.println("支付宝同步通知 :  " + out_trade_no + trade_no + total_amount);
            Orders order = orderService.getOrderById(out_trade_no);
            System.out.println("支付宝同步通知 :  " + order);
            Product product = productService.getProductById(order.getProductId());
            System.out.println("支付宝同步通知 :  " + product);

            log.info("********************** 支付成功(支付宝同步通知) **********************");
            log.info("* 订单号: {}", out_trade_no);
            log.info("* 支付宝交易号: {}", trade_no);
            log.info("* 实付金额: {}", total_amount);
            log.info("* 购买产品: {}", product.getName());
            log.info("***************************************************************");
            mv.addObject("out_trade_no", out_trade_no);
            mv.addObject("trade_no", trade_no);
            mv.addObject("total_amount", total_amount);
            mv.addObject("productName", product.getName());
        } else {
            System.out.println("验签失败");
        }

        return mv;
    }

    /**
     * @Title: AlipayController.java
     * @Package com.sihai.controller
     * @Description: 支付宝异步 通知页面
     * Copyright: Copyright (c) 2017
     * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
     * @author sihai
     * @date 2017年8月23日 下午8:51:13
     * @version V1.0
     */
    @RequestMapping(value = "/alipayNotifyNotice")
    @ResponseBody
    public String alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {

        log.info("支付成功, 进入异步通知接口...");

        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] SET = (String[]) requestParams.get(name);
            String SETtr = "";
            for (int i = 0; i < SET.length; i++) {
                SETtr = (i == SET.length - 1) ? SETtr + SET[i]
                        : SETtr + SET[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//			SETtr = new String(SETtr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, SETtr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE); //调用SDK验证签名
        System.out.println(signVerified);
        //——请在这里编写您的程序（以下代码仅作参考）——

		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/
        if (signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            if (trade_status.equals("TRADE_FINISHED")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知

                // 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水
                orderService.updateOrderStatus(out_trade_no, trade_no, total_amount);
                System.out.println("支付宝异步通知 :  " + out_trade_no + trade_no + total_amount);
                Orders order = orderService.getOrderById(out_trade_no);
                System.out.println("支付宝异步通知 :  " + order);
                Product product = productService.getProductById(order.getProductId());
                System.out.println("支付宝异步通知 :  " + product);

                log.info("********************** 支付成功(支付宝异步通知) **********************");
                log.info("* 订单号: {}", out_trade_no);
                log.info("* 支付宝交易号: {}", trade_no);
                log.info("* 实付金额: {}", total_amount);
                log.info("* 购买产品: {}", product.getName());
                log.info("***************************************************************");
            }
            log.info("支付成功...");

        } else {//验证失败
            log.info("支付, 验签失败...");
        }

        return "success";
    }
}
