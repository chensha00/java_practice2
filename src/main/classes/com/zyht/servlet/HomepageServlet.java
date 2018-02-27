package com.zyht.servlet;/********************************************************************/
/**
 * @Project: jspweb
 * @Package com.zyht.controller
 * @author caoxin
 * @date 2018/1/27 11:17
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.zyht.domain.Account;
import com.zyht.domain.Seller;
import com.zyht.service.AccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author caoxin
 * @ClassName HomepageServlet
 * @Description 主页的servlet
 * @date 2018/1/27
 */
public class HomepageServlet extends HttpServlet {
    /**
     * @Title: doGet
     * @Description: 重写doGet方法
     * @author caoxin
     * @date 2018/1/28
     * @param request, response
     * @throw IOException, ServletException
     */
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException, ServletException{
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
        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("applicationContext.xml");
//        AccountServiceImpl accountServiceImpl=new AccountServiceImpl();
//        System.out.println("Enter successfully");
        AccountServiceImpl accountServiceImpl= (AccountServiceImpl) applicationContext.getBean("accountService");
//        Account account=new Account();
      Account account=(Account)applicationContext.getBean("account");
        HttpSession session=null;
//        Seller seller=new Seller();
      Seller seller=(Seller)applicationContext.getBean("seller");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取登录的账户名和密码
        session=request.getSession();
        String userName=(String)session.getAttribute("username");
        String password=(String)session.getAttribute("password");
        //将获取的用户名和密码传递给account对象
        account.setAccount(userName);
        account.setPassword(password);
        //将数据库中查询的数据传递给account账户
        account=accountServiceImpl.logInUser(account);
        seller.setId(account.getSellerId());
        Long sellerId=seller.getId();
        Long buyerId=account.getBuyerId();
        request.setAttribute("sellerid",sellerId);
        request.setAttribute("buyerid",buyerId);
        //判定传入的参数值，将页面根据传入值转发到不同页面
        if(request.getParameter("shop")!=null&&request.getParameter("shop").equals("shop")){
            SellerServlet ss=new SellerServlet();
            ss.doPost(request,response);
        }else if(request.getParameter("buy-orders")!=null&&request.getParameter("buy-orders").equals("buy-orders")){
            OrderServlet os=new OrderServlet();
            os.doPost(request,response);
        }else{
            OrderDetailServlet ods=new OrderDetailServlet();
            ods.doPost(request,response);
        }
    }
}
