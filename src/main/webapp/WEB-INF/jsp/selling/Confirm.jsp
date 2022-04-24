<%--
  Created by IntelliJ IDEA.
  User: nndng
  Date: 3/21/2022
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Xác nhận hoá đơn</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="bootstraplib/bootstrap.4.0.0.min.css" crossorigin="anonymous">
    <script src="bootstraplib/jquery-3.2.1.js" crossorigin="anonymous"></script>
    <script src="bootstraplib/popper.min.js" crossorigin="anonymous"></script>
    <script src="bootstraplib/bootstrap.min.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<body>
<%
    String msg = (String) request.getSession().getAttribute("confirmBillMsg");
    System.out.println("View" + msg);

    if (msg != null) {
%>
<script type="text/javascript">
    var msg = "${addClientMsg}";
    alert(msg);
    window.location.href = '/seller/home';
</script>
<%
        request.getSession().removeAttribute("confirmBillMsg");
    }
%>
<div class="container-fluid">
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>Tên</th>
                <th>Đơn vị</th>
                <th>Giá</th>
                <th>Số lượng</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${bill.buyingGoodsList}" var="element">
                <tr>
                    <td>${element.goods.name}</td>
                    <td>${element.goods.unity}</td>
                    <td>${element.goods.pricePerUnit}</td>
                    <td>${element.amount}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <div>
        <label>Tổng tiền: ${bill.paymentTotal}</label><br>
        <label>Giảm giá: ${bill.saleOff}</label><br>
        <label>Thành tiền: ${bill.paymentTotal - bill.saleOff*bill.paymentTotal}</label><br>
    </div>

    <div>
        <label class="form-label">Tên:</label>
        &nbsp;
        <label class="form-label">${bill.client.name}</label>
        <br>
        <label class="form-label">Số điện thoại:</label>
        &nbsp;
        <label class="form-label">${bill.client.phoneNumber}</label>
        <br>
        <label class="form-label">Ngày mua hàng:</label>
        &nbsp;
        <label class="form-label">${bill.paymentDate}</label>
    </div>
    <div>
        <table>
            <tr>
                <td>
                    <form action="/selling/save-bill" method="post">
                        <input type="hidden" name="action" value="save_bill">
                        <button class="btn btn-primary" type="submit">Lưu</button>
                    </form>
                </td>
                <td>
                    <form action="/selling/cancel-bill" method="post">
                        <input type="hidden" name="action" value="cancel_bill">
                        <button class="btn btn-primary" type="submit">Huỷ</button>
                    </form>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>

</html>
