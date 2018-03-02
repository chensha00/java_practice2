package com.zyht.servlet;

import com.zyht.common.util.SpringContextUtil;
import com.zyht.domain.OrderStatement;
import com.zyht.service.OrderStatementService;
import com.zyht.service.OrderStatementServiceImpl;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OrderStatementController
 *
 * @author wangchuan
 * @Description
 * @Date 2018/1/30
 */
public class OrderStatementServlet extends HttpServlet {
    /**
     * @Title: doGet
     * @Description: 方法描述
     * @author wangchuan
     * @date 2018/1/30
     * @param req resp
     * @throws IOException ServletException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //ApplicationContext applicationContext= SpringContextUtil.getApplicationContext();
        //List<OrderStatement> orderStatementList= (List<OrderStatement>) applicationContext.getBean("List<OrderStatement>");
        //OrderStatementServiceImpl orderStatementService= (OrderStatementServiceImpl) applicationContext.getBean("OrderStatementServiceImpl");
        List<OrderStatement> orderStatementList=null;
        OrderStatementServiceImpl orderStatementService=new OrderStatementServiceImpl();
        Map<String,String > stringStringMap=new HashMap<String, String>();
        String str=req.getParameter("id");
        stringStringMap.put("`ID`",str);
        try {
            orderStatementList=orderStatementService.queryOrderStatementByCondition(stringStringMap,0,5);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            req.setAttribute("orderStatementList",orderStatementList);
            System.out.println(orderStatementList.get(0).getId());
            req.getRequestDispatcher("/jsp/order_statement.jsp").forward(req,resp);
        }
    }
    /**
     * @Title: doPost
     * @Description: 方法描述
     * @author wangchuan
     * @date 2018/1/30
     * @param req resp
     * @throws IOException ServletException
     */
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //ApplicationContext applicationContext= SpringContextUtil.getApplicationContext();
        //List<OrderStatement> orderStatementList= (List<OrderStatement>) applicationContext.getBean("List<OrderStatement>");
        //OrderStatementServiceImpl orderStatementService= (OrderStatementServiceImpl) applicationContext.getBean("OrderStatementServiceImpl");
        List<OrderStatement> orderStatementList=null;
        OrderStatementServiceImpl orderStatementService=new OrderStatementServiceImpl();
        Map<String,String > stringStringMap=new HashMap<String,String>();
        String str=req.getParameter("id");
        stringStringMap.put("`ID`",str);
        try {
            orderStatementList=orderStatementService.queryOrderStatementByCondition(stringStringMap,0,5);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            req.setAttribute("orderStatementList",orderStatementList);
            req.getRequestDispatcher("/jsp/order_statement.jsp").forward(req, resp);
        }
    }
}
