package com.zyht.servlet;

import com.zyht.domain.Account;
import com.zyht.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
 * @ClassName ManagerServlet
 * @Package com.zyht.servlet
 * @Description 类描述
 * @date 2018/2/20
 */
public class ManagerServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = (AccountService) context.getBean("accountService");

        List<Account> list = accountService.selectAll();
        req.setAttribute("accounts",list);
//        冻结账户
        if(req.getParameter("frozen")!=null){
            Account account = new Account();
            account.setId(Long.valueOf(req.getParameter("frozen")));
            account.setIsFrozen(true);
            accountService.modifyUser(account);
        }
//        注销账户
        if(req.getParameter("canceled")!=null){
            Account account1 = new Account();
            account1.setId(Long.valueOf(req.getParameter("canceled")));
            account1.setIsCanceled(true);
            accountService.modifyUser(account1);
        }
//        删除账户
        if(req.getParameter("delete")!=null){
            Account account2 = new Account();
            account2.setId(Long.valueOf(req.getParameter("delete")));
            account2.setIsDelete(true);
            accountService.modifyUser(account2);
        }
        req.getRequestDispatcher("/jsp/accountList.jsp").forward(req,resp);
    }
}