package com.zyht.service;


import com.zyht.domain.*;
import com.zyht.exception.BuyException;
import com.zyht.exception.FetchException;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author caoxin
 * @ClassName BuyerService
 * @Description 商品类及其方法和属性
 * @date 2018/1/9
 */
public interface BuyerService {
    /**
     * @Title: bill
     * @Description: 计算账单总金额
     * @author caoxin
     * @date 2018/1/16
     * @param goodsList
     */
    public Double bill(Map<GoodsSellerRelation,Double> goodsList);
    /**
     * @Title: buy
     * @Description: 计算购买金额
     * @author caoxin
     * @date 2018/1/16
     * @param buyMoney 购买金额 buyNum 购买数量 goods 商品 seller 卖家 orderState 订单状态
     * @throws BuyException
     */
    public List<OrderDetail> buy(Double buyMoney, Map<GoodsSellerRelation,Double> buyList,Buyer buyer)throws BuyException;
    /**
     * @Title: pay
     * @Description: 支付账单
     * @author caoxin
     * @date 2018/1/16
     * @param    buyer 商品 order订单
     * @throw BuyException
     */
    public void pay(Buyer buyer, Order order)throws BuyException;
    /**
     * @Title: fetchGoods
     * @Description: 收货
     * @author caoxin
     * @date 2018/1/16
     * @param goodsState 商品状态 order 订单 receiptDate 收货时间
     * @throws FetchException
     */
    public void fetchGoods(boolean goodsState, OrderDetail orderDetail, Date fetchDate, Buyer buyer)throws FetchException;

    /**
     * @Title: deleteBuyerById
     * @Description: 通过买家ID删除
     * @author caoxin
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    public int deleteBuyerById(Long id);

    /**
     * @Title: deleteBuyerByIds
     * @Description: 通过ID批量删除买家
     * @author caoxin
     * @date 2018/1/19
     * @param  ids, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    public int deleteBuyerByIds(Long[] ids);
    /**
     * @Title: updateBuyer
     * @Description: 添加或者修改买家信息
     * @author caoxin
     * @date 2018/1/19
     * @param buyer, connection, preparedStatement
     * @return domain.Buyer
     * @throw SQLException
     */

    public int updateBuyer(Buyer buyer);
    /**
     * @Title: insertOrUpdateBuyer
     * @Description: 添加或者修改买家信息
     * @author caoxin
     * @date 2018/1/19
     * @param buyer, connection, preparedStatement
     * @return domain.Buyer
     * @throw SQLException
     */
    public int insertBuyer(Buyer buyer);
    /**
     * @Title: queryBuyerById
     * @Description: 通过ID查询买家信息表
     * @author caoxin
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return domain.Buyer
     * @throw SQLException
     */

    public Buyer queryBuyerById(Long id);
    /**
     * @Title: queryBuyerAccountById
     * @Description: 通过ID查询买家信息表
     * @author caoxin
     * @date 2018/1/19
     * @param id
     * @return domain.Account
     */
    public Account queryBuyerAccountById(Long id);
    /**
     * @Title: queryBuyerByCondition
     * @Description: 通过条件查询买家信息
     * @author caoxin
     * @date 2018/1/19
     * @param stringBuyerMap, connection, preparedStatement
     * @return domain.Buyer
     * @throw SQLException
     */
    public List<Buyer> queryBuyerByCondition(Map<String,Object> stringBuyerMap);
    /**
     * @Title: findBuyerByCondition
     * @Description: 通过条件批量查询买家信息
     * @author caoxin
     * @date 2018/1/19
     * @param stringBuyerMap, offset, size, connection, preparedStatement
     * @return java.util.List<domain.Buyer>
     * @throw SQLException
     */
    public List<Buyer> queryBuyerByConditionPage(Map<String,Object> stringBuyerMap);
}
