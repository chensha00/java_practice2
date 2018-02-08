package service;


import common.util.JdbcConnectionUtils;
import common.util.JdbcUtils;
import dao.OrderStatementDao;
import dao.OrderStatementDaoImpl;
import domain.OrderStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderStatementServiceImpl implements OrderStatementService {
    /**
     * 查询结果集
     */
    private ResultSet resultSet = null;
    /**
     * 买家信息
     */
    private OrderStatement orderStatement = null;
    /**
     * 买家信息集合
     */
    private List<OrderStatement> orderStatements = null;
    /**
     * 受影响行
     */
    private Integer operatedRows = null;

    /**
     * @Title: deleteOrderStatmentServiceById
     * @Description: 通过流水ID删除
     * @author cuixinyuan
     * @date 2018/1/22 0022
     */
    @Override
    public Integer deleteOrderStatementById(Long id) throws SQLException {
        OrderStatementDao orderStatementDao=new OrderStatementDaoImpl();
        Connection connection= JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement1=null;
        Integer result=0;
        try{
            connection.setAutoCommit(false);
            result =orderStatementDao.deleteOrderStatementById(id,connection,preparedStatement1);
            System.out.println(result);
        }catch (SQLException e){
            try{
                //出现异常，事务回滚
                connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
         JdbcUtils.release(connection,preparedStatement1);
        }
        return result;
    }

    /**
     * @Title: deleteOrderStatmentServiceImplByIds
     * @Description: 通过ID批量删除流水
     * @author cuixinyuan
     * @date 2018/1/22 0022
     */
    @Override
    public Integer deleteOrderStatementByIds(Long[] ids) throws SQLException {
        Connection connection=JdbcConnectionUtils.getConnection();
        OrderStatementDao orderStatementDao=new OrderStatementDaoImpl();
        PreparedStatement preparedStatement1=null;
        Integer result=0;
        try{
            connection.setAutoCommit(false);
            result=orderStatementDao.deleteOrderStatementByIds(ids,connection,preparedStatement1);
            System.out.println(result);
        }catch (SQLException e){
            try{
                //出现异常，事务回滚
                connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
           JdbcUtils.release(connection,preparedStatement1);
        }
        return result;
    }

    /**
     * @Title: updateOrderStatement
     * @Description: 修改流水
     * @author cuixinyuan
     * @date 2018/1/25 0025
     */
    @Override
    public OrderStatement updateOrderStatement(OrderStatement orderStatement) throws SQLException {
        OrderStatementDao orderStatementDao=new OrderStatementDaoImpl();
        Connection connection=JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement=null;
        try{
            connection.setAutoCommit(false);
            orderStatement=orderStatementDao.updateOrderStatement(orderStatement,connection,preparedStatement);
            System.out.println("流水修改成功");
        }catch (SQLException e){
            try{
                //出现异常，事务回滚
                connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            JdbcUtils.release(connection,preparedStatement);
        }
        return orderStatement;
    }

    /**
     * @Title:insertOrderStatment
     * @Description: 添加
     * @author cuixinyuan
     * @date 2018/1/23 0023
     */
    @Override
    public OrderStatement insertOrderStatement(OrderStatement orderStatement) throws SQLException {
        OrderStatementDaoImpl orderStatementDao=new OrderStatementDaoImpl();
        Connection connection=JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement=null;
        try{
            connection.setAutoCommit(false);
            orderStatement=orderStatementDao.insertOrderStatement(orderStatement,connection,preparedStatement);
            System.out.println("流水添加成功");
        }catch (SQLException e){
            try{
                //事务回滚
                connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            JdbcUtils.release(connection,preparedStatement);
        }
        return orderStatement;
    }

    /**
     * @Title:queryOrderStatementServiceById
     * @Description: 根据id查询
     * @author cuixinyuan
     * @date 2018/1/23 0023
     */
    @Override
    public OrderStatement queryOrderStatementById(Long id) throws SQLException {
        OrderStatementDao orderStatementDao=new OrderStatementDaoImpl();
        Connection connection = JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement1=null;
        try{
            connection.setAutoCommit(false);
            orderStatement=orderStatementDao.queryOrderStatementById(id,connection,preparedStatement1);
        }catch (SQLException e){
            try{
                //事务回滚
                connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            JdbcUtils.release(connection,preparedStatement1);
        }
        return null;
    }

    /**
     * @Title:queryOrderStatementDaoByCondition
     * @Description: 查询条件
     * @author cuixinyuan
     * @date 2018/1/23 0023
     */
    @Override
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, String> stringOrderStatementMap) throws SQLException {
        OrderStatementDao orderStatementDao=new OrderStatementDaoImpl();
        Connection connection=JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement1=null;
        try{
            connection.setAutoCommit(false);
             orderStatements=orderStatementDao.queryOrderStatementByCondition(stringOrderStatementMap, connection, preparedStatement1);
        }catch (SQLException e){
            try{
                //事务回滚
                connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
                JdbcUtils.release(connection,preparedStatement1);
        }
        return orderStatements;
    }
/**
 * @Title: queryOrderStatementByCondition
 * @Description: 按条件批量查询
 * @author cuixinyuan
 * @date 2018/1/25 0025
 */
    @Override
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, String> stringOrderStatementMap, Integer startRow, Integer size) throws SQLException {
        OrderStatementDao orderStatementDao=new OrderStatementDaoImpl();
        Connection connection=JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement1=null;
        try{
            connection.setAutoCommit(false);
            orderStatements=orderStatementDao.queryOrderStatementByCondition(stringOrderStatementMap, connection, preparedStatement1);
        }catch (SQLException e){
            try{
                //事务回滚
                connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
           JdbcUtils.release(connection,preparedStatement1);
        }
        return orderStatements;
    }
}

