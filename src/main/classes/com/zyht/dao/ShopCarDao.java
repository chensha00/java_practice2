package com.zyht.dao;

import com.zyht.domain.ShopCar;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * ShopCar
 *
 * @author wangchuan
 * @Title: OrderStatementDao
 * @Description: 购物车接口
 * @Date 2018/2/26
 */
public interface ShopCarDao {
    /**
     * @param id
     * @return java.lang.Integer
     * @Title: deleteShopCarById
     * @Description: 通过购物车ID删除
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public Integer deleteShopCarById(Long id)throws SQLException;

    /**
     * @param ids
     * @return java.lang.Integer
     * @Title: deleteShopCarByIds
     * @Description: 通过购物车ID删除多条
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public Integer deleteShopCarByIds(Long[] ids)throws SQLException;
    /**
     * @param shopCar
     * @return java.lang.Integer
     * @Title: updateShopCar
     * @Description: 更新购物车信息
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public Integer updateShopCar(ShopCar shopCar)throws SQLException;
    /**
     * @param shopCar
     * @return java.lang.Integer
     * @Title: insertShopCar
     * @Description: 新增购物车信息
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public Integer insertShopCar(ShopCar shopCar)throws SQLException;
    /**
     * @param id
     * @return java.lang.Integer
     * @Title: queryOrderStatementById
     * @Description: 通过id查询一条流水
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public ShopCar queryShopCarById(Long id)throws SQLException;
    /**
     * @param stringShopCarMap
     * @return java.util.*
     * @Title: queryShopCarByCondition
     * @Description: 查询多条购物车
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public List<ShopCar> queryShopCarByCondition(Map<String, String> stringShopCarMap)throws SQLException;
    /**
     * @param stringShopCarMap startRow size
     * @return java.util.*
     * @Title: queryShopCarByCondition
     * @Description: 查询多条购物车并分页
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public List<ShopCar> queryShopCarByCondition(Map<String, String> stringShopCarMap, Integer startRow, Integer size)throws SQLException;
}
