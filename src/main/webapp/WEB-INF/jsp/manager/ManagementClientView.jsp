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
    <div class="row">
        <div class="col-sm-4">
            <form action="<c:url value="/ManagerHomeServlet"/>" method="post">
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
            <h1>Quản lí khách hàng</h1>
        </div>
    </div>
    <div class="row ">
        <div class="col-12 text-center">
            <form class="form-inline" action="/client/search" method="POST">
                <div class="input-group">
                    <input type="text" class="form-control" size="80" placeholder="SĐT của Khách hàng"
                           name="search_phone">
                    <input class="btn btn-primary" type="submit" name="search" value="Tìm kiếm">
                    <input type="hidden" name="action" value="search">
                </div>
            </form>

            <table cellspacing="6" cellpadding="6" border="1">
                <tr>
                    <th>ID</th>
                    <th>Tên</th>
                    <th>Địa chỉ</th>
                    <th>SĐT</th>
                </tr>

                <c:forEach var="i" items="${listClient}" varStatus="status">
                    <tr valign="top">
                        <td>${i.ID}</td>
                        <td>${i.name}</td>
                        <td>${i.address}</td>
                        <td>${i.phoneNumber}</td>
                        <td>
                            <form action="/client/edit" method="POST">
                                <input type="hidden" name="eid" value="${i.ID}">
                                <input type="hidden" name="ename" value="${i.name}">
                                <input type="hidden" name="eaddress" value="${i.address}">
                                <input type="hidden" name="ephoneNumber" value="${i.phoneNumber}">
                                <input type="submit" class="btn btn-primary" value="Sửa" name="edit">
                                <input type="hidden" name="action" value="edit">
                            </form>
                        </td>

                        <td>
                            <!--form action="<c:url value="/ManagementClientServlet"/>" method="POST">
                                <input type="hidden" name="did" value="${i.ID}">
                                <input type="hidden" name="dname" value="${i.name}">
                                <input type="hidden" name="daddress" value="${i.address}">
                                <input type="hidden" name="dphoneNumber" value="${i.phoneNumber}">
                                <input type="submit" class="btn btn-primary" value="Xóa" name="delete">
                                <input type="hidden" name="action" value="delete">
                            </form-->
                            <button class="btn btn-primary" onclick="Confirm(${i.ID})">Xoá</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </div>
    </div>
</div>
<script>
    function Confirm(id){
        var choose = confirm("ban co muon xoa khach hang nay?");
        if(choose==true){
            window.location.href='/client/delete?id='+id;
        }
    }
</script>
</body>
</html>
