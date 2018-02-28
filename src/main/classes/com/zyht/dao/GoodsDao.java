package com.zyht.dao;/********************************************************************/
/**
 * @Project: java_practice
 * @Package dao
 * @author caoxin
 * @date 2018/1/19 17:04
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.zyht.domain.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author caoxin
 * @InterfaceName GoodsDao
 * @Description 商品信息表访问接口
 * @date 2018/1/19
 */
public interface GoodsDao {
    /**
     * @Title: deleteGoodsById
     * @Description: 通过商品ID删除
     * @author caoxin
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    public Integer deleteGoodsById(Long id)throws SQLException;

    /**
     * @Title: deleteGoodsByIds
     * @Description: 通过ID批量删除商品
     * @author caoxin
     * @date 2018/1/19
     * @param  ids, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    public Integer deleteGoodsByIds(Long[] ids)throws SQLException;
    /**
     * @Title: insertGoods
     * @Description: 添加商品信息
     * @author caoxin
     * @date 2018/1/19
     * @param goods, connection, preparedStatement
     * @return domain.Goods
     * @throw SQLException
     */

    public Goods insertGoods(Goods goods)throws SQLException;
    /**
     * @Title: updateGoods
     * @Description: 修改商品信息
     * @author caoxin
     * @date 2018/1/19
     * @param goods, connection, preparedStatement
     * @return domain.Goods
     * @throw SQLException
     */
    public Integer updateGoods(Goods goods) throws SQLException;

        /**
         * @Title: queryGoodsById
         * @Description: 通过ID查询商品信息表
         * @author caoxin
         * @date 2018/1/19
         * @param id, connection, preparedStatement
         * @return domain.Goods
         * @throw SQLException
         */

    public Goods queryGoodsById(Long id)throws SQLException;
    /**
     * @Title: queryGoodsByCondition
     * @Description: 通过条件查询商品信息
     * @author caoxin
     * @date 2018/1/19
     * @param stringGoodsMap, connection, preparedStatement
     * @return domain.Goods
     * @throw SQLException
     */
    public List<Goods> queryGoodsByCondition(Map<String,String> stringGoodsMap)throws SQLException;
    /**
     * @Title: findGoodsByCondition
     * @Description: 通过条件批量查询商品信息并分页显示
     * @author caoxin
     * @date 2018/1/19
     * @param stringGoodsMap, offset, size, connection, preparedStatement
     * @return java.util.List<domain.Goods>
     * @throw SQLException
     */
    public List<Goods> queryGoodsByCondition(Map<String,String> stringGoodsMap,Integer startRow,Integer size)throws SQLException;
}