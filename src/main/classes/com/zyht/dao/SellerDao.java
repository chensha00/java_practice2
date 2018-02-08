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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    public Integer deleteSellerById(Long id,Connection connection,PreparedStatement preparedStatement)throws SQLException;

    /**
     * @Title: deleteSellerByIds
     * @Description: 通过ID批量删除卖家
     * @author caoxin
     * @date 2018/1/19
     * @param  ids, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    public Integer deleteSellerByIds(Long[] ids,Connection connection,PreparedStatement preparedStatement)throws SQLException;
    /**
     * @Title: insertOrUpdateSeller
     * @Description: 添加卖家信息
     * @author caoxin
     * @date 2018/1/19
     * @param Seller, connection, preparedStatement
     * @return domain.Seller
     * @throw SQLException
     */
    public Seller insertSeller(Seller Seller,Connection connection,PreparedStatement preparedStatement)throws SQLException;
    /**
     * @Title: updateBuyer
     * @Description: 修改卖家信息
     * @author caoxin
     * @date 2018/1/19
     * @param seller, connection, preparedStatement
     * @return domain.Buyer
     * @throw SQLException
     */

    public Seller updateSeller(Seller seller,Connection connection,PreparedStatement preparedStatement)throws SQLException;
    /**
     * @Title: querySellerById
     * @Description: 通过ID查询卖家信息表
     * @author caoxin
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return domain.Seller
     * @throw SQLException
     */

    public Seller querySellerById(Long id,Connection connection,PreparedStatement preparedStatement)throws SQLException;
    /**
     * @Title: querySellerByCondition
     * @Description: 通过条件查询卖家信息
     * @author caoxin
     * @date 2018/1/19
     * @param stringSellerMap, connection, preparedStatement
     * @return domain.Seller
     * @throw SQLException
     */
    public List<Seller> querySellerByCondition(Map<String,String> stringSellerMap,Connection connection, PreparedStatement preparedStatement)throws SQLException;
    /**
     * @Title: findSellerByCondition
     * @Description: 通过条件批量查询卖家信息
     * @author caoxin
     * @date 2018/1/19
     * @param stringSellerMap, offset, size, connection, preparedStatement
     * @return java.util.List<domain.Seller>
     * @throw SQLException
     */
    public List<Seller> querySellerByCondition(Map<String,String> stringSellerMap,Integer startRow,Integer size, Connection connection, PreparedStatement preparedStatement)throws SQLException;
}