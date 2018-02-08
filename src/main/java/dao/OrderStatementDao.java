package dao;

/**
 * Created by Administrator on 2018/1/20.
 */


import domain.Order;
import domain.OrderStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author chendong
 * @Title: OrderStatementDao
 * @Description: 流水表接口
 * @date 2018/1/20
 */

public interface OrderStatementDao {

    /**
     * @Title: deleteOrderStatementById
     * @Description: 通过流水ID删除
     * @author chendong
     * @date 2018/1/20
     * @param id, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    public Integer deleteOrderStatementById(Long id,Connection connection,PreparedStatement preparedStatement)throws SQLException;

    /**
     * @Title: deleteOrderStatementByIds
     * @Description: 通过ID批量删除订单流水
     * @author chendong
     * @date 2018/1/19
     * @param  ids, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    public Integer deleteOrderStatementByIds(Long[] ids,Connection connection,PreparedStatement preparedStatement)throws SQLException;
    /**
     * @Title: updateOrderStatement
     * @Description: 添加或者修改流水信息
     * @author chendong
     * @date 2018/1/20
     * @param orderStatement, connection, preparedStatement
     * @return domain.OrderStatement
     * @throw SQLException
     */

    public OrderStatement updateOrderStatement(OrderStatement orderStatement,Connection connection,PreparedStatement preparedStatement)throws SQLException;
    /**
     * @Title: insertOrUpdateOrderStatement
     * @Description: 添加或者修改流水信息
     * @author chendong
     * @date 2018/1/20
     * @param orderStatement, connection, preparedStatement
     * @return domain.OrderStatement
     * @throw SQLException
     */
    public OrderStatement insertOrderStatement(OrderStatement orderStatement, Connection connection, PreparedStatement preparedStatement)throws SQLException;
    /**
     * @Title: queryOrderStatementById
     * @Description: 通过ID查询流水信息表
     * @author chendong
     * @date 2018/1/20
     * @param id, connection, preparedStatement
     * @return domain.OrderStatement
     * @throw SQLException
     */

    public OrderStatement queryOrderStatementById(Long id, Connection connection, PreparedStatement preparedStatement)throws SQLException;
    /**
     * @Title: queryOrderStatementByCondition
     * @Description: 通过条件查询流水信息
     * @author chendong
     * @date 2018/1/20
     * @param stringOrderStatementMap, connection, preparedStatement
     * @return domain.OrderStatement
     * @throw SQLException
     */
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, String> stringOrderStatementMap, Connection connection, PreparedStatement preparedStatement)throws SQLException;
    /**
     * @Title: findOrderStatementByCondition
     * @Description: 通过条件批量查询流水信息
     * @author chendong
     * @date 2018/1/20
     * @param stringOrderStatementMap, offset, size, connection, preparedStatement
     * @return java.util.List<domain.OrderStatement>
     * @throw SQLException
     */
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, String> stringOrderStatementMap, Integer startRow, Integer size, Connection connection, PreparedStatement preparedStatement)throws SQLException;
}
