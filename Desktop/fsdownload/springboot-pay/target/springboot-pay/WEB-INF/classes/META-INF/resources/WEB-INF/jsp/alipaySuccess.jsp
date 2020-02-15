<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="<%=request.getContextPath() %>/lib/jquery-3.3.1.min.js" type="text/javascript"></script>

<html>

<head>

</head>

<body>
<h1 style="color: green;" align="center">购买成功</h1>
<table border="1px" align="center" bgcolor="#f0f8ff">
    <tr>
        <td>
            订单编号 &nbsp;: ${out_trade_no }
        </td>
    </tr>
    <td>
        支付宝交易号 &nbsp;: ${trade_no }
    </td>
    <tr>
    <tr>
        <td>
            实付金额 &nbsp;: ${total_amount }
        </td>
    </tr>
    <tr>
        <td>
            <p style="color: palevioletred;" align="center"><a
                    href="<%=request.getContextPath() %>/alipay/alipayQuerypays?outTradeNo=${out_trade_no }&tradeNo=${trade_no }">查询或者退款</a>
            </p>
        </td>
    </tr>
</table>
</body>

</html>


