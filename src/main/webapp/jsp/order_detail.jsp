﻿<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.zyht.domain.OrderDetail" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.zyht.common.util.OrderState" %>
<%@ page import="java.util.LinkedList" %><%--
  Created by IntelliJ IDEA.
  User: renxu   流水页面
  Date: 2018/1/28
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div>
    <form method="post" action="${pageContext.request.contextPath}/action/OrderDetail!orderDetailResult.do"><!--跳转到本页面-->
        <div>
            <h2 align="center">订单详情</h2>
            <br/>
            <br/>
        </div>
        <div style="margin-left: 120px">
            <table align="center" border="1">
                <thead>
                <tr>
                    <th>
                        序号
                    </th>
                    <th>
                        买家序号
                    </th>
                    <th>
                        卖家序号
                    </th>
                    <th>
                        商品序号
                    </th>
                    <th>
                        订单序号
                    </th>
                    <th>
                        金额
                    </th>
                    <th>
                        交易是否完成
                    </th>
                    <th>
                        订单状态
                    </th>
                    <th>
                        订单编号
                    </th>
                    <th>
                        交易时间
                    </th>
                    <th>
                        完成时间
                    </th>
                    <th>
                        点击发货
                    </th>
                    <th>
                        物流信息
                    </th>
                    <th>
                        评价
                    </th>
                    <th>
                        留言
                    </th>
                    <th>
                        支付流水
                    </th>
                </tr>
                </thead>
                <tbody id="tbody"><!--通过查询结果，动态设置行数-->
                <%
                    List<OrderDetail> orderDetailList = (ArrayList) request.getAttribute("orderDetailList");
                %>
                <c:forEach items="${orderDetailList}" var="det">
                    <tr>
                        <td>
                                ${det.getId()}
                        </td>
                        <td>
                                ${det.getBuyerId()}
                        </td>
                        <td>
                                ${det.getSellerId()}
                        </td>
                        <td>
                                ${det.getGoodsId()}
                        </td>
                        <td>
                                ${det.getOrderId()}
                        </td>
                        <td>
                                ${det.getAmount()}
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${det.getIsSuccess()== true}">
                                    ${"完成"}
                                </c:when>
                                <c:otherwise>
                                    ${"未完成"}
                                </c:otherwise>
                            </c:choose>

                        </td>
                        <td>
                            <c:forEach var="os" items="<%=OrderState.values()%>">
                                <c:if test="${det.getOrderStatus()== os.stateNum}">
                                    ${os.stateStr}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                                ${det.getOrderNumber()}
                        </td>
                        <td>
                            <fmt:formatDate type="time" value="${det.getCreationTime()}"
                                                    pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <fmt:formatDate type="time" value="${det.getFinishTime()}"
                                            pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <c:choose>
                            <c:when test="${det.getOrderStatus()==1}"><!--当支付状态为1支付成功时发货-->
                                <td>
                                    <button name="send" type="submit" value="${det.getId()}" id="clickSend">点击发货</button>
                                </td>
                            </c:when>
                            <c:otherwise>
                            <td>
                                <button name="send" type="submit" value="点击发货" disabled>点击发货</button><!--按钮置灰 disabled-->
                            </td>
                            </c:otherwise>
                        </c:choose>

                        <td id="wl_info"><!--物流信息-->
                                    ${det.getPhysicalDistribution()}
                            </td>
                        <td id="pj"><!--评价-->
                                    ${det.getEvaluate()}
                            </td>
                        <td id="ly"><!--留言-->
                                    ${det.getLeaveWord()}
                            </td>
                        <c:choose>
                            <c:when test="${det.getOrderStatus()==2}"><!--当支付状态为2支付成功时可查看订单详情-->
                           <td>
                            <a href="${pageContext.request.contextPath}/action/order-statement!showOrderStatement.do"  name="id" value="${orders.getBuyerId()}">
                                <p>点击查看</p>
                            </a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>
                            <a href="javascript:return false"  name="id" value="${orders.getBuyerId()}"><!--不可点击 javascript:return false-->
                                <p style="opacity: 0.2">点击查看</p><!--置灰-->
                            </a>
                            </td>
                        </c:otherwise>
                        </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </form>
</div>

</body>
</html>
