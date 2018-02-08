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
<%--设置页面使用参数--%>
<%--<%--%>
    <%--String basePath=request.getContextPath();--%>
    <%--request.setAttribute("basepath",basePath);--%>
<%--%>--%>
<div id="head-banner">
    <div id="account-info" class="banner">
        <span >${sessionScope.username},欢迎登录!</span>
        <span id="cur-time">当前时间为:${sessionScope.curtime}</span>
    </div>
    <div id="forward" class="banner">
        <a id="buy"  href="" >我要购买</a>
        <a id="log-out" href="jsp/log_in.jsp" target="_self"> 退出登录 </a>
    </div>
</div>
<%--功能菜单--%>
    <div id="menu" class="show">
        <form action="${pageContext.request.contextPath}/HomepageServlet.htm" method="post">
                <c:choose>
                    <c:when test="${sessionScope.sellerid ne null&&sessionScope.sellerid ne 0 }">
                        <button id="shop" class="menu" type="submit" name="shop" value="shop" >我的店铺</button>
                        <button id="sell-buy-orders" class="menu" type="button" name="buy-orders" value="buy-orders">我的购买订单</button>
                        <button id="sell-orders" class="menu" type="button" name="sell-orders" value="sell-orders" >我的出售订单</button>
                    </c:when>
                    <c:otherwise>
                        <button  id="buy-orders" class="menu" type="button" name="buy-orders" value="buy-orders" >我的购买订单</button>
                    </c:otherwise>
                </c:choose>
            </ul>
        </form>
    </div>
    <%--展示信息区域--%>
    <div id="display" class="show"></div>
<script>
    $(function(){
//    我的购买订单跳转请求
        $("#sell-buy-orders").click(function myBuy(){
            var value="buy-orders";
            $.ajax({
                type : "POST",
                url : "${pageContext.request.contextPath}/HomepageServlet.htm?buy-orders="+value,
                dataType : "text",
                contentType:"application/x-www-form-urlencoded",
                success : function(data) {
                    $("#display").html(data);
                },
                error : function(e) {
                    console.log(e);
                }
            });
        })

        $("#buy-orders").click(function myBuy(){
            var value="buy-orders";
            $.ajax({
                type : "POST",
                url : "${pageContext.request.contextPath}/HomepageServlet.htm?buy-orders="+value,
                dataType : "text",
                contentType:"application/x-www-form-urlencoded",
                success : function(data) {
                    $("#display").html(data);
                },
                error : function(e) {
                    console.log(e);
                }
            });
        })
//    我的出售订单跳转请求
        $("#sell-orders").click(function mySell(){
            var value="sell-orders";
            $.ajax({
                type : "POST",
                url : "${pageContext.request.contextPath}/HomepageServlet.htm?sell-orders="+value,
                dataType : "text",
                contentType:"application/x-www-form-urlencoded",
                success : function(data) {
                    $("#display").html(data);
                },
                error : function(e) {
                    console.log(e);
                }
            });
        });
//    点击id为buy的a标签传递buyerid参数
        $("#buy").click(function pass(){
            var id=${sessionScope.buyerid};
            document.getElementById("buy").href="${pageContext.request.contextPath}/GoodsServlet.htm?buyerid="+id;
        });
//
        $("#log-out").click(function () {
            <%--window.location.href="${}"--%>
        })
    })


</script>
</body>
</html>
