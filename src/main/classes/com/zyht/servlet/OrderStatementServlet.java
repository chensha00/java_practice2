package com.zyht.servlet;

import com.zyht.domain.OrderStatement;
import com.zyht.service.OrderStatementServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
 * @Description OrderStatementServlet
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
        doPost(req,resp);
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
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderStatementServiceImpl orderStatementService= (OrderStatementServiceImpl) applicationContext.getBean("orderStatementService");
//        OrderStatementServiceImpl orderStatementService=new OrderStatementServiceImpl();
        List<OrderStatement> orderStatementList=null;
        Map<String,Object> stringStringMap=new HashMap<String, Object>();
        String str=req.getParameter("id");
        stringStringMap.put("`ID`",str);
        orderStatementList=orderStatementService.queryOrderStatementByCondition(stringStringMap,0,5);
        req.setAttribute("orderStatementList",orderStatementList);
        req.getRequestDispatcher("/jsp/order_statement.jsp").forward(req,resp);
    }
}