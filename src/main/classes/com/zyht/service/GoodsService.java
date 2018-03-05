package com.zyht.service;

import com.zyht.domain.Goods;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * GoodsService
 *
 * @author PIN
 * @Description
 * @Date 2018/1/24
 */
public interface GoodsService {
    /**
     * @Title: deleteGoodsById
     * @Description: 通过订单ID删除
     * @author guoxin
     * @date 2018/1/19
     * @param id
     * @return java.lang.Integer
     * @throw SQLException
     */
    public Integer deleteGoodsById(Long id);

    /**
     * @Title: deleteGoodsByIds
     * @Description: 通过ID批量删除订单
     * @author guoxin
     * @date 2018/1/19
     * @param  ids
     * @return java.lang.Integer
     * @throw SQLException
     */
    public Integer deleteGoodsByIds(Long[] ids);
    /**
     * @Title: updateBuyer
     * @Description: 添加或者修改订单信息
     * @author guoxin
     * @date 2018/1/19
     * @param goods
     * @return domain.Buyer
     * @throw SQLException
     */

    public Integer updateGoods(Goods goods);
    /**
     * @Title: insertOrUpdateBuyer
     * @Description: 添加或者修改订单信息
     * @author guoxin
     * @date 2018/1/19
     * @param goods
     * @return domain.Buyer
     * @throw SQLException
     */
    public Integer insertGoods(Goods goods);
    /**
     * @Title: queryGoodsById
     * @Description: 通过ID查询订单信息表
     * @author guoxin
     * @date 2018/1/19
     * @param id
     * @return domain.Buyer
     * @throw SQLException
     */

    public Goods queryGoodsById(Long id);
    /**
     * @Title: queryGoodsByCondition
     * @Description: 通过条件查询订单信息
     * @author guoxin
     * @date 2018/1/19
     * @param stringBuyerMap
     * @return domain.Buyer
     * @throw SQLException
     */
        public List<Goods>  queryGoodsByCondition(Map<String,Object> stringBuyerMap);
    /**
     * @Title: findGoodsByCondition
     * @Description: 通过条件批量查询订单信息
     * @author guoxin
     * @date 2018/1/19
     * @param stringBuyerMap, offset, size,
     * @return java.util.List<domain.Buyer>
     * @throw SQLException
     */
    public  List<Goods>  queryGoodsByCondition(Map<String,Object> stringBuyerMap, Integer startRow, Integer size);
}
