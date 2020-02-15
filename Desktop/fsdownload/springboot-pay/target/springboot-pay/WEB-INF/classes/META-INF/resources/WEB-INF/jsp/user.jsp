<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<head>
    <title>用户列表</title>
</head>

<body>

<table border="1px" align="center" bgcolor="#f0f8ff">
    <tr>
        <td>
            用户编号
        </td>
        <td>
            用户名称
        </td>
        <td>
            性别
        </td>
    </tr>
    <c:forEach items="${userList }" var="u">
        <tr>
            <td>
                    ${u.id }
            </td>
            <td>
                    ${u.username}
            </td>
            <td>
                    ${u.sex}
            </td>
        </tr>

    </c:forEach>
</table>
</body>
</html>


