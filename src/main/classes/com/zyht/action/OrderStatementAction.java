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

    private Long orderStatementId;

    List<OrderStatement> orderStatementList=null;

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    OrderStatementService orderStatementService = (OrderStatementService) context.getBean("orderStatementService");

    public String showOrderStatement() throws SQLException {
        Map<String, Object> stringObjectMap = new HashMap<String, Object>();
        if(orderStatementId!=null){
            stringObjectMap.put("`ORDER_STATEMENT_ID`",orderStatementId);
            orderStatementList=orderStatementService.queryOrderStatementByCondition(stringObjectMap,0,5);
            return STATEMENT;
        }
        return null;
    }

    public Long getOrderStatementId() {
        return orderStatementId;
    }

    public void setOrderStatementId(Long orderStatementId) {
        this.orderStatementId = orderStatementId;
    }
}
