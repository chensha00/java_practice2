package com.zyht.action;/********************************************************************/
/**
 * @Project: jspweb
 * @Package com.zyht.action
 * @author caoxin
 * @date 2018/3/5 9:32
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.opensymphony.xwork2.ActionSupport;
import com.zyht.base.BaseAction;
import com.zyht.common.util.DateTransferUtil;
import com.zyht.common.util.SpringContextUtil;
import com.zyht.domain.Account;
import com.zyht.service.AccountService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author caoxin
 * @ClassName LogInAction
 * @Description 登录Action
 * @date 2018/3/5
 */
@Action
//@Results({
//        @Result(name = "logIn",location = "/jsp/log_in.jsp"),
//        @Result(name = "manager",location = "/jsp/manager.jsp"),
//        @Result(name = "home",location = "/jsp/homepage.jsp")
//})
public class LogInAction extends ActionSupport implements BaseAction {
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
    public String logIn()throws IOException {

        //        获取request,response,session对象
        request = ServletActionContext.getRequest();
        response = ServletActionContext.getResponse();
        HttpSession session=request.getSession();
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=UTF-8");
//        获取对象
        AccountService accountService = (AccountService) SpringContextUtil.getBean("accountService");
//        获取本地时间
        Date time = new Date();
        String curTime = DateTransferUtil.dateToString(time);
        //获取用户名
        String userName = request.getParameter("username");
        //获取密码
        String password = request.getParameter("password");
        //将用户名存到session对象中
        session.setAttribute("username", userName);
        //将密码存到session对象中
        session.setAttribute("password", password);
        //将当前时间存放到session中
        session.setAttribute("curtime", curTime);
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
            PrintWriter out = response.getWriter();
            out.println("<script> alert('用户名不存在，请重新登录');</script>");
            return LOG_IN;
        } else if (account.getIsFrozen() == true) {
            PrintWriter out = response.getWriter();
            out.println("<script> alert('该用户已被冻结，请重新登录');</script>");
            return LOG_IN;
        } else if (account.getIsCanceled() == true) {
            PrintWriter out = response.getWriter();
            out.println("<script> alert('该用户已被注销，请重新登录');</script>");
            return LOG_IN;
        } else if (account.getIsDelete() == true) {
            PrintWriter out = response.getWriter();
            out.println("<script> alert('该用户已被删除，请重新登录');</script>");
            return LOG_IN;
        } else {
            Long sellerId = account.getSellerId();
            Long buyerId = account.getBuyerId();
            session.setAttribute("sellerid", sellerId);
            session.setAttribute("buyerid", buyerId);
//            判断是否是管理员，如果是管理员就进入管理员界面，否则就进入主界面
            if (userName.equals("admin")) {
                return MANAGER;
            } else {
                return HOME;
            }
        }
    }
}
