package com.xiaochen.beatles.test;

import com.alipay.api.request.AlipayDataAiserviceCloudbusPredictresultQueryRequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class Demo {
    /*public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");//加载驱动类
        String url = "jdbc:mysql://localhost:3306/springboot-pay?useUnicode=true&characterEncoding=utf-8&userSSL=false&serverTimezone=GMT%2B8";
        String username = "root";
        String password = "#c+Admin.27";
        Connection conn = DriverManager.getConnection(url, username, password);//用参数得到连接对象
        System.out.println("连接成功！");
        System.out.println(conn);

    }*/
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        // 获得年份
        int year = calendar.get(Calendar.YEAR);
        // 获得月份
        int month = calendar.get(Calendar.MONTH) + 1;
        // 获得日期
        int date = calendar.get(Calendar.DATE);
        // 获得小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        // 获得分钟
        int minute = calendar.get(Calendar.MINUTE);
        // 获得秒
        int second = calendar.get(Calendar.SECOND);
        //获取毫秒
        int millissecond = calendar.get(Calendar.MILLISECOND);
        // 获得星期几（注意（这个与Date类是不同的）：1代表星期日、2代表星期1、3代表星期二，以此类推）
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        //生成商户订单号
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(year).append(month).append(date).append(hour).append(minute).append(second).append(millissecond).append(day);
        System.out.println(stringBuffer);

    }
}
