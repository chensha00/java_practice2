package com.zyht.servlet;/********************************************************************/
/**
 * @Project: jspweb
 * @Package com.zyht.servlet
 * @author caoxin
 * @date 2018/2/12 16:20
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

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
 * @ClassName AddrServlet
 * @Description 地址管理
 * @date 2018/2/12
 */
public class AddrServlet extends HttpServlet{
    /**
     * @Title: doGet
     * @Description: 重写doGet方法
     * @author caoxin
     * @date 2018/1/28
     * @param request, response
     * @throw IOException, ServletException
     */
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
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
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        BuyerService buyerService=(BuyerService) context.getBean("buyerService");
//        HttpSession session=request.getSession();
//        Long buyerId=Long.parseLong(request.getParameter("buyerid"));
        Long buyerId=1l;
        Buyer buyer=buyerService.queryBuyerById(buyerId);
        String addr=buyer.getPermanentAddr();
        request.setAttribute("addr",addr);
        request.setAttribute("buyerid",buyerId);
        request.getRequestDispatcher("/jsp/addr.jsp").forward(request,response);
    }
}
