<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: guoxin
  Date: 2018/1/29
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <script  src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>
    <link href="${pageContext.request.contextPath}/css/goods_seller_relation.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="" method="get">
<%--<c:forEach items="${seller}" var="seller">--%>
<td >
    <div id='marguee' class="shop_name">欢迎来到${seller.getName()}的店铺</div>
</td>
    <%--</c:forEach>--%>
    <div class="pho"><img class="pictureone" src="img/HP.png" alt="商品样式图"/></div>
<div class="goodsDiv">
        <p class="details">商品名字：${goodsSellerRelation.get(0).getName()}</p>
        <p class="details">单价为：${goodsSellerRelation.get(0).getPrice()}</p>
        <p class="details">数量：${goodsSellerRelation.get(0).getInventory()}</p>
 <div class="know">

            <input id="min" type="button" value="-" />
            <input id="text_box" name="goodsNumber" type="text" value="1" style="width:25px;" />
            <input id="add"  type="button" value="+" /></td>
            <script>
                $(function(){
                    var t = $("#text_box");
                    $("#add").click(function(){
                        t.val(parseInt(t.val())+1)
                        setTotal();
                    })
                    $("#min").click(function(){
                        t.val(parseInt(t.val())-1)
                        setTotal();
                    })
                    function setTotal(){
                        var tt = $("#text_box").val();
                        var  pbinfoid=$("#pbinfoid").val();
                        if(tt<=0){
                            alert('输入的值错误！');
                            t.val(parseInt(t.val())+1)
                        }else if(tt>= ${goodsSellerRelation.get(0).getInventory()}){
                            alert('输入的值错误！');
                            t.val(parseInt(t.val())-1)
                        }
                    }
                })
            </script>
    </div>
    <div>
        <a href="jump_prompt.jsp"><button class="buy">加入购物车</button></a>
        <p><button class="buy" type="submit">购买</button></p>
    </div>
    <div style="clear:both;"></div>
    </div>
    <input type="hidden" name="grsid" value="${requestScope.goodsSellerRelation.get(0).getId()}">
    <input type="hidden" name="buyerid" value="${requestScope.buyer.getId()}">
</form>
</body>
</html>
