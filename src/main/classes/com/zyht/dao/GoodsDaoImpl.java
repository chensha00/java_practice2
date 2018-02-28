package com.zyht.dao;/********************************************************************/
/**
 * @Project: java_practice
 * @Package dao
 * @author caoxin
 * @date 2018/1/19 22:18
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.zyht.domain.Goods;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * @author caoxin
 * @ClassName GoodsDaoImpl
 * @Description 商品表接口实现类
 * @date 2018/1/19
 */
@Repository("goodsDao")
public class GoodsDaoImpl extends BaseDaoImpl implements GoodsDao {
    protected SqlSessionTemplate sqlSessionTemplate;

    @Override
    public Integer deleteGoodsById(Long id) throws SQLException {


         return  this.sqlSessionTemplate.insert(getMybaitsNameSpace()+"delete",id);
    }

    @Override
    public Integer deleteGoodsByIds(Long[] ids) throws SQLException {


        return  this.sqlSessionTemplate.insert(getMybaitsNameSpace()+"delete",ids);
    }

    @Override
    public Goods insertGoods(Goods goods) throws SQLException {
        return  this.sqlSessionTemplate.selectOne(getMybaitsNameSpace()+"insert",goods);
    }

    @Override
    public Integer updateGoods(Goods goods) throws SQLException {
        return  this.sqlSessionTemplate.insert(getMybaitsNameSpace()+"update",goods);
    }

    @Override
    public Goods queryGoodsById(Long id) throws SQLException {


        return  this.sqlSessionTemplate.selectOne(getMybaitsNameSpace()+"query",id);
    }

    @Override
    public List<Goods> queryGoodsByCondition(Map<String, String> stringGoodsMap) throws SQLException {

        return this.sqlSessionTemplate.selectList(getMybaitsNameSpace()+"query",stringGoodsMap);
    }

    @Override
    public List<Goods> queryGoodsByCondition(Map<String, String> stringGoodsMap, Integer startRow, Integer size) throws SQLException {
        return this.sqlSessionTemplate.selectList(getMybaitsNameSpace()+"query",stringGoodsMap);
    }
    /**
     * @Title: deleteGoodsById
     * @Description: 通过买家ID删除
     * @author caoxin
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */

}
