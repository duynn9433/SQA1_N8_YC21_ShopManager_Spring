<%--
    Document   : SellerHome
    Created on : Nov 2, 2021, 9:19:22 PM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <div class="row">

        <div class="col-sm-4">
            <form action="/user/logout" method="get">
                <input class="btn btn-danger" type="submit" value="Đăng xuất">
            </form>
        </div>

        <div class="col-sm-4"></div>

        <div class="col-sm-4">
            <h3>Chào: ${user.name} </h3>
        </div>

    </div>

    <div class ="row" style = "margin-top:20px;">
        <div class="col-12 text-center">
            <h1>Trang chủ nhân viên bán hàng</h1>
        </div>
    </div>

    <div class="row ">
        <div class="col-12 text-center">
            <table>
                <tr>
                    <form action ="/seller/add-client" method="get">
                        <input class="btn btn-primary" type="submit" value="Thêm khách hàng">
                    </form>
                </tr>
                <tr></tr> <br><br>
                <tr>
                    <form action = "<c:url value="/seller/selling"/>" method="get">
                        <input class="btn btn-primary" type="submit" value="Bán hàng">
                    </form>
                </tr>
            </table>

        </div>
    </div>
</div>

</body>
</html>
