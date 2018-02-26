package com.zyht.servlet;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.zyht.common.util.SpringContextUtil;
import com.zyht.domain.Order;
import com.zyht.service.OrderService;
import com.zyht.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author denghongbo
 * @ClassName OrderServlet
 * @Package com.zyht.servlet
 * @Description Order表serlvet类
 * @date 2018/1/29
 */

public class OrderServlet extends HttpServlet {
    /**
     * @Title: doGet
     * @Description: 重写doGet方法
     * @author DengHongbo
     * @date 2018/2/2 14:10
     * @param req, resp
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    /**
     * @Title: doPost
     * @Description: 重写doPost方法
     * @author DengHongbo
     * @date 2018/2/2 14:10
     * @param req, resp
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
//        ApplicationContext appCtx = SpringContextUtil.getApplicationContext();
//        OrderService orderService = (OrderService) SpringContextUtil.getBean("orderService");

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderService orderService = (OrderService)context.getBean("orderService");
//        判断传入的是买家ID还是卖家ID，并将其放入map中
        Object theId = null;
        Map<String, Object> stringMap = new HashMap<String, Object>();
        if(req.getAttribute("buyerId")!=null) {
            theId = req.getAttribute("buyerid");
            stringMap.put("BUYER_ID", theId);
        }
        if(req.getAttribute("sellerId")!=null) {
            theId = req.getAttribute("sellerid");
            stringMap.put("SELLER_ID", theId);
        }
//        根据条件查询订单
        List<Order> orderList = orderService.queryOrderByCondition(stringMap);
//        在request中存放信息
        req.setAttribute("orderList", orderList);
        req.setAttribute("theid",theId);
//        将request转发给页面
        req.getRequestDispatcher("jsp/order_info.jsp").forward(req,resp);
    }
}