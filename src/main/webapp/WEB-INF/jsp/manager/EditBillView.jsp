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
<% String error = request.getSession().getAttribute("error").toString();%>
<script>
    const error = "<%= error %>";
    if (error !== "") {
        alert(error);
        <%request.getSession().removeAttribute("error");%>
    }
</script>

<h1>Sửa thông tin đơn hàng</h1>
<div class="container">
    <div class="row">
        <div class="col-md-6" style="border:1px solid #cecece;">
            <form action="/bill/save" method="post">
                <table>
                    <tr class="tbl-row">
                        <td>ID:</td>
                        <td>${bill.id}</td>
                        <td><input class="form-control" type="hidden" name="id" value="${bill.id}" required></td>
                    </tr>
                    <tr class="tbl-row">
                        <td>Ngày thanh toán:</td>
                        <td><input class="form-control" type="datetime-local" name="paymentDate"
                                   value="${bill.paymentDate}" required></td>
                    </tr>
                    <tr class="tbl-row">
                        <td>Giảm giá:</td>
                        <td><input class="form-control" type="text" name="saleOff" value="${bill.saleOff}" required>
                        </td>
                    </tr>
                    <tr class="tbl-row">
                        <td>Chú thích:</td>
                        <td><input class="form-control" type="text" name="note" value="${bill.note}" required></td>
                    </tr>
                </table>
                <input type="hidden" name="action" value="edit"><br>
                <input class="btn btn-primary" type="submit" value="Sửa">
            </form>
        </div>
        <div class="col-md-6" style="border:1px solid #cecece;">

        </div>
    </div>
</div>

<div class="container">
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>Tên</th>
                <th>Đơn vị</th>
                <th>Giá</th>
                <th>Số lượng</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${bill.buyingGoodsList}" var="element" varStatus="status">
                <tr>
                    <td>${element.goods.name}</td>
                    <td>${element.goods.unity}</td>
                    <td>${element.goods.pricePerUnit}</td>
                        <%--                                <td>${element.amount}</td>--%>
                    <td>
                        <form action="/bill/update-goods" method="post">
                            <input type="text" name="amount" placeholder="Số lượng mặt hàng"
                                   value="${element.amount}" size="5" maxlength="5" pattern="[0-9]+" required/>
                            <input type="hidden" name="action" value="update_goods">
                            <input type="hidden" name="index" value="${status.count}">

                    </td>
                    <td>
                        <input type="submit" value="Sửa">
                        </form>
                    </td>
                    <td>
                        <form action="/bill/remove-goods" method="post">
                            <input type="hidden" name="action" value="remove_goods">
                            <input type="hidden" name="index" value="${status.count}">
                            <input type="submit" value="Xoá">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<form action="/manager/home" method="post">
    <input class="btn btn-primary" type="submit" value="HOME">
</form>

</body>
</html>

