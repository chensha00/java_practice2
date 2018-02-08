package dao;

import domain.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * OrderDaoImpl
 *
 * @author Administrator
 * @Description
 * @Date 2018/1/20
 */
public class OrderDaoImpl implements OrderDao {
    java.sql.Date sqlTime=null;
    /**
     * 查询结果集
     */
    private ResultSet resultSet=null;
    /**
     *订单信息
     */
    private Order order=null;
    /**
     *订单信息集合
     */
    private List<Order> orders=null;
    /**
     * @Title: deleteOrderById
     * @Description: 通过买家ID删除
     * @author wangchuan
     * @date 2018/1/19
     * @param id, connection连接, preparedStatement预编译
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer deleteOrderById(Long id, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement=connection.prepareStatement("DELETE FROM `order` WHERE ID=?");
        preparedStatement.setLong((int)1,id);
        Integer operatedRow=preparedStatement.executeUpdate();
        connection.commit();
        return operatedRow;
    }
    /**
     * @Title: deleteOrderByIds
     * @Description: 通过ID批量删除买家
     * @author wangchuan
     * @date 2018/1/19
     * @param  ids, connection连接, preparedStatement预编译
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer deleteOrderByIds(Long[] ids, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        for(Long id:ids){
            preparedStatement=connection.prepareStatement("DELETE FROM `order` WHERE ID=?");
            preparedStatement.setLong((int)1,id);
        }
        Integer operatedRows=preparedStatement.executeUpdate();
        connection.commit();
        return operatedRows;
    }
    /**
     * @Title: updateOrder
     * @Description: 修改买家信息
     * @author wangchuan
     * @date 2018/1/19
     * @param order, connection连接, preparedStatement预编译
     * @return domain.order
     * @throw SQLException
     */
    @Override
    public Integer updateOrder(Order order, Connection connection, PreparedStatement preparedStatement) throws SQLException {

        preparedStatement=connection.prepareStatement("UPDATE `order` SET NUMBER=?,BUYER_ID=?,AMOUNT=?,IS_SUCCESS=?,ORDER_STATUS=?,CREATION_TIME=?,FINISH_TIME=? WHERE ID=?;");

        preparedStatement.setString(1, order.getNumber());
        preparedStatement.setLong(2,order.getBuyerId());
        preparedStatement.setDouble(3,order.getAmount());
        if(order.getIsSuccess()!=null&&order.getIsSuccess().booleanValue()){
            preparedStatement.setByte(4, (byte)1);
        }else{
            preparedStatement.setByte(4, (byte)0);
        }
        preparedStatement.setByte(5, order.getOrderStatus());
        sqlTime=new java.sql.Date(order.getCreationTime().getTime());
        preparedStatement.setDate(6, sqlTime);
        sqlTime=new java.sql.Date(order.getFinishTime().getTime());
        preparedStatement.setDate(7, sqlTime);
        preparedStatement.setLong(8,order.getId());
        Integer operatedRow=preparedStatement.executeUpdate();
        connection.commit();
        return operatedRow;
    }
    /**
     * @Title: insertOrder
     * @Description: 添加买家信息
     * @author wangchuan
     * @date 2018/1/19
     * @param order, connection连接, preparedStatement预编译
     * @return domain.order
     * @throw SQLException
     */
    @Override
    public Integer insertOrder(Order order, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement=connection.prepareStatement("INSERT INTO `order`(NUMBER,BUYER_ID,AMOUNT,IS_SUCCESS,ORDER_STATUS,CREATION_TIME,FINISH_TIME)VALUES(?,?,?,?,?,?,?);");
        preparedStatement.setString((int)1, order.getNumber());
        preparedStatement.setLong((int)2, order.getBuyerId());
        preparedStatement.setDouble((int)3, order.getAmount());
        if(order.getIsSuccess()){
            preparedStatement.setByte((int)4, (byte)1);
        }else{
            preparedStatement.setByte((int)4, (byte)0);
        }
        preparedStatement.setByte((int)5, order.getOrderStatus());
        sqlTime=new java.sql.Date(order.getCreationTime().getTime());
        preparedStatement.setDate((int)6, sqlTime);
        sqlTime=new java.sql.Date(order.getFinishTime().getTime());
        preparedStatement.setDate((int)7, sqlTime);
        Integer operatedRow=preparedStatement.executeUpdate();
        connection.commit();
        return operatedRow;
    }

    /**
     * @Title: queryOrderById
     * @Description: 通过ID查询商品信息表
     * @author wangchuan
     * @date 2018/1/19
     * @param id 编号, connection连接, preparedStatement预编译
     * @return domain.order
     * @throw SQLException
     */
    @Override
    public Order queryOrderById(Long id, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement=connection.prepareStatement("SELECT NUMBER,AMOUNT,CREATION_TIME,FINISH_TIME FROM `order` WHERE ID=?;");
        preparedStatement.setLong((int)1,id);
        //获取查询的结果集
        resultSet=preparedStatement.executeQuery();
        //若非空，开始遍历结果集并给order赋值
        if(resultSet!=null){
            while(resultSet.next()){
                order=new Order();
                order.setNumber(resultSet.getString(1));
                order.setAmount(resultSet.getDouble(2));
                sqlTime=new java.sql.Date(resultSet.getDate(3).getTime());
                order.setCreationTime(sqlTime);
                sqlTime=new java.sql.Date(resultSet.getDate(4).getTime());
                order.setFinishTime(sqlTime);
            }
            return order;
        }else{
            //结果集为空返回null
            return null;
        }
    }
    /**
     * @Title: queryOrderByCondition
     * @Description: 通过特定条件查询商品表
     * @author wangchuan
     * @date 2018/1/19
     * @param stringOrderMap, connection连接, preparedStatement预编译
     * @return java.util.List<domain.order>
     * @throw SQLException
     */

    @Override
    public List<Order> queryOrderByCondition(Map<String, String> stringOrderMap, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        //取出stringOrderMap中的鍵集合
        Set<String> keySet=stringOrderMap.keySet();
        //储存查询结果集
        orders=new ArrayList<Order>();
        //遍历键集，取出对应值并通过键值对条件进行查询
        for(String key:keySet){
            String keyValue=stringOrderMap.get(key);
            preparedStatement=connection.prepareStatement("SELECT NUMBER,AMOUNT,CREATION_TIME,FINISH_TIME FROM `order` WHERE ?=?;");
            preparedStatement.setString((int)1,key);
            preparedStatement.setString((int)2,keyValue);
            resultSet=preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    order=new Order();
                    order.setNumber(resultSet.getString(1));
                    order.setAmount(resultSet.getDouble(2));
                    sqlTime=new java.sql.Date(resultSet.getDate(3).getTime());
                    order.setCreationTime(sqlTime);
                    sqlTime=new java.sql.Date(resultSet.getDate(4).getTime());
                    order.setFinishTime(sqlTime);
                    orders.add(order);
                }
            }else {
                return null;
            }
        }
        return orders;
    }
    /**
     * @Title: queryOrderByCondition
     * @Description: 通过特定条件查询商品表结果分页显示
     * @author wangchuan
     * @date 2018/1/19
     * @param stringOrderMap, connection连接, preparedStatement预编译
     * @return java.util.List<domain.order>
     * @throw SQLException
     */
    @Override
    public List<Order> queryOrderByCondition(Map<String, String> stringOrderMap, Integer startRow, Integer size, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        //取出stringOrderMap中的鍵集合
        Set<String> keySet=stringOrderMap.keySet();
        //储存查询结果集
        orders=new ArrayList<Order>();
        //遍历键集，取出对应值并通过键值对条件进行查询并对结果进行分页显示
        for(String key:keySet){
            String keyValue=stringOrderMap.get(key);
            preparedStatement=connection.prepareStatement("SELECT NUMBER,AMOUNT,CREATION_TIME,FINISH_TIME FROM `order` WHERE ?=? ;");
            preparedStatement.setString((int)1,key);
            preparedStatement.setString((int)2,keyValue);
            resultSet=preparedStatement.executeQuery();
            //指针定位到要startRow行开始输出记录
            resultSet.absolute(startRow);
            // 设置最大查询记录条数
            preparedStatement.setMaxRows(size);
            if(resultSet!=null){
                while(resultSet.next()){
                    order=new Order();
                    order.setNumber(resultSet.getString(1));
                    order.setAmount(resultSet.getDouble(2));
                    sqlTime=new java.sql.Date(resultSet.getDate(3).getTime());
                    order.setCreationTime(sqlTime);
                    sqlTime=new java.sql.Date(resultSet.getDate(4).getTime());
                    order.setFinishTime(sqlTime);
                    orders.add(order);
                }
            }else {
                return null;
            }
        }
        return orders;
    }
}
