package com.zyht.service;

import com.zyht.common.util.DateTransferUtil;
import com.zyht.common.util.OrderState;
import com.zyht.dao.BuyerDao;
import com.zyht.domain.*;
import com.zyht.exception.BuyException;
import com.zyht.exception.FetchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

/**
 * BuyerInfoServiceImpl
 *
 * @author Administrator
 * @Description
 * @Date 2018/1/16
 */
@Service("buyerService")
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private BuyerDao buyerDao;

    /**
     * @param goodsList
     * @return sum 账单总金额
     * @Title: bill
     * @Description: 计算订单支付金额
     * @author caoxin
     * @date 2018/1/16
     */
    @Override
    public Double bill(Map<GoodsSellerRelation, Double> goodsList) {
        Double sum = 0.0;
        Set<GoodsSellerRelation> goodsSet = goodsList.keySet();
        for (GoodsSellerRelation goods : goodsSet) {
            sum = goods.getPrice().doubleValue() * goodsList.get(goods).doubleValue() + sum.doubleValue();
        }

        return sum;
    }

    /**
     * @param bill 购买金额 buyNum 购买数量 goods 商品 seller 卖家 \
     * @Title: buy
     * @Description: 买商品
     * @author caoxin
     * @date 2018/1/16
     */
    @Override
    public List<OrderDetail> buy(Double bill, Map<GoodsSellerRelation,Double> buyList, Buyer buyer) throws BuyException {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderDetail orderDetail = null;
        Order order = null;
        OrderDetailService ods=(OrderDetailService) context.getBean("orderDetailService");
//        OrderDetailServiceImpl orderDetailServiceImpl=new OrderDetailServiceImpl();
//        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
        OrderService os=(OrderService)context.getBean("orderService");
        List<OrderDetail> orderDetails=new ArrayList<OrderDetail>();
        //在订单表数据库中生成一条订单信息
        try {
            order = new Order( buyer.getId(), bill, false, OrderState.UNPAID.getStateNum(), UUID.randomUUID().toString(), new Date(), DateTransferUtil.stringToDate("2018-02-01 18:12:33"));
            os.insertOrder(order);
            Map<String,Object> query=new HashMap<String,Object>();
            query.put("NUMBER",order.getNumber());
            order=os.queryOrderByCondition(query).get(0);
            Set<GoodsSellerRelation> listSet=buyList.keySet();
            Iterator<GoodsSellerRelation> list=listSet.iterator();
            while(list.hasNext()){
                GoodsSellerRelation eachGoods=list.next();
                Double eachBill=buyList.get(eachGoods)*eachGoods.getPrice();
                orderDetail = new OrderDetail( buyer.getId(), eachGoods.getSellerId(), eachGoods.getGoodsId(), order.getId(), eachBill, false, OrderState.UNPAID.getStateNum(), order.getNumber(), order.getCreationTime(), DateTransferUtil.stringToDate("2018-02-01 18:12:33"));
                orderDetails.add(orderDetail);
            }
            //向订单详情表插入数据
            for(OrderDetail od:orderDetails){
                ods.insertOrder(od);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //支付该订单
        if (buyer.getSaving().doubleValue() < bill.doubleValue()) {
            //修改订单相关状态
            order.setIsSuccess(false);
            order.setOrderStatus(OrderState.ABORTION.getStateNum());
            order.setFinishTime(new Date());
            os.updateOrder(order);
            //修改订单详情相关状态
            for(OrderDetail od:orderDetails){
                od.setIsSuccess(false);
                od.setOrderStatus(OrderState.ABORTION.getStateNum());
                od.setFinishTime(new Date());
                ods.updateOrder(od);
            }
            throw new BuyException(buyer.getName() + ":账单总额大于账户余额，交易失败！");
        }
        return orderDetails;
    }

    /**
     * @param buyer 商品 order 订单
     * @throws BuyException
     * @Title: pay
     * @Description: 付钱
     * @author caoxin
     * @date 2018/1/16
     */
    @Override
    public void pay(Buyer buyer, Order order) throws BuyException {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderDetail orderDetail = null;
        OrderDetailService ods=(OrderDetailService) context.getBean("orderDetailService");
//        OrderDetailServiceImpl orderDetailServiceImpl=new OrderDetailServiceImpl();
//        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
        OrderService os=(OrderService)context.getBean("orderService");
        Map<String,Object> query=new HashMap<String,Object>();
        query.put("ORDER_NUMBER",order.getNumber());
        List<OrderDetail> orderDetails=new LinkedList<OrderDetail>();
        orderDetails=ods.queryOrderByCondition(query);
        if (buyer.getSaving().doubleValue() >= order.getAmount().doubleValue()) {
            //订单及订单详情的状态修改为已支付
            order.setOrderStatus(OrderState.PAID.getStateNum());
            for(int i=0,length=orderDetails.size();i<length;i++){
                orderDetail=orderDetails.get(i);
                orderDetail.setOrderStatus(OrderState.PAID.getStateNum());
                ods.updateOrder(orderDetail);
            }
            os.updateOrder(order);
            //修改商品账户余额
            buyer.setSaving(buyer.getSaving().doubleValue() - order.getAmount().doubleValue());
            updateBuyer(buyer);
        } else {
            throw new BuyException(buyer.getName() + ":账单总额大于账户余额，交易失败！");
        }
    }

    /**
     * @param goodsState 商品状态 order 订单 receiptDate 收货时间
     * @throws FetchException
     * @Title: fetchGoods
     * @Description: 收货
     * @author caoxin
     * @date 2018/1/16
     */
    @Override
    public void fetchGoods(boolean goodsState, OrderDetail orderDetail, Date fetchDate, Buyer buyer) throws FetchException {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderDetailService ods=(OrderDetailService) context.getBean("orderDetailService");
//        OrderDetailServiceImpl orderDetailServiceImpl=new OrderDetailServiceImpl();
//        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
        OrderService os=(OrderService)context.getBean("orderService");
        OrderStatementServiceImpl orderStatementServiceImpl = new OrderStatementServiceImpl();
//        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
        Order order=os.queryOrderById(orderDetail.getOrderId());
        //订单流水对象
        OrderStatement ost = new OrderStatement( orderDetail.getBuyerId(), orderDetail.getSellerId(), orderDetail.getId(), orderDetail.getAmount(), "OS" + UUID.randomUUID().toString(), fetchDate);
        //设置订单，详情及流水的完成日期
        order.setFinishTime(fetchDate);
        orderDetail.setFinishTime(fetchDate);
        ost.setFinishTime(fetchDate);
        //判定商品是否完好
        if (goodsState) {
            //商品完好，设定订单成功，设置完成日期，订单状态为完成
            order.setIsSuccess(true);
            order.setOrderStatus(OrderState.FINISH.getStateNum());
            //修改订单详情
            orderDetail.setIsSuccess(true);
            orderDetail.setOrderStatus(OrderState.FINISH.getStateNum());

            //更新订单表
            os.updateOrder(order);
            ods.updateOrder(orderDetail);
            orderStatementServiceImpl.insertOrderStatement(ost);
        } else {
            //否则，设定订单失败，订单状态为支付失败
            order.setIsSuccess(false);
            order.setOrderStatus(OrderState.ABORTION.getStateNum());
            orderDetail.setIsSuccess(false);
            orderDetail.setOrderStatus(OrderState.ABORTION.getStateNum());
            os.updateOrder(order);
            ods.updateOrder(orderDetail);
            throw new FetchException(buyer.getName() + "收货失败！交易结束，订单作废！");
        }
    }
   /**
     * @param id, connection, preparedStatement
     * @return int
     * @Title: deleteBuyerById
     * @Description: 通过买家ID删除
     * @author caoxin
     * @date 2018/1/19
     */
    @Override
    public int deleteBuyerById(Long id) {
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
//        BuyerDao buyerDaoImpl =(BuyerDao) SpringContextUtil.getBean("buyerDao");
        //连接数据库
//        Connection connection = JdbcUtils.getConnection();
        //预编译语句
//        PreparedStatement preparedStatement = null;

        //根据传入的id在buyer表中删除对应的数据并返回受影响的行数

            //关闭事务自动提交
//            connection.setAutoCommit(false);
        return buyerDao.deleteBuyerById(id);

    }

    /**
     * @param ids
     * @return int
     * @Title: deleteBuyerByIds
     * @Description: 通过ID批量删除买家
     * @author caoxin
     * @date 2018/1/19
     */
    @Override
    public int deleteBuyerByIds(Long[] ids) {
//        BuyerDaoImpl buyerDaoImpl = new BuyerDaoImpl();
//        Integer operationRows = null;
        //连接数据库
//        Connection connection = JdbcUtils.getConnection();
        //预编译语句
//        PreparedStatement preparedStatement = null;
        //根据传入的id数组进行删除操作
//        try {
            //关闭事务自动提交
//            connection.setAutoCommit(false);
           return  buyerDao.deleteBuyerByIds(ids);
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//            e.printStackTrace();
//        } finally {
//            JdbcUtils.release(connection, preparedStatement);
//        }
//        return operationRows;
    }

    /**
     * @param buyer
     * @return int
     * @Title: updateBuyer
     * @Description: 修改买家信息
     * @author caoxin
     * @date 2018/1/19
     */
    @Override
    public int updateBuyer(Buyer buyer) {
//        BuyerDaoImpl buyerDaoImpl = new BuyerDaoImpl();
        //连接数据库
//        Connection connection = JdbcUtils.getConnection();
        //预编译语句
//        PreparedStatement preparedStatement = null;
        //根据传入的用户进行更新
//        try {
            //关闭事务自动提交
//            connection.setAutoCommit(false);


//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//            e.printStackTrace();
//        } finally {
//            JdbcUtils.release(connection, preparedStatement);
//        }
//        int line=0;
        return buyerDao.updateBuyer(buyer);
    }

    /**
     * @param buyer
     * @return int
     * @Title: insertBuyer
     * @Description: 添加买家信息
     * @author caoxin
     * @date 2018/1/19
     */
    @Override
    public int insertBuyer(Buyer buyer) {
//        BuyerDaoImpl buyerDaoImpl = new BuyerDaoImpl();
        //连接数据库
//        Connection connection = JdbcUtils.getConnection();
        //预编译语句
//        PreparedStatement preparedStatement = null;
        //插入传入的买家信息
//        try {
//            connection.setAutoCommit(false);
        return buyerDao.insertBuyer(buyer);
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//            e.printStackTrace();
//        } finally {
//            JdbcUtils.release(connection, preparedStatement);
//        }


    }

    /**
     * @param id
     * @return domain.Buyer
     * @Title: queryBuyerById
     * @Description: 通过ID查询买家信息表
     * @author caoxin
     * @date 2018/1/19
     */
    @Override
    public Buyer queryBuyerById(Long id) {
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
//        BuyerDao buyerDao =(BuyerDao) SpringContextUtil.getBean("buyerDao");
        //连接数据库
//        Connection connection = JdbcUtils.getConnection();
        //预编译语句
//        PreparedStatement preparedStatement = null;
        //按传入的买家id查询buyer表

        return buyerDao.queryBuyerById(id);
    }
    /**
     * @Title: queryBuyerAccountById
     * @Description: 通过ID查询买家信息表
     * @author caoxin
     * @date 2018/1/19
     * @param id
     * @return domain.Account
     */
    @Override
    public Account queryBuyerAccountById(Long id){
        return buyerDao.queryBuyerAccountById(id);
    }
    /**
     * @param stringBuyerMap
     * @return java.util.List<domain.Buyer>
     * @Title: queryBuyerByCondition
     * @Description: 通过特定条件查询买家表
     * @author caoxin
     * @date 2018/1/19
     */

    @Override
    public List<Buyer> queryBuyerByCondition(Map<String, Object> stringBuyerMap) {
//        List<Buyer> buyersForOperation = null;
//        ResultSet resultSet = null;
//        BuyerDaoImpl buyerDaoImpl = new BuyerDaoImpl();
        //连接数据库
//        Connection connection = JdbcUtils.getConnection();
        //预编译语句
//        PreparedStatement preparedStatement = null;
        //根据条件查询buyer表并返回查询结果
        return buyerDao.queryBuyerByCondition(stringBuyerMap);
    }

    /**
     * @param stringBuyerMap
     * @return java.util.List<domain.Buyer>
     * @Title: queryBuyerByCondition
     * @Description: 通过特定条件查询买家表并分页显示
     * @author caoxin
     * @date 2018/1/19
     */
    @Override
    public List<Buyer> queryBuyerByConditionPage(Map<String, Object> stringBuyerMap) {
//        List<Buyer> buyersForOperation = null;
//        ResultSet resultSet = null;
//        BuyerDaoImpl buyerDaoImpl = new BuyerDaoImpl();
        //连接数据库
//        Connection connection = JdbcUtils.getConnection();
        //预编译语句
//        PreparedStatement preparedStatement = null;
        //根据条件查询buyer表，并将结果分页显示
        return  buyerDao.queryBuyerByConditionPage(stringBuyerMap);

    }
}
