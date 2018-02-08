package service;/********************************************************************/
/**
 * @Project: java_practice
 * @Package service
 * @author caoxin
 * @date 2018/1/25 15:22
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import domain.GoodsSellerRelation;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author caoxin
 * @InterfaceName GoodsSellerRelationService
 * @Description 商品卖家关系表服务接口
 * @date 2018/1/25
 */
public interface GoodsSellerRelationService {
    /**
     * @Title: deleteGoodsSellerRelationById
     * @Description: 通过主键ID删除行
     * @author caoxin
     * @date 2018/1/25
     * @param id
     * @return java.lang.Integer
     * @throw SQLException
     */
    public Integer deleteGoodsSellerRelationById(Long id)throws SQLException;

    /**
     * @Title: deleteGoodsSellerRelationByIds
     * @Description: 通过ID批量删除
     * @author caoxin
     * @date 2018/1/25
     * @param  ids
     * @return java.lang.Integer
     * @throw SQLException
     */
    public Integer deleteGoodsSellerRelationByIds(Long[] ids)throws SQLException;
    /**
     * @Title: insertGoodsSellerRelation
     * @Description: 添加商品卖家关系信息
     * @author caoxin
     * @date 2018/1/19
     * @param goodsSellerRelation
     * @return domain.Goods
     * @throw SQLException
     */

    public GoodsSellerRelation insertGoodsSellerRelation(GoodsSellerRelation goodsSellerRelation)throws SQLException;
    /**
     * @Title: updateGoodsSellerRelation
     * @Description: 修改商品信息
     * @author caoxin
     * @date 2018/1/25
     * @param goodsSellerRelation
     * @return domain.GoodsSellerRelation
     * @throw SQLException
     */
    public GoodsSellerRelation updateGoodsSellerRelation(GoodsSellerRelation goodsSellerRelation) throws SQLException;

    /**
     * @Title: queryGoodsSellerRelationById
     * @Description: 通过ID查询商品卖家关系表
     * @author caoxin
     * @date 2018/1/25
     * @param id
     * @return domain.Goods
     * @throw SQLException
     */

    public GoodsSellerRelation queryGoodsSellerRelationById(Long id)throws SQLException;
    /**
     * @Title: queryGoodsSellerRelationByCondition
     * @Description: 通过条件查询商品卖家关系信息表
     * @author caoxin
     * @date 2018/1/25
     * @param stringGoodsSellerMap
     * @return java.util.List<domain.GoodsSellerRelation>
     * @throw SQLException
     */
    public List<GoodsSellerRelation> queryGoodsSellerRelationByCondition(Map<String,String> stringGoodsSellerMap)throws SQLException;
    /**
     * @Title: queryGoodsSellerRelationByCondition
     * @Description: 通过条件批量查询商品卖家信息表并分页显示
     * @author caoxin
     * @date 2018/1/25
     * @param stringGoodsSellerMap, startRow, size
     * @return java.util.List<domain.GoodsSellerRelation>
     * @throw SQLException
     */
    public List<GoodsSellerRelation> queryGoodsSellerRelationByCondition(Map<String,String> stringGoodsSellerMap,Integer startRow,Integer size)throws SQLException;
}