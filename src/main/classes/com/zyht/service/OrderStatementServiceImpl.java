package com.zyht.service;


import com.zyht.common.util.JdbcConnectionUtils;
import com.zyht.common.util.JdbcUtils;
import com.zyht.dao.OrderStatementDao;
import com.zyht.dao.OrderStatementDaoImpl;
import com.zyht.domain.OrderStatement;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
@Service("OrderStatementServiceImpl")
public class OrderStatementServiceImpl implements OrderStatementService {

    /**
     * @Title: deleteOrderStatmentServiceById
     * @Description: 通过流水ID删除
     * @author cuixinyuan
     * @date 2018/1/22 0022
     */
    @Override
    public Integer deleteOrderStatementById(Long id) throws SQLException {
        //ApplicationContext applicationContext= SpringContextUtil.getApplicationContext();
        //OrderStatementDaoImpl orderStatementDao= (OrderStatementDaoImpl) applicationContext.getBean("OrderStatementDaoImpl");
        OrderStatementDaoImpl orderStatementDao=new OrderStatementDaoImpl();
        Connection connection= JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement=null;
        Integer result=0;
        try{
            connection.setAutoCommit(false);
            result =orderStatementDao.deleteOrderStatementById(id,connection,preparedStatement);
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
         JdbcUtils.release(connection,preparedStatement);
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
        //ApplicationContext applicationContext= SpringContextUtil.getApplicationContext();
        //OrderStatementDaoImpl orderStatementDao= (OrderStatementDaoImpl) applicationContext.getBean("OrderStatementDaoImpl");
        Connection connection=JdbcConnectionUtils.getConnection();
        OrderStatementDaoImpl orderStatementDao=new OrderStatementDaoImpl();
        PreparedStatement preparedStatement=null;
        Integer result=0;
        try{
            connection.setAutoCommit(false);
            result=orderStatementDao.deleteOrderStatementByIds(ids,connection,preparedStatement);
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
           JdbcUtils.release(connection,preparedStatement);
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
        //ApplicationContext applicationContext= SpringContextUtil.getApplicationContext();
        //OrderStatementDaoImpl orderStatementDao= (OrderStatementDaoImpl) applicationContext.getBean("OrderStatementDaoImpl");
        OrderStatementDaoImpl orderStatementDao=new OrderStatementDaoImpl();
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
        //ApplicationContext applicationContext= SpringContextUtil.getApplicationContext();
        //OrderStatementDaoImpl orderStatementDao= (OrderStatementDaoImpl) applicationContext.getBean("OrderStatementDaoImpl");
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
        //ApplicationContext applicationContext= SpringContextUtil.getApplicationContext();
        //OrderStatementDaoImpl orderStatementDao= (OrderStatementDaoImpl) applicationContext.getBean("OrderStatementDaoImpl");
        OrderStatementDaoImpl orderStatementDao=new OrderStatementDaoImpl();
        Connection connection = JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement=null;
        OrderStatement orderStatement = null;
        try{
            connection.setAutoCommit(false);
            orderStatement=orderStatementDao.queryOrderStatementById(id,connection,preparedStatement);
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
     * @Title:queryOrderStatementDaoByCondition
     * @Description: 查询条件
     * @author cuixinyuan
     * @date 2018/1/23 0023
     */
    @Override
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, String> stringOrderStatementMap) throws SQLException {
        //ApplicationContext applicationContext= SpringContextUtil.getApplicationContext();
        //OrderStatementDaoImpl orderStatementDao= (OrderStatementDaoImpl) applicationContext.getBean("OrderStatementDaoImpl");
        OrderStatementDaoImpl orderStatementDao=new OrderStatementDaoImpl();
        Connection connection=JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement=null;
        List<OrderStatement> orderStatements = null;
        try{
            connection.setAutoCommit(false);
             orderStatements=orderStatementDao.queryOrderStatementByCondition(stringOrderStatementMap, connection, preparedStatement);
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
        //ApplicationContext applicationContext= SpringContextUtil.getApplicationContext();
        //OrderStatementDaoImpl orderStatementDao= (OrderStatementDaoImpl) applicationContext.getBean("OrderStatementDaoImpl");
        OrderStatementDaoImpl orderStatementDao=new OrderStatementDaoImpl();
        Connection connection=JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement=null;
        List<OrderStatement> orderStatements = null;
        try{
            connection.setAutoCommit(false);
            orderStatements=orderStatementDao.queryOrderStatementByCondition(stringOrderStatementMap,startRow,size, connection, preparedStatement);
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
        return orderStatements;
    }
}

