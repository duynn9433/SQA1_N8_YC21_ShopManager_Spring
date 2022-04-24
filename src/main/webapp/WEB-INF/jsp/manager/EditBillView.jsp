<%-- 
    Document   : EditProduct
    Created on : Mar 15, 2022, 7:47:45 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--<!DOCTYPE html>-->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Trang Chủ Sản Phẩm</title>

    <link rel="stylesheet" href="bootstraplib/bootstrap.4.0.0.min.css"
          crossorigin="anonymous">
    <script src="bootstraplib/jquery-3.2.1.js" crossorigin="anonymous"></script>
    <script src="bootstraplib/popper.min.js" crossorigin="anonymous"></script>
    <script src="bootstraplib/bootstrap.min.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>

<h1>Sửa thông tin đơn hàng</h1>
<form action="<c:url value="/EditBillServlet"/>" method="post">
    <table>
        <tr class="tbl-row">
            <td>ID:</td>
            <td>${id}</td>
            <td><input class="form-control" type="hidden" name="id" value="${id}" required></td>
        </tr>
        <tr class="tbl-row">
            <td>Ngày thanh toán:</td>
            <td><input class="form-control" type="datetime-local" name="paymentDate" value="${paymentDate}" required></td>
        </tr>
        <%--         <tr class="tbl-row">
                    <td>Tổng thanh toán:</td>
                    <td><input class="form-control" type="text" name="paymentTotal" value="${paymentTotal}" required></td>
                </tr>
        <%--        <tr class="tbl-row">--%>
<%--            <td>Phương thức thanh toán:</td>--%>
<%--            <td><input class="form-control" type="text" name="paymentType" value="${paymentType}" required></td>--%>
<%--        </tr>--%>
        <tr class="tbl-row">
            <td>Giảm giá:</td>
            <td><input class="form-control" type="text" name="saleOff" value="${saleOff}" required></td>
        </tr>
        <tr class="tbl-row">
            <td>Chú thích:</td>
            <td><input class="form-control" type="text" name="note" value="${note}" required></td>
        </tr>
    </table>
    <input type="hidden" name="action" value="edit"><br>
    <input class="btn btn-primary" type="submit" value="Sửa">
</form>
<form action="<c:url value="/manager/ManagerHomeView.jsp"/>" method="post">
    <input class="btn btn-primary" type="submit" value="HOME">
</form>

</body>
</html>

