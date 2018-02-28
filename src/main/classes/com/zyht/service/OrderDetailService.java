package com.zyht.service;

import com.zyht.domain.OrderDetail;

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
     * @return int 删除行数
     * @throw Exception
     */
    public int deleteOrderById(Long id);

    /**
     * @Title: deleteOrderByIds
     * @Description: 通过ID批量删除订单
     * @author renxu
     * @date 2018/1/23
     * @param  ids, connection, preparedStatement
     * @return java.lang.Integer
     * @throw Exception
     */
    public int deleteOrderByIds(Long[] ids);
    /**
     * @Title: insertOrder
     * @Description: 添加订单详情
     * @author renxu
     * @date 2018/1/23
     * @param orderDetail, connection, preparedStatement
     * @return Long id
     * @throw Exception
     */
    public int insertOrder(OrderDetail orderDetail);
    /**
     * @Title: updateOrder
     * @Description:  修改订单详情
     * @author renxu
     * @date 2018/1/23
     * @param orderDetail, connection, preparedStatement
     * @return domain.Order
     * @throw Exception
     */
    public String updateOrder(OrderDetail orderDetail);
    /**
     * @Title: queryOrderById
     * @Description: 通过ID查询订单信息表
     * @author renxu
     * @date 2018/1/23
     * @param id, connection, preparedStatement
     * @return domain.Order
     * @throw Exception
     */

    public OrderDetail queryOrderById(Long id);
    /**
     * @Title: queryOrderByCondition
     * @Description: 通过条件查询订单信息
     * @author renxu
     * @date 2018/1/23
     * @param stringOrderMap, connection, preparedStatement
     * @return domain.Order
     * @throw Exception
     */
    public List<OrderDetail> queryOrderByCondition(Map<String,Object> stringOrderMap);
    /**
     * @Title: findOrderByCondition
     * @Description: 通过条件批量查询订单信息
     * @author renxu
     * @date 2018/1/23
     * @param stringOrderMap, offset, size, connection, preparedStatement
     * @return java.util.List<domain.Order>
     * @throw Exception
     */
    public List<OrderDetail> queryOrderByCondition(Map<String,Object> stringOrderMap,Integer offset,Integer size);

}
