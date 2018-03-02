package service;


import common.util.DataSourceUtils;
import dao.SellerDaoImpl;
import domain.Buyer;
import domain.Goods;
import domain.GoodsSellerRelation;
import domain.Seller;
import exception.SellException;

import java.sql.*;
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
public class SellerServiceImpl implements SellerService {
    DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 出售的商品清单
     */
    private List<Goods> sellList=new ArrayList<Goods>();
    /**
     * 卖家表接口类
     */
    private SellerDaoImpl sellerDaoImpl=null;
    /**
     * 预编译SQL对象
     */
    private PreparedStatement preparedStatement=null;
    /**
     * 连接对象
     */
    private Connection connection=null;
    /**
     *自定义连接池对象
     */
    DataSourceUtils dataSourceUtils=new DataSourceUtils();
    /**
     * 查询结果集
     */
    private ResultSet resultSet = null;
    /**
     * 卖家信息
     */
    private Seller seller = null;
    /**
     * @Title: addSeller
     * @Description: 向卖家表添加卖家
     * @author chendong
     * @date 2018/1/20
     * @param seller
     */

    public void addSeller(Seller seller){
        sellerDaoImpl=new SellerDaoImpl();
        try{
            connection=dataSourceUtils.getConnection();
            connection.setAutoCommit(false);
            sellerDaoImpl.insertSeller(seller,connection,preparedStatement);
            System.out.println("买家信息添加成功！");
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
            System.out.println("要添加的商品已存在，请不要重复添加！");
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
        }else{
            System.out.println("商品库中找不到该商品，请确认后重新输入！");
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
        System.out.println("商品单价为："+goodsSellerRelation.getPrice()+"，现有库存："+goodsSellerRelation.getInventory().doubleValue());
        //判断剩余存货数量是否满足购买数量
        if(goodsSellerRelation.getInventory().doubleValue()<buyNumber.doubleValue()){
            //如不满足 则提示存货不够
            System.out.println("出售的数量不足，请及时进货。");
            throw new SellException("出售的数量不足，请及时进货。");
        }else{
            System.out.println("本次买家"+buyer.getName()+"购买了"+buyNumber+"件商品，请及时发货。");
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
        //进货后更新商家账户金额
        seller.setSaving(seller.getSaving().doubleValue() - buyNumber.doubleValue() * price.doubleValue());
        System.out.println("以单价"+price.doubleValue()+"进货"+buyNumber+"件商品，共花费了"+(buyNumber.doubleValue()*price.doubleValue())+"元。");
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
        System.out.println(buyer.getName() + "购买的" + buyNumber + "件商品" + "商品已经发货。发货时间是：" + dateFormat.format(time));
        System.out.println("发货后，商品现在库存为：" + goodsSellerRelation.getInventory().doubleValue());
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
        System.out.println(buyer.getName()+"购买了"+buyNumber.doubleValue()+"件商品");
        System.out.println("应收"+(goodsSellerRelation.getPrice().doubleValue()*buyNumber.doubleValue())+"元。");
        return ;
    }

    public SellerDaoImpl getSellerDaoImpl() {
        return sellerDaoImpl;
    }

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
//        preparedStatement.setLong(1, seller.getId());
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
    public ResultSet querySellerById(Long id, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT NAME ,SEX,AGE FROM seller WHERE ID=?;");
        preparedStatement.setLong(1, id);
        //获取查询的结果集
        resultSet = preparedStatement.executeQuery();
        //若非空，开始遍历结果集并给orderStatement赋值
        if (resultSet != null) {
            while (resultSet.next()) {
                seller = new Seller();
                seller.setName(resultSet.getString(1));
                seller.setSex(resultSet.getString(2));
                seller.setAge(resultSet.getByte(3));
            }
            return resultSet;
        } else {
            //结果集为空返回null
            return null;
    }
    }
}
