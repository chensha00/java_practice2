<%--
  Created by IntelliJ IDEA.
  User: Nick
  Date: 2018/1/28
  Time: 20:08
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>E-COMMERCE PAY ORDER</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/stylesheet.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="pay-head">
    <img src="${pageContext.request.contextPath}/image/pay-order-head.jpg" id="pay-pic"/>
</div>
<form action="${pageContext.request.contextPath}/pay-order!payOrder.do" method="post">
    <div id="pay-this-order">
        <table id="order-detail" style="text-align: center;height: 300px;width:100%;" border="1">
            <tr>
                <th>序号</th>
                <th>商家序号</th>
                <th>商品序号</th>
                <th>订单总额</th>
                <th>订单号</th>
                <th>订单生成时间</th>
            </tr>
            <c:forEach items="${requestScope.buyList}" var="list">
                <tr>
                    <td>${list.getId()}</td>
                    <td>${list.getSellerId()}</td>
                    <td>${list.getGoodsId()}</td>
                    <td>${list.getAmount}</td>
                    <td>${list.getOrderNumber()}</td>
                    <td>${list.getCreationTime()}</td>
                </tr>
            </c:forEach>
            <tr><td colspan="2">支付密码</td>
                <td colspan="4"><input type="password" name="paypassword" id="payword"></td></tr>
            <tr>
                <td colspan="6">
                    <button id="commit" class="order-operate" type="submit" name="id" value="${requestScope.id}">支付订单</button>
                    <button id="cancel" class="order-operate" type="button" onclick="goBack()">取消支付</button>
                </td>
            </tr>
        </table>
    </div>
</form>
<script>
    <%--取消按钮动作--%>
    $(function(){
        $("#cancel").click(function(){
           window.history.go(-2);
        })
    });
</script>
</body>
</html>
