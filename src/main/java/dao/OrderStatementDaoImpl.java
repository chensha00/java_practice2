package dao;

/**
 * Created by Administrator on 2018/1/20.
 */


import domain.OrderStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chendong
 * @Title: OrderStatementImpl
 * @Description: 流水表实现
 * @date 2018/1/20
 */
public class OrderStatementDaoImpl implements OrderStatementDao {
    /**
     * 查询结果集
     */
    private ResultSet resultSet = null;
    /**
     * 流水信息
     */
    private OrderStatement orderStatement = null;
    /**
     * 流水信息集合
     */
    private List<OrderStatement> orderStatements = null;

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
     * @return domain.OrderStatement
     * @Title: updateOrderStatement
     * @Description: 修改流水信息
     * @author chendong
     * @date 2018/1/20
     * @throw SQLException
     */

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
     * @return domain.OrderStatement
     * @Title: insertOrderStatement
     * @Description: 添加流水信息
     * @author chendong
     * @date 2018/1/20
     * @throw SQLException
     */

    public OrderStatement insertOrderStatement(OrderStatement orderStatement, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO order_statement(ID,ORDER_DETAIL_ID,BUYER_ID,SELLER_ID,NUMBER,AMOUNT,FINISH_TIME)VALUES(DEFAULT,?,?,?,?,?,?);");
        preparedStatement.setLong(1, orderStatement.getOrderDetailId());
        preparedStatement.setLong(2, orderStatement.getBuyerId());
        preparedStatement.setLong(3, orderStatement.getSellerId());
        preparedStatement.setString(4, orderStatement.getNumber());
        preparedStatement.setDouble(5, orderStatement.getAmount());
        java.sql.Date sqlTime=new  java.sql.Date(orderStatement.getFinishTime().getTime());
        preparedStatement.setDate(6, sqlTime);
        preparedStatement.executeUpdate();
        connection.commit();
        return orderStatement;
    }




    /**
     * @param id, connection, preparedStatement
     * @return domain.OrderStatement
     * @Title: queryOrderStatementById
     * @Description: 通过ID查询流水信息表
     * @author chendong
     * @date 2018/1/20
     * @throw SQLException
     */
    public OrderStatement queryOrderStatementById(Long id, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT NUMBER ,AMOUNT,FINISH_TIME FROM order_statement WHERE ID=?;");
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
     * @return java.util.List<domain.OrderStatement>
     * @Title: queryOrderStatementByCondition
     * @Description: 通过特定条件查询流水表
     * @author chendong
     * @date 2018/1/20
     * @throw SQLException
     */

    @Override
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, String> stringOrderStatementMap, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        //取出stringOrderStatementMap中的鍵集合
        Set<String> keySet = stringOrderStatementMap.keySet();
        //储存查询结果集
//        orderStatement = new OrderStatement();
        orderStatements=new ArrayList<OrderStatement>();
        //遍历键集，取出对应值并通过键值对条件进行查询
        for (String key : keySet) {
            String keyValue = stringOrderStatementMap.get(key);
            preparedStatement = connection.prepareStatement("SELECT NUMBER ,AMOUNT,FINISH_TIME FROM order_statement WHERE ?=?;");
            preparedStatement.setString(1, key);
            preparedStatement.setString(2, keyValue);
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    orderStatement = new OrderStatement();
                    orderStatement.setNumber(resultSet.getString(1));
                    orderStatement.setAmount(resultSet.getDouble(2));
                    orderStatement.setFinishTime(resultSet.getDate(3));
                    orderStatements.add(orderStatement);
                }
            } else {
                return null;
            }
        }
        return orderStatements;
    }

    /**
     * @param stringOrderStatementMap, connection, preparedStatement
     * @return java.util.List<domain.OrderStatement>
     * @Title: queryOrderStatementByCondition
     * @Description: 通过特定条件查询流水表并分页显示
     * @author chendong
     * @date 2018/1/19
     * @throw SQLException
     */
    @Override
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, String> stringOrderStatementMap, Integer startRow, Integer size, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        //取出stringOrderStatementMap中的鍵集合
        Set<String> keySet = stringOrderStatementMap.keySet();
        //储存查询结果集
//        orderStatement = new OrderStatement();
        orderStatements=new ArrayList<OrderStatement>();
        //遍历键集，取出对应值并通过键值对条件进行查询并对结果进行分页显示
        for (String key : keySet) {
            String keyValue = stringOrderStatementMap.get(key);
            preparedStatement = connection.prepareStatement("SELECT NUMBER ,AMOUNT,FINISH_TIME FROM order_statement WHERE ?=? ;");
            preparedStatement.setString(1, key);
            preparedStatement.setString(2, keyValue);
            resultSet = preparedStatement.executeQuery();
            //指针定位到要startRow行开始输出记录
            resultSet.absolute(startRow);
            // 设置最大查询记录条数
            preparedStatement.setMaxRows(size);
            if (resultSet != null) {
                while (resultSet.next()) {
                    orderStatement = new OrderStatement();
                    orderStatement.setNumber(resultSet.getString(1));
                    orderStatement.setAmount(resultSet.getDouble(2));
                    orderStatement.setFinishTime(resultSet.getDate(3));
                    orderStatements.add(orderStatement);
                }
            } else {
                return null;
            }
        }
        return orderStatements;
    }


}


