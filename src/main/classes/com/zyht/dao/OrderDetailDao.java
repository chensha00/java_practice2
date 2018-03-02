package com.zyht.dao;/********************************************************************/
/**
 * @Project: java_practice
 * @Package dao
 * @author renxu
 * @date 2018/1/19 17:13
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.zyht.domain.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author renxu
 * @InterfaceName OrderDao
 * @Description 订单信息表接口
 * @date 2018/1/19
 */
public interface OrderDetailDao {
    /**
     * @Title: deleteOrderById
     * @Description: 通过订单ID删除
     * @author renxu
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return java.lang.Integer
     * @throw Exception
     */
    public Integer deleteOrderById(Long id);

    /**
     * @Title: deleteOrderByIds
     * @Description: 通过ID批量删除订单
     * @author renxu
     * @date 2018/1/19
     * @param  ids, connection, preparedStatement
     * @return java.lang.Integer
     * @throw Exception
     */
    public Integer deleteOrderByIds(List ids);
    /**
     * @Title: insertOrder
     * @Description: 添加订单详情
     * @author renxu
     * @date 2018/1/22
     * @param orderDetail, connection, preparedStatement
     * @return Long id
     * @throw Exception
     */
    public int insertOrder(OrderDetail orderDetail);
    /**
     * @Title: updateOrder
     * @Description: 修改订单详情
     * @author renxu
     * @date 2018/1/19
     * @param orderDetail, connection, preparedStatement
     * @return int
     * @throw Exception
     */
    public int updateOrder(OrderDetail orderDetail);
    /**
     * @Title: queryOrderById
     * @Description: 通过ID查询订单信息表
     * @author renxu
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return domain.Order
     * @throw Exception
     */

    public OrderDetail queryOrderById(Long id);
    /**
     * @Title: queryOrderByCondition
     * @Description: 通过条件查询订单信息
     * @author renxu
     * @date 2018/1/19
     * @param stringOrderMap, connection, preparedStatement
     * @return domain.Order
     * @throw Exception
     */
    public List<OrderDetail> queryOrderByCondition(Map<String,Object> stringOrderMap);
    /**
     * @Title: findOrderByCondition
     * @Description: 通过条件批量查询订单信息
     * @author renxu
     * @date 2018/1/19
     * @param stringOrderMap, offset, size, connection, preparedStatement
     * @return java.util.List<domain.Order>
     * @throw Exception
     */
    public List<OrderDetail> queryOrderByCondition(Map<String,Object> stringOrderMap,Integer offset,Integer size);
}