package com.zyht.service;

import com.zyht.domain.OrderStatement;

import java.sql.SQLException;
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
     * @throw SQLException
     */
    public Integer deleteOrderStatementById(Long id)throws SQLException;

    /**
     * @Title: deleteOrderStatementByIds
     * @Description: 通过ID批量删除订单流水
     * @author wangchuan
     * @date 2018/1/19
     * @param  ids
     * @return java.lang.Integer
     * @throw SQLException
     */
    public Integer deleteOrderStatementByIds(Long[] ids)throws SQLException;
    /**
     * @Title: updateOrderStatement
     * @Description: 添加或者修改流水信息
     * @author wangchuan
     * @date 2018/1/20
     * @param orderStatement
     * @return Integer
     * @throw SQLException
     */

    public Integer updateOrderStatement(OrderStatement orderStatement)throws SQLException;
    /**
     * @Title: insertOrUpdateOrderStatement
     * @Description: 添加或者修改流水信息
     * @author wangchuan
     * @date 2018/1/20
     * @param orderStatement
     * @return Integer
     * @throw SQLException
     */
    public Integer insertOrderStatement(OrderStatement orderStatement)throws SQLException;
    /**
     * @Title: queryOrderStatementById
     * @Description: 通过ID查询流水信息表
     * @author wangchuan
     * @date 2018/1/20
     * @param id
     * @return OrderStatement
     * @throw SQLException
     */

    public OrderStatement queryOrderStatementById(Long id)throws SQLException;
    /**
     * @Title: queryOrderStatementByCondition
     * @Description: 通过条件查询流水信息
     * @author wangchuan
     * @date 2018/1/20
     * @param stringOrderStatementMap
     * @return List<OrderStatement>
     * @throw SQLException
     */
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, Object> stringOrderStatementMap)throws SQLException;
    /**
     * @Title: findOrderStatementByCondition
     * @Description: 通过条件批量查询流水信息
     * @author wangchuan
     * @date 2018/1/20
     * @param stringOrderStatementMap, startRow, size
     * @return List<OrderStatement>
     * @throw SQLException
     */
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, Object> stringOrderStatementMap, Integer startRow, Integer size)throws SQLException;
}

