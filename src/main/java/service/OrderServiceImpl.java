package service;

import common.util.JdbcConnectionUtils;
import common.util.JdbcUtils;
import dao.OrderDao;
import dao.OrderDaoImpl;
import domain.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * OrderServiceImpl
 * @author guoxin
 * @Description
 * @Date 2018/1/20
 */
public class OrderServiceImpl implements OrderService {
    OrderDao orderDao =new OrderDaoImpl();
    /**
     * @ClassName deleteOrderById
     * @Description 删除订单
     * @author guoxin
     * @Date 2018/1/23
     * @param id
     * @return  respond
    */
    @Override
    public String deleteOrderById(Long id) throws SQLException {
        Connection connection= JdbcConnectionUtils.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement=null;
        String respond=null;
        try{
            connection.setAutoCommit(false);
            Integer result = orderDao.deleteOrderById(id, connection, preparedStatement);
            if(result == 0){
                respond = "删除失败！";
            }else {
                respond = "删除成功！";
            }
        }catch (SQLException e){
            try{
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                connection.rollback();//出现异常，事物回滚
            }
            e.printStackTrace();
        }
        finally {
            try {
                    connection.commit();
                JdbcUtils.release(connection, preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.release(connection,preparedStatement);//关闭连接
        }
        return respond;
    }
   /**
    * @ClassName deleteOrderByIds
    * @Description   批量删除订单
    * @author guoxin
    * @Date 2018/1/23
    * @param ids
    * @return   respond
    */
    @Override
    public String deleteOrderByIds(Long[] ids) throws SQLException {
        Connection connection= JdbcConnectionUtils.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement=null;
        String  respond = null;                                      //返回给用户的信息
        try{
            connection.setAutoCommit(false);
            Integer result = orderDao.deleteOrderByIds(ids, connection, preparedStatement);
            if(result == 0){
                respond = "删除失败！";
            }else {
                respond = "删除成功！";
            }
        }catch (SQLException e){
            try{
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                connection.rollback();//出现异常，事物回滚
            }
            e.printStackTrace();
        }
        finally {
            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.release(connection,preparedStatement);                        //关闭连接
            return  respond;                                                           //返回结果
        }

    }
    /**
     * @ClassName delOrder
     * @Description 删除订单
     * @author guoxin
     * @Date 2018/1/2
     * @param order
     *@return  respond
     */
    @Override
    public String updateOrder(Order order) throws SQLException {
        Connection  connection= JdbcConnectionUtils.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement=null;
        String respond=null;
        try{
            connection.setAutoCommit(false);
            Integer result = orderDao.updateOrder(order, connection, preparedStatement);
            if(result == 0){
                respond = "更新失败！";
            }else {
                respond = "更新成功！";
            }
        }catch (SQLException e){
            try{
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                connection.rollback();//出现异常，事物回滚
            }
            e.printStackTrace();
        }
        finally {
            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.release(connection,preparedStatement);//关闭连接
        }
        return respond;
    }
    /**
     * @ClassName insertOrder
     * @Description 插入订单
     * @author guoxin
     * @Date 2018/1/22
     * @param order
     *@return  respond
     */
    @Override
    public String insertOrder(Order order) throws SQLException {
        Connection connection= JdbcConnectionUtils.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement=null;
        String respond=null;
        try{
            connection.setAutoCommit(false);
            Integer result = orderDao.insertOrder(order, connection, preparedStatement);
            if(result == 0){
                respond = "插入失败！";
            }else {
                respond = "插入成功！";
            }
        }catch (SQLException e){
            try{
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                connection.rollback();//出现异常，事物回滚
            }
            e.printStackTrace();
        }
        finally {
            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return respond;
    }
    /**
     * @ClassName queryOrder
     * @Description 查询订单
     * @author guoxin
     * @Date 2018/1/22
     * @param id
     * @return id
     */
    @Override
    public String queryOrderById(Long id) throws SQLException {
        Connection connection= JdbcConnectionUtils.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement=null;
        String respond=null;
        try{
            connection.setAutoCommit(false);
           Order result = orderDao.queryOrderById(id,connection, preparedStatement);
            if(result == null){
                respond = "查询失败！";
            }else {
                respond = "查询成功！";
            }
        }catch (SQLException e){
            try{
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                connection.rollback();//出现异常，事物回滚
            }
            e.printStackTrace();
        }
        finally {
            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return respond;
    }

    /**
     * @ClassName queryOrderByCondition
     * @Description 查询条件
     * @author guoxin
     * @Date 2018/1/22
     *  @param stringBuyerMap connection  preparedStatement
     * @return  stringBuyerMap
     */
    @Override
    public Map<String, String> queryOrderByCondition(Map<String, String> stringBuyerMap) throws SQLException {
        Connection connection= JdbcConnectionUtils.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement=null;
        String respond=null;//返回给用户的字符串
        try{
            connection.setAutoCommit(false);
            List<Order> result = orderDao.queryOrderByCondition(stringBuyerMap, connection, preparedStatement);
            if(result == null){
                respond = "查询失败！";
            }else {
                respond = "查询成功！";
            }
            System.out.print(result);
        }catch (SQLException e){
            try{
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                connection.rollback();//出现异常，事物回滚
            }
            e.printStackTrace();
        }
        finally {
            try {
                connection.commit();                               //提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.release(connection,preparedStatement);       //关闭连接
        }
        return stringBuyerMap;
    }

/**
 * @ClassName  queryOrderByCondition
 * @Description 按条件能查询
 * @author guoxin
 * @Date 2018/1/23
 * @param  stringBuyerMap   startRow size
 * @return stringBuyerMap
*/
    @Override
    public Map<String, String> queryOrderByCondition(Map<String, String> stringBuyerMap, Integer startRow, Integer size) throws SQLException {
        Connection connection= JdbcConnectionUtils.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement=null;
        String respond=null;//返回给用户的字符串
        try{
            connection.setAutoCommit(false);
            List<Order> result = orderDao.queryOrderByCondition(stringBuyerMap, connection, preparedStatement);
            if(result == null){
                respond = "查询失败！";
            }else {
                respond = "查询成功！";
            }
            System.out.println(respond);
        }catch (SQLException e){
            try{
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                connection.rollback();//出现异常，事物回滚
            }
            e.printStackTrace();
        }
        finally {
            try {
                connection.commit();                //提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.release(connection,preparedStatement);//关闭连接
        }
        return stringBuyerMap;                           //返回查询的信息给用户
    }
}
