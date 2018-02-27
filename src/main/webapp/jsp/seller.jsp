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
    <script type="text/javascript"> function openwindow()
    {
        var temp=  window.showModalDialog("newbox.jsp",window,"status:no;scroll:no;dialogWidth:235px;dialogHeight:150px");
        if(temp!=undefined)   document.all.price.value += temp+",";
    }
    </script>

    <script>
        function openwindow()
        {
            window.open ("newbox.jsp","Sample","fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no, copyhistory=no,width=350,height=140,left=200,top=300");
        }

        /*在newbox.jsp里调用这个函数，用来刷新seller.jsp*/

        function opensub(){

            document.form1.submit();

        }
    </script>
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
<li>全场满99包邮 满200减50</li>
<body>  <jsp:useBean id= "sd " scope= "page " class= "dao.StudentsDao "> </jsp:useBean> <%int result=sd.delete(request.getParameter( "id ")); %>   <script type= "text/javascript ">   var result= <%=result%>    if(result==1){  alert( '删除成功 ');

}else{  alert( '删除失败 '); }   window.location= 'view.jsp ';   </script> </body>
<a href="<%=request.getContextPath()%>jsp/newbox.jsp">
    <button class="update">修改价格</button></a>
<button class="delete" type="submit">商品下架</button>
<a href="<%=request.getContextPath()%>jsp/homepage.jsp"><h2><i>返回主页</i></h2></a>
</body>
</html>
