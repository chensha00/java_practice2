package com.zyht.servlet;/********************************************************************/
/**
* @Project: jspweb
* @Package com.zyht.servlet
* @author caoxin
* @date 2018/2/1 17:18
* @Copyright: 2018 www.zyht.com Inc. All rights reserved.
* @version V1.0
*/

import com.zyht.common.util.SpringContextUtil;
import com.zyht.domain.*;
import com.zyht.service.*;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author caoxin
* @ClassName BuyServlet
* @Description 购买动作servlet
* @date 2018/2/1
*/
public class BuyServlet extends HttpServlet{
    /**
     * @Title: doGet
     * @Description: 重写doGet方法
     * @author caoxin
     * @date 2018/1/28
     * @param request, response
     * @throw IOException, ServletException
     */
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException, ServletException {
        doPost(request,response);
    }
    /**
     * @Title: doPost
     * @Description: 重写doPost方法
     * @author caoxin
     * @date 2018/1/28
     * @param request, response
     * @throw IOException, ServletException
     */
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        ApplicationContext applicationContext= SpringContextUtil.getApplicationContext();
//        BuyerServiceImpl buyerServiceImpl=new BuyerServiceImpl();
        BuyerService buyerService= (BuyerService) applicationContext.getBean("buyerService");
        Buyer buyer=new Buyer();
//      Buyer buyer =(Buyer) applicationContext.getBean("buyer");
        HttpSession session=null;
        Seller seller=null;
        Goods goods=new Goods();
//      Goods goods=(Goods)applicationContext.getBean("goods");
//        SellerServiceImpl ssi=new SellerServiceImpl();
      SellerService ssi=(SellerService)applicationContext.getBean("sellerService");
//        GoodsServiceImpl gsi=new GoodsServiceImpl();
      GoodsService gsi=(GoodsService)applicationContext.getBean("goodsService");
//        OrderDetailServiceImpl odsi=new OrderDetailServiceImpl();
      OrderDetailService odsi=(OrderDetailService)applicationContext.getBean("orderDetailService");
//        OrderServiceImpl osi=new OrderServiceImpl();
      OrderService osi=(OrderService)applicationContext.getBean("orderService");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取购买的商品id,买家id 购买数量buyAmount
        session=request.getSession();
        Long goodsSellerRelationId=Long.parseLong(request.getParameter("grsid"));
        Long buyerId=Long.parseLong(request.getParameter("buyerid"));
        Double buyAmount= Double.parseDouble(request.getParameter("goodsNumber"));
        GoodsSellerRelation gsr=null;
        GoodsSellerRelationServiceImpl gsrsi=new GoodsSellerRelationServiceImpl();
//      GoodsSellerRelationServiceImpl gsrsi=(GoodsSellerRelationServiceImpl)applicationContext.getBean("goodsSellerRelationServiceImpl");
        //查询出购买的产品
        gsr=gsrsi.queryGoodsSellerRelationById(goodsSellerRelationId);
        String goodsName=gsr.getName();
        //购买清单
        Map<GoodsSellerRelation,Double> map=new HashMap<GoodsSellerRelation,Double>();
        map.put(gsr,buyAmount);
        Double bill=buyerService.bill(map);
        goods.setId(gsr.getGoodsId());
        try {
            seller=ssi.querySellerById(gsr.getSellerId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sellerName=seller.getName();
        buyer=buyerService.queryBuyerById(buyerId);
        //购买行为产生订单详情
//        OrderDetail orderDetail=buyerServiceImpl.buy(bill, goods, seller, buyer);
        //查询出产生的订单详情的id
        Map<String,String> query=new HashMap<String,String>();
//        query.put("ORDER_NUMBER",orderDetail.getOrderNumber());
        //获取查询结果
        List<OrderDetail> orderDetails=null;
        orderDetails=odsi.queryOrderByCondition(query);
//        orderDetail=orderDetails.get(0);
//        Long id=orderDetail.getId();
//        String orderNum=orderDetail.getOrderNumber();
//        String creationTime= DateTransferUtil.dateToString(orderDetail.getCreationTime());

        //将id,sellername,amount,goodsname ordernumber creationtime用请求传递
//        request.setAttribute("id",id);
        request.setAttribute("sellername",sellerName);
        request.setAttribute("amount",bill);
        request.setAttribute("goodsname",goodsName);
//        request.setAttribute("ordernumber",orderNum);
//        request.setAttribute("creationtime",creationTime);
//        System.out.println(id+sellerName+goodsName+orderNum);
//        将请求转发到pay_order.jsp页面
        request.getRequestDispatcher("jsp/pay_order.jsp").forward(request,response);

    }
}
