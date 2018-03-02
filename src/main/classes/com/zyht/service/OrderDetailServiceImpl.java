package com.zyht.service;/**
 * Created by HAN on 2018/1/23.
 */

import com.zyht.common.util.JdbcUtils;
import com.zyht.dao.OrderDetailDao;
import com.zyht.dao.OrderDetailDaoImpl;
import com.zyht.domain.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @Description 订单详情服务实现类
 * @date 2018/1/23
 */
@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {
    //定义一个OrderDetailDao层的对象
    @Autowired
    static OrderDetailDao orderDetailDao;
    /**
     * @Title: deleteOrderById
     * @Description: 通过订单ID删除
     * @author renxu
     * @date 2018/1/23
     * @param id
     * @return int 删除行数
     * @throw Exception
     */
    @Override
    public int deleteOrderById(Long id){
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = null;
        Integer result = null;
        result = orderDetailDao.deleteOrderById(id);
        return result;
    }

    /**
     * @Title: deleteOrderByIds
     * @Description: 通过ID批量删除订单
     * @author renxu
     * @date 2018/1/23
     * @param  ids, connection, preparedStatement
     * @return  int 删除行数
     * @throw Exception
     */
    @Override
    public int deleteOrderByIds(List ids){
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = null;
        Integer result = null;
        result = orderDetailDao.deleteOrderByIds(ids);
        return result;
    }

    /**
     * @Title: insertOrder
     * @Description: 添加订单详情
     * @author renxu
     * @date 2018/1/23
     * @param orderDetail, connection, preparedStatement
     * @return Long id
     * @throw Exception
     */
    @Override
    public int insertOrder(OrderDetail orderDetail){
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = null;
        int result = 0;
        result = orderDetailDao.insertOrder(orderDetail);
        return result;//返回结果给用户
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
    public String updateOrder(OrderDetail orderDetail){
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = null;
        String respond = null;//返回给用户的字符串
        int result = orderDetailDao.updateOrder(orderDetail);
        return respond;//返回结果给用户
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
    public OrderDetail queryOrderById(Long id){
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = null;
        OrderDetail result = new OrderDetail();
        result = orderDetailDao.queryOrderById(id);
        return result;
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
    public List<OrderDetail> queryOrderByCondition(Map<String,Object> stringOrderMap){
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = null;
        List<OrderDetail> result = new LinkedList<OrderDetail>();
        result = orderDetailDao.queryOrderByCondition(stringOrderMap);
        return result;
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
    public List<OrderDetail> queryOrderByCondition(Map<String,Object> stringOrderMap,Integer offset,Integer size){
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = null;
        List<OrderDetail> result = new LinkedList<OrderDetail>();
        result = orderDetailDao.queryOrderByCondition(stringOrderMap);
        Iterator<OrderDetail> iterator = result.iterator();
        return result;
    }

}
