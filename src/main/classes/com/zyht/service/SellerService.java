package com.zyht.service;

import com.zyht.domain.Seller;

import java.util.List;
import java.util.Map;

/**
 * @author chendong
 * @title sellerInterface
 * @Description:
 * @Date 2018/1/16
 */
public interface SellerService {

//    /**
//     * @Title: sell
//     * @Description: 出售商品
//     * @author chendong
//     * @date 2018/1/16
//     * @param buyer, goods, buyNumber
//     * @throw SellException
//     */
//     public Seller sell(Buyer buyer, GoodsSellerRelation goodsSellerRelation, Double buyNumber)throws SellException;
//    /**
//     * @Title: restock
//     * @Description: 进货
//     * @author chendong
//     * @date 2018/1/16
//     * @param goodsSellerRelation, buyNumber, price,seller
//     */
//     public void restock(GoodsSellerRelation goodsSellerRelation, Double buyNumber, Double price, Seller seller);
//    /**
//     * @Title: deliverGoods
//     * @Description: 发货
//     * @author chendong
//     * @date 2018/1/16
//     * @param buyer, goods, time
//     */
//     public void deliverGoods(Buyer buyer, GoodsSellerRelation goodsSellerRelation, Date time, Double buyNumber);
//    /**
//     * @Title: receiveMoney
//     * @Description: 收货款
//     * @author chendong
//     * @date 2018/1/16
//     * @param goodsSellerRelation, buyer,buyNumber,seller
//     */
//     public void receiveMoney(GoodsSellerRelation goodsSellerRelation, Buyer buyer, Double buyNumber, Seller seller);




    /**
     * @param id, connection, preparedStatement
     * @return java.lang.Integer
     * @Title: deleteSellerById
     * @Description: 通过卖家ID删除
     * @author chendong
     * @date 2018/1/19
     * @throw SQLException
     */
    public int deleteSellerById(Long id);

    /**
     * @param ids, connection, preparedStatement
     * @return java.lang.Integer
     * @Title: deleteSellerByIds
     * @Description: 通过ID批量删除卖家
     * @author chendong
     * @date 2018/1/19
     * @throw SQLException
     */
    public Integer deleteSellerByIds(Long[] ids);

    /**
     * @return domain.Seller
     * @Title: insertOrUpdateSeller
     * @Description: 添加卖家信息
     * @author chendong
     * @date 2018/1/19
     * @throw SQLException
     */
    public int insertSeller(Seller seller);

    /**
     * @return domain.Buyer
     * @Title: updateBuyer
     * @Description: 修改卖家信息
     * @author chendong
     * @date 2018/1/19
     * @throw SQLException
     */

    public int updateSeller(Seller seller);
    /**
     * @Title: querySellerById
     * @Description: 通过ID查询卖家信息表
     * @author chendong
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return domain.Seller
     * @throw SQLException
     */

    public Seller querySellerById(Long id);
    /**
     * @Title:
     * @Description: 方法描述
     * @author chendong
     * @date 2018/3/5
     */
    public List<Seller> querySellerByCondition(Map<String, Object> stringMap);

}