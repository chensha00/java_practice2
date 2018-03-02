package com.zyht.service;


import com.zyht.dao.OrderStatementDao;
import com.zyht.dao.OrderStatementDaoImpl;
import com.zyht.domain.OrderStatement;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 * OrderStatementServiceImpl
 *
 * @author wangchuan
 * @Description OrderStatementServiceImpl
 * @Date 2018/2/26
 */
@Service("orderStatementService")
public class OrderStatementServiceImpl implements OrderStatementService {
    /**
     * @Title: deleteOrderStatmentServiceById
     * @Description: 通过流水ID删除
     * @author cuixinyuan
     * @date 2018/1/22 0022
     */
    @Override
    public Integer deleteOrderStatementById(Long id) throws SQLException {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderStatementDaoImpl orderStatementDao= (OrderStatementDaoImpl) applicationContext.getBean("orderStatementDao");
        Integer rows=orderStatementDao.deleteOrderStatementById(id);
        return rows;
    }

    /**
     * @Title: deleteOrderStatmentServiceImplByIds
     * @Description: 通过ID批量删除流水
     * @author cuixinyuan
     * @date 2018/1/22 0022
     */
    @Override
    public Integer deleteOrderStatementByIds(Long[] ids) throws SQLException {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderStatementDaoImpl orderStatementDao= (OrderStatementDaoImpl) applicationContext.getBean("orderStatementDao");
        Integer rows=orderStatementDao.deleteOrderStatementByIds(ids);
        return rows;
    }

    /**
     * @Title: updateOrderStatement
     * @Description: 修改流水
     * @author cuixinyuan
     * @date 2018/1/25 0025
     */
    @Override
    public Integer updateOrderStatement(OrderStatement orderStatement) throws SQLException {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderStatementDaoImpl orderStatementDao= (OrderStatementDaoImpl) applicationContext.getBean("orderStatementDao");
        Integer rows=orderStatementDao.updateOrderStatement(orderStatement);
        return rows;
    }

    /**
     * @Title:insertOrderStatment
     * @Description: 添加
     * @author cuixinyuan
     * @date 2018/1/23 0023
     */
    @Override
    public Integer insertOrderStatement(OrderStatement orderStatement) throws SQLException {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderStatementDaoImpl orderStatementDao= (OrderStatementDaoImpl) applicationContext.getBean("orderStatementDao");
        Integer rows=orderStatementDao.insertOrderStatement(orderStatement);
        return rows;
    }

    /**
     * @Title:queryOrderStatementServiceById
     * @Description: 根据id查询
     * @author cuixinyuan
     * @date 2018/1/23 0023
     */
    @Override
    public OrderStatement queryOrderStatementById(Long id) throws SQLException {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderStatementDaoImpl orderStatementDao= (OrderStatementDaoImpl) applicationContext.getBean("orderStatementDao");
        OrderStatement orderStatement=orderStatementDao.queryOrderStatementById(id);
        return orderStatement;
    }

    /**
     * @Title:queryOrderStatementDaoByCondition
     * @Description: 查询条件
     * @author cuixinyuan
     * @date 2018/1/23 0023
     */
    @Override
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, Object> stringOrderStatementMap) throws SQLException {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderStatementDaoImpl orderStatementDao= (OrderStatementDaoImpl) applicationContext.getBean("orderStatementDao");
        List<OrderStatement> orderStatementList=orderStatementDao.queryOrderStatementByCondition(stringOrderStatementMap);
        return orderStatementList;
    }
/**
 * @Title: queryOrderStatementByCondition
 * @Description: 按条件批量查询
 * @author cuixinyuan
 * @date 2018/1/25 0025
 */
    @Override
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, Object> stringOrderStatementMap, Integer startRow, Integer size) throws SQLException {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderStatementDaoImpl orderStatementDao= (OrderStatementDaoImpl) applicationContext.getBean("orderStatementDao");
        List<OrderStatement> orderStatementList=orderStatementDao.queryOrderStatementByCondition(stringOrderStatementMap,startRow,size);
        return orderStatementList;
    }
}

