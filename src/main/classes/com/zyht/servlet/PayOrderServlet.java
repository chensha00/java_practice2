package com.zyht.servlet;/********************************************************************/
/**
 * @Project: jspweb
 * @Package com.zyht.servlet
 * @author caoxin
 * @date 2018/1/30 9:11
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.zyht.common.util.SpringContextUtil;
import com.zyht.domain.Buyer;
import com.zyht.domain.Order;
import com.zyht.domain.OrderDetail;
import com.zyht.service.BuyerServiceImpl;
import com.zyht.service.OrderDetailServiceImpl;
import com.zyht.service.OrderServiceImpl;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        ApplicationContext applicationContext= SpringContextUtil.getApplicationContext();
        OrderDetail orderDetail=null;
//      OrderDetail orderDetail=(OrderDetail)applicationContext.getBean("orderDetail");
        Order order=null;
//      Order order=(Order)applicationContext.getBean("order");
        OrderServiceImpl osi=new OrderServiceImpl();
//      OrderServiceImpl osi=(OrderServiceImpl)applicationContext.getBean("orderServiceImpl");
        BuyerServiceImpl bsi=new BuyerServiceImpl();
//      BuyerServiceImpl bsi=(BuyerServiceImpl)applicationContext.getBean("buyerServiceImpl");
        Buyer buyer=null;
//      Buyer buyer=(Buyer)applicationContext.getBean("buyer");
        HttpSession session=null;
        OrderDetailServiceImpl odsi=new OrderDetailServiceImpl();
//      OrderDetailServiceImpl odsi=(OrderDetailServiceImpl)applicationContext.getBean("orderDetailServiceImpl");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        session=request.getSession();
        Long orderDetailId= Long.parseLong(request.getParameter("id"));
        //获取订单详情订单信息及买家对象并修改相关状态
        orderDetail=odsi.queryOrderById(orderDetailId);
        buyer=bsi.queryBuyerById(orderDetail.getBuyerId());
        order=osi.queryOrderById(orderDetail.getOrderId());
//        bsi.setOrderDetail(orderDetail);
        bsi.pay(buyer,order);
//        odsi.updateOrder(orderDetail);
//        osi.updateOrder(order);
        //将获取的用户名和密码传递给account对象
        request.getRequestDispatcher("jsp/homepage.jsp").forward(request,response);
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
