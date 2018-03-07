<%--
  Created by IntelliJ IDEA.
  User: caoxin
  Date: 2018/1/28
  Time: 19:18
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>E-COMMERCE HOMEPAGE</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/stylesheet.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="head-banner">
    <div id="blank"></div>
    <div id="account-info" class="banner">
        <span >${sessionScope.username}</span>
        <span id="cur-time">,欢迎登录!当前时间为:${sessionScope.curtime}</span>
    </div>
    <div id="forward" class="banner">
        <a id="buy" class="function" href="" >我要购买</a>
        <a id="log-out" class="function" href="${pageContext.request.contextPath}/action/log-out!logOut.do" target="_self"> 退出登录 </a>
    </div>
</div>
<%--功能菜单--%>
<div id="menu" class="show">
    <form action="${pageContext.request.contextPath}/action/seller!showSeller.do?sellerId="+${sessionScope.sellerid} method="post">
        <c:choose>
            <c:when test="${sessionScope.sellerid ne null&&sessionScope.sellerid ne 0&&sessionScope.buyerid ne null&&sessionScope.buyerid ne 0 }">
                <button id="shop" class="menu" type="submit" name="shop" value="shop" >我的店铺</button>
                <button id="sell-buy-orders" class="menu" type="button" name="buy-orders" value="buy-orders">我的购买订单</button>
                <button id="sell-orders" class="menu" type="button" name="sell-orders" value="sell-orders" >我的出售订单</button>
                <button id="buy-addr" class="menu" type="button" name="buy-addr" value="buy-addr">收货地址</button>
            </c:when>
            <c:when test="${sessionScope.sellerid ne null&&sessionScope.sellerid ne 0&&(sessionScope.buyerid ne null||sessionScope.buyerid ne 0) }">
                <button id="shop" class="menu" type="submit" name="shop" value="shop" >我的店铺</button>
                <button id="sell-orders" class="menu" type="button" name="sell-orders" value="sell-orders" >我的出售订单</button>
                <button id="buy-addr" class="menu" type="button" name="buy-addr" value="buy-addr">收货地址</button>
            </c:when>
            <c:otherwise>
                <button  id="buy-orders" class="menu" type="button" name="buy-orders" value="buy-orders" >我的购买订单</button>
                <button id="buyer-addr" class="menu" type="button" name="buy-addr" value="buy-addr">收货地址</button>
            </c:otherwise>
        </c:choose>
    </form>
</div>
<%--展示信息区域--%>
<div id="display" class="show"></div>
<script>
    $(function(){
//    我的购买订单跳转请求
        $("#sell-buy-orders").click(function(){
            $.ajax({
                type : "POST",
                url : "${pageContext.request.contextPath}/action/order!showOrder.do?buyerId="+${sessionScope.buyerid},
                dataType : "text",
                contentType:"application/x-www-form-urlencoded",
                success : function(data) {
                    $("#display").html(data);
                },
                error : function(e) {
                    alert("参数错误！");
                }
            });
        })

        $("#buy-orders").click(function(){
            $.ajax({
                type : "POST",
                url : "${pageContext.request.contextPath}/action/order!showOrder.do?buyerId="+${sessionScope.buyerid},
                dataType : "text",
                contentType:"application/x-www-form-urlencoded",
                success : function(data) {
                    $("#display").html(data);
                },
                error : function(e) {
//                    console.log(e);
                    alert("参数错误！");
                }
            });
        })
//    我的出售订单跳转请求
        $("#sell-orders").click(function(){
            $.ajax({
                type : "POST",
                url : "${pageContext.request.contextPath}/action/OrderDetail!orderDetailResult.do?sellerId="+${sessionScope.sellerid},
                dataType : "text",
                contentType:"application/x-www-form-urlencoded",
                success : function(data) {
                    $("#display").html(data);
                },
                error : function(e) {
//                    console.log(e);
                    alert("参数错误！");
                }
            })
        });
//    点击id为buy的a标签传递buyerid参数
        $("#buy").click(function (){
//            var id=1;
            document.getElementById("buy").href="${pageContext.request.contextPath}/action/Goods!goodsShow.do?buyerid="+${sessionScope.buyerid};
        });
//        点击收货地址的跳转
        $("#buy-addr").click(function(){
//            var thisId=1;
            $.ajax({
                type : "POST",
                url : "${pageContext.request.contextPath}/action/addr!addr.do?buyerId="+${sessionScope.buyerid},
                dataType : "text",
                contentType:"application/x-www-form-urlencoded",
                success : function(data) {
                    $("#display").html(data);
                },
                error : function(e) {
//                    console.log(e);
                    alert("参数错误！");
                }
            });
        })
        $("#buyer-addr").click(function(){
//            var thisId=1;
            $.ajax({
                type : "POST",
                url :"${pageContext.request.contextPath}/action/addr!addr.do?buyerId="+${sessionScope.buyerid},
                dataType : "text",
                contentType:"application/x-www-form-urlencoded",
                success : function(data) {
                    $("#display").html(data);
                },
                error : function(e) {
//                    console.log(e);
                    alert("参数错误！");
                }
            });
        })
    })
</script>
</body>
</html>
