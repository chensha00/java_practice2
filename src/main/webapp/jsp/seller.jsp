<%--
/**
* @Title:seller.jsp
* @Description: 卖家出售
* @author chendong
* @date 2018/1/30
*/
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>卖家店铺</title>
    <link href="${pageContext.request.contextPath}/css/seller.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div>
    <span>我的商品列表</span>
</div>
<c:forEach items="${requestScope.goodsSellerRelationList}" var="goodsList">
<div class="list">
    <li><h3>全场满99包邮 满200减50!!!</h3></li>
    <img  src="${pageContext.request.contextPath}/images/yangshitu.png">
    <h2>${goodsList.getName()}&nbsp;&nbsp;&nbsp;现余：${goodsList.getInventory()}</h2>
    <h2><i>¥</i>${goodsList.getPrice()}</h2>
    <input type="button" onClick="msgbox(1)" value="修改价格">
    <form action="${pageContext.request.contextPath}/action/seller!showSeller.do?sellerId=" method="post">
    <input type="button" value="商品下架" onclick="document.getElementById('num').value = '';"/>
    </form>
    <a href="<%=request.getContextPath()%>jsp/homepage.jsp"><h3><i>返回主页</i></h3></a>
</div>

<script>
    function msgbox(n){
        document.getElementById('inputbox').style.display=n?'block':'none';     /* 点击按钮打开/关闭 对话框 */
    }
</script>
<div id='inputbox' class="box">
    <a class='x' href=''; onclick="msgbox(0); return false;">关闭</a>
    <form action="${pageContext.request.contextPath}/action/seller!showSeller.do?sellerId=" method="post">
        <input type="text">
        <input type="button" onclick="document.formName.submit()" value="确认修改">
    </form>
</div>





<style>
    .box{
        width:30%; margin-top:10%; margin:auto; padding:28px;
        height:150px; border:1px #111 solid;
        display:none;            /* 默认对话框隐藏 */
    }
    .box.show{display:block;}
    .box .x{ font-size:18px; text-align:right; display:block;}
    .box input{width:20%; font-size:18px; margin-top:18px;}
</style>
</c:forEach>
</body>
</html>

