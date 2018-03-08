package com.zyht.action;

import com.opensymphony.xwork2.ActionSupport;
import com.zyht.base.BaseAction;
import com.zyht.domain.OrderStatement;
import com.zyht.service.OrderStatementService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OrderStatementAction
 *
 * @author Administrator
 * @Description
 * @Date 2018/3/2
 */
@Action
//@Results({
//        @Result(name = "showOrderStatement", location = "/order_statement.jsp")
//})
public class OrderStatementAction extends ActionSupport implements BaseAction{

    private Long orderDetailId;

    private HttpServletRequest request;

    private HttpServletResponse response;

    List<OrderStatement> orderStatementList=null;

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    OrderStatementService orderStatementService = (OrderStatementService) context.getBean("orderStatementService");

    public String showOrderStatement() throws SQLException {
        Map<String, Object> stringObjectMap = new HashMap<String, Object>();
        if(orderDetailId!=null){
            stringObjectMap.put("`ORDER_DETAIL_ID`",orderDetailId);
            orderStatementList=orderStatementService.queryOrderStatementByCondition(stringObjectMap);
            request.setAttribute("orderStatementList",orderStatementList);
            return STATEMENT;
        }
        return null;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
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

    public List<OrderStatement> getOrderStatementList() {
        return orderStatementList;
    }

    public void setOrderStatementList(List<OrderStatement> orderStatementList) {
        this.orderStatementList = orderStatementList;
    }
}
