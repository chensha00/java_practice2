package service;

import domain.Order;

import java.sql.SQLException;
import java.util.Map;

/**
 * OrderService
 *
 * @author PIN
 * @Description
 * @Date 2018/1/20
 */
public interface OrderService  {
    /**
     * @Title: deleteOrderById
     * @Description: 通过订单ID删除
     * @author wangchuan
     * @date 2018/1/19
     * @param id
     * @return java.lang.Integer
     * @throw SQLException
     */
    public String deleteOrderById(Long id)throws SQLException;

    /**
     * @Title: deleteOrderByIds
     * @Description: 通过ID批量删除订单
     * @author wangchuan
     * @date 2018/1/19
     * @param  ids
     * @return java.lang.Integer
     * @throw SQLException
     */
    public String deleteOrderByIds(Long[] ids)throws SQLException;
    /**
     * @Title: updateBuyer
     * @Description: 添加或者修改订单信息
     * @author wangchuan
     * @date 2018/1/19
     * @param order
     * @return domain.Buyer
     * @throw SQLException
     */

    public String updateOrder(Order order)throws SQLException;
    /**
     * @Title: insertOrUpdateBuyer
     * @Description: 添加或者修改订单信息
     * @author wangchuan
     * @date 2018/1/19
     * @param order, connection连接, preparedStatement预编译
     * @return domain.Buyer
     * @throw SQLException
     */
    public String insertOrder(Order order)throws SQLException;
    /**
     * @Title: queryOrderById
     * @Description: 通过ID查询订单信息表
     * @author wangchuan
     * @date 2018/1/19
     * @param id, connection连接, preparedStatement预编译
     * @return domain.Buyer
     * @throw SQLException
     */

    public String queryOrderById(Long id)throws SQLException;
    /**
     * @Title: queryOrderByCondition
     * @Description: 通过条件查询订单信息
     * @author wangchuan
     * @date 2018/1/19
     * @param stringBuyerMap, connection连接, preparedStatement预编译
     * @return domain.Buyer
     * @throw SQLException
     */
    public Map<String, String> queryOrderByCondition(Map<String, String> stringBuyerMap)throws SQLException;
    /**
     * @Title: findOrderByCondition
     * @Description: 通过条件批量查询订单信息
     * @author wangchuan
     * @date 2018/1/19
     * @param stringBuyerMap, offset, size, connection连接, preparedStatement预编译
     * @return java.util.List<domain.Buyer>
     * @throw SQLException
     */
    public Map<String, String> queryOrderByCondition(Map<String, String> stringBuyerMap, Integer startRow, Integer size)throws SQLException;
}
