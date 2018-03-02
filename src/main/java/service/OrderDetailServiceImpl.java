package service;/**
 * Created by HAN on 2018/1/23.
 */

import common.util.JdbcConnectionUtils;
import common.util.JdbcUtils;
import dao.OrderDetailDao;
import dao.OrderDetailDaoImpl;
import domain.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author renxu
 * @ClassName OrderDetailServiceImpl
 * @Description 类描述
 * @date 2018/1/23
 */
public class OrderDetailServiceImpl implements OrderDetailService {
    //定义一个OrderDetailDao层的对象
    static OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
    /**
     * @Title: deleteOrderById
     * @Description: 通过订单ID删除
     * @author renxu
     * @date 2018/1/23
     * @param id, connection, preparedStatement
     * @return java.lang.Integer
     * @throw Exception
     */
    @Override
    public String deleteOrderById(Long id)throws SQLException{
        Connection connection = JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement = null;
        String respond = null;//返回给用户的字符串
        try {
            //建立事物
            connection.setAutoCommit(false);
            Integer result = orderDetailDao.deleteOrderById(id, connection, preparedStatement);
            if(result == 0){
                respond = "删除失败!";
            }else {
                respond = "删除成功!";
                connection.commit();//提交事务
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();//出现异常，事物回滚
        }finally {
            JdbcUtils.release(connection, preparedStatement);//关闭连接
            return respond;//返回结果给用户
        }
    }

    /**
     * @Title: deleteOrderByIds
     * @Description: 通过ID批量删除订单
     * @author renxu
     * @date 2018/1/23
     * @param  ids, connection, preparedStatement
     * @return java.lang.Integer
     * @throw Exception
     */
    @Override
    public String deleteOrderByIds(Long[] ids)throws SQLException{
        Connection connection = JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement = null;
        String respond = null;//返回给用户的字符串
        try {
            //建立事物
            connection.setAutoCommit(false);
            Integer result = orderDetailDao.deleteOrderByIds(ids, connection, preparedStatement);
            if(result == 0){
                respond = "删除失败!";
            }else {
                respond = "删除成功!";
                connection.commit();//提交事务
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();//出现异常，事物回滚
        }finally {
            JdbcUtils.release(connection, preparedStatement);//关闭连接
            return respond;//返回结果给用户
        }
    }

    /**
     * @Title: insertOrder
     * @Description: 添加订单详情
     * @author renxu
     * @date 2018/1/23
     * @param orderDetail, connection, preparedStatement
     * @return domain.Order
     * @throw Exception
     */
    @Override
    public String insertOrder(OrderDetail orderDetail)throws SQLException{
        Connection connection = JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement = null;
        String respond = null;//返回给用户的字符串
        try {
            //建立事物
            connection.setAutoCommit(false);
            OrderDetail result = orderDetailDao.insertOrder(orderDetail, connection, preparedStatement);
            if(result == null){
                respond = "添加失败!";
            }else {
                respond = "添加成功!";
                connection.commit();//提交事务
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();//出现异常，事物回滚
        }finally {
            JdbcUtils.release(connection, preparedStatement);//关闭连接
            return respond;//返回结果给用户
        }
    }
    /**
     * @Title: updateOrder
     * @Description: 更新订单详情
     * @author renxu
     * @date 2018/1/23
     * @param orderDetail, connection, preparedStatement
     * @return domain.Order
     * @throw Exception
     */
    @Override
    public String updateOrder(OrderDetail orderDetail)throws SQLException{
        Connection connection = JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement = null;
        String respond = null;//返回给用户的字符串
        try {
            //建立事物
            connection.setAutoCommit(false);
            OrderDetail result = orderDetailDao.updateOrder(orderDetail, connection, preparedStatement);
            if(result == null){
                respond = "更新失败!";
            }else {
                respond = "更新成功!";
                connection.commit();//提交事务
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();//出现异常，事物回滚
        }finally {
            JdbcUtils.release(connection, preparedStatement);//关闭连接
            return respond;//返回结果给用户
        }
    }
    /**
     * @Title: queryOrderById
     * @Description: 通过ID查询订单信息表
     * @author renxu
     * @date 2018/1/23
     * @param id, connection, preparedStatement
     * @return domain.Order
     * @throw Exception
     */
    @Override
    public OrderDetail queryOrderById(Long id)throws SQLException{
        Connection connection = JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement = null;
        OrderDetail result = new OrderDetail();
        try {
            //建立事物
            connection.setAutoCommit(false);
             result = orderDetailDao.queryOrderById(id, connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();//出现异常，事物回滚
        }finally {
            JdbcUtils.release(connection, preparedStatement);//关闭连接
            return result;
        }
    }
    /**
     * @Title: queryOrderByCondition
     * @Description: 通过条件查询订单信息
     * @author renxu
     * @date 2018/1/23
     * @param stringOrderMap, connection, preparedStatement
     * @return domain.Order
     * @throw Exception
     */
    @Override
    public List<OrderDetail> queryOrderByCondition(Map<String,String> stringOrderMap)throws SQLException{
        Connection connection = JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement = null;
        List<OrderDetail> result = new LinkedList<OrderDetail>();
        try {
            //建立事物
            connection.setAutoCommit(false);
             result = orderDetailDao.queryOrderByCondition(stringOrderMap, connection, preparedStatement);
            Iterator<OrderDetail> iterator = result.iterator();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();//出现异常，事物回滚
        }finally {
            JdbcUtils.release(connection, preparedStatement);//关闭连接
            return result;
        }
    }
    /**
     * @Title: findOrderByCondition
     * @Description: 通过条件分页查询订单信息
     * @author renxu
     * @date 2018/1/23
     * @param stringOrderMap, offset, size, connection, preparedStatement
     * @return java.util.List<domain.Order>
     * @throw Exception
     */
    @Override
    public List<OrderDetail> queryOrderByCondition(Map<String,String> stringOrderMap,Integer offset,Integer size)throws SQLException{
        Connection connection = JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement = null;
        List<OrderDetail> result = new LinkedList<OrderDetail>();
        try {
            //建立事物
            connection.setAutoCommit(false);
            result = orderDetailDao.queryOrderByCondition(stringOrderMap, connection, preparedStatement);
            Iterator<OrderDetail> iterator = result.iterator();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();//出现异常，事物回滚
        }finally {
            JdbcUtils.release(connection, preparedStatement);//关闭连接
            return result;
        }
    }

}
