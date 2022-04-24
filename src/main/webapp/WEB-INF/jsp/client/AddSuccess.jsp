<%--
  Created by IntelliJ IDEA.
  User: nndng
  Date: 3/13/2022
  Time: 12:04 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Thêm thành công</title>
</head>
<body>

<%
    String msg = (String) request.getSession().getAttribute("addClientMsg");
    System.out.println("View" + msg);

    if (msg!=null){
%>
<script type="text/javascript">
    var msg = "${addClientMsg}";
    alert(msg);
    window.location.href='/seller/home';
</script>
<%
        request.getSession().removeAttribute("addClientMsg");
    }
%>


<h3>Thông tin khách hàng</h3>
<table cellpadding="2" cellspacing="2" border="1">
    <tr>
        <td>Tên </td>
        <td>${client.name }</td>
    </tr>
    <tr>
        <td>Số điện thoại</td>
        <td>${client.phoneNumber }</td>
    </tr>
    <tr>
        <td>Địa chỉ</td>
        <td>${client.address }</td>
    </tr>
    <tr>

    </tr>
</table>
<form:form method="post" action="/client/accept" modelAttribute="client">
    <input type="hidden" name="action" value="accept">
    <form:input type="hidden" path="name" value="${client.name }"/>
    <form:input type="hidden" path="phoneNumber" value="${client.phoneNumber }"/>
    <form:input type="hidden" path="address" value="${client.address }"/>
    <input type="submit" value="Xác nhận">
</form:form>
</body>
</html>