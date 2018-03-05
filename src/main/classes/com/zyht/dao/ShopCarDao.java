package com.zyht.dao;

import com.zyht.domain.ShopCar;

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
    public Integer deleteShopCarById(Long id);

    /**
     * @param ids
     * @return java.lang.Integer
     * @Title: deleteShopCarByIds
     * @Description: 通过购物车ID删除多条
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public Integer deleteShopCarByIds(Long[] ids);
    /**
     * @param shopCar
     * @return java.lang.Integer
     * @Title: updateShopCar
     * @Description: 更新购物车信息
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public Integer updateShopCar(ShopCar shopCar);
    /**
     * @param shopCar
     * @return java.lang.Integer
     * @Title: insertShopCar
     * @Description: 新增购物车信息
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public Integer insertShopCar(ShopCar shopCar);
    /**
     * @param id
     * @return java.lang.Integer
     * @Title: queryOrderStatementById
     * @Description: 通过id查询一条流水
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public ShopCar queryShopCarById(Long id);
    /**
     * @param stringShopCarMap
     * @return java.util.*
     * @Title: queryShopCarByCondition
     * @Description: 查询多条购物车
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public List<ShopCar> queryShopCarByCondition(Map<String, Object> stringShopCarMap);
    /**
     * @param stringShopCarMap startRow size
     * @return java.util.*
     * @Title: queryShopCarByCondition
     * @Description: 查询多条购物车并分页
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    public List<ShopCar> queryShopCarByCondition(Map<String, Object> stringShopCarMap, Integer startRow, Integer size);
}
