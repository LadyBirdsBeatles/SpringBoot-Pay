<%--
  Created by IntelliJ IDEA.
  User: xiaochen
  Date: 2019/7/9
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script>
    //获取本月最大的天数
    var getMaxDays = function (year, month) {
        var date = new Date(year, month, 0);
        return date.getDate();
    };
    //计算某月1号是星期几
    var getWeekInMonth = function (year, month) {
        return new Date(year + '/' + month + '/' + '01').getDay();
    };
    alert(getMaxDays(2019,7));
    alert(getWeekInMonth(2019,7))
</script>
</body>
</html>
