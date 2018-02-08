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
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>卖家店铺</title>
    <link href="${pageContext.request.contextPath}/css/seller.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<span>我的商品列表</span>
<c:forEach items="${requestScope.goodsSellerRelationList}" var="goodsList">
    <ul class="list">
        <div>
            <li><img  src="${pageContext.request.contextPath}/image/lianyiqun.png"></li>
            <h2>${goodsList.getName()}&nbsp;&nbsp;&nbsp;现余：${goodsList.getInventory()}</h2>
            <h2><i>¥</i>${goodsList.getPrice()}</h2>
        </div>
        </div>
    </ul>
</c:forEach>
<a href="<%=request.getContextPath()%>jsp/homepage.jsp"><h2><i>返回主页</i></h2></a>
</body>
</html>
