package service;


import domain.*;
import exception.BuyException;
import exception.FetchException;

import java.sql.SQLException;
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
     * @throws exception.BuyException
     */
    public OrderDetail buy(Double buyMoney, Goods goods, Seller seller, Buyer buyer)throws BuyException;
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
    public Integer deleteBuyerById(Long id)throws SQLException;

    /**
     * @Title: deleteBuyerByIds
     * @Description: 通过ID批量删除买家
     * @author caoxin
     * @date 2018/1/19
     * @param  ids, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    public Integer deleteBuyerByIds(Long[] ids)throws SQLException;
    /**
     * @Title: updateBuyer
     * @Description: 添加或者修改买家信息
     * @author caoxin
     * @date 2018/1/19
     * @param buyer, connection, preparedStatement
     * @return domain.Buyer
     * @throw SQLException
     */

    public Buyer updateBuyer(Buyer buyer)throws SQLException;
    /**
     * @Title: insertOrUpdateBuyer
     * @Description: 添加或者修改买家信息
     * @author caoxin
     * @date 2018/1/19
     * @param buyer, connection, preparedStatement
     * @return domain.Buyer
     * @throw SQLException
     */
    public Buyer insertBuyer(Buyer buyer)throws SQLException;
    /**
     * @Title: queryBuyerById
     * @Description: 通过ID查询买家信息表
     * @author caoxin
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return domain.Buyer
     * @throw SQLException
     */

    public Buyer queryBuyerById(Long id)throws SQLException;
    /**
     * @Title: queryBuyerByCondition
     * @Description: 通过条件查询买家信息
     * @author caoxin
     * @date 2018/1/19
     * @param stringBuyerMap, connection, preparedStatement
     * @return domain.Buyer
     * @throw SQLException
     */
    public List<Buyer> queryBuyerByCondition(Map<String,String> stringBuyerMap)throws SQLException;
    /**
     * @Title: findBuyerByCondition
     * @Description: 通过条件批量查询买家信息
     * @author caoxin
     * @date 2018/1/19
     * @param stringBuyerMap, offset, size, connection, preparedStatement
     * @return java.util.List<domain.Buyer>
     * @throw SQLException
     */
    public List<Buyer> queryBuyerByCondition(Map<String,String> stringBuyerMap,Integer startRow,Integer size)throws SQLException;
}
