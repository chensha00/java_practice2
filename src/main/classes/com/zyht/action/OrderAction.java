package com.zyht.action;

import com.opensymphony.xwork2.ActionSupport;
import com.zyht.domain.Order;
import com.zyht.service.OrderService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author denghongbo
 * @ClassName OrderAction
 * @Package com.zyht.action
 * @Description 类描述
 * @date 2018/3/2
 */
@Action("order")
@Results({
        @Result(name = "showOrder",location = "/jsp/order_info.jsp")
})
public class OrderAction extends ActionSupport {
//    买家ID
    private Long buyerId;
//    用于存放查询到的订单
    private List<Order> orderList;
    private HttpServletRequest request;
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    OrderService orderService = (OrderService) context.getBean("orderService");

    public String showOrder(){
        request = ServletActionContext.getRequest();
//        判断传入的是买家ID还是卖家ID，并将其放入map中
        Map<String, Object> stringMap = new HashMap<String, Object>();
        if(buyerId!=null){
            stringMap.put("BUYER_ID",buyerId);
        }
//        根据条件查询订单
        orderList = orderService.queryOrderByCondition(stringMap);
        request.setAttribute("orderList",orderList);
//        在request中存放信息
        return "showOrder";
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}