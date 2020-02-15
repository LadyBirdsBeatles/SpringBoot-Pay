package com.xiaochen.beatles.test;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.xiaochen.beatles.config.AlipayConfig;
import com.xiaochen.beatles.mapper.FlowMapper;
import com.xiaochen.beatles.mapper.OrdersMapper;
import com.xiaochen.beatles.mapper.ProductMapper;
import com.xiaochen.beatles.pojo.*;
import com.xiaochen.beatles.service.CostService;
import com.xiaochen.beatles.service.OrdersService;
import com.xiaochen.beatles.service.ProductService;
import com.xiaochen.beatles.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsTest {

    @Autowired
    private CostService costService;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrdersService ordersService;

    @Test
    public void getpay() {
        List<Pay> pays = costService.queryPay();
        for (Pay users : pays) {
            if (users != null) {
                System.out.println(users);
            } else {
                System.out.println("无数据");
            }

        }
    }

    @Test
    public void getcost() {
        String p = "{\n" + "\t\"payMoney\": \"1598\"\n" + "}";
        //boolean b = costService.addPay(p);
        Orders orderById = ordersService.getOrderById("202021418161696");

        System.out.println(orderById);

    }

    /**
     *
     */


    @Autowired
    private FlowMapper flowMapper;

    @Test
    public void getLists() {
        Orders orders = ordersMapper.selectByPrimaryKey("1263861");
        System.out.println(orders);
        /**
         * 增加流水
         */
        //String flowId = sid.nextShort();
        // flow.setId(flowId);
        String outTradeNo = (String.valueOf((int) ((Math.random() * 9 + 1) * 1000000)));
        Flow flow = new Flow();
        flow.setId(outTradeNo);
        flow.setFlowNum(outTradeNo);
        flow.setOrderNum("1263861");
        flow.setProductId(orders.getProductId());
        flow.setPaidAmount("20.00");
        flow.setPaidMethod(1);
        flow.setBuyCounts(orders.getBuyCounts());
        flow.setCreateTime(new Date());
        int i = flowMapper.insert(flow);
        System.out.println(i);
    }


    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;


    @Test
    public void getBeans() {
        Orders orders = new Orders();
        long uid = (long) ((Math.random() * 9 + 1) * 100000);
        orders.setId(String.valueOf(uid));
        orders.setOrderNum("10008");
        orders.setOrderStatus("1");
        orders.setOrderAmount("66555");
        orders.setPaidAmount("10003");
        orders.setProductId("5555");
        orders.setBuyCounts(445555);
        orders.setCreateTime(new Date());
        orders.setPaidTime(new Date());
        ordersService.saveOrder(orders);
        //ordersService.updateOrderStatus(String.valueOf(1),String.valueOf("0000001"),String.valueOf("105"));
        Orders orderById = ordersService.getOrderById(String.valueOf(718348));
        //System.out.println(orderById);
        User user = new User();
        user.setId(String.valueOf(17));
        user.setUsername("辜");
        user.setSex("男");
        //userService.saveUser(user);
        //User userById = userService.getUserById(String.valueOf(17));
        //userService.updateUserById(user);
        //userService.deleteUserById(String.valueOf(17));
        //Product productById = productService.getProductById(String.valueOf(1));
        //System.out.println(productById);
        /*List<User> userList = userService.getUserList();
        for (User user1 : userList) {
            System.out.println(user1);
        }*/
        System.out.println(orderById);


    }

    /*@Autowired
    private Sid sid;*/
    @Autowired
    ProductMapper productMapper;

    @Test

    public void ChenLin() {

        Product product = new Product();
        String name = "郎酒";
        String substring = String.valueOf(System.currentTimeMillis()).substring(10);
        String substring1 = substring.substring(0, 1);
        String substring2 = substring.substring(1);
        StringBuffer stringBuffer = new StringBuffer().append(substring1).append(".").append(substring2);
        String s1 = String.valueOf(stringBuffer);
        String price = "";
        if (substring1.equals("0")) {
            /*Integer.valueOf(substring1) == 0*/
            price = s1;
        } else {
            price = substring;

        }
        product.setId((String.valueOf(System.currentTimeMillis())).substring(5));
        product.setName(name);
        product.setPrice(price);
        int insert = productMapper.insert(product);
        System.out.println(insert);

    }

    @Test
    public void alipays() {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
        // 2、设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        AlipayTradePayModel model = new AlipayTradePayModel();
        request.setBizModel(model);
        String moony = String.valueOf(System.currentTimeMillis()).substring(8);
        System.out.println(moony);
        model.setOutTradeNo(System.currentTimeMillis() + "");
        model.setSubject("chenlin");
        model.setTotalAmount(moony);
        model.setBody("商品价格: " + moony);
        model.setTimeoutExpress("1c");
        // model.setAuthCode("beaucg6198@sandbox.com");//沙箱钱包中的付款码
        //model.setScene("bar_code");
        String from = "";
        AlipayTradePayResponse response = null;
        try {
            response = alipayClient.execute(request);
            System.out.println(response.getBody());
            System.out.println(response.getTradeNo());
            from = response.getBody();
            System.out.println(from);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

    }
}
