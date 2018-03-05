package com.zyht.dao;

import com.zyht.domain.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * OrderDao
 *
 * @author Administrator
 * @Description
 * @Date 2018/1/20
 */
public interface OrderDao {
    /**
     * @Title: deleteOrderById
     * @Description: 通过订单ID删除
     * @author wangchuan
     * @date 2018/1/19
     * @param id, connection连接, preparedStatement预编译
     * @return java.lang.Integer
     * @throw SQLException
     */
    public Integer deleteOrderById(Long id);

    /**
     * @Title: deleteOrderByIds
     * @Description: 通过ID批量删除订单
     * @author wangchuan
     * @date 2018/1/19
     * @param  ids, connection连接, preparedStatement预编译
     * @return java.lang.Integer
     * @throw SQLException
     */
    public Integer deleteOrderByIds(List<Long> ids);
    /**
     * @Title: updateBuyer
     * @Description: 添加或者修改订单信息
     * @author wangchuan
     * @date 2018/1/19
     * @param order, connection连接, preparedStatement预编译
     * @return domain.Buyer
     * @throw SQLException
     */

    public int updateOrder(Order order);
    /**
     * @Title: insertOrUpdateBuyer
     * @Description: 添加或者修改订单信息
     * @author wangchuan
     * @date 2018/1/19
     * @param order, connection连接, preparedStatement预编译
     * @return domain.Buyer
     * @throw SQLException
     */
    public int insertOrder(Order order);
    /**
     * @Title: queryOrderById
     * @Description: 通过ID查询订单信息表
     * @author wangchuan
     * @date 2018/1/19
     * @param id 编号, connection连接, preparedStatement预编译
     * @return domain.Buyer
     * @throw SQLException
     */

    public Order queryOrderById(Long id);
    /**
     * @Title: queryOrderByCondition
     * @Description: 通过条件查询订单信息
     * @author wangchuan
     * @date 2018/1/19
     * @param stringBuyerMap, connection连接, preparedStatement预编译
     * @return domain.Buyer
     * @throw SQLException
     */
    public List<Order> queryOrderByCondition(Map<String,Object> stringBuyerMap);
    /**
     * @Title: findOrderByCondition
     * @Description: 通过条件批量查询订单信息
     * @author wangchuan
     * @date 2018/1/19
     * @param stringBuyerMap, offset, size, connection连接, preparedStatement预编译
     * @return java.util.List<domain.Buyer>
     * @throw SQLException
     */
    public List<Order> queryOrderConditionPage(Map<String,Object> stringBuyerMap);
}
