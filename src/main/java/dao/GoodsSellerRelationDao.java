package dao;/********************************************************************/
/**
 * @Project: java_practice
 * @Package dao
 * @author caoxin
 * @date 2018/1/25 14:06
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import domain.GoodsSellerRelation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author caoxin
 * @InterfaceName GoodsSellerRelationDao
 * @Description 商品卖家关系表接口
 * @date 2018/1/25
 */
public interface GoodsSellerRelationDao {
    /**
     * @Title: deleteGoodsSellerRelationById
     * @Description: 通过主键ID删除行
     * @author caoxin
     * @date 2018/1/25
     * @param id, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    public Integer deleteGoodsSellerRelationById(Long id,Connection connection,PreparedStatement preparedStatement)throws SQLException;

    /**
     * @Title: deleteGoodsSellerRelationByIds
     * @Description: 通过ID批量删除
     * @author caoxin
     * @date 2018/1/25
     * @param  ids, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    public Integer deleteGoodsSellerRelationByIds(Long[] ids,Connection connection,PreparedStatement preparedStatement)throws SQLException;
    /**
     * @Title: insertGoodsSellerRelation
     * @Description: 添加商品卖家关系信息
     * @author caoxin
     * @date 2018/1/19
     * @param goodsSellerRelation, connection, preparedStatement
     * @return domain.Goods
     * @throw SQLException
     */

    public GoodsSellerRelation insertGoodsSellerRelation(GoodsSellerRelation goodsSellerRelation,Connection connection,PreparedStatement preparedStatement)throws SQLException;
    /**
     * @Title: updateGoodsSellerRelation
     * @Description: 修改商品信息
     * @author caoxin
     * @date 2018/1/25
     * @param goodsSellerRelation, connection, preparedStatement
     * @return domain.GoodsSellerRelation
     * @throw SQLException
     */
    public GoodsSellerRelation updateGoodsSellerRelation(GoodsSellerRelation goodsSellerRelation, Connection connection, PreparedStatement preparedStatement) throws SQLException;

    /**
     * @Title: queryGoodsSellerRelationById
     * @Description: 通过ID查询商品卖家关系表
     * @author caoxin
     * @date 2018/1/25
     * @param id, connection, preparedStatement
     * @return domain.Goods
     * @throw SQLException
     */

    public GoodsSellerRelation queryGoodsSellerRelationById(Long id,Connection connection,PreparedStatement preparedStatement)throws SQLException;
    /**
     * @Title: queryGoodsSellerRelationByCondition
     * @Description: 通过条件查询商品卖家关系信息表
     * @author caoxin
     * @date 2018/1/25
     * @param stringGoodsSellerMap, connection, preparedStatement
     * @return java.util.List<domain.GoodsSellerRelation>
     * @throw SQLException
     */
    public List<GoodsSellerRelation> queryGoodsSellerRelationByCondition(Map<String,String> stringGoodsSellerMap,Connection connection, PreparedStatement preparedStatement)throws SQLException;
    /**
     * @Title: queryGoodsSellerRelationByCondition
     * @Description: 通过条件批量查询商品卖家信息表并分页显示
     * @author caoxin
     * @date 2018/1/25
     * @param stringGoodsSellerMap, startRow, size, connection, preparedStatement
     * @return java.util.List<domain.GoodsSellerRelation>
     * @throw SQLException
     */
    public List<GoodsSellerRelation> queryGoodsSellerRelationByCondition(Map<String,String> stringGoodsSellerMap,Integer startRow,Integer size, Connection connection, PreparedStatement preparedStatement)throws SQLException;
}