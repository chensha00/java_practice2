<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zyht.domain.OrderStatement" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/css/order_statement.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<a href="${pageContext.request.contextPath}/HomepageServlet.htm" class="top">
    <img class="logo" alt="这里是个logo" src="${pageContext.request.contextPath}/image/orderStatementLogo.jpg"/>
</a>
<span>流水表</span>

<div class="likeMore">
    <p>猜你喜欢</p>
    <a href="${pageContext.request.contextPath}/HomepageServlet.htm"><img class="like1" src="${pageContext.request.contextPath}/image/orderStatementImg.jpg" alt="这是张图片"/></a>
    <a href="${pageContext.request.contextPath}/HomepageServlet.htm"><img class="like2" src="${pageContext.request.contextPath}/image/orderStatementImg.jpg" alt="这是张图片"/></a>
    <a href="${pageContext.request.contextPath}/HomepageServlet.htm"><img class="like3" src="${pageContext.request.contextPath}/image/orderStatementImg.jpg" alt="这是张图片"/></a>
    <a href="${pageContext.request.contextPath}/HomepageServlet.htm"><img class="like4" src="${pageContext.request.contextPath}/image/orderStatementImg.jpg" alt="这是张图片"/></a>
</div>

<div class="order">
    <table border="1">
        <thead>
            <tr>
                <th>序号</th>
                <th>流水号</th>
                <th>卖家编号</th>
                <th>买家编号</th>
                <th>交易金额</th>
                <th>交易完成时间</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${orderStatementList}" var="orderList">
                <tr>
                    <td>
                        ${orderList.getId()}
                    </td>
                    <td>
                        ${orderList.getNumber()}
                    </td>
                    <td>
                        ${orderList.getSellerId()}
                    </td>
                    <td>
                        ${orderList.getBuyerId()}
                    </td>
                    <td>
                        ${orderList.getAmount()}
                    </td>
                    <td>
                        ${orderList.getFinishTime()}
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
