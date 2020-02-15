<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="<%=request.getContextPath() %>/lib/jquery-3.3.1.min.js" type="text/javascript"></script>

<html>

<head>
    <title>购买</title>
</head>

<body>
<%--/alipay/createOrder.action--%>
<form action="<%=request.getContextPath() %>/alipay/createOrder" method="post">
    <input type="hidden" id="productId" name="productId" value="${p.id }"/>
    <table border="1px" align="center" bgcolor="#f0f8ff">
        <tr>
            <td>
                产品编号: ${p.id }
            </td>
        </tr>
        <tr>
            <td>
                产品名称: ${p.name }
            </td>
        </tr>
        <tr>
            <td>
                产品价格: ${p.price }
            </td>
        <tr>
        <tr>
            <td>
                购买个数： <input id="buyCounts" name="buyCounts"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="form提交，生成订单" style="color: deeppink"/>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="ajax提交，生成订单" onclick="createOrder() " style="color: deeppink"/>
            </td>
        </tr>
    </table>
</form>


<input type="hidden" id="hdnContextPath" name="hdnContextPath" value="<%=request.getContextPath() %>"/>
</body>

</html>

<script type="text/javascript">


    var hdnContextPath = $("#hdnContextPath").val();


    function createOrder() {
        console.log(hdnContextPath);
        $.ajax({
            url: "/alipay/createOrder",
            type: "POST",
            data: {"productId": $("#productId").val(), "buyCounts": $("#buyCounts").val()},
            dataType: "json",
            success: function (data) {
                if (data.status == 200 && data.msg == "OK") {
                    console.log(JSON.stringify(data.data));
                    // 提交订单成功后, 进入购买页面
                    window.location.href = "/alipay/goPay?orderId=" + data.data;
                } else {
                    alert(data.msg);
                    console.log(JSON.stringify(data));
                }
            }
        });
    }

</script>

