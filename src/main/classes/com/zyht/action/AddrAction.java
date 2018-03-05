package com.zyht.action;/********************************************************************/
/**
 * @Project: jspweb
 * @Package com.zyht.action
 * @author caoxin
 * @date 2018/3/2 16:44
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.opensymphony.xwork2.ActionSupport;
import com.zyht.common.util.SpringContextUtil;
import com.zyht.domain.Buyer;
import com.zyht.service.BuyerService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author caoxin
 * @ClassName AddrAction
 * @Description 获取账户对应收货地址Action
 * @date 2018/3/2
 */
@Action
@Results({
        @Result(name = "addr",location = "/jsp/addr.jsp")
})
public class AddrAction extends ActionSupport{

    private String buyerId;
    private HttpServletRequest request;
    private HttpServletResponse response;
    /**
     * @Title: addr
     * @Description: 获取对应买家的地址
     * @author caoxin
     * @date 2018/3/2
     * @return java.lang.String
     */

    public String addr(){
//        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        BuyerService buyerService=(BuyerService) SpringContextUtil.getBean("buyerService");
        Long id=Long.parseLong(buyerId);
        Buyer buyer=buyerService.queryBuyerById(id);
        String addr= buyer.getPermanentAddr();
        request=ServletActionContext.getRequest();
        request.setAttribute("addr",addr);
        request.setAttribute("buyerid",id);
        return "addr";
    }
    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

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
}
