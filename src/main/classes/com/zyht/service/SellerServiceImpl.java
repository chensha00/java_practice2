package com.zyht.service;


import com.zyht.common.util.JdbcUtils;
import com.zyht.dao.SellerDaoImpl;
import com.zyht.domain.Buyer;
import com.zyht.domain.Goods;
import com.zyht.domain.GoodsSellerRelation;
import com.zyht.domain.Seller;
import com.zyht.exception.SellException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @title  SellerInfoServiceImpl
 * @author chendong
 * @Description 卖家操作实现方法
 * @Date 2018/1/16
 */
@Service("sellerService")
public class SellerServiceImpl implements SellerService {
    /**
     * @Title: addSeller
     * @Description: 向卖家表添加卖家
     * @author chendong
     * @date 2018/1/20
     * @param seller
     */
    @Autowired
    public void addSeller(Seller seller){
         SellerDaoImpl sellerDaoImpl=new SellerDaoImpl();
         sellerDaoImpl=new SellerDaoImpl();
         PreparedStatement preparedStatement=null;
         Connection connection=null;
        try{
            connection=JdbcUtils.getConnection();
            connection.setAutoCommit(false);
            sellerDaoImpl.insertSeller(seller, connection, preparedStatement);
        }catch (SQLException e){
            try{
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     *
     */
    /**
     * @Title: getSellList
     * @Description: 获取商家出售货品列表
     * @author chendong
     * @date 2018/1/18
     * @return java.util.List<com.zyht.sclouds.entity.Goods>
     */
    public List<Goods> getSellList() {
        return sellList;
    }
    private List<Goods> sellList=new ArrayList<Goods>();
    /**
     * @Title: setSellList
     * @Description: 设置商家出售货品列表
     * @author chendong
     * @date 2018/1/18
     * @param sellList
     */
    public void setSellList(List<Goods> sellList) {
        this.sellList = sellList;
    }
    /**
     * @Title: addNewGoods
     * @Description: 上架新商品
     * @author chendong
     * @date 2018/1/18
     * @param goods
     */

    public void addNewGoods(Goods goods){
        if(sellList.contains(goods)){

        }else {
            sellList.add(goods);
        }

    }
    /**
     * @Title: removeOldGoods
     * @Description: 下架商品
     * @author chendong
     * @date 2018/1/18
     * @param goods 被下架产品
     */
    public void removeOldGoods(Goods goods){
        if(sellList.contains(goods)){
            sellList.remove(goods);
        }
    }
    /**
     * @Title: sell
     * @Description: 卖家出售商品
     * @author chendong
     * @date 2018/1/18
     * @param buyer, goods, buyNumber
     * @throw SellException
     */


    public Seller sell(Buyer buyer, GoodsSellerRelation goodsSellerRelation, Double buyNumber)throws SellException {
        Seller seller = null;
        //判断剩余存货数量是否满足购买数量
        if(goodsSellerRelation.getInventory().doubleValue()<buyNumber.doubleValue()){
            //如不满足 则提示存货不够
            throw new SellException("出售的数量不足，请及时进货。");
        }
        return seller;
    }
    /**
     * @ClassName restock
     * @Description 商家进货
     * @author chendong
     * @Date 2018/1/16
     * @param goodsSellerRelation buyNumber price,seller
     */

    public void restock(GoodsSellerRelation goodsSellerRelation,Double buyNumber,Double price,Seller seller) {
        //进货后更改商品的库存
        goodsSellerRelation.setInventory(goodsSellerRelation.getInventory().doubleValue()+buyNumber.doubleValue());
        GoodsSellerRelation goodsSellerRelation1 = new GoodsSellerRelation();
        goodsSellerRelation1.updateGoodsSellerRelation();
        //进货后更新商家账户金额
        seller.setSaving(seller.getSaving().doubleValue() - buyNumber.doubleValue() * price.doubleValue());
    }



    /**
     * @ClassName deliverGoods
     * @Description 发货（注：时间为订单生成时间）
    * @author chendong
    * @Date 2018/1/16
    */

    public void deliverGoods(Buyer buyer, GoodsSellerRelation goodsSellerRelation , Date time,Double buyNumber) {
        //根据goods的发货数量修改其库存
        goodsSellerRelation.setInventory(goodsSellerRelation.getInventory().doubleValue()-buyNumber.doubleValue());
        GoodsSellerRelation goodsSellerRelation1 = new GoodsSellerRelation();
        goodsSellerRelation1.updateGoodsSellerRelation();
    }
    /**
     * @Title: receiveMoney
     * @Description: 商家收取货款
     * @author chendong
     * @date 2018/1/18
     * @param goodsSellerRelation, buyer, buyNumber, seller
     */

    public void receiveMoney(GoodsSellerRelation goodsSellerRelation,Buyer buyer,Double buyNumber,Seller seller ) {
        //根据收取货款修改卖家账户金额
        seller.setSaving(seller.getSaving().doubleValue()+goodsSellerRelation.getPrice().doubleValue()+buyNumber.doubleValue());
        return ;
    }

    public SellerDaoImpl getSellerDaoImpl() {
        return sellerDaoImpl;
    }
    private SellerDaoImpl sellerDaoImpl=new SellerDaoImpl();

    public void setSellerDaoImpl(SellerDaoImpl sellerDaoImpl) {
        this.sellerDaoImpl = sellerDaoImpl;
    }

    @Override
    public Integer deleteSellerById(Long id, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM seller WHERE ID=?");
        preparedStatement.setLong(1, id);
        Integer operatedRow = preparedStatement.executeUpdate();
        connection.commit();
        return operatedRow;
    }

    @Override
    public Integer deleteSellerByIds(Long[] ids, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        Integer operatedRows=0;
        for (Long id : ids) {
            preparedStatement = connection.prepareStatement("DELETE FROM seller WHERE ID=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
        connection.commit();
        return operatedRows;
    }

    @Override
    public Seller insertSeller(Seller seller, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO seller(NAME,SEX,AGE,ID_NUMBER,TEL,ADDR,PROFESSION,WORK_UNIT,SAVING)VALUES(?,?,?,?,?,?,?,?,?);");
        preparedStatement.setString(1,seller.getName());
        preparedStatement.setString(2, seller.getSex());
        preparedStatement.setByte(3, seller.getAge());
        preparedStatement.setString(4,seller.getIdNumber());
        preparedStatement.setString(5, seller.getTel());
        preparedStatement.setString(6, seller.getAddr());
        preparedStatement.setString(7, seller.getProfession());
        preparedStatement.setString(8, seller.getWorkUnit());
        preparedStatement.setDouble(9, seller.getSaving());


        preparedStatement.executeUpdate();
        connection.commit();
        return seller;
    }

    @Override
    public Seller updateSeller(Seller seller, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        preparedStatement = connection.prepareStatement("UPDATE seller SET NAME=?,SEX=?,AGE=?,ID_NUMBER=?,TEL=?,ADDR=?,PROFESSION=?,WORK_UNIT=?,SAVING=?WHERE ID=?;");
        preparedStatement.setString(1, seller.getName());
        preparedStatement.setString(2, seller.getSex());
        preparedStatement.setByte(3, seller.getAge());
        preparedStatement.setString(4, seller.getIdNumber());
        preparedStatement.setString(5, seller.getTel());
        preparedStatement.setString(6, seller.getAddr());
        preparedStatement.setString(7, seller.getProfession());
        preparedStatement.setString(8, seller.getWorkUnit());
        preparedStatement.setDouble(9, seller.getSaving());
        preparedStatement.setLong(10, seller.getId());
        connection.commit();
        return seller;
    }

    @Override
    public Seller querySellerById(Long id)  {
        Seller seller = null;
        PreparedStatement preparedStatement=null;
        Connection connection=null;
        connection= JdbcUtils.getConnection();
        try {
            seller=sellerDaoImpl.querySellerById(id,connection,preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcUtils.release(connection,preparedStatement);
        }
        return seller;
    }
}
