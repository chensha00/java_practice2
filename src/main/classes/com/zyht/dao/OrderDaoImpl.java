package com.zyht.dao;

import com.zyht.base.Base;
import com.zyht.domain.Order;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

/**
 * OrderDaoImpl·
 *
 * @author Administrator
 * @Description
 * @Date 2018/1/20
 */
@Repository("orderDao")
public class OrderDaoImpl extends Base<Order> implements OrderDao {
        @Autowired
    private JdbcTemplate jdbcTemplate;
//    @Autowired
//    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * @param id
     * @return java.lang.Integer
     * @Title: deleteOrderById
     * @Description: 单个删除订单
     * @author DengHongbo
     * @date 2018/2/6 17:30
     */
    @Override
    public Integer deleteOrderById(Long Id) {
//        Order order = new Order();
//        order.setId(id);
        return this.sqlSessionTemplate.delete(getMybaitsNameSpace() + "delete", Id);
//        preparedStatement=connection.prepareStatement("DELETE FROM `order` WHERE ID=?");
//        preparedStatement.setLong((int)1,id);
//        Integer operatedRow=preparedStatement.executeUpdate();
//        connection.commit();
//        return operatedRow;
    }

    /**
     * @param ids
     * @return java.lang.Integer
     * @Title: deleteOrderByIds
     * @Description: 批量删除订单
     * @author DengHongbo
     * @date 2018/2/6 17:29
     */
    @Override
    public Integer deleteOrderByIds(List<Long> ids) {
        return this.sqlSessionTemplate.delete(getMybaitsNameSpace() + "deletes", ids);

//                for(Long id:ids){
//            preparedStatement=connection.prepareStatement("DELETE FROM `order` WHERE ID=?");
//            preparedStatement.setLong((int)1,id);
//        }
//        Integer successRow=preparedStatement.executeUpdate();
//        connection.commit();
//        return successRow;
    }

    /**
     * @param order
     * @return int
     * @Title: updateOrder
     * @Description: 更新订单数据
     * @author DengHongbo
     * @date 2018/2/6 17:30
     * @throw
     */
    @Override
    public int updateOrder(Order order) {
        return this.sqlSessionTemplate.update(getMybaitsNameSpace() + "update", order);
//        preparedStatement = connection.prepareStatement("UPDATE `order` SET NUMBER=?,BUYER_ID=?,AMOUNT=?,IS_SUCCESS=?,ORDER_STATUS=?,CREATION_TIME=?,FINISH_TIME=? WHERE ID=?;");
//        preparedStatement.setString(1, order.getNumber());
//        preparedStatement.setLong(2, order.getBuyerId());
//        preparedStatement.setDouble(3, order.getAmount());
//        if (order.getIsSuccess() != null && order.getIsSuccess().booleanValue()) {
//            preparedStatement.setByte(4, (byte) 1);
//        } else {
//            preparedStatement.setByte(4, (byte) 0);
//        }
//        preparedStatement.setByte(5, order.getOrderStatus());
//        preparedStatement.setString(6, DateTransferUtil.dateToString(order.getCreationTime()));
//        preparedStatement.setString(7, DateTransferUtil.dateToString(order.getFinishTime()));
//        preparedStatement.setLong(8, order.getId());
//        preparedStatement.executeUpdate();
//        connection.commit();
//        return order;
    }

    /**
     * @param order
     * @return int
     * @Title: insertOrder
     * @Description: 插入订单数据
     * @author DengHongbo
     * @date 2018/2/6 17:31
     */
    @Override
    public int insertOrder(Order order) {
        return this.sqlSessionTemplate.insert(getMybaitsNameSpace()+"insert", order);
//        preparedStatement = connection.prepareStatement("INSERT INTO `order`(NUMBER,BUYER_ID,AMOUNT,IS_SUCCESS,ORDER_STATUS,CREATION_TIME,FINISH_TIME)VALUES(?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
//        preparedStatement.setString((int) 1, order.getNumber());
//        preparedStatement.setLong((int) 2, order.getBuyerId());
//        preparedStatement.setDouble((int) 3, order.getAmount());
//        if (order.getIsSuccess()) {
//            preparedStatement.setByte((int) 4, (byte) 1);
//        } else {
//            preparedStatement.setByte((int) 4, (byte) 0);
//        }
//        preparedStatement.setByte((int) 5, order.getOrderStatus());
//        preparedStatement.setString(6, DateTransferUtil.dateToString(order.getCreationTime()));
//        preparedStatement.setString(7, DateTransferUtil.dateToString(order.getFinishTime()));
//        preparedStatement.executeUpdate();
//        ResultSet resultSet = preparedStatement.getGeneratedKeys();
//        if (resultSet.next()) {
//            order.setId((long) resultSet.getInt(1));
//        }
//        connection.commit();
//        return order;
    }

    /**
     * @param id
     * @return com.zyht.domain.Order
     * @Title: queryOrderById
     * @Description: 按id查询订单
     * @author DengHongbo
     * @date 2018/2/6 17:31
     */
    @Override
    public Order queryOrderById(Long id) {
        return this.sqlSessionTemplate.selectOne(getMybaitsNameSpace() + "queryOrderById", id);

//        ResultSet resultSet = null;
//        preparedStatement=connection.prepareStatement("SELECT ID, NUMBER,AMOUNT,CREATION_TIME,FINISH_TIME,BUYER_ID,IS_SUCCESS,ORDER_STATUS FROM `order` WHERE ID=?;");
//        preparedStatement.setLong((int)1,id);
//        //获取查询的结果集
//        resultSet=preparedStatement.executeQuery();
//        Order order = null;
        //若非空，开始遍历结果集并给order赋值
//        if(resultSet!=null){
//            while(resultSet.next()){
//                order =new Order();
//                order.setId(resultSet.getLong(1));
//                order.setNumber(resultSet.getString(2));
//                order.setAmount(resultSet.getDouble(3));
//                order.setCreationTime(resultSet.getDate(4));
//                order.setFinishTime(resultSet.getDate(5));
//                order.setBuyerId(resultSet.getLong(6));
//                if(resultSet.getByte(7)==(short)1){
//                    order.setIsSuccess(true);
//                }else {
//                    order.setIsSuccess(false);
//                }
//                order.setOrderStatus(resultSet.getByte(8));
//
//            }
//            return order;
//        }else{
//            //结果集为空返回null
//            return null;
//        }
    }

    /**
     * @param stringOrderMap
     * @return java.util.List<com.zyht.domain.Order>
     * @Title: queryOrderByCondition
     * @Description: 通过特定条件查询订单，map的key值为字段，value为对应的值
     * @author DengHongbo
     * @date 2018/2/6 17:28
     */
    @Override
    public List<Order> queryOrderCondition(Map<String, Object> stringOrderMap) {
        return this.sqlSessionTemplate.selectList(getMybaitsNameSpace() + "queryOrderCondition", stringOrderMap);
//        ResultSet resultSet = null;
//        //取出stringOrderMap中的鍵集合
//        Set<String> keySet = stringOrderMap.keySet();
//        //储存查询结果集
//        List<Order> orders = new ArrayList<Order>();
//        //遍历键集，取出对应值并通过键值对条件进行查询
//        for (String key : keySet) {
//            String keyValue = stringOrderMap.get(key);
//            String selectStr = "SELECT ID,NUMBER,BUYER_ID,AMOUNT,IS_SUCCESS,ORDER_STATUS,CREATION_TIME,FINISH_TIME FROM `order` WHERE " + key + "=" + "'" + keyValue + "'";
//            preparedStatement = connection.prepareStatement(selectStr);
//            resultSet = preparedStatement.executeQuery();
//            if (resultSet != null) {
//                while (resultSet.next()) {
//                    Order order = new Order();
//                    order.setId(resultSet.getLong(1));
//                    order.setNumber(resultSet.getString(2));
//                    order.setBuyerId(resultSet.getLong(3));
//                    order.setAmount(resultSet.getDouble(4));
//                    if (resultSet.getByte(5) == 1) {
//                        order.setIsSuccess(true);
//                    } else {
//                        order.setIsSuccess(false);
//                    }
//                    order.setOrderStatus(resultSet.getByte(6));
//                    order.setCreationTime(resultSet.getDate(7));
//                    order.setFinishTime(resultSet.getDate(8));
//                    orders.add(order);
//                }
//            } else {
//                return null;
//            }
//        }
//        return orders;
    }

    /**
     * @param stringOrderMap, connection连接, preparedStatement预编译
     * @return java.util.List<domain.Order>
     * @Title: queryOrderByCondition
     * @Description: 通过特定条件查询商品表结果分页显示
     * @author wangchuan
     * @date 2018/1/19
     * @throw SQLException
     */
    @Override
    public List<Order> queryOrderConditionPage(Map<String, Object> stringOrderMap) {
        return this.sqlSessionTemplate.selectList(getMybaitsNameSpace() + "queryOrderConditionPage", stringOrderMap);
//        ResultSet resultSet = null;
//        //取出stringOrderMap中的鍵集合
//        Set<String> keySet = stringOrderMap.keySet();
//        //储存查询结果集
//        List<Order
//                > orders = new ArrayList<Order>();
//        //遍历键集，取出对应值并通过键值对条件进行查询并对结果进行分页显示
//        for (String key : keySet) {
//            String keyValue = stringOrderMap.get(key);
//            preparedStatement = connection.prepareStatement("SELECT ID,NUMBER,BUYER_ID,AMOUNT,IS_SUCCESS,ORDER_STATUS,CREATION_TIME,FINISH_TIME FROM `order` WHERE ?=? ;");
//            preparedStatement.setString((int) 1, key);
//            preparedStatement.setString((int) 2, keyValue);
//            resultSet = preparedStatement.executeQuery();
//            //指针定位到要startRow行开始输出记录
//            resultSet.absolute(startRow);
//            // 设置最大查询记录条数
//            preparedStatement.setMaxRows(size);
//            if (resultSet != null) {
//                while (resultSet.next()) {
//                    Order order = new Order();
//                    order.setId(resultSet.getLong(1));
//                    order.setNumber(resultSet.getString(2));
//                    order.setBuyerId(resultSet.getLong(3));
//                    order.setAmount(resultSet.getDouble(4));
//                    order.setIsSuccess(resultSet.getBoolean(5));
//                    order.setOrderStatus(resultSet.getByte(6));
//                    order.setCreationTime(resultSet.getDate(7));
//                    order.setFinishTime(resultSet.getDate(8));
//                    orders.add(order);
//                }
//            } else {
//                return null;
//            }
//        }
//        return orders;
    }
}
