package com.zyht.servlet;

import com.zyht.domain.OrderDetail;
import com.zyht.service.OrderDetailService;
import com.zyht.service.OrderDetailServiceImpl;

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
 * @ClassName OrderDetailServlet
 * @Package com.zyht.servlet
 * @Description 类描述
 * @date 2018/1/30
 */
public class OrderDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        OrderDetailService orderDetailService = new OrderDetailServiceImpl();
        Map<String, Object> stringMap = new HashMap<String,Object>();
        String sellerid = "" + req.getAttribute("sellerid");
        if(sellerid !=null){
            stringMap.put("SELLER_ID",sellerid);
        }
        String buyerid = ""+ req.getParameter("alldetail");
        if(buyerid!=null){
            stringMap.put("BUYER_ID", buyerid);
        }
        String orderid = ""+ req.getParameter("myorder");
        if(orderid != null){
            stringMap.put("ORDER_ID",orderid);
        }
        List<OrderDetail> orderDetailList = orderDetailService.queryOrderByCondition(stringMap);
        req.setAttribute("orderDetailList",orderDetailList);
        req.getRequestDispatcher("jsp/order_detail.jsp").forward(req,resp);
    }
}