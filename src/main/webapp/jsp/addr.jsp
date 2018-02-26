<%--
  Created by IntelliJ IDEA.
  User: Nick
  Date: 2018/2/12
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>管理地址</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>
    <link href="${pageContext.request.contextPath}/css/stylesheet.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="this-form">
    <form action="${pageContext.request.contextPath}/AddrChangeServlet.htm"  method="post">
        <input type="hidden" value="${buyerid}" name="buyerid"/>
        <table border="1" >
            <tr><td>地址</td>
            <td><input type="text" value="${requestScope.addr}" name="myaddr" id="myaddr"/></td>
            </tr>
            <tr><td colspan="2"><button type="submit" class="click">确认修改</button>
                <button id="goback" type="button" class="click">返回</button></td></tr>
        </table>
    </form>
</div>
<script>
    $(function(){
        $("#goback").click(function(){
            window.location.href="${pageContext.request.contextPath}/jsp/homepage.jsp";
        })
    })
</script>
</body>
</html>
