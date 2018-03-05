package com.zyht.service;


import com.zyht.dao.OrderStatementDaoImpl;
import com.zyht.domain.OrderStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private OrderStatementDaoImpl orderStatementDao;
    /**
     * @Title: deleteOrderStatmentServiceById
     * @Description: 通过流水ID删除
     * @author cuixinyuan
     * @date 2018/1/22 0022
     */
    @Override
    public Integer deleteOrderStatementById(Long id) {
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
    public Integer deleteOrderStatementByIds(Long[] ids) {
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
    public Integer updateOrderStatement(OrderStatement orderStatement) {
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
    public Integer insertOrderStatement(OrderStatement orderStatement) {
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
    public OrderStatement queryOrderStatementById(Long id) {
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
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, Object> stringOrderStatementMap) {
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
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, Object> stringOrderStatementMap, Integer startRow, Integer size) {
        List<OrderStatement> orderStatementList=orderStatementDao.queryOrderStatementByCondition(stringOrderStatementMap,startRow,size);
        return orderStatementList;
    }
}

