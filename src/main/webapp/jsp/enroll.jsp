<%--
  Created by IntelliJ IDEA.
  User:cuixinyuan
  Date: 2018/1/28 0028
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>用户注册</title>
    <link href="${pageContext.request.contextPath}/css/enroll.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="wrap">
    <div class="container" >
        <form action="${pageContext.request.contextPath}/action/enroll!enroll.do" method="post">
            <table align="center" width="900px">
                <caption class="user">用户注册</caption>
                <tr>
                    <td class="birthdate" align="center">*姓名</td>
                    <td><input type="text" name="name" size="20"/></td>
                    <td class="birthdate" align="center">*性别</td>
                    <td><input type="radio" name="sex" value="男"/>男</td>
                    <td><input type="radio" name="sex" value="女"/>女 </td>
                </tr>
                <tr>
                    <td class="birthdate" align="center">*年龄</td>
                    <td><input type="text" name="age" /></td>
                    <td class="birthdate" align="center">*账户</td>
                    <td><input type="text" name="account" /></td>
                </tr>
                <tr>
                    <td class="birthdate" align="center">*出生日期</td>
                    <td><input type="text" name="birthdate"  placeholder="时间格式为1900-01-01"/></td>
                    <td class="birthdate" align="center">*身份证号</td>
                    <td><input type="text" name="id_number" /></td>
                </tr>
                <tr>
                    <td class="birthdate" align="center">*联系方式</td>
                    <td><input type="text" name="tel" /></td>
                    <td class="birthdate" align="center">职位</td>
                    <td><input type="text" name="profession" /></td>
                </tr>
                <tr>
                    <td class="birthdate" align="center">*账户密码</td>
                    <td><input type="text" name="password" placeholder="六位密码" /></td>
                    <td class="birthdate" align="center">*地址</td>
                    <td><input type="text" name="permanentAddr" /></td>
                </tr>
                <tr>
                    <td class="birthdate" align="center">*存款</td>
                    <td><input type="text" name="saving" /></td>
                    <td class="birthdate" align="center">工作单位</td>
                    <td><input type="text" name="work_unit" /></td>
                </tr>
                <tr>
                    <td class="birthdate">*支付密码</td>
                    <td><input  type="text" name="paypassword" placeholder="六位密码"></td></tr>
                <tr>
                    <td class="birthdate" align="">*注册类型</td>
                    <td><input type="checkbox" name="status" value="buyer"/>买家</td>
                    <td><input type="checkbox" name="status" value="seller"/>卖家</td>
                </tr>
                <tr>
                    <td></td>
                    <td align="center" >
                        <button type="submit">确定</button>
                    </td>
                    <td align="center">
                        <button type="reset">重置</button>
                    </td>
                    <td></td>
                </tr>
                <td align="center"class="td-b">带*的项目必填</td>
            </table>
        </form>
    </div>
</div>
<script>
    <s:if test="message!=null">
    alert("${message}")
    </s:if>
</script>
</body>
</html>
