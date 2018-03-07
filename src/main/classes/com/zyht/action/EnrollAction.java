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
import com.zyht.common.util.SpringContextUtil;
import com.zyht.domain.Account;
import com.zyht.domain.Buyer;
import com.zyht.domain.Seller;
import com.zyht.service.AccountService;
import com.zyht.service.BuyerService;
import com.zyht.service.SellerService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

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
@Action
public class EnrollAction extends ActionSupport implements BaseAction {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        SellerService sellerService=(SellerService) SpringContextUtil.getBean("sellerService");
//        获取前台传入的参数
        String name =request.getParameter("name");
        String sex = request.getParameter("sex");
        String age=request.getParameter("age");
        String idNumber =request.getParameter("id_number");
        String tel = request.getParameter("tel");
        String permanentAddr = request.getParameter("permanentAddr");
        String profession =request.getParameter("profession");
        String workUnit =request.getParameter("work_unit");
        String saving =request.getParameter("saving");
        String payPassword=request.getParameter("paypassword");
        String[] identity=request.getParameterValues("status");
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        Date date=new Date();

        Buyer buyer = new Buyer();
        buyer.setName(name);
        buyer.setSex(sex);
        buyer.setAge(Byte.parseByte(age));
        buyer.setIdNumber(idNumber);
        buyer.setTel(tel);
        buyer.setPermanentAddr(permanentAddr);
        buyer.setProfession(profession);
        buyer.setWorkUnit(workUnit);
        buyer.setSaving(Double.parseDouble(saving));

        Seller seller=new Seller();
        seller.setName(name);
        seller.setSex(sex);
        seller.setAge(Byte.parseByte(age));
        seller.setIdNumber(idNumber);
        seller.setTel(tel);
        seller.setAddr(permanentAddr);
        seller.setProfession(profession);
        seller.setWorkUnit(workUnit);
        seller.setSaving(Double.parseDouble(saving));
        AccountService accountService=(AccountService)SpringContextUtil.getBean("accountService");
        Account account1=new Account();
        account1.setAccount(account);
        account1.setPassword(password);
        account1.setAddTime(date);
        account1.setUpdateTime(date);
        account1.setPayPassword(payPassword);

//        注册新用户
        if(identity!=null&&identity.length==1){
            if(identity[0].equals("buyer")){
                buyerService.insertBuyer(buyer);
                Long buyerId=buyer.getId();
                account1.setBuyerId(buyerId);
                account1.setSellerId(null);
            }else {
                sellerService.insertSeller(seller);
                Long sellerId=seller.getId();
                account1.setSellerId(sellerId);
                account1.setBuyerId(null);
            }
        }else if(identity!=null&&identity.length==2){
            buyerService.insertBuyer(buyer);
            sellerService.insertSeller(seller);
            Long buyerId=buyer.getId();
            Long sellerId=seller.getId();
            account1.setBuyerId(buyerId);
            account1.setSellerId(sellerId);
        }else {
//                PrintWriter pw=response.getWriter();
//                pw.write("<script>alert('注册类型信息不完整，请重新注册!');</script>");
                message="注册失败，请重新按要求填写个人信息！";
                return ENROLL;
        }

//        注册新账户
        accountService.registerUser(account1);
        message="注册成功，可以登录网站！";
       return LOG_IN;
    }
}
