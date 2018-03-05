package com.zyht.action;/********************************************************************/
/**
 * @Project: jspweb
 * @Package com.zyht.action
 * @author caoxin
 * @date 2018/3/5 13:40
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.opensymphony.xwork2.ActionSupport;
import com.zyht.base.BaseAction;
import com.zyht.common.util.DateTransferUtil;
import com.zyht.common.util.SpringContextUtil;
import com.zyht.domain.Account;
import com.zyht.domain.Buyer;
import com.zyht.service.AccountService;
import com.zyht.service.BuyerService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author caoxin
 * @ClassName EnrollAction
 * @Description 注册Action
 * @date 2018/3/5
 */
public class EnrollAction extends ActionSupport implements BaseAction {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    /**
     * @Title: enroll
     * @Description: 注册
     * @author caoxin
     * @date 2018/3/5
     * @return java.lang.String
     * @throw Exception
     */

    public String enroll(){

        //        获取request,response对象
        request = ServletActionContext.getRequest();
        response = ServletActionContext.getResponse();

        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=UTF-8");
        BuyerService buyerService=(BuyerService) SpringContextUtil.getBean("buyerService");
        String name =request.getParameter("name");
        String sex = request.getParameter("sex");
        String age=request.getParameter("age");
        String idNumber =request.getParameter("id_number");
        String tel = request.getParameter("tel");
        String permanentAddr = request.getParameter("permanentAddr");
        String profession =request.getParameter("profession");
        String work_unit =request.getParameter("work_unit");
        String saving =request.getParameter("saving");
        String payPassword=request.getParameter("paypassword");

        String account=request.getParameter("account");
        String password=request.getParameter("password");
        Date date=new Date();
        String dateString= DateTransferUtil.dateToString(date);

        Buyer buyer = new Buyer();
        buyer.setName(name);
        buyer.setSex(sex);
        buyer.setAge(Byte.parseByte(age));
        buyer.setIdNumber(idNumber);
        buyer.setTel(tel);
        buyer.setPermanentAddr(permanentAddr);
        buyer.setProfession(profession);
        buyer.setWorkUnit(work_unit);
        buyer.setSaving(Double.parseDouble(saving));
//        注册新买家
        buyerService.insertBuyer(buyer);
        Long buyerId=buyer.getId();
        AccountService accountService=(AccountService)SpringContextUtil.getBean("accountService");
        Account account1=new Account();
        account1.setAccount(account);
        account1.setPassword(password);
        account1.setBuyerId(buyerId);
        account1.setSellerId(null);
        account1.setAddTime(date);
        account1.setUpdateTime(date);
        account1.setPayPassword(payPassword);
//        注册新账户
        accountService.registerUser(account1);
       return LOG_IN;
    }
}
