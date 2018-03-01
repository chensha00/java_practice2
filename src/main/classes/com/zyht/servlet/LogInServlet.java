package com.zyht.servlet;


import com.zyht.common.util.DateTransferUtil;
import com.zyht.domain.Account;
import com.zyht.service.AccountService;
import com.zyht.service.AccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LogInServlet extends HttpServlet {
    /**
     * 会话对象
     */
    private HttpSession session = null;

    /**
     * @Title: doGet
     * @Description: 重写doget方法
     * @author cuixinyuan
     * @date 2018/1/31 0031
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.doPost(request, response);
    }

    /**
     * @Title: doPost
     * @Description: 重写doPost方法
     * @author cuixinyuan
     * @date 2018/1/31 0031
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
//        获取对象
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = (AccountService) context.getBean("accountService");
//        获取本地时间
        Date time = new Date();
        String curTime = DateTransferUtil.dateToString(time);
        //获取会话对象
        session = request.getSession();
        //获取用户名
        String userName = request.getParameter("username");
        //获取密码
        Object password = request.getParameter("password");
        //将用户名存到session对象中
        session.setAttribute("username", userName);
        //将密码存到session对象中
        session.setAttribute("password", password);
        //将当前时间存放到session中
        session.setAttribute("curtime", curTime);
        //        //创建账户服务实现类对象
//        AccountServiceImpl aci=new AccountServiceImpl();

        //创建对象
//        Account account=new Account();
        //将用户名和密码的值传递给账户
//        account.setAccount(userName);
//        account.setPassword(password);
        //账户登录
//        account=aci.logInUser(account);

//        System.out.println(account);
//        通过账号密码查询账户
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("account",userName);
        map.put("password",password);
        Account account = accountService.logInUser(map);
//        判断账户状态，看用户是否存在/冻结/注销/删除
        if (account == null) {
            request.setCharacterEncoding("UTF-8");//汉字转码
            PrintWriter out = response.getWriter();
            out.println("<script> alert('用户名不存在，请重新登录');window.location.href='jsp/log_in.jsp';</script>");
        } else if (account.getIsFrozen() == true) {
            PrintWriter out = response.getWriter();
            out.println("<script> alert('该用户已被冻结，请重新登录');window.location.href='jsp/log_in.jsp';</scipt>");
        } else if (account.getIsCanceled() == true) {
            PrintWriter out = response.getWriter();
            out.println("<script> alert('该用户已被注销，请重新登录');window.location.href='jsp/log_in.jsp';</scipt>");
        } else if (account.getIsDelete() == true) {
            PrintWriter out = response.getWriter();
            out.println("<script> alert('该用户已被删除，请重新登录');window.location.href='jsp/log_in.jsp';</scipt>");
        } else {
            Long sellerId = account.getSellerId();
            Long buyerId = account.getBuyerId();
            session.setAttribute("sellerid", sellerId);
            session.setAttribute("buyerid", buyerId);
//            判断是否是管理员，如果是管理员就进入管理员界面，否则就进入主界面
            if (userName.equals("admin")) {
                request.getRequestDispatcher("jsp/manager.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("jsp/homepage.jsp").forward(request, response);
            }
        }
    }
}