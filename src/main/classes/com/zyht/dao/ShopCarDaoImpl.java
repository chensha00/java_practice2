package com.zyht.dao;

import com.zyht.domain.ShopCar;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ShopCarImpl
 *
 * @author Administrator
 * @Description
 * @Date 2018/2/26
 */
@Repository("shopCarDao")
public class ShopCarDaoImpl implements ShopCarDao{
    @Resource
    protected SqlSessionTemplate sqlSessionTemplate;
    /**
     * @param id
     * @return java.lang.Integer
     * @Title: deleteShopCarById
     * @Description: 通过购物车ID删除
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    @Override
    public Integer deleteShopCarById(Long id) {
        return this.sqlSessionTemplate.delete("com.zyht.domain.ShopCar."+"deleteById",id);
    }
    /**
     * @param ids
     * @return java.lang.Integer
     * @Title: deleteShopCarByIds
     * @Description: 通过购物车ID删除多条
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    @Override
    public Integer deleteShopCarByIds(Long[] ids) {
        Map<String,Object> stringObjectMap= new HashMap<String,Object>();
        stringObjectMap.put("ids",ids);
        return this.sqlSessionTemplate.delete("com.zyht.domain.ShopCar."+"deleteByIds",stringObjectMap);
    }
    /**
     * @param shopCar
     * @return java.lang.Integer
     * @Title: updateShopCar
     * @Description: 更新购物车信息
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    @Override
    public Integer updateShopCar(ShopCar shopCar) {
        return this.sqlSessionTemplate.update("com.zyht.domain.ShopCar."+"update",shopCar);
    }
    /**
     * @param shopCar
     * @return java.lang.Integer
     * @Title: insertShopCar
     * @Description: 新增购物车信息
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    @Override
    public Integer insertShopCar(ShopCar shopCar) {
        return this.sqlSessionTemplate.update("com.zyht.domain.ShopCar."+"add",shopCar);
    }
    /**
     * @param id
     * @return java.lang.Integer
     * @Title: queryOrderStatementById
     * @Description: 通过id查询一条流水
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    @Override
    public ShopCar queryShopCarById(Long id) {
        return this.sqlSessionTemplate.selectOne("com.zyht.domain.ShopCar."+"findById",id);
    }
    /**
     * @param stringShopCarMap
     * @return java.util.*
     * @Title: queryShopCarByCondition
     * @Description: 查询多条购物车
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    @Override
    public List<ShopCar> queryShopCarByCondition(Map<String, String> stringShopCarMap) {
        return this.sqlSessionTemplate.selectList("com.zyht.domain.ShopCar." + "find", stringShopCarMap);
    }
    /**
     * @param stringShopCarMap startRow size
     * @return java.util.*
     * @Title: queryShopCarByCondition
     * @Description: 查询多条购物车并分页
     * @author wangchuan
     * @date 2018/2/7
     * @throw SQLException
     */
    @Override
    public List<ShopCar> queryShopCarByCondition(Map<String, String> stringShopCarMap, Integer startRow, Integer size) {
        return this.sqlSessionTemplate.selectList("com.zyht.domain.ShopCar."+"findPage",stringShopCarMap);
    }
}
