<%--
  Created by IntelliJ IDEA.
  User: nndng
  Date: 3/13/2022
  Time: 11:40 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Thêm khách hàng</title>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row col-12 text-center" style="margin-top:180px;">
        <h3>Thông tin khách hàng</h3>
        ${errors }
        <%--@elvariable id="client" type="Client"--%>
        <form:form class = "form-inline" method="post" action="/client/add" charset="utf-8" modelAttribute="client" >
            <input type="hidden" name="action" value="add">
            <div class="input-group">
                <table>
                    <tr>
                        <td>Tên</td>
                        <td><form:input class="form-control" type="text" path="name" value="${client.name }"/></td>
                    </tr>
                    <tr></tr>
                    <tr>
                        <td>Số điện thoại</td>
                        <td><form:input class="form-control" type="text" path="phoneNumber" value="${client.phoneNumber }"/>
                        </td>
                    </tr>
                    <tr></tr>
                    <tr>
                        <td>Địa chỉ</td>
                        <td><form:input class="form-control" type="text" path="address" value="${client.address }"/></td>
                    </tr>
                    <tr></tr>
                    <tr>
                        <td><input class="button" type="submit" value="Thêm khách hàng"></td>
                    </tr>
                </table>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
