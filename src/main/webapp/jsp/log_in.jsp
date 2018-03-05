<%--
  Created by IntelliJ IDEA.
  User: cuixinyuan
  Date: 2018/1/28 0028
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div class="container"></div>
<h2>用户登录</h2>
<br>
<form method="post" action="${pageContext.request.contextPath}/Login"/>
    <label>用户名：</label>
    <input type="text" name="username"/>
    <br>
    <label>密　码：</label>
    <input type="password" name="password"/>
</br>
<td>类型</td>
<td>
    <input type="radio"  name="userclass" checked="checked"/>用户<input type="radio"  name="userclass"/>管理员</td></tr>
<tr bgcolor="peru">
    <td colspan="2" align="center"></td>
</tr>
    <p>
        <button type="submit" >登录</button>
        <button type="reset">重置</button>
        <br>
    </p>
</form>
如果你还没注册，请点击<a href="enroll.jsp">这里</a>注册！


<%--<form action="servlet/CheckLogin" method="post">--%>
    <%--<table align="center">--%>
        <%--<tr bgcolor="yellow">--%>
            <%--<td colspan="2" align="center">用户登录</td>--%>
        <%--</tr>--%>
        <%--<tr bgcolor="peru">--%>
            <%--<td>用户名</td>--%>
            <%--<td>--%>
                <%--<input type="text" name="username"/>--%>
            <%--</td></tr>--%>
        <%--<tr bgcolor="peru">--%>
            <%--<td>密码</td>--%>
            <%--<td>--%>
                <%--<input type="password" name="password"/></td></tr>--%>
        <%--<tr bgcolor="peru">--%>
            <%--<td>类型</td>--%>
            <%--<td>--%>
            <%--<input type="radio"  name="userclass" checked="checked"/>用户<input type="radio"  name="userclass"/>管理员</td></tr>--%>
        <%--<tr bgcolor="peru">--%>
            <%--<td colspan="2" align="center">--%>
            <%--<input type="submit" value="登录"/>--%>
                <%--<a href="regist.jsp" >注册</a></td></tr>--%>
    <%--</table>--%>
<%--</form>--%>
<%--</body>--%>
</body>
</html>
