package com.zyht.service;

import com.zyht.domain.OrderStatement;

import java.util.List;
import java.util.Map;

public interface OrderStatementService {
    /**
     * @Title: deleteOrderStatementById
     * @Description: 通过流水ID删除
     * @author wangchuan
     * @date 2018/1/20
     * @param id
     * @return java.lang.Integer
     */
    public Integer deleteOrderStatementById(Long id);

    /**
     * @Title: deleteOrderStatementByIds
     * @Description: 通过ID批量删除订单流水
     * @author wangchuan
     * @date 2018/1/19
     * @param  ids
     * @return java.lang.Integer
     */
    public Integer deleteOrderStatementByIds(Long[] ids);
    /**
     * @Title: updateOrderStatement
     * @Description: 添加或者修改流水信息
     * @author wangchuan
     * @date 2018/1/20
     * @param orderStatement
     * @return Integer
     */

    public Integer updateOrderStatement(OrderStatement orderStatement);
    /**
     * @Title: insertOrUpdateOrderStatement
     * @Description: 添加或者修改流水信息
     * @author wangchuan
     * @date 2018/1/20
     * @param orderStatement
     * @return Integer
     */
    public Integer insertOrderStatement(OrderStatement orderStatement);
    /**
     * @Title: queryOrderStatementById
     * @Description: 通过ID查询流水信息表
     * @author wangchuan
     * @date 2018/1/20
     * @param id
     * @return OrderStatement
     */

    public OrderStatement queryOrderStatementById(Long id);
    /**
     * @Title: queryOrderStatementByCondition
     * @Description: 通过条件查询流水信息
     * @author wangchuan
     * @date 2018/1/20
     * @param stringOrderStatementMap
     * @return List<OrderStatement>
     */
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, Object> stringOrderStatementMap);
    /**
     * @Title: findOrderStatementByCondition
     * @Description: 通过条件批量查询流水信息
     * @author wangchuan
     * @date 2018/1/20
     * @param stringOrderStatementMap, startRow, size
     * @return List<OrderStatement>
     */
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, Object> stringOrderStatementMap, Integer startRow, Integer size);
}

