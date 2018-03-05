package com.zyht.action;/********************************************************************/
/**
 * @Project: jspweb
 * @Package com.zyht.action
 * @author caoxin
 * @date 2018/3/4 19:10
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.opensymphony.xwork2.ActionSupport;
import com.zyht.common.util.SpringContextUtil;
import com.zyht.domain.*;
import com.zyht.service.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caoxin
 * @ClassName BuyAction
 * @Description 购买Actino
 * @date 2018/3/4
 */
@Action
@Results({
        @Result(name ="buy" ,location = "/jsp/pay_order.jsp")
})
public class BuyAction extends ActionSupport{
    private HttpServletRequest request;
    private HttpServletResponse response;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String buy(){
//        获取request,response对象
        request= ServletActionContext.getRequest();
        response=ServletActionContext.getResponse();
        BuyerService buyerService= (BuyerService) SpringContextUtil.getBean("buyerService");
        Buyer buyer;
        Goods goods=new Goods();
        SellerService ssi=(SellerService)SpringContextUtil.getBean("sellerService");
        GoodsService gsi=(GoodsService)SpringContextUtil.getBean("goodsService");
        OrderDetailService odsi=(OrderDetailService)SpringContextUtil.getBean("orderDetailService");
        OrderService osi=(OrderService)SpringContextUtil.getBean("orderService");
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        //获取购买的商品id,买家id 购买数量buyAmount
        Long goodsSellerRelationId=Long.parseLong(request.getParameter("grsid"));
        Long buyerId=Long.parseLong(request.getParameter("buyerid"));
        Double buyAmount= Double.parseDouble(request.getParameter("goodsNumber"));
        GoodsSellerRelation gsr=null;
        GoodsSellerRelationServiceImpl gsrsi=(GoodsSellerRelationServiceImpl)SpringContextUtil.getBean("goodsSellerRelationService");
        //查询出购买的产品
        gsr=gsrsi.queryGoodsSellerRelationById(goodsSellerRelationId);
        String goodsName=gsr.getName();
        //购买清单
        Map<GoodsSellerRelation,Double> map=new HashMap<GoodsSellerRelation,Double>();
        map.put(gsr,buyAmount);
        Double bill=buyerService.bill(map);
        goods.setId(gsr.getGoodsId());
        buyer=buyerService.queryBuyerById(buyerId);
        //购买行为产生订单详情
        List<OrderDetail> buyList=new ArrayList<OrderDetail>();
        buyList=buyerService.buy(bill,map,buyer);

        //将buyList用请求传递
        request.setAttribute("buylist",buyList);
        return "buy";
    }
}
