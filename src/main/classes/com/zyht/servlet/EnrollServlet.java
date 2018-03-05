package com.zyht.servlet;

import com.zyht.common.util.DateTransferUtil;
import com.zyht.common.util.SpringContextUtil;
import com.zyht.domain.Account;
import com.zyht.domain.Buyer;
import com.zyht.service.AccountService;
import com.zyht.service.BuyerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class EnrollServlet extends HttpServlet{
    /**
     * @Title: doGet
     * @Description: 重写doGet方法
     * @author cuixinyuan
     * @date 2018/2/5 0005
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        doPost(request,response);
    }
    /**
     * @Title: doPost
     * @Description: 重写doPost方法
     * @author cuixinyuan
     * @date 2018/2/5 0005
     */
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        /**
         * 向买家表里插入数据
         */
//        BuyerService buyerService=new BuyerServiceImpl();
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        BuyerService buyerService=(BuyerService) SpringContextUtil.getBean("buyerService");
        String name =request.getParameter("name");
        String sex = request.getParameter("sex");
        String age=request.getParameter("age");
        String id_number =request.getParameter("id_number");
        String tel = request.getParameter("tel");
        String permanentAddr = request.getParameter("permanentAddr");
        String profession =request.getParameter("profession");
        String work_unit =request.getParameter("work_unit");
        String saving =request.getParameter("saving");

        String account=request.getParameter("account");
        String password=request.getParameter("password");

        Long seller_id=1l;
        Date date=new Date();
        String dateString= DateTransferUtil.dateToString(date);

        Buyer buyer = new Buyer();
        buyer.setName(name);
        buyer.setSex(sex);
        buyer.setAge(Byte.parseByte(age));
        buyer.setIdNumber(id_number);
        buyer.setTel(tel);
        buyer.setPermanentAddr(permanentAddr);
        buyer.setProfession(profession);
        buyer.setWorkUnit(work_unit);
        buyer.setSaving(Double.parseDouble(saving));
//        try {
//            buyer=buyerService.insertBuyer(buyer);
//        } catch (SQLException e1) {
//            e1.printStackTrace();
//        }
//        注册新买家
        buyerService.insertBuyer(buyer);
        Long buyer_id=buyer.getId();
//        AccountService accountService=new AccountServiceImpl();
        AccountService accountService=(AccountService)SpringContextUtil.getBean("accountService");
        Account account1=new Account();
        account1.setAccount(account);
        account1.setPassword(password);
//        account1.setIsFrozen(false);
//        account1.setIsCanceled(true);
//        account1.setIsDelete(false);
        account1.setBuyerId(buyer_id);
        account1.setSellerId(seller_id);
        account1.setAddTime(date);
        account1.setUpdateTime(date);
//        注册新账户
        accountService.registerUser(account1);
//        request.setAttribute("buyerService",buyerService);
        request.getRequestDispatcher("jsp/log_in.jsp").forward(request,response);
        }
    }



