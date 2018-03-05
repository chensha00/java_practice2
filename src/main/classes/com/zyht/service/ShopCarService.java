package com.zyht.service;

import com.zyht.domain.ShopCar;

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
     */
    public Integer deleteShopCarById(Long id);

    /**
     * @Title: deleteShopCarByIds
     * @Description: 通过ID批量删除购物车
     * @author wangchuan
     * @date 2018/1/19
     * @param  ids
     * @return java.lang.Integer
     */
    public Integer deleteShopCarByIds(Long[] ids);
    /**
     * @Title: updateShopCar
     * @Description: 添加或者修改购物车信息
     * @author wangchuan
     * @date 2018/1/20
     * @param shopCar
     * @return Integer
     */
    public Integer updateShopCar(ShopCar shopCar);
    /**
     * @Title: insertShopCar
     * @Description: 添加或者修改购物车信息
     * @author wangchuan
     * @date 2018/1/20
     * @param shopCar
     * @return Integer
     */
    public Integer insertShopCar(ShopCar shopCar);
    /**
     * @Title: queryShopCarById
     * @Description: 通过ID查询购物车信息表
     * @author wangchuan
     * @date 2018/1/20
     * @param id
     * @return ShopCar
     */
    public ShopCar queryShopCarById(Long id);
    /**
     * @Title: queryShopCarByCondition
     * @Description: 通过条件查询购物车信息
     * @author wangchuan
     * @date 2018/1/20
     * @param stringShopCarMap
     * @return List<ShopCar>
     */
    public List<ShopCar> queryShopCarByCondition(Map<String, Object> stringShopCarMap);
    /**
     * @Title: queryShopCarByCondition
     * @Description: 通过条件批量查询购物车信息
     * @author wangchuan
     * @date 2018/1/20
     * @param stringShopCarMap, startRow, size
     * @return List<ShopCar>
     */
    public List<ShopCar> queryShopCarByCondition(Map<String, Object> stringShopCarMap, Integer startRow, Integer size);
}
