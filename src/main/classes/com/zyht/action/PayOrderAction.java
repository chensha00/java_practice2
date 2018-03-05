package com.zyht.action;/********************************************************************/
/**
 * @Project: jspweb
 * @Package com.zyht.action
 * @author caoxin
 * @date 2018/3/4 19:18
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.opensymphony.xwork2.ActionSupport;
import com.zyht.common.util.SpringContextUtil;
import com.zyht.domain.Account;
import com.zyht.domain.Buyer;
import com.zyht.domain.Order;
import com.zyht.domain.OrderDetail;
import com.zyht.service.AccountService;
import com.zyht.service.BuyerService;
import com.zyht.service.OrderDetailService;
import com.zyht.service.OrderService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caoxin
 * @ClassName PayOrderAction
 * @Description 订单支付Action
 * @date 2018/3/4
 */
@Action
@Results({
        @Result(name = "home",location = "/jsp/homepage.jsp"),
        @Result(name = "buy",location = "/jsp/pay_order.jsp")
})
public class PayOrderAction extends ActionSupport {
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

    public String payOrder() {
        //        获取request,response对象
        request = ServletActionContext.getRequest();
        response = ServletActionContext.getResponse();

        OrderDetail orderDetail = null;
        Account account = null;
        AccountService as = (AccountService) SpringContextUtil.getBean("accountService");
        Order order = null;
        OrderService osi = (OrderService) SpringContextUtil.getBean("orderService");
        BuyerService bsi = (BuyerService) SpringContextUtil.getBean("buyerService");
        Buyer buyer = null;
        OrderDetailService odsi = (OrderDetailService) SpringContextUtil.getBean("orderDetailService");
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        Long orderDetailId = Long.parseLong(request.getParameter("id"));
        //获取订单详情订单信息及买家对象并修改相关状态
        orderDetail = odsi.queryOrderById(orderDetailId);
        buyer = bsi.queryBuyerById(orderDetail.getBuyerId());

        order = osi.queryOrderById(orderDetail.getOrderId());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("buyerId", buyer.getId());
        List<Account> accounts = new ArrayList<Account>();

        account = bsi.queryBuyerAccountById(buyer.getId());
        String authenticPayPass = account.getPayPassword();

        String payPass = request.getParameter("paypassword");
//        判定支付密码正确性进行页面跳转
        if (payPass != null && payPass.equals(authenticPayPass)) {
            bsi.pay(buyer, order);
            return "home";
        } else {
            try {
                request.setCharacterEncoding("UTF-8");//汉字转码
                PrintWriter out = response.getWriter();
                out.println("<script> alert('支付密码错误，支付失败');</script>");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                return "buy";
            }
        }
    }
}
