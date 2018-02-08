package com.zyht.servlet;


import com.zyht.common.util.DateTransferUtil;
import com.zyht.domain.Account;
import com.zyht.service.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class LogInServlet extends HttpServlet{
    /**
     * 会话对象
     */
    private HttpSession session=null;
    /**
     * @Title: doGet
     * @Description: 重写doget方法
     * @author cuixinyuan
     * @date 2018/1/31 0031
     */
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException, ServletException{
        this.doPost(request,response);
    }
    /**
     * @Title: doPost
     * @Description: 重写doPost方法
     * @author cuixinyuan
     * @date 2018/1/31 0031
     */
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException, ServletException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        Date time=new Date();
        String curTime= DateTransferUtil.dateToString(time);
        //获取会话对象
        session=request.getSession();
        //获取用户名
        String userName=request.getParameter("username");
        //将用户名存到session对象中
        session.setAttribute("username",userName);
        //获取密码
        String password=request.getParameter("password");
        //将密码存到session对象中
        session.setAttribute("password",password);
        //将当前时间存放到session中
        session.setAttribute("curtime",curTime);
        //创建账户服务实现类对象
        AccountServiceImpl aci=new AccountServiceImpl();
        //创建对象
        Account account=new Account();
        //将用户名和密码的值传递给账户
        account.setAccount(userName);
        account.setPassword(password);
        //账户登录
        account=aci.logInUser(account);
        //判断是否存在该用户（若该用户不存在则返回null）
        if(account == null){
            request.setCharacterEncoding("UTF-8");//汉字转码
            PrintWriter out = response.getWriter();
            out.println("<script> alert('用户名不存在，请重新登录');window.location.href='jsp/log_in.jsp';</script>");
        }else {
            Long sellerId=account.getSellerId();
            Long buyerId=account.getBuyerId();
            session.setAttribute("sellerid",sellerId);
            session.setAttribute("buyerid",buyerId);
            //将获取的传递给homepage
            request.getRequestDispatcher("jsp/homepage.jsp").forward(request,response);
        }

    }
}