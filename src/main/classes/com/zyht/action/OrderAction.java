package com.zyht.action;

import com.opensymphony.xwork2.ActionSupport;
import com.zyht.dao.OrderDao;
import com.zyht.dao.OrderDaoImpl;
import com.zyht.domain.Order;
import com.zyht.service.OrderService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
@Action("Order")
@Results({
        @Result(name = "showOrder",location = "/order_info.jsp")
})
public class OrderAction extends ActionSupport {
//    买家ID
    private Long buyerId;
//    卖家ID
    private Long sellerId;
//    用于存放查询到的订单
    private List<Order> orderList;
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    OrderDao orderDao = (OrderDao) context.getBean("orderDao");

    public String showOrder(){
//        判断传入的是买家ID还是卖家ID，并将其放入map中

        Map<String, Object> stringMap = new HashMap<String, Object>();
        if(buyerId!=null){
            stringMap.put("BUYER_ID",buyerId);
        }else if(sellerId!=null){
            stringMap.put("SELLER_ID",sellerId);
        }else {

        }
//        根据条件查询订单
        orderList = orderDao.queryOrderCondition(stringMap);
//        在request中存放信息
        return "showOrder";
//        将request转发给页面
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}