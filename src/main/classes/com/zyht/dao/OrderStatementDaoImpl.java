package com.zyht.dao;

/**
 * Created by Administrator on 2018/1/20.
 */


import com.zyht.domain.OrderStatement;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.*;

/**
 * @author wangchuan
 * @Title: OrderStatementImpl
 * @Description: 流水表实现
 * @date 2018/1/20
 */
@Repository("orderStatementDao")
public class OrderStatementDaoImpl implements OrderStatementDao {
    @Resource
    protected SqlSessionTemplate sqlSessionTemplate;
    /**
     * @param id
     * @return java.lang.Integer
     * @Title: deleteOrderStatementById
     * @Description: 通过流水ID删除
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    @Override
    public Integer deleteOrderStatementById(Long id) throws SQLException {
        return this.sqlSessionTemplate.delete("com.zyht.domain.OrderStatement."+"deleteById",id);
    }
    /**
     * @param ids
     * @return java.lang.Integer
     * @Title: deleteOrderStatementByIds
     * @Description: 通过流水ID删除多条
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    @Override
    public Integer deleteOrderStatementByIds(Long[] ids) throws SQLException {
        Map<String, Object> stringObjectMap=new HashMap<String,Object>();
        stringObjectMap.put("ids",ids);
        return this.sqlSessionTemplate.delete("com.zyht.domain.OrderStatement."+"deleteByIds",stringObjectMap);
    }
    /**
     * @param orderStatement
     * @return java.lang.Integer
     * @Title: updateOrderStatement
     * @Description: 更新流水信息
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    @Override
    public Integer updateOrderStatement(OrderStatement orderStatement) throws SQLException {
        return this.sqlSessionTemplate.update("com.zyht.domain.OrderStatement."+"update",orderStatement);
    }
    /**
     * @param orderStatement
     * @return java.lang.Integer
     * @Title: insertOrderStatement
     * @Description: 新增流水信息
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    @Override
    public Integer insertOrderStatement(OrderStatement orderStatement) throws SQLException {
        return this.sqlSessionTemplate.update("com.zyht.domain.OrderStatement."+"add",orderStatement);
    }
    /**
     * @param id
     * @return java.lang.Integer
     * @Title: queryOrderStatementById
     * @Description: 通过id查询一条流水
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    @Override
    public OrderStatement queryOrderStatementById(Long id) throws SQLException {
        return this.sqlSessionTemplate.selectOne("com.zyht.domain.OrderStatement."+"findById",id);
    }
    /**
     * @param stringOrderStatementMap
     * @return java.util.*
     * @Title: queryOrderStatementByCondition
     * @Description: 查询多条流水
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    @Override
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, Object> stringOrderStatementMap) throws SQLException {
        return this.sqlSessionTemplate.selectList("com.zyht.domain.OrderStatement."+"find",stringOrderStatementMap);
    }
    /**
     * @param stringOrderStatementMap startRow size
     * @return java.util.*
     * @Title: queryOrderStatementByCondition
     * @Description: 查询多条流水并分页
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    @Override
    public List<OrderStatement> queryOrderStatementByCondition(Map<String, Object> stringOrderStatementMap, Integer startRow, Integer size) throws SQLException {
        return this.sqlSessionTemplate.selectList("com.zyht.domain.OrderStatement."+"findPage",stringOrderStatementMap);
    }
}


