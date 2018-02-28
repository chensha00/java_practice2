package com.zyht.dao;/********************************************************************/
/**
 * @Project: java_practice
 * @Package dao
 * @author renxu
 * @date 2018/1/19 22:45
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.zyht.domain.OrderDetail;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author renxu
 * @ClassName OrderDaoImpl
 * @Description 订单表接口实现类
 * @date 2018/1/19
 */
@Repository("orderDetailDao")
public class OrderDetailDaoImpl implements OrderDetailDao {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    /**
     * @Title: deleteOrderById
     * @Description: 通过买家ID删除
     * @author renxu
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer deleteOrderById(Long id) {
        return this.sqlSessionTemplate.delete("com.zyht.domain.OrderDetail." + "delete", id);
       /* preparedStatement=connection.prepareStatement("DELETE FROM order_detail WHERE ID=?");
        preparedStatement.setLong(1,id);
        Integer operatedRow=preparedStatement.executeUpdate();
        return operatedRow;*/
    }
    /**
     * @Title: deleteOrderByIds
     * @Description: 通过ID批量删除买家
     * @author renxu
     * @date 2018/1/19
     * @param  ids, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer deleteOrderByIds(Long[] ids) {
        return this.sqlSessionTemplate.delete("com.zyht.domain.OrderDetail." + "delete", ids);
       /* Integer operatedRows = 0;
        for(Long id:ids){
            preparedStatement=connection.prepareStatement("DELETE FROM order_detail WHERE ID=?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            operatedRows++;
        }
        return operatedRows;*/
    }
    /**
     * @Title: updateOrder
     * @Description: 修改订单详情
     * @author renxu
     * @date 2018/1/22
     * @param orderDetail, connection, preparedStatement
     * @return int i 受影响的行数
     * @throw SQLException
     */
    @Override
    public int updateOrder(OrderDetail orderDetail){
        return this.sqlSessionTemplate.update("com.zyht.domain.OrderDetail." + "update", orderDetail);
       /* Long id = null;
        preparedStatement=connection.prepareStatement("UPDATE order_detail SET AMOUNT=?,IS_SUCCESS=?,ORDER_STATUS=?,ORDER_NUMBER=?,CREATION_TIME=?,FINISH_TIME=? WHERE ID=?;");
        preparedStatement.setLong(7,orderDetail.getId());//条件
        preparedStatement.setDouble(1, orderDetail.getAmount());
        preparedStatement.setBoolean(2, orderDetail.getIsSuccess());
        preparedStatement.setByte(3, orderDetail.getOrderStatus());
        preparedStatement.setString(4, orderDetail.getOrderNumber());
        java.sql.Date sqlCreationDate = new java.sql.Date(orderDetail.getCreationTime().getTime());
        preparedStatement.setDate(5, sqlCreationDate);
        java.sql.Date sqlFinishDate = new java.sql.Date(orderDetail.getCreationTime().getTime());
        preparedStatement.setDate(6,sqlFinishDate);
        int i = preparedStatement.executeUpdate();
        return i;*/
    }
    /**
     * @Title: insertOrder
     * @Description: 添加买家信息
     * @author renxu
     * @date 2018/1/19
     * @param orderDetail, connection, preparedStatement
     * @return Long  id
     * @throw SQLException
     */
    @Override
    public int insertOrder(OrderDetail orderDetail){
        return this.sqlSessionTemplate.insert("com.zyht.domain.OrderDetail." + "insert", orderDetail);
      /*  Long id = null;
        preparedStatement=connection.prepareStatement("INSERT INTO order_detail(ID,SELLER_ID,BUYER_ID,GOODS_ID,ORDER_ID,AMOUNT,IS_SUCCESS,ORDER_STATUS,ORDER_NUMBER,CREATION_TIME,FINISH_TIME )VALUES(DEFAULT,?,?,?,?,?,?,?,?,?,?);");
        preparedStatement.setLong(1, orderDetail.getSellerId());
        preparedStatement.setLong(2, orderDetail.getBuyerId());
        preparedStatement.setLong(3,orderDetail.getGoodsId());
        preparedStatement.setLong(4,orderDetail.getOrderId());
        preparedStatement.setDouble(5, orderDetail.getAmount());
        preparedStatement.setBoolean(6, orderDetail.getIsSuccess());
        preparedStatement.setByte(7, orderDetail.getOrderStatus());
        preparedStatement.setString(8, orderDetail.getOrderNumber());
        java.sql.Date sqlCreationDate = new java.sql.Date(orderDetail.getCreationTime().getTime());
        preparedStatement.setDate(9, sqlCreationDate);
        java.sql.Date sqlFinishDate = new java.sql.Date(orderDetail.getCreationTime().getTime());
        preparedStatement.setDate(10,sqlFinishDate);
        int i = preparedStatement.executeUpdate();
        if(i != 0 ){//添加数据成功
            id = orderDetail.getId();
        }
        return id;*/
    }

    /**
     * @Title: queryOrderById
     * @Description: 通过ID查询商品信息表
     * @author renxu
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return domain.order
     * @throw SQLException
     */
    @Override
    public OrderDetail queryOrderById(Long id)  {
        return this.sqlSessionTemplate.selectOne("com.zyht.domain.OrderDetail." + "getById", id);
       /* preparedStatement=connection.prepareStatement("SELECT  ID,BUYER_ID,SELLER_ID,GOODS_ID,AMOUNT,IS_SUCCESS,ORDER_STATUS,ORDER_NUMBER,CREATION_TIME,FINISH_TIME,ORDER_ID FROM order_detail WHERE ID=?;");
        preparedStatement.setLong(1,id);
        //获取查询的结果集
        ResultSet resultSet=preparedStatement.executeQuery();
        //若非空，开始遍历结果集并给order赋值
        OrderDetail orderDetail = null;
        if(resultSet!=null){
            while(resultSet.next()){
                orderDetail=new OrderDetail();
                orderDetail.setId(resultSet.getLong("ID"));
                orderDetail.setBuyerId(resultSet.getLong("BUYER_ID"));
                orderDetail.setSellerId(resultSet.getLong("SELLER_ID"));
                orderDetail.setGoodsId(resultSet.getLong("GOODS_ID"));
                orderDetail.setAmount(resultSet.getDouble("AMOUNT"));
                if(resultSet.getByte("IS_SUCCESS")==0){
                    orderDetail.setIsSuccess(false);
                }else {
                    orderDetail.setIsSuccess(true);
                }
                orderDetail.setOrderStatus(resultSet.getByte("ORDER_STATUS"));
                orderDetail.setOrderNumber(resultSet.getString("ORDER_NUMBER"));
                orderDetail.setCreationTime(resultSet.getDate("CREATION_TIME"));
                orderDetail.setFinishTime(resultSet.getDate("FINISH_TIME"));
                orderDetail.setOrderId(resultSet.getLong("ORDER_ID"));
            }
            resultSet.close();
            return orderDetail;
        }else{
            //结果集为空返回null
            return null;
        }*/
    }
    /**
     * @Title: queryOrderByCondition
     * @Description: 通过特定条件查询商品表
     * @author renxu
     * @date 2018/1/19
     * @param stringOrderMap, connection, preparedStatement
     * @return java.util.List<domain.order>
     * @throw SQLException
     */

    @Override
    public List<OrderDetail> queryOrderByCondition(Map<String, Object> stringOrderMap){
        return this.sqlSessionTemplate.selectList("com.zyht.domain.OrderDetail." + "queryCondition", stringOrderMap);
       /* //取出stringOrderMap中的鍵集合
        Set<String> keySet=stringOrderMap.keySet();
        //储存查询结果集
       List<OrderDetail> list = new LinkedList<OrderDetail>();
        //遍历键集，取出对应值并通过键值对条件进行查询
        for(String key:keySet){
            String keyValue=stringOrderMap.get(key);
            String sqlString = "SELECT  ID,BUYER_ID,SELLER_ID,GOODS_ID,ORDER_ID,AMOUNT,IS_SUCCESS,ORDER_STATUS,ORDER_NUMBER,CREATION_TIME,FINISH_TIME FROM order_detail WHERE "+key+"="+"'"+keyValue+"'";
            preparedStatement=connection.prepareStatement(sqlString);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    OrderDetail orderDetail=new OrderDetail();
                    orderDetail.setId(resultSet.getLong("ID"));
                    orderDetail.setBuyerId(resultSet.getLong("BUYER_ID"));
                    orderDetail.setSellerId(resultSet.getLong("SELLER_ID"));
                    orderDetail.setGoodsId(resultSet.getLong("GOODS_ID"));
                    orderDetail.setOrderId(resultSet.getLong("ORDER_ID"));
                    orderDetail.setAmount(resultSet.getDouble("AMOUNT"));
                    if(resultSet.getByte("IS_SUCCESS")==0){
                        orderDetail.setIsSuccess(false);
                    }else {
                        orderDetail.setIsSuccess(true);
                    }
                    orderDetail.setOrderStatus(resultSet.getByte("ORDER_STATUS"));
                    orderDetail.setOrderNumber(resultSet.getString("ORDER_NUMBER"));
                    orderDetail.setCreationTime(resultSet.getDate("CREATION_TIME"));
                    orderDetail.setFinishTime(resultSet.getDate("FINISH_TIME"));
                    list.add(orderDetail);
                }
                sqlString = "SELECT  ID,BUYER_ID,SELLER_ID,GOODS_ID,ORDER_ID,AMOUNT,IS_SUCCESS,ORDER_STATUS,ORDER_NUMBER,CREATION_TIME,FINISH_TIME FROM order_detail WHERE ";
                resultSet.close();
            }else {
                return null;
            }
        }
        return list;*/
    }
    /**
     * @Title: queryOrderByCondition
     * @Description: 通过特定条件查询商品表结果分页显示
     * @author renxu
     * @date 2018/1/19
     * @param stringOrderMap, connection, preparedStatement
     * @return java.util.List<domain.order>
     * @throw SQLException
     */
    @Override
    public List<OrderDetail> queryOrderByCondition(Map<String, Object> stringOrderMap, Integer startRow, Integer size) {
        return this.sqlSessionTemplate.selectList("com.zyht.domain.OrderDetail." + "queryConditionPage", stringOrderMap);
      /*  //取出stringOrderMap中的鍵集合
        Set<String> keySet=stringOrderMap.keySet();
        //储存查询结果集
        List<OrderDetail> list = new LinkedList<OrderDetail>();
        //遍历键集，取出对应值并通过键值对条件进行查询并对结果进行分页显示
        for(String key:keySet){
            String keyValue=stringOrderMap.get(key);
            preparedStatement=connection.prepareStatement("SELECT ID,BUYER_ID,SELLER_ID,GOODS_ID,AMOUNT,IS_SUCCESS,ORDER_STATUS,ORDER_NUMBER,CREATION_TIME,FINISH_TIME FROM order_detail WHERE ?=?  limit startRow,size;");
            preparedStatement.setString(1,key);
            preparedStatement.setString(2,keyValue);
            ResultSet resultSet=preparedStatement.executeQuery();
            //指针定位到要startRow行开始输出记录
            resultSet.absolute(startRow);
            // 设置最大查询记录条数
            preparedStatement.setMaxRows(size);
            if(resultSet!=null){
                while(resultSet.next()){
                    OrderDetail orderDetail=new OrderDetail();
                    orderDetail.setId(resultSet.getLong("ID"));
                    orderDetail.setBuyerId(resultSet.getLong("BUYER_ID"));
                    orderDetail.setSellerId(resultSet.getLong("SELLER_ID"));
                    orderDetail.setGoodsId(resultSet.getLong("GOODS_ID"));
                    orderDetail.setAmount(resultSet.getDouble("AMOUNT"));
                    if(resultSet.getByte("IS_SUCCESS")==0){
                        orderDetail.setIsSuccess(false);
                    }else {
                        orderDetail.setIsSuccess(true);
                    }
                    orderDetail.setOrderStatus(resultSet.getByte("ORDER_STATUS"));
                    orderDetail.setOrderNumber(resultSet.getString("ORDER_NUMBER"));
                    orderDetail.setCreationTime(resultSet.getDate("CREATION_TIME"));
                    orderDetail.setFinishTime(resultSet.getDate("FINISH_TIME"));
                    list.add(orderDetail);
                }
                resultSet.close();
            }else {
                return null;
            }
        }
        return list;*/
    }
}
