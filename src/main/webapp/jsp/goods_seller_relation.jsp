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

<%--<c:forEach items="${seller}" var="seller">--%>
<td >
    <div id='marguee' class="shop_name">欢迎来到${seller.getName()}的店铺</div>
</td>
<div><p class="shop_cars"> <a href="${pageContext.request.contextPath}/action/shop-car!showShopCar.do">我的购物车</a></p></div>
<%--</c:forEach>--%>
<div class="pho"><img class="pictureone" src="${pageContext.request.contextPath}/img/HP.png" alt="商品样式图"/></div>
<form name="Jump" action="" method="get">
    <div class="goodsDiv">
<c:forEach items="${goods}" var="goods">
        <p class="details">商品名字：${goods.get(0).getName()}</p>
        <p class="details">单价为：${goods.get(0).getPrice()}</p>
        <p class="details">数量：${goods.get(0).getInventory()}</p>
    </c:forEach>
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
                    $("#shop_car").click(function(){
                        alert('添加成功！');
                        document.Jump.action="${pageContext.request.contextPath}/action/shop-car!addShopCar.do";

                    })
                    $("#buyGoods").click(function(){
                        document.Jump.action="${pageContext.request.contextPath}/pay-order!buy.do";
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
            <p><button class="buy" id="shop_car" type="submit">加入购物车</button></p>
        </div>
        <div>
            <p><button class="buy" id="buyGoods" type="submit">购买</button></p>
        </div>
        <input type="hidden" name="grsid" value="${requestScope.goodsSellerRelation.get(0).getId()}">
        <input type="hidden" name="buyerid" value="${requestScope.buyer.getId()}">
    </div>
</form>
<div style="clear:both;"></div>
</div>
</body>
</html>
