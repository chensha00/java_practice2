package service;

import domain.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Title: OrderDetailService
 * @Description: 订单详情
 * @author renxu
 * @date 2018/1/23
 */
public interface OrderDetailService {
    /**
     * @Title: deleteOrderById
     * @Description: 通过订单ID删除
     * @author renxu
     * @date 2018/1/23
     * @param id, connection, preparedStatement
     * @return java.lang.Integer
     * @throw Exception
     */
    public String deleteOrderById(Long id)throws SQLException;

    /**
     * @Title: deleteOrderByIds
     * @Description: 通过ID批量删除订单
     * @author renxu
     * @date 2018/1/23
     * @param  ids, connection, preparedStatement
     * @return java.lang.Integer
     * @throw Exception
     */
    public String deleteOrderByIds(Long[] ids)throws SQLException;
    /**
     * @Title: insertOrder
     * @Description: 修改订单详情
     * @author renxu
     * @date 2018/1/23
     * @param orderDetail, connection, preparedStatement
     * @return domain.Order
     * @throw Exception
     */
    public String insertOrder(OrderDetail orderDetail)throws SQLException;
    /**
     * @Title: updateOrder
     * @Description: 添加订单详情
     * @author renxu
     * @date 2018/1/23
     * @param orderDetail, connection, preparedStatement
     * @return domain.Order
     * @throw Exception
     */
    public String updateOrder(OrderDetail orderDetail)throws SQLException;
    /**
     * @Title: queryOrderById
     * @Description: 通过ID查询订单信息表
     * @author renxu
     * @date 2018/1/23
     * @param id, connection, preparedStatement
     * @return domain.Order
     * @throw Exception
     */

    public OrderDetail queryOrderById(Long id)throws SQLException;
    /**
     * @Title: queryOrderByCondition
     * @Description: 通过条件查询订单信息
     * @author renxu
     * @date 2018/1/23
     * @param stringOrderMap, connection, preparedStatement
     * @return domain.Order
     * @throw Exception
     */
    public List<OrderDetail> queryOrderByCondition(Map<String,String> stringOrderMap)throws SQLException;
    /**
     * @Title: findOrderByCondition
     * @Description: 通过条件批量查询订单信息
     * @author renxu
     * @date 2018/1/23
     * @param stringOrderMap, offset, size, connection, preparedStatement
     * @return java.util.List<domain.Order>
     * @throw Exception
     */
    public List<OrderDetail> queryOrderByCondition(Map<String,String> stringOrderMap,Integer offset,Integer size)throws SQLException;

}
