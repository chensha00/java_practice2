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
<form method="post" action="${pageContext.request.contextPath}/action/log-in!logIn.do"/>
    <label>用户名：</label>
    <input type="text" name="username"/>
    <br>
    <label>密　码：</label>
    <input type="password" name="password"/>
    <p>
        <button type="submit" >登录</button>
        <button type="reset">重置</button>
        <br>
    </p>
</form>
如果你还没注册，请点击<a href="${pageContext.request.contextPath}/jsp/enroll.jsp">这里</a>注册！



</body>
</html>
