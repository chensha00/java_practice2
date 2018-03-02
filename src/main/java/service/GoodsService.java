package service;

import domain.Goods;

import java.sql.SQLException;
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
    public String deleteGoodsById(Long id)throws SQLException;

    /**
     * @Title: deleteGoodsByIds
     * @Description: 通过ID批量删除订单
     * @author guoxin
     * @date 2018/1/19
     * @param  ids
     * @return java.lang.Integer
     * @throw SQLException
     */
    public String deleteGoodsByIds(Long[] ids)throws SQLException;
    /**
     * @Title: updateBuyer
     * @Description: 添加或者修改订单信息
     * @author guoxin
     * @date 2018/1/19
     * @param goods
     * @return domain.Buyer
     * @throw SQLException
     */

    public String updateGoods(Goods goods)throws SQLException;
    /**
     * @Title: insertOrUpdateBuyer
     * @Description: 添加或者修改订单信息
     * @author guoxin
     * @date 2018/1/19
     * @param goods
     * @return domain.Buyer
     * @throw SQLException
     */
    public String insertGoods(Goods goods)throws SQLException;
    /**
     * @Title: queryGoodsById
     * @Description: 通过ID查询订单信息表
     * @author guoxin
     * @date 2018/1/19
     * @param id
     * @return domain.Buyer
     * @throw SQLException
     */

    public String queryGoodsById(Long id)throws SQLException;
    /**
     * @Title: queryGoodsByCondition
     * @Description: 通过条件查询订单信息
     * @author guoxin
     * @date 2018/1/19
     * @param stringBuyerMap
     * @return domain.Buyer
     * @throw SQLException
     */
    public String queryGoodsByCondition(Map<String, String> stringBuyerMap)throws SQLException;
    /**
     * @Title: findGoodsByCondition
     * @Description: 通过条件批量查询订单信息
     * @author guoxin
     * @date 2018/1/19
     * @param stringBuyerMap, offset, size,
     * @return java.util.List<domain.Buyer>
     * @throw SQLException
     */
    public String queryGoodsByCondition(Map<String, String> stringBuyerMap, Integer startRow, Integer size)throws SQLException;
}
