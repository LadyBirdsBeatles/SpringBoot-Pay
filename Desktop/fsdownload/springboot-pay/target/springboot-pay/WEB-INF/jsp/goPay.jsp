<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="<%=request.getContextPath() %>/lib/jquery-3.3.1.min.js" type="text/javascript"></script>

<html>

<head>
    <title>订单列表</title>
</head>

<body>

<form id="payForm" action="<%=request.getContextPath() %>/alipay/goAlipay" method="post">
    <input type="hidden" name="orderId" value="${order.id }"/>
    <table border="1px" align="center" bgcolor="#f0f8ff">
        <tr>
            <td>
                订单编号: ${order.id }
            </td>
        </tr>
        <tr>
            <td>
                产品名称: ${p.name }
            </td>
        <tr>
        <tr>
            <td>
                订单价格: ${order.orderAmount }
            </td>
        <tr>
        <tr>
            <td>
                购买个数：${order.buyCounts }
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="前往支付宝进行支付" style="color: deeppink"/>
                <input type="button" value="微信扫码支付" style="color: deeppink" onclick="wxpayDisplay()">
            </td>
        </tr>
    </table>
</form>


<input type="hidden" id="hdnContextPath" name="hdnContextPath" value="<%=request.getContextPath() %>"/>
</body>

</html>


<script type="text/javascript">

    function wxpayDisplay() {
        debugger;

        var hdnContextPath = $("#hdnContextPath").val();

        $("#payForm").attr("action", hdnContextPath + "/wxpay/createPreOrder.action");
        $("#payForm").submit();
    }

    // 	$(document).ready(function() {

    // 		var hdnContextPath = $("#hdnContextPath").val();

    // 	});


</script>

