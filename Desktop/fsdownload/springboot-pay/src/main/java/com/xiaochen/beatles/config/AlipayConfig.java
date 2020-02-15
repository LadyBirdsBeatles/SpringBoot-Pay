package com.xiaochen.beatles.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String APP_ID = "2016092800619320";
    // 支付宝网关https://openapi.alipaydev.com/gateway.do
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCd281Kgcon5Z8QwW7IwbMH0OXxv6zkNcq9+HA+81yfnSjeiRmIaReNA0WabCaaARDBfxtql/Ps5+yl3m+i6Q7tOrqN1kXYTXx11bqoIeczW8LZ2yToYwUiaLBpW35y6hk9ANkvOJPNLvrkz5mCal11ZwPI2LSY2JhUNts0E5z3URrv0BMiKz2XKSuD86B7+o5CkC6F7wLH4TocYFnywk3qem5qXEiDCTpp6bRMkB0m7Y6wd4YyRsJisi00bxW9uhEhz9+3VY9vcD369XIksThV1JE+2X+BhgcR6l72V883NGH1CxdxPOrce9NX4egUrF7Z+ImKeDH1gNiIBXyacrcNAgMBAAECggEAS/o54a18Vh2PpKnlH10TRSTHN8bQePpm/W4w4CuTngMvY4RLNLuYKTe07XE2VxNCNE9wK6q9LX4nCWJ5CsjvdUC5/4BtF4Gb8L8Q2tGOjMqSivaN2gemtfv7RIpT3w1LXnAlGDQ79xqwiSZeNUXqe5I8YRPkQFinYIkSkgsKloHBTvCQzNDmJKu5v7ZvjU0zgUkmaFJNL0kiwdUMtDar3xv11FrAi54Cc2b/m34eP95jBuwWcC4068X79u3JPio0/yFrRrJrJH5EM4GV4v3X81SBEmyr92dETFiXwoPqbbFmijfm8/ARfGH87RLW+ZPX8eTkh+iOeSjPIz4fDHVrxQKBgQDe+wuoVW6n0lPExf1j7rtgjHg4uCcDSMibwX4RNV7yMzjgNXR9bG0EmH1q0/cDmXExi+H+i/kWEiVNoYMDjxh/94ohjhWOVDwWbsFPA+lraLAukFp5HUjd/qtrrZUeYjq3Au95/+tmKYXuDwtGi/bTWd1cOu8YpDmXe/m8iP1RQwKBgQC1PAzet1RP7UjKNP8QgkSZ/3TAdlfEZlkhrcHKtK08lcNaicsIBxR4neIjUoU78q+bx2meslWFNl6ATVBmAO1DRifCZY29YTdhBKt2UctrsDI0HwqhsFTtHjYcesw2DsUfnW5Y32cDkkQMny93oNKvVZJv0GQckwPO+kMIkBhpbwKBgQCgDPCMLm5T9Yt9RWQhE93Zalps0/nkyJx9+EAGGBoDnTKo9JWd5XLHBrGXOXgnVD9EghSAQnZTGkk+VL0Wk+LRD5xNg5ADboEG1Q7aKYOlPiqf8auPHa8M8VyX2KudeIKOlIPSPRj5ZrqkapX8WECd6fYS2pZ+SHqyOiHC601wwwKBgGoTCZ1ZQ/m78fyX5CnvdOOFhvoWyevj04tIvcI82aA6L6rQN4stSagHEJKH+/9IXcwZfRIrYq6jUdBacBIjvLrBVwgKlFkhi143c3u/CAaxi9DB0mEsaaiXJCirHtcM8gc9B5bwusR2WWnhydCEyotg1GKpX87xpSsBgzaB0Cf1AoGAR7/EbAxh60xYPH7kjHK4HvmIiY6cGZClh9uN/rLu9Zp94om0XjFO6OQgTs0pd9jNxX4MSz45zcPIsxbhpFncxhFF1hAZ8jdWCyV3dB70STonHFYz87pVjrnj0aa4bkvASmzQNYc5fH7Nai0jEXDxyQMQ3uc1gwkuD62ZAUxZ5io=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmHETxSFp6fyYqyDWl9ZTXJDGUtlrR4mU10v020wTJeX18YnnmihBeodkg4IcRAbhGM5jth5NyHZoCvIVI4kpMuEHO5vJpz+y8Nh80N+fVStIzY/k3pI6zqUrWpz2RtiwS8CsLGGe0lWtpiFe6cMBbGCBY2JX8s7PU9koITWWuGQPgCS9W5qWkyKPAx2BPAkBFuMMspmq4Oa+AJsBEtLBlOUDcoVsHzIvBFUWSqA9KSwFqxvmEK1FU+nUnsEstYV7ZzOBAxT/G7bBG7e9uiT6kughlMQm8h0KyO6WV4w7Ey8VbQfEQ+6H9taW8ss4Mil1ixSS6+FJMWbMdySpGYXehwIDAQAB";
    //数据格式
    public static String FORMAT = "json";
    // 字符编码格式
    public static String CHARSET = "utf-8";
    // 签名方式
    public static String SIGN_TYPE = "RSA2";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String RETURN_URL = "http://localhost:8080/alipay/alipayReturnNotice";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String NOTIFY_URL = "http://localhost:8080/alipay/alipayNotifyNotice";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/return_url.jsp";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/notify_url.jsp";

    // 支付宝网关
    public static String log_path = "D:/Software/IntelliJ IDEA/WorkSpaces/OpenSource/Examples/springboot-pays/src/main/resources/log/alipaylog";


//请在这里配置您的基本信息

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
