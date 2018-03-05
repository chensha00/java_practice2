package com.zyht.service;

import com.zyht.dao.ShopCarDaoImpl;
import com.zyht.domain.ShopCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * ShopCarServiceImpl
 *
 * @author wangchuan
 * @Description ShopCarServiceImpl
 * @Date 2018/2/26
 */
@Service("shopCarService")
public class ShopCarServiceImpl implements ShopCarService{
    @Autowired
    private  ShopCarDaoImpl shopCarDao;
    /**
     * @Title: deleteShopCarById
     * @Description: 通过购物车ID删除
     * @author wangchuan
     * @date 2018/1/20
     * @param id
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer deleteShopCarById(Long id) {
        Integer rows=shopCarDao.deleteShopCarById(id);
        return rows;
    }
    /**
     * @Title: deleteShopCarByIds
     * @Description: 通过ID批量删除购物车
     * @author wangchuan
     * @date 2018/1/19
     * @param  ids
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer deleteShopCarByIds(Long[] ids) {
        Integer rows=shopCarDao.deleteShopCarByIds(ids);
        return rows;
    }
    /**
     * @Title: updateShopCar
     * @Description: 添加或者修改购物车信息
     * @author wangchuan
     * @date 2018/1/20
     * @param shopCar
     * @return Integer
     * @throw SQLException
     */
    @Override
    public Integer updateShopCar(ShopCar shopCar) {
        Integer rows=shopCarDao.updateShopCar(shopCar);
        return rows;
    }
    /**
     * @Title: insertShopCar
     * @Description: 添加或者修改购物车信息
     * @author wangchuan
     * @date 2018/1/20
     * @param shopCar
     * @return Integer
     * @throw SQLException
     */
    @Override
    public Integer insertShopCar(ShopCar shopCar) {
        Integer rows=shopCarDao.insertShopCar(shopCar);
        return rows;
    }
    /**
     * @Title: queryShopCarById
     * @Description: 通过ID查询购物车信息表
     * @author wangchuan
     * @date 2018/1/20
     * @param id
     * @return ShopCar
     * @throw SQLException
     */
    @Override
    public ShopCar queryShopCarById(Long id) {
        ShopCar shopCar=shopCarDao.queryShopCarById(id);
        return shopCar;
    }
    /**
     * @Title: queryShopCarByCondition
     * @Description: 通过条件查询购物车信息
     * @author wangchuan
     * @date 2018/1/20
     * @param stringShopCarMap
     * @return List<ShopCar>
     * @throw SQLException
     */
    @Override
    public List<ShopCar> queryShopCarByCondition(Map<String, String> stringShopCarMap) {
        List<ShopCar> shopCarList=shopCarDao.queryShopCarByCondition(stringShopCarMap);
        return shopCarList;
    }
    /**
     * @Title: queryShopCarByCondition
     * @Description: 通过条件批量查询购物车信息
     * @author wangchuan
     * @date 2018/1/20
     * @param stringShopCarMap, startRow, size
     * @return List<ShopCar>
     * @throw SQLException
     */
    @Override
    public List<ShopCar> queryShopCarByCondition(Map<String, String> stringShopCarMap, Integer startRow, Integer size) {
        List<ShopCar> shopCarList=shopCarDao.queryShopCarByCondition(stringShopCarMap, startRow, size);
        return shopCarList;
    }
}
