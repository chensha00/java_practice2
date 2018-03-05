package com.zyht.service;

import com.zyht.common.util.JdbcConnectionUtils;
import com.zyht.common.util.JdbcUtils;
import com.zyht.dao.OrderDao;
import com.zyht.dao.OrderDaoImpl;
import com.zyht.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * OrderServiceImpl
 *
 * @author guoxin
 * @Description
 * @Date 2018/1/20
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    /**
     * @param id
     * @return respond
     * @ClassName deleteOrderById
     * @Description 删除订单
     * @author guoxin
     * @Date 2018/1/23
     */
    @Override
    public Integer deleteOrderById(Long id)  {
//        Connection connection = JdbcUtils.getConnection();
//        PreparedStatement preparedStatement = null;
//        String respond = null;
//        Integer result = null;
//        try {
//            connection.setAutoCommit(false);
            return orderDao.deleteOrderById(id);
//
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//            e.printStackTrace();
//        } finally {
//            try {
//                connection.commit();
//                JdbcUtils.release(connection, preparedStatement);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            JdbcUtils.release(connection, preparedStatement);//关闭连接
//            return result;
//        }

    }

    /**
     * @param ids
     * @return respond
     * @ClassName deleteOrderByIds
     * @Description 批量删除订单
     * @author guoxin
     * @Date 2018/1/23
     */
    @Override
    public Integer deleteOrderByIds(List<Long> ids) {
//        Connection connection = JdbcUtils.getConnection();
//        PreparedStatement preparedStatement = null;
//        String respond = null;                                      //返回给用户的信息
//        Integer result = null;
//        try {
//            connection.setAutoCommit(false);
            return orderDao.deleteOrderByIds(ids);
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//                connection.rollback();//出现异常，事物回滚
//            }
//            e.printStackTrace();
//        } finally {
//            try {
//                connection.commit();//提交事务
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            JdbcUtils.release(connection, preparedStatement);                        //关闭连接
//            return result;                                                           //返回结果
//        }

    }

    /**
     * @param order
     * @return respond
     * @ClassName delOrder
     * @Description 删除订单
     * @author guoxin
     * @Date 2018/1/2
     */
    @Override
    public Integer updateOrder(Order order) {
//        Connection connection = JdbcUtils.getConnection();
//        PreparedStatement preparedStatement = null;
//        String respond = null;
//        Integer result = null;
//        try {
//            connection.setAutoCommit(false);
            return orderDao.updateOrder(order);
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//            e.printStackTrace();
//        } finally {
//            try {
//                connection.commit();//提交事务
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            JdbcUtils.release(connection, preparedStatement);//关闭连接
//            return result;
//        }

    }

    /**
     * @param order
     * @return respond
     * @ClassName insertOrder
     * @Description 插入订单
     * @author guoxin
     * @Date 2018/1/22
     */
    @Override
    public Integer insertOrder(Order order)  {
//        Connection connection = JdbcUtils.getConnection();
//        PreparedStatement preparedStatement = null;
//        String respond = null;
//        Integer result = null;
//        try {
//            connection.setAutoCommit(false);
            return orderDao.insertOrder(order);
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//            e.printStackTrace();
//        } finally {
//            try {
//                connection.commit();//提交事务
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return result;
//        }

    }

    /**
     * @param id
     * @return id
     * @ClassName queryOrder
     * @Description 查询订单
     * @author guoxin
     * @Date 2018/1/22
     */
    @Override
    public Order queryOrderById(Long id) {
//        Connection connection = JdbcUtils.getConnection();
//        PreparedStatement preparedStatement = null;
//        Order result=null;
//        try {
//            connection.setAutoCommit(false);
            return  orderDao.queryOrderById(id);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JdbcUtils.release(connection,preparedStatement);
//        }
//        return result;
    }

    /**
     * @param stringBuyerMap connection  preparedStatement
     * @return stringBuyerMap
     * @ClassName queryOrderByCondition
     * @Description 查询条件
     * @author guoxin
     * @Date 2018/1/22
     */
    @Override
    public List<Order> queryOrderByCondition(Map<String, Object> stringBuyerMap) {
//        Connection connection = JdbcUtils.getConnection();
//        PreparedStatement preparedStatement = null;
//        String respond = null;//返回给用户的字符串
//        List<Order> result = null;
//        try {
//            connection.setAutoCommit(false);
           return orderDao.queryOrderByCondition(stringBuyerMap);
//            if (result == null) {
//                System.out.println("查询失败！");
//            } else {
//                System.out.println( "查询成功！");
//            }
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//            e.printStackTrace();
//        } finally {
//            try {
//                connection.commit();                               //提交事务
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            JdbcUtils.release(connection, preparedStatement);       //关闭连接
//            return result;
//        }

    }

    /**
     * @param stringBuyerMap startRow size
     * @return stringBuyerMap
     * @ClassName queryOrderByCondition
     * @Description 按条件能查询
     * @author guoxin
     * @Date 2018/1/23
     */
    @Override
    public List<Order> queryOrderByConditionPage(Map<String,Object> stringBuyerMap,Integer startRow,Integer size) {
//        Connection connection = JdbcUtils.getConnection();
//        PreparedStatement preparedStatement = null;
//        String respond = null;//返回给用户的字符串
//        List<Order> result = null;
//        try {
//            connection.setAutoCommit(false);
            return orderDao.queryOrderConditionPage(stringBuyerMap);
//            if (result == null) {
//                respond = "查询失败！";
//            } else {
//                respond = "查询成功！";
//            }
//            System.out.println(respond);
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//            e.printStackTrace();
//        } finally {
//            try {
//                connection.commit();                //提交事务
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            JdbcUtils.release(connection, preparedStatement);//关闭连接
//            return result;                           //返回查询的信息给用户
//        }
//
    }
}
