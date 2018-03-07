package com.zyht.dao;/********************************************************************/
/**
 * @Project: java_practice
 * @Package dao
 * @author caoxin
 * @date 2018/1/19 16:40
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.zyht.domain.Seller;

import java.util.List;
import java.util.Map;

/**
 * @author caoxin
 * @InterfaceName SellerDao
 * @Description 卖家信息表接口
 * @date 2018/1/19
 */
public interface SellerDao {
    /**
     * @Title: deleteSellerById
     * @Description: 通过卖家ID删除
     * @author caoxin
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    public int deleteSellerById(Long id);

    /**
     * @Title: deleteSellerByIds
     * @Description: 通过ID批量删除卖家
     * @author caoxin
     * @date 2018/1/19
     * @param  ids, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    public int deleteSellerByIds(Long[] ids);
    /**
     * @Title: insertOrUpdateSeller
     * @Description: 添加卖家信息
     * @author caoxin
     * @date 2018/1/19
     * @return domain.Seller
     * @throw SQLException
     */
    public int insertSeller(Seller seller);
    /**
     * @Title: updateBuyer
     * @Description: 修改卖家信息
     * @author caoxin
     * @date 2018/1/19
     * @return domain.Buyer
     * @throw SQLException
     */

    public int updateSeller(Seller seller);
    /**
     * @Title: querySellerById
    * @Description: 通过ID查询卖家信息表
     * @author caoxin
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return domain.Seller
     * @throw SQLException
     */

    public Seller querySellerById(Long id);
    /**
     * @Title: querySellerByCondition
     * @Description: 通过条件查询卖家信息
     * @author caoxin
     * @date 2018/1/19
     * @return domain.Seller
     * @throw SQLException
     */
    public List<Seller> querySellerByCondition(Map<String, Object> stringSellerMap);
    /**
     * @Title: findSellerByCondition
     * @Description: 通过条件批量查询卖家信息
     * @author caoxin
     * @date 2018/1/19
     * @return java.util.List<domain.Seller>
     * @throw SQLException
     */
    public List<Seller> querySellerByConditionPage(Map<String,Object> stringSellerMap);

    /**
     * @Title: updatePrice
     * @Description: 修改价格
     * @author chendong
     * @date 2018/3/7
     */
    public int updatePrice(Seller seller);
}