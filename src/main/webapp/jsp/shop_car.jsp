<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/css/shop_car.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/artDialog-6.0.2/dist/dialog-min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/artDialog-6.0.2/css/ui-dialog.css"/>
</head>
<body>
<a href="${pageContext.request.contextPath}/HomepageServlet.htm" class="top">
    <img class="logo" alt="这里是个logo" src="${pageContext.request.contextPath}/image/orderStatementLogo.jpg"/>
</a>

<div class="middle">
    <div class="box">
        <table cellpadding="0" cellspacing="0" border="0">
            <thead>

            <tr>
                <th><label>全部</label></th>
                <th>商品信息</th>
                <th>数量</th>
                <th>单价</th>
                <th>金额</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#if totalMoney gt 0>
                <#list goodsList as goods>
                    <tr>

                        <td style="overflow: hidden;">
                            <input type="checkbox" checked="checked" value="${goods.id}" class="checkAll" id="box_${goods.id}" style=" float:left;"/></td>

                        <td style=" margin-top:25px; font-size: 18px; padding-left: 85px;">${goods.name}</td>
                        <td style=" margin-top:25px; font-size: 18px; margin-left:50px;">${goods.goodsNum}</td>
                        <input type="hidden" value="${goods.goodsNum}" id="num_${goods.id}"/>
                        <td style=" margin-top:25px; font-size: 18px; margin-left:50px;">${goods.price}</td>
                        <input type="hidden" value="${goods.price}" id="price_${goods.id}"/>
                        <td style=" margin-top:25px; font-size: 18px; margin-left:50px;">${goods.buyMoney}</td>
                        <td ><a href="javascript:void(0)" style=" margin-top:25px; font-size: 18px;" onclick="deleteCar(${goods.id})">删除</a></td>

                    </tr>
                </#list>
            </#if>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="6">
                    <span><label><input type="checkbox" checked="checked" class="checkAll" id="checkAll"/>全部</label></span>
                    <span style="width:150px;">合计￥ <i id="total_price">${totalMoney}元</i></span>
                    <form id="subimtOrderForm" action="${ctx}/customer/submitOrder" method="post">
                        <input type="hidden" name="goodsIdArrays" id="goodsIdArrays"/>
                        <div class="pay" style=" float: right; margin-right:50px;">
                            <a href="javascript:void(0)" id="submitOrderBtn" style="font-size: 20px;">结算</a>
                        </div>
                    </form>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
<script>
    $(function(){
        $("#checkAll").click(function(){
            //设置复选框全选或反选
            $("input[type='checkbox'][id^='box_']").attr("checked", this.checked);
            /** 获取下面的checkbox为它们绑定点击事件 */
            var boxs = $("input[type='checkbox'][id^='box_']");
            boxs.click(function(){
                /** 计算商品的总金额 */
                calcPrice();
                /** 让全选选中 */
                $("#checkAll").attr("checked", boxs.length == boxs.filter(":checked").length);
            });



            /** 获取反选复选框为它绑定点击事件 */
            $("#uncheck").click(function(){
                /** 迭代下面所有的checkbox */
                boxs.each(function(){
                    /** 让自己取选自己的checked属性值 */
                    $(this).attr("checked", !this.checked);
                });
                /** 计算商品的总金额 */
                calcPrice();
                /** 让全选选中 */
                $("#checkAll").attr("checked", boxs.length == boxs.filter(":checked").length);
            });
            calcPrice();

        });

    });
    //删除购物车中的商品
    function deleteCar(goodsId){
        var d = dialog({
            title: '温馨提示',
            content: '<img src=${ctx}/img/logo.jpg><br><font size=6 color=red>您确定要删除该数据吗？</font>',
            okValue: '确定',
            ok:function(){
                this.title("请稍等...");
                var url ="${ctx}/customer/deleteCar";
                var params = {"goodsId":goodsId};
                $.post(url,params,function(data){
                    if(data.status == 0){
                        window.location="${ctx}/customer/showBuyCar";
                        $("#goods_"+goodsId).fadeToggle(500,function(){
                            this.remove();

                            //更新购物车数量显示(页面元素在header_macro.html)
                            //$("#totalNum").text(data.totalNum);
                            //重新计算商品总价
                            //calcPrice();
                            // 显示购物车中商品总数量
                            //loadAjaxShopCarNum();
                        });
                    }else{
                        var d = dialog({
                            title: '温馨提示',
                            content: '<img src=${ctx}/img/logo.jpg><br><br><font size=5 color=red>删除失败！</font>'
                        });
                        d.show();
                        setTimeout(function () {
                            d.close().remove();
                        }, 1000);
                        //alert("添加失败");
                    }
                },"json");
                //window.location="${ctx}/admin/goods/delete?id="+id;
                //return false;
            },
            cancelValue:'取消',
            cancel:function(){}
        });
        d.showModal();
    };

    /** 计算商品总金额的函数 */
    var calcPrice = function(){
        /** 获取下面所有选中的checkbox */
        var boxs = $("input[type='checkbox'][id^='box_']:checked");
        /** 定义总金额 */
        var total_price = 0;
        /** 如果有选中的，就计算 */
        if (boxs.length > 0){
            /** 迭代所有选中的checkbox */
            boxs.each(function(){

                /** 获取价格 */
                var price = parseFloat($(this.id.replace("box", "#price")).val());

                /** 获取数量 */
                var num = parseInt($(this.id.replace("box", "#num")).val());
                /* 设置小计金额 **/
                total_price +=  (price * num);
            });
            /** 设置显示总金额 */
            $("#total_price").html(total_price +" 元");
        }else{
            /** 设置显示总金额 */
            $("#total_price").html("0.00 元");
        }
    };

    // 提交订单js
    $("#submitOrderBtn").click(function(){
        var boxs = $("input[id^='box_']:checked");
        if(boxs.length == 0){
            var d = dialog({
                title: '温馨提示',
                content: '<img src=${ctx}/img/logo.jpg><br><font size=6 color=red>亲，请选择需要结算的商品！</font>'
            });
            d.show();
            setTimeout(function () {
                d.close().remove();
            }, 1000);
        }else{
            //获取选中的复选框的值（值是商品id）
            var ids = boxs.map(function(i,item){
                return item.value;

            });
            $("#goodsIdArrays").val(ids.get());
            $("#subimtOrderForm").submit();
        }
    });
</script>
</body>
</html>
