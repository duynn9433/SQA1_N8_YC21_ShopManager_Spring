<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-Mar-22
  Time: 9:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--<!DOCTYPE html>-->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Seller Home</title>

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

<div class="container">
<%--    <div class="row">--%>
<%--        <div class="col-sm-4">--%>
<%--            <form action="<c:url value="/ManagerHomeServlet"/>" method="post">--%>
<%--                <input class="btn btn-danger" type="submit" value="Log out">--%>
<%--            </form>--%>
<%--        </div>--%>
<%--        <div class="col-sm-4"></div>--%>
<%--        <div class="col-sm-4">--%>
<%--            <h3>Logged in as: ${user.name} </h3>--%>
<%--        </div>--%>
<%--    </div>--%>
    <div class="row" style="margin-top:20px;">
        <div class="col-12 text-center">
            <h1>Quản lí hoá đơn</h1>
        </div>
    </div>
    <div class="row ">
        <div class="col-12 text-center">
            <form class="form-inline" action="<c:url value="/ManagementBillServlet"/>" method="POST">
                <div class="input-group">
                    <input type="text" class="form-control" size="80" placeholder="id của đơn hàng"
                           name="search_id">
                    <input class="btn btn-primary" type="submit" name="search" value="Tìm kiếm">
                    <input type="hidden" name="action" value="search">
                </div>
            </form>

            <table cellspacing="6" cellpadding="6" border="1">
                <tr>
                    <th>id</th>
                    <th>Ngày mua hàng</th>
                    <th>Tổng thanh toán</th>
                    <th>giảm giá</th>
                    <th>chú ý</th>
                </tr>

                <c:forEach var="b" items="${listBill}" varStatus="status">
                    <tr valign="top">
                        <td>${b.id}</td>
                        <td>${b.paymentDate}</td>
                        <td>${b.paymentTotal}</td>
                        <td>${b.saleOff}</td>
                        <td>${b.note}</td>
                        <td>
                            <form action="<c:url value="/ManagementBillServlet"/>" method="POST">
                                <input type="hidden" name="eid" value="${b.id}">
                                <input type="hidden" name="epaymentDate" value="${b.paymentDate}">
                                <input type="hidden" name="epaymentTotal" value="${b.paymentTotal}">
                                <input type="hidden" name="esaleOff" value="${b.saleOff}">
                                <input type="hidden" name="enote" value="${b.note}">
                                <input type="submit" class="btn btn-primary" value="Sửa" name="edit">
                                <input type="hidden" name="action" value="edit">
                            </form>
                        </td>

                        <td>
                            <button class="btn btn-primary" onclick="Confirm(${b.id})">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </div>
    </div>
</div>
<script>
    function Confirm(id){
        var choose = confirm("ban co muon xoa hoa don nay?");
        if(choose==true){
            window.location.href='ManagementBillServlet?id='+id;
            //var x = window.location.href;
            //console.log(x);
        }
    }
</script>
</body>
</html>
