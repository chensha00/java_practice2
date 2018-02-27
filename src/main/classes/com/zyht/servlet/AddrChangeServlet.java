package com.zyht.servlet;/********************************************************************/
/**
 * @Project: jspweb
 * @Package com.zyht.servlet
 * @author caoxin
 * @date 2018/2/12 17:19
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.zyht.common.util.SpringContextUtil;
import com.zyht.domain.Buyer;
import com.zyht.service.BuyerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author caoxin
 * @ClassName AddrChangeServlet
 * @Description 地址修改Servlet
 * @date 2018/2/12
 */
public class AddrChangeServlet extends HttpServlet{
    /**
     * @Title: doGet
     * @Description: 重写doGet方法
     * @author caoxin
     * @date 2018/1/28
     * @param request, response
     * @throw IOException, ServletException
     */
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException {
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
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        BuyerService buyerService=(BuyerService) SpringContextUtil.getBean("buyerService");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
//        HttpSession session=request.getSession();
        Long buyerId=Long.parseLong(request.getParameter("buyerid"));
        String addr=request.getParameter("myaddr");
        Buyer buyer=buyerService.queryBuyerById(buyerId);
        buyer.setPermanentAddr(addr);
        buyerService.updateBuyer(buyer);
//        request.getRequestDispatcher("/jsp/homepage.jsp").forward(request,response);
        response.sendRedirect("jsp/homepage.jsp");
    }
}
