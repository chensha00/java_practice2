package com.zyht.service;

import com.zyht.domain.ShopCar;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * ShopCarService
 *
 * @author Administrator
 * @Description
 * @Date 2018/2/26
 */
public interface ShopCarService {
    /**
     * @Title: deleteShopCarById
     * @Description: 通过购物车ID删除
     * @author wangchuan
     * @date 2018/1/20
     * @param id
     * @return java.lang.Integer
     * @throw SQLException
     */
    public Integer deleteShopCarById(Long id)throws SQLException;

    /**
     * @Title: deleteShopCarByIds
     * @Description: 通过ID批量删除购物车
     * @author wangchuan
     * @date 2018/1/19
     * @param  ids
     * @return java.lang.Integer
     * @throw SQLException
     */
    public Integer deleteShopCarByIds(Long[] ids)throws SQLException;
    /**
     * @Title: updateShopCar
     * @Description: 添加或者修改购物车信息
     * @author wangchuan
     * @date 2018/1/20
     * @param shopCar
     * @return Integer
     * @throw SQLException
     */
    public Integer updateShopCar(ShopCar shopCar)throws SQLException;
    /**
     * @Title: insertShopCar
     * @Description: 添加或者修改购物车信息
     * @author wangchuan
     * @date 2018/1/20
     * @param shopCar
     * @return Integer
     * @throw SQLException
     */
    public Integer insertShopCar(ShopCar shopCar)throws SQLException;
    /**
     * @Title: queryShopCarById
     * @Description: 通过ID查询购物车信息表
     * @author wangchuan
     * @date 2018/1/20
     * @param id
     * @return ShopCar
     * @throw SQLException
     */
    public ShopCar queryShopCarById(Long id)throws SQLException;
    /**
     * @Title: queryShopCarByCondition
     * @Description: 通过条件查询购物车信息
     * @author wangchuan
     * @date 2018/1/20
     * @param stringShopCarMap
     * @return List<ShopCar>
     * @throw SQLException
     */
    public List<ShopCar> queryShopCarByCondition(Map<String, String> stringShopCarMap)throws SQLException;
    /**
     * @Title: queryShopCarByCondition
     * @Description: 通过条件批量查询购物车信息
     * @author wangchuan
     * @date 2018/1/20
     * @param stringShopCarMap, startRow, size
     * @return List<ShopCar>
     * @throw SQLException
     */
    public List<ShopCar> queryShopCarByCondition(Map<String, String> stringShopCarMap, Integer startRow, Integer size)throws SQLException;
}
