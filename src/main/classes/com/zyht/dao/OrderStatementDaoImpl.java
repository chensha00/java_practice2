package com.zyht.dao;

/**
 * Created by Administrator on 2018/1/20.
 */


import com.zyht.domain.OrderStatement;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author chendong
 * @Title: OrderStatementImpl
 * @Description: 流水表实现
 * @date 2018/1/20
 */
@Repository("OrderStatementDao")
public class OrderStatementDaoImpl implements OrderStatementDao {

    /**
     * @param id, connection, preparedStatement
     * @param id
     * @return java.lang.Integer
     * @Title: deleteOrderStatementById
     * @Description: 通过流水ID删除
     * @author chendong
     * @date 2018/1/20
     * @throw SQLException
     */
    @Override
    public Integer deleteOrderStatementById(Long id, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM order_statement WHERE ID=?");
        preparedStatement.setLong(1, id);
        Integer operatedRow = preparedStatement.executeUpdate();
        connection.commit();
        return operatedRow;
    }

    /**
     * @param ids, connection, preparedStatement
     * @return java.lang.Integer
     * @Title: deleteOrderStatementByIds
     * @Description: 通过ID批量删除流水
     * @author chednong
     * @date 2018/1/20
     * @throw SQLException
     */
    @Override
    public Integer deleteOrderStatementByIds(Long[] ids, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        for (Long id : ids) {
            preparedStatement = connection.prepareStatement("DELETE FROM order_statement WHERE ID=?");
            preparedStatement.setLong(1, id);
        }
        Integer operatedRows = preparedStatement.executeUpdate();
        connection.commit();
        return operatedRows;
    }

    /**
     * @param orderStatement, connection, preparedStatement
     * @return OrderStatement
     * @Title: updateOrderStatement
     * @Description: 修改流水信息
     * @author chendong
     * @date 2018/1/20
     * @throw SQLException
     */
    @Override
    public OrderStatement updateOrderStatement(OrderStatement orderStatement, Connection connection, PreparedStatement preparedStatement) throws SQLException {

        preparedStatement = connection.prepareStatement("UPDATE order_statement SET ORDER_DETAIL_ID=?,BUYER_ID=?,SELLER_ID=?,NUMBER=?,AMOUNT=?,FINISH_TIME=? WHERE ID=?;");
        preparedStatement.setLong(1, orderStatement.getOrderDetailId());
        preparedStatement.setLong(2, orderStatement.getBuyerId());
        preparedStatement.setLong(3, orderStatement.getSellerId());
        preparedStatement.setString(4, orderStatement.getNumber());
        preparedStatement.setDouble(5, orderStatement.getAmount());
        java.sql.Date sqlTime=new  java.sql.Date(orderStatement.getFinishTime().getTime());
        preparedStatement.setDate(6, sqlTime);
        preparedStatement.setLong(7, orderStatement.getId());
        preparedStatement.executeUpdate();
        connection.commit();
        return orderStatement;
    }

    /**
     * @param orderStatement, connection, preparedStatement
     * @return OrderStatement
     * @Title: insertOrderStatement
     * @Description: 添加流水信息
     * @author chendong
     * @date 2018/1/20
     * @throw SQLException
     */
    @Override
    public OrderStatement insertOrderStatement(OrderStatement orderStatement, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet;
        preparedStatement = connection.prepareStatement("INSERT INTO order_statement(ID,ORDER_DETAIL_ID,BUYER_ID,SELLER_ID,NUMBER,AMOUNT,FINISH_TIME)VALUES(DEFAULT,?,?,?,?,?,?);");
        preparedStatement.setLong(1, orderStatement.getOrderDetailId());
        preparedStatement.setLong(2, orderStatement.getBuyerId());
        preparedStatement.setLong(3, orderStatement.getSellerId());
        preparedStatement.setString(4, orderStatement.getNumber());
        preparedStatement.setDouble(5, orderStatement.getAmount());
        java.sql.Date sqlTime=new  java.sql.Date(orderStatement.getFinishTime().getTime());
        preparedStatement.setDate(6, sqlTime);
        preparedStatement.executeUpdate();
        resultSet=preparedStatement.getGeneratedKeys();
        resultSet.getInt(1);
        connection.commit();
        return orderStatement;
    }

    /**
     * @param id, connection, preparedStatement
     * @return OrderStatement
     * @Title: queryOrderStatementById
     * @Description: 通过ID查询流水信息表
     * @author chendong
     * @date 2018/1/20
     * @throw SQLException
     */
    @Override
    public OrderStatement queryOrderStatementById(Long id, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet;
        OrderStatement orderStatement = null;
        preparedStatement = connection.prepareStatement("SELECT ORDER_DETAIL_ID,BUYER_ID,SELLER_ID,NUMBER ,AMOUNT,FINISH_TIME FROM order_statement WHERE ID=?;");
        preparedStatement.setLong(1, id);
        //获取查询的结果集
        resultSet = preparedStatement.executeQuery();
        //若非空，开始遍历结果集并给orderStatement赋值
        if (resultSet != null) {
            while (resultSet.next()) {
                orderStatement = new OrderStatement();
                orderStatement.setNumber(resultSet.getString(1));
                orderStatement.setAmount(resultSet.getDouble(2));
                orderStatement.setFinishTime(resultSet.getDate(3));
            }

        } else {
            //结果集为空返回null
            return null;
        }
        return orderStatement;
    }

    /**
     * @param stringOrderStatementMap, connection, preparedStatement
     * @return java.util.List<OrderStatement>
     * @Title: queryOrderStatementByCondition
     * @Description: 通过特定条件查询流水表
     * @author chendong
     * @date 2018/1/20
     * @throw SQLException
     */
    @Override
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, String> stringOrderStatementMap, Connection connection, PreparedStatement preparedStatement) throws SQLException {

        ResultSet resultSet;
        OrderStatement orderStatement;
        List<OrderStatement> orderStatementList = new ArrayList<OrderStatement>();
        String sql = null;
        if (stringOrderStatementMap != null) {
            sql = "SELECT\n" + " `ID`,\n" + "`ORDER_DETAIL_ID`,\n" + "`BUYER_ID`,\n" + "`SELLER_ID`,\n" + "`NUMBER`,\n" + "`AMOUNT`,\n" + "`FINISH_TIME\n`" + "FROM\n" + "`order_statement` WHERE 1=1 ";
            Set<Map.Entry<String, String>> set = stringOrderStatementMap.entrySet();
            Iterator<Map.Entry<String, String>> iterator = set.iterator();
            if (iterator.hasNext()) {
                Map.Entry<String, String> map = iterator.next();
                sql =sql+ "AND"+ map.getKey() + " like '%" + map.getValue() + "%'";
            }
            while (iterator.hasNext()) {
                Map.Entry<String, String> map = iterator.next();
                sql =sql+ "AND " + map.getKey() + " like '%" + map.getValue() + "%'";
            }
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderStatement = new OrderStatement();
                orderStatement.setId(resultSet.getLong(1));
                orderStatement.setOrderDetailId(resultSet.getLong(2));
                orderStatement.setBuyerId(resultSet.getLong(3));
                orderStatement.setSellerId(resultSet.getLong(4));
                orderStatement.setNumber(resultSet.getString(5));
                orderStatement.setAmount(resultSet.getDouble(6));
                orderStatement.setFinishTime(resultSet.getDate(7));
                orderStatementList.add(orderStatement);
            }
        }else{

        }
        return orderStatementList;
    }

    /**
     * @param stringOrderStatementMap, connection, preparedStatement
     * @return java.util.List<OrderStatement>
     * @Title: queryOrderStatementByCondition
     * @Description: 通过特定条件查询流水表并分页显示
     * @author chendong
     * @date 2018/1/19
     * @throw SQLException
     */
    @Override
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, String> stringOrderStatementMap, Integer startRow, Integer size, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet;
        OrderStatement orderStatement;
        List<OrderStatement> orderStatementList = new ArrayList<OrderStatement>();
        String sql = null;
        if (stringOrderStatementMap != null) {
            sql = "SELECT\n" + " `ID`,\n" + "`ORDER_DETAIL_ID`,\n" + "`BUYER_ID`,\n" + "`SELLER_ID`,\n" + "`NUMBER`,\n" + "`AMOUNT`,\n" + "`FINISH_TIME`\n" + "FROM\n" + "`order_statement` WHERE 1=1 ";
            Set<Map.Entry<String, String>> set = stringOrderStatementMap.entrySet();
            Iterator<Map.Entry<String, String>> iterator = set.iterator();
            if (iterator.hasNext()) {
                Map.Entry<String, String> map = iterator.next();
                sql =sql+ "AND"+ map.getKey() + " like '%" + map.getValue() + "%'";
            }
            while (iterator.hasNext()) {
                Map.Entry<String, String> map = iterator.next();
                sql =sql+ "AND " + map.getKey() + " like '%" + map.getValue() + "%'";
            }
            sql = sql+" LIMIT " + startRow + ", " + size;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderStatement = new OrderStatement();
                orderStatement.setId(resultSet.getLong(1));
                orderStatement.setOrderDetailId(resultSet.getLong(2));
                orderStatement.setBuyerId(resultSet.getLong(3));
                orderStatement.setSellerId(resultSet.getLong(4));
                orderStatement.setNumber(resultSet.getString(5));
                orderStatement.setAmount(resultSet.getDouble(6));
                orderStatement.setFinishTime(resultSet.getDate(7));
                orderStatementList.add(orderStatement);
            }
        }
        return orderStatementList;
    }

}


