package service;/********************************************************************/
/**
 * @Project: java_practice
 * @Package service
 * @author caoxin
 * @date 2018/1/25 22:09
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import domain.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author caoxin
 * @ClassName TransactionTest
 * @Description 简单交易模拟
 * @date 2018/1/25
 */
public class TransactionTest {

    public static void main(String[] args)throws ParseException {
        /**
         * 交易买方
         */
        Buyer  youngLady=null;
        /**
         * 交易卖方
         */
        Seller seller = new Seller(1l, "李一", "男", (byte) 29, "812888129837819912", "20180123", "重庆市","销售","天奇数码店",200000.2);
        //格式化日期
        DateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date makeDate=date.parse("2017-10-19 16:59:01");
        /**
         * 交易产品类别对象
         */
        Goods phone=new Goods(1l,"iPhoneX","0101","手机",5000.0,"中国郑州",makeDate,null,"部");
        /**
         * 商品卖家关系对象:交易的商品
         */
        GoodsSellerRelation sellPhone=null;
        /**
         * 商品卖家关系表服务
         */
        GoodsSellerRelationServiceImpl gsrsi=new GoodsSellerRelationServiceImpl();
        /**
         * 交易订单
         */
        Order order=null;
        /**
         * 交易订单详情
         */
        OrderDetail orderDetail=null;
        /**
         * 买家服务对象
         */
        BuyerServiceImpl buyerServiceImpl=new BuyerServiceImpl();
        /**
         * 卖家服务对象
         */
        SellerServiceImpl sellerServiceImpl=new SellerServiceImpl();
        /**
         * 订单服务对象
         */
        OrderServiceImpl orderServiceImpl=new OrderServiceImpl();
        /**
         * 选购的产品
         */
        Map<GoodsSellerRelation,Double> goodsList=new HashMap<GoodsSellerRelation, Double>();
        /**
         * 账单总和
         */
        Double sum=0.0;
        //获取交易的买方
        youngLady=buyerServiceImpl.queryBuyerById(4l);
        //获取实际交易的产品
        sellPhone=gsrsi.queryGoodsSellerRelationById(1l);
        //购买清单
        goodsList.put(sellPhone,1.0);
        //设置购物清单
        buyerServiceImpl.setGoodsList(goodsList);
        //订单总额
        sum=buyerServiceImpl.bill(goodsList);
        //购买商品phone
        buyerServiceImpl.buy(sum,phone,seller,youngLady);
        //获得订单
        order=buyerServiceImpl.getOrder();
        //获取交易的订单详情
        orderDetail=buyerServiceImpl.getOrderDetail();
        //卖家出售phone
        sellerServiceImpl.sell(youngLady,sellPhone,buyerServiceImpl.getGoodsList().get(sellPhone));
        //发货日期
        java.sql.Date sendDate=new java.sql.Date(date.parse("2018-01-28 13:22:17").getTime());
        //卖家发货
        sellerServiceImpl.deliverGoods(youngLady,sellPhone,sendDate,buyerServiceImpl.getGoodsList().get(sellPhone));
        //获取当前交易订单
        try {
            String info=orderServiceImpl.queryOrderById(1l);
            System.out.println(info);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //收货时间
        Date fetchDay=date.parse("2018-01-30 18:29:27");
        //收货
        buyerServiceImpl.fetchGoods(true,orderDetail,fetchDay,youngLady);
    }
}
