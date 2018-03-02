package com.zyht.dao;

/**
 * Created by Administrator on 2018/1/20.
 */


import com.zyht.domain.OrderStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author wangchuan
 * @Title: OrderStatementDao
 * @Description: 流水表接口
 * @date 2018/1/20
 */

public interface OrderStatementDao {
    /**
     * @param id
     * @return java.lang.Integer
     * @Title: deleteOrderStatementById
     * @Description: 通过流水ID删除
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public Integer deleteOrderStatementById(Long id)throws SQLException;

    /**
     * @param ids
     * @return java.lang.Integer
     * @Title: deleteOrderStatementByIds
     * @Description: 通过流水ID删除多条
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public Integer deleteOrderStatementByIds(Long[] ids)throws SQLException;
    /**
     * @param orderStatement
     * @return java.lang.Integer
     * @Title: updateOrderStatement
     * @Description: 更新流水信息
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public Integer updateOrderStatement(OrderStatement orderStatement)throws SQLException;
    /**
     * @param orderStatement
     * @return java.lang.Integer
     * @Title: insertOrderStatement
     * @Description: 新增流水信息
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public Integer insertOrderStatement(OrderStatement orderStatement)throws SQLException;
    /**
     * @param id
     * @return java.lang.Integer
     * @Title: queryOrderStatementById
     * @Description: 通过id查询一条流水
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public OrderStatement queryOrderStatementById(Long id)throws SQLException;
    /**
     * @param stringOrderStatementMap
     * @return java.util.*
     * @Title: queryOrderStatementByCondition
     * @Description: 查询多条流水
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, Object> stringOrderStatementMap)throws SQLException;
    /**
     * @param stringOrderStatementMap startRow size
     * @return java.util.*
     * @Title: queryOrderStatementByCondition
     * @Description: 查询多条流水并分页
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, Object> stringOrderStatementMap, Integer startRow, Integer size)throws SQLException;
}
