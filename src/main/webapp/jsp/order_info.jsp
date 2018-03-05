<%@ page import="com.zyht.common.util.OrderState" %>
<%--Created by IntelliJ IDEA.
  User: 邓洪波
  Date: 2018/1/29
  Time: 8:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>订单信息</title>
    <link href="${pageContext.request.contextPath}/css/order_info.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js" type="text/javascript"></script>
</head>
<body>
<div id="total">
    <div id="header">
        <p id="head_text">订单信息</p>
    </div>
    <%--<div id="sidebar">--%>
        <%--<b>广告位</b>--%>
    <%--</div>--%>
    <div id="content">
        <div id="imgdiv">
            <img id="myimgage" src="" alt="这里应该有一张图片"/>
        </div>
        <div id="formdiv">
            <fieldset>
                <legend>订单信息</legend>
                <form id="myForm" action="${pageContext.request.contextPath}/action/orderStatement!showOrder.do"
                      method="post">
                    <table id="mytable" border="1">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>订单编号</th>
                            <th>买家序号</th>
                            <th>金额</th>
                            <th>交易是否成功</th>
                            <th>订单状态</th>
                            <th>订单创建时间</th>
                            <th>订单完成时间</th>
                            <th>订单详情</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.orderList}" var="orders">
                            <tr>
                                <td>
                                        ${orders.getId()}
                                </td>
                                <td>
                                        ${orders.getNumber()}
                                </td>
                                <td>
                                        ${orders.getBuyerId()}
                                </td>
                                <td>
                                        ${orders.getAmount()}
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${orders.getIsSuccess()== true}">
                                            ${"成功"}
                                        </c:when>
                                        <c:otherwise>
                                            ${"失败"}
                                        </c:otherwise>
                                    </c:choose>

                                </td>
                                <td>
                                    <c:forEach var="os" items="<%=OrderState.values()%>">
                                        <c:if test="${orders.getOrderStatus()== os.stateNum}">
                                            ${os.stateStr}
                                        </c:if>
                                    </c:forEach>

                                </td>
                                <td>
                                    <fmt:formatDate type="time" value="${orders.getCreationTime()}"
                                                    pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td>
                                    <fmt:formatDate type="time" value="${orders.getFinishTime()}"
                                                    pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <th>
                                    <button id="mybutton" type="submit" name="myorder" value="${orders.getId()}">查看更多</button>
                                </th>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="9" align="center">
                                <button name="alldetail" type="submit" value="${requestScope.theid}">查看所有订单详情</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </fieldset>
        </div>
    </div>
    <div id="footer"></div>
</div>

</body>
</html>
