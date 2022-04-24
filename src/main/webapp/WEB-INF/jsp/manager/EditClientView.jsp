<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-Mar-22
  Time: 8:48 PM
  To change this template use File | Settings | File Templates.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--<!DOCTYPE html>-->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Manager Home</title>

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

<h1>Sửa thông tin khách hàng</h1>
<form action="/client/update" method="post">
    <table>
        <tr class="tbl-row">
            <td>ID:</td>
            <td>${id}</td>
            <td><input class="form-control" type="hidden" name="id" value="${id}" required></td>
        </tr>
        <tr class="tbl-row">
            <td>Tên:</td>
            <td><input class="form-control" type="text" name="name" value="${name}" required></td>
        </tr>
        <tr class="tbl-row">
            <td>Địa chỉ:</td>
            <td><input class="form-control" type="text" name="address" value="${address}" required></td>
        </tr>
        <tr class="tbl-row">
            <td>SĐT:</td>
            <td><input class="form-control" type="text" name="phoneNumber" value="${phoneNumber}" required></td>
        </tr>

    </table>
    <input type="hidden" name="action" value="edit"><br>
    <input class="btn btn-primary" type="submit" value="Sửa">
</form>
<form action="/manager/home" method="post">
    <input class="btn btn-primary" type="submit" value="HOME">
</form>

</body>
</html>
