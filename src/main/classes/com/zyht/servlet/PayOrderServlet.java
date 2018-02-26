package com.zyht.servlet;/********************************************************************/
/**
 * @Project: jspweb
 * @Package com.zyht.servlet
 * @author caoxin
 * @date 2018/1/30 9:11
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.zyht.domain.Account;
import com.zyht.domain.Buyer;
import com.zyht.domain.Order;
import com.zyht.domain.OrderDetail;
import com.zyht.service.AccountService;
import com.zyht.service.BuyerService;
import com.zyht.service.OrderDetailService;
import com.zyht.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caoxin
 * @ClassName PayOrderServlet
 * @Description 支付页面servlet
 * @date 2018/1/30
 */
public class PayOrderServlet extends HttpServlet {

    /**
     * @Title: doGet
     * @Description: 重写doGet方法
     * @author caoxin
     * @date 2018/1/30
     * @param  request, response
     * @throw IOException,ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException{
        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderDetail orderDetail=null;
        Account account=null;
        AccountService as=(AccountService) applicationContext.getBean("accountService");
//      OrderDetail orderDetail=(OrderDetail)applicationContext.getBean("orderDetail");
        Order order=null;
//      Order order=(Order)applicationContext.getBean("order");
//        OrderServiceImpl osi=new OrderServiceImpl();
      OrderService osi=(OrderService)applicationContext.getBean("orderService");
//        BuyerServiceImpl bsi=new BuyerServiceImpl();
      BuyerService bsi=(BuyerService)applicationContext.getBean("buyerService");
        Buyer buyer=null;
//      Buyer buyer=(Buyer)applicationContext.getBean("buyer");
        HttpSession session=null;
//        OrderDetailServiceImpl odsi=new OrderDetailServiceImpl();
      OrderDetailService odsi=(OrderDetailService)applicationContext.getBean("orderDetailService");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        session=request.getSession();
//        Long orderDetailId=20l;
        Long orderDetailId= Long.parseLong(request.getParameter("id"));
        //获取订单详情订单信息及买家对象并修改相关状态
        orderDetail=odsi.queryOrderById(orderDetailId);
        buyer=bsi.queryBuyerById(orderDetail.getBuyerId());
//        buyer=bsi.queryBuyerById(1l);
//        order=osi.queryOrderById(22l);
        order=osi.queryOrderById(orderDetail.getOrderId());
//        bsi.setOrderDetail(orderDetail);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("buyerId",buyer.getId());
        List<Account> accounts=new ArrayList<Account>();
//        accounts=as.queryAccountByCondition(map);
        account=bsi.queryBuyerAccountById(buyer.getId());
        String authenticPayPass=account.getPayPassword();
//        String authenticPayPass="000000";
        String payPass=request.getParameter("paypassword");
        if(payPass!=null&&payPass.equals(authenticPayPass)){
            bsi.pay(buyer,order);
            request.getRequestDispatcher("jsp/homepage.jsp").forward(request,response);
        }else{
            request.setCharacterEncoding("UTF-8");//汉字转码
            PrintWriter out = response.getWriter();
            out.println("<script> alert('支付密码错误，支付失败');window.location.href='jsp/pay_order.jsp;';</script>");
        }

//        odsi.updateOrder(orderDetail);
//        osi.updateOrder(order);
    }
    /**
     * @Title: doPost
     * @Description: 重写doPost方法
     * @author caoxin
     * @date 2018/1/30
     * @param  request, response
     * @throw IOException,ServletException
     */
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        doGet(request,response);
    }
}
