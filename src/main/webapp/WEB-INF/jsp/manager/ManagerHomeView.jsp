<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-Mar-22
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Trang chủ quản lí </title>

    <link rel="stylesheet" href="bootstraplib/bootstrap.4.0.0.min.css"crossorigin="anonymous">
    <script src="bootstraplib/jquery-3.2.1.js" crossorigin="anonymous"></script>
    <script src="bootstraplib/popper.min.js" crossorigin="anonymous"></script>
    <script src="bootstraplib/bootstrap.min.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <form action="<c:url value="/ManagementClientServlet"/>" method="post">
                <input class="btn btn-danger" type="submit" value="Log out">
            </form>
        </div>
        <div class="col-sm-4"></div>
        <div class="col-sm-4">
            <h3>Logged in as: ${user.name} </h3>
        </div>
    </div>
    <div class="row" style="margin-top:20px;">
        <div class="col-12 text-center">
            <h1>Trang chủ quản lý</h1>
        </div>
    </div>
    <div class="row ">
        <div class="col-12 text-center">
            <form action="/manager/client" method="get">
                <input class="btn btn-primary" type="submit" value="Quản lý thông tin khách hàng">
            </form>
        </div>
    </div>
    <div class="row ">
        <div class="col-12 text-center">
            <form action="/manager/bill" method="get">
                <input class="btn btn-primary" type="submit" value="Quản lý thông tin hóa đơn">
            </form>
        </div>
    </div>
</div>
</body>
</html>
