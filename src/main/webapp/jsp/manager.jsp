<%--
  Created by IntelliJ IDEA.
  User: 邓洪波
  Date: 2018/2/11
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>管理员信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/manager.css">
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>--%>
</head>
<body>
<div id="total">
    <header id="header">
        <p>欢迎光临！管理员</p>${requestScope.managerName}
    </header>
    <div id="content">
        <form action="/Manager.htm" method="post">
            <div id="myview">
                <button id="show" type="submit">查看所有账户</button>
            </div>
            <div>
                <table border="1" id="mytable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>账户名</th>
                        <th>密码</th>
                        <th>是否冻结</th>
                        <th>是否注销</th>
                        <th>是否删除</th>
                        <th>买家序号</th>
                        <th>卖家序号</th>
                        <th>添加时间</th>
                        <th>修改时间</th>
                        <th>冻结账户</th>
                        <th>注销账户</th>
                        <th>删除账户</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.accounts}" var="account">
                        <tr>
                            <td>
                                    ${account.getId()}
                            </td>
                            <td>
                                    ${account.getAccount()}
                            </td>
                            <td>
                                    ${account.getPassword()}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${account.getIsFrozen() == true}">
                                        ${"冻结"}
                                    </c:when>
                                    <c:otherwise>
                                        ${"未冻结"}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${account.getIsCanceled() == true}">
                                        ${"注销"}
                                    </c:when>
                                    <c:otherwise>
                                        ${"未注销"}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${account.getIsDelete() == true}">
                                        ${"删除"}
                                    </c:when>
                                    <c:otherwise>
                                        ${"未删除"}
                                    </c:otherwise>
                                </c:choose>

                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${account.getBuyerId() == null}">
                                        ${"非买家"}
                                    </c:when>
                                    <c:otherwise>
                                        ${"买家"}
                                    </c:otherwise>
                                </c:choose>

                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${account.getSellerId() == null}">
                                        ${"非卖家"}
                                    </c:when>
                                    <c:otherwise>
                                        ${account.getSellerId()}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <fmt:formatDate type="time"
                                                value="${account.getAddTime()}"
                                                pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                                <fmt:formatDate type="time"
                                                value="${account.getUpdateTime()}"
                                                pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                                <div>
                                    <button id="frozen" name="frozen" type="submit" value="${account.getId()}">冻结
                                    </button>

                                </div>
                                <div>
                                    <button id="not_frozen" name="not_frozen" type="submit" value="${account.getId()}">
                                        解冻
                                    </button>
                                </div>
                            </td>
                            <td>
                                <button id="canceled" name="canceled" type="submit" value="${account.getId()}">注销
                                </button>
                            </td>
                            <td>
                                <button id="delete" name="delete" type="submit" value="${account.getId()}">删除</button>
                                <button id="not_delete" name="not_delete" type="submit" value="${account.getId()}">恢复
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
    </div>
    <footer id="footer"></footer>
</div>
<script type="text/javascript">
    <%--$(function () {--%>
    <%--$("#show").click(function myshow(){--%>
    <%--$.ajax({--%>
    <%--type : "POST",--%>
    <%--url : "${pageContext.request.contextPath}/Manager.htm?",--%>
    <%--dataType : "text",--%>
    <%--contentType:"application/x-www-form-urlencoded",--%>
    <%--success : function(data) {--%>
    <%--$("#display").html(data);--%>
    <%--},--%>
    <%--error : function(e) {--%>
    <%--console.log(e);--%>
    <%--}--%>
    <%--});--%>
    <%--})--%>

    <%--$("#frozen").click(function myshow(){--%>
    <%--var value="${account.getId()}"--%>
    <%--$.ajax({--%>
    <%--type : "POST",--%>
    <%--url : "${pageContext.request.contextPath}/Manager.htm?frozen="+value,--%>
    <%--dataType : "text",--%>
    <%--contentType:"application/x-www-form-urlencoded",--%>
    <%--success : function(data) {--%>
    <%--$("#display").html(data);--%>
    <%--},--%>
    <%--error : function(e) {--%>
    <%--console.log(e);--%>
    <%--}--%>
    <%--});--%>
    <%--})--%>

    <%--$("#cancaled").click(function myshow(){--%>
    <%--var value="${account.getId()}"--%>
    <%--$.ajax({--%>
    <%--type : "POST",--%>
    <%--url : "${pageContext.request.contextPath}/Manager.htm?canceled="+value,--%>
    <%--dataType : "text",--%>
    <%--contentType:"application/x-www-form-urlencoded",--%>
    <%--success : function(data) {--%>
    <%--$("#display").html(data);--%>
    <%--},--%>
    <%--error : function(e) {--%>
    <%--console.log(e);--%>
    <%--}--%>
    <%--});--%>
    <%--})--%>

    <%--$("#delete").click(function myshow(){--%>
    <%--var value="${account.getId()}"--%>
    <%--$.ajax({--%>
    <%--type : "POST",--%>
    <%--url : "${pageContext.request.contextPath}/Manager.htm?delete="+value,--%>
    <%--dataType : "text",--%>
    <%--contentType:"application/x-www-form-urlencoded",--%>
    <%--success : function(data) {--%>
    <%--$("#display").html(data);--%>
    <%--},--%>
    <%--error : function(e) {--%>
    <%--console.log(e);--%>
    <%--}--%>
    <%--});--%>
    <%--})--%>
    })
</script>
</body>
</html>
