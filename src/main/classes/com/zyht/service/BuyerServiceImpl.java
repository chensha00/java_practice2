package com.zyht.service;

import com.zyht.common.util.DateTransferUtil;
import com.zyht.common.util.JdbcUtils;
import com.zyht.common.util.OrderState;
import com.zyht.dao.BuyerDaoImpl;
import com.zyht.domain.*;
import com.zyht.exception.BuyException;
import com.zyht.exception.FetchException;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * BuyerInfoServiceImpl
 *
 * @author Administrator
 * @Description
 * @Date 2018/1/16
 */
@Service("buyerServiceImpl")
public class BuyerServiceImpl implements BuyerService {
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
    public OrderDetail buy(Double bill, Goods goods, Seller seller, Buyer buyer) throws BuyException {
        OrderDetail orderDetail = null;
        Order order = null;
        OrderDetailServiceImpl orderDetailServiceImpl=new OrderDetailServiceImpl();
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
        //在订单表数据库中生成一条订单信息
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            order = new Order( buyer.getId(), bill, false, OrderState.UNPAID.getStateNum(), UUID.randomUUID().toString(), new Date(), DateTransferUtil.stringToDate("2018-02-01 18:12:33"));
            orderServiceImpl.insertOrder(order);
            Map<String,String> query=new HashMap<String,String>();
            query.put("NUMBER",order.getNumber());
            order=orderServiceImpl.queryOrderByCondition(query).get(0);
            orderDetail = new OrderDetail( buyer.getId(), seller.getId(), goods.getId(), order.getId(), bill, false, OrderState.UNPAID.getStateNum(), order.getNumber(), order.getCreationTime(), DateTransferUtil.stringToDate("2018-02-01 18:12:33"));
            connection.setAutoCommit(false);
            //向订单详情表插入数据
            orderDetailServiceImpl.insertOrder(orderDetail);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(connection, preparedStatement);
        }
        //支付该订单
        if (buyer.getSaving().doubleValue() < bill.doubleValue()) {
            //修改订单相关状态
            order.setIsSuccess(false);
            order.setOrderStatus(OrderState.ABORTION.getStateNum());
            order.setFinishTime(new Date());
            //修改订单详情相关状态
            orderDetail.setIsSuccess(false);
            orderDetail.setOrderStatus(OrderState.ABORTION.getStateNum());
            orderDetail.setFinishTime(new Date());
            orderServiceImpl.updateOrder(order);
            orderDetailServiceImpl.updateOrder(orderDetail);
            throw new BuyException(buyer.getName() + ":账单总额大于账户余额，交易失败！");
        }
        return orderDetail;
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
        OrderDetail orderDetail = null;
        OrderDetailServiceImpl orderDetailServiceImpl=new OrderDetailServiceImpl();
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
        Map<String,String> query=new HashMap<String,String>();
        query.put("ORDER_NUMBER",order.getNumber());
        List<OrderDetail> orderDetails=new ArrayList<OrderDetail>();
        orderDetails=orderDetailServiceImpl.queryOrderByCondition(query);
        if (buyer.getSaving().doubleValue() >= order.getAmount().doubleValue()) {
            //订单及订单详情的状态修改为已支付
            order.setOrderStatus(OrderState.PAID.getStateNum());
            for(int i=0,length=orderDetails.size();i<length;i++){
                orderDetail=orderDetails.get(i);
                orderDetail.setOrderStatus(OrderState.PAID.getStateNum());
                orderDetailServiceImpl.updateOrder(orderDetail);
            }
            orderServiceImpl.updateOrder(order);
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
        OrderDetailServiceImpl orderDetailServiceImpl=new OrderDetailServiceImpl();
        OrderStatementServiceImpl orderStatementServiceImpl = new OrderStatementServiceImpl();
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
        Order order=orderServiceImpl.queryOrderById(orderDetail.getOrderId());
        //订单流水对象
        OrderStatement os = new OrderStatement( orderDetail.getBuyerId(), orderDetail.getSellerId(), orderDetail.getId(), orderDetail.getAmount(), "OS" + UUID.randomUUID().toString(), fetchDate);
        //设置订单，详情及流水的完成日期
        order.setFinishTime(fetchDate);
        orderDetail.setFinishTime(fetchDate);
        os.setFinishTime(fetchDate);
        //判定商品是否完好
        if (goodsState) {
            //商品完好，设定订单成功，设置完成日期，订单状态为完成
            order.setIsSuccess(true);
            order.setOrderStatus(OrderState.FINISH.getStateNum());
            //修改订单详情
            orderDetail.setIsSuccess(true);
            orderDetail.setOrderStatus(OrderState.FINISH.getStateNum());

            //更新订单表
            try {
                orderServiceImpl.updateOrder(order);
                orderDetailServiceImpl.updateOrder(orderDetail);
                orderStatementServiceImpl.insertOrderStatement(os);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            //否则，设定订单失败，订单状态为支付失败
            order.setIsSuccess(false);
            order.setOrderStatus(OrderState.ABORTION.getStateNum());
            orderDetail.setIsSuccess(false);
            orderDetail.setOrderStatus(OrderState.ABORTION.getStateNum());
            orderServiceImpl.updateOrder(order);
            orderDetailServiceImpl.updateOrder(orderDetail);
            throw new FetchException(buyer.getName() + "收货失败！交易结束，订单作废！");
        }
    }
   /**
     * @param id, connection, preparedStatement
     * @return java.lang.Integer
     * @Title: deleteBuyerById
     * @Description: 通过买家ID删除
     * @author caoxin
     * @date 2018/1/19
     * @throw SQLException
     */
    @Override
    public Integer deleteBuyerById(Long id) {
        BuyerDaoImpl buyerDaoImpl = new BuyerDaoImpl();
        Integer operationRows = null;
        //连接数据库
        Connection connection = JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement = null;

        //根据传入的id在buyer表中删除对应的数据并返回受影响的行数
        try {
            //关闭事务自动提交
            connection.setAutoCommit(false);
            operationRows = buyerDaoImpl.deleteBuyerById(id, connection, preparedStatement);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JdbcUtils.release(connection, preparedStatement);
        }
        return operationRows;
    }

    /**
     * @param ids, connection, preparedStatement
     * @return java.lang.Integer
     * @Title: deleteBuyerByIds
     * @Description: 通过ID批量删除买家
     * @author caoxin
     * @date 2018/1/19
     * @throw SQLException
     */
    @Override
    public Integer deleteBuyerByIds(Long[] ids) {
        BuyerDaoImpl buyerDaoImpl = new BuyerDaoImpl();
        Integer operationRows = null;
        //连接数据库
        Connection connection = JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement = null;
        //根据传入的id数组进行删除操作
        try {
            //关闭事务自动提交
            connection.setAutoCommit(false);
            operationRows = buyerDaoImpl.deleteBuyerByIds(ids, connection, preparedStatement);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JdbcUtils.release(connection, preparedStatement);
        }
        return operationRows;
    }

    /**
     * @param buyer, connection, preparedStatement
     * @return domain.Buyer
     * @Title: updateBuyer
     * @Description: 修改买家信息
     * @author caoxin
     * @date 2018/1/19
     * @throw SQLException
     */
    @Override
    public Buyer updateBuyer(Buyer buyer) {
        BuyerDaoImpl buyerDaoImpl = new BuyerDaoImpl();
        //连接数据库
        Connection connection = JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement = null;
        //根据传入的用户进行更新
        try {
            //关闭事务自动提交
            connection.setAutoCommit(false);
            buyerDaoImpl.updateBuyer(buyer, connection, preparedStatement);

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JdbcUtils.release(connection, preparedStatement);
        }
        return buyer;
    }

    /**
     * @param buyer, connection, preparedStatement
     * @return domain.Buyer
     * @Title: insertBuyer
     * @Description: 添加买家信息
     * @author caoxin
     * @date 2018/1/19
     * @throw SQLException
     */
    @Override
    public Buyer insertBuyer(Buyer buyer) {
        BuyerDaoImpl buyerDaoImpl = new BuyerDaoImpl();
        //连接数据库
        Connection connection = JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement = null;
        //插入传入的买家信息
        try {
            connection.setAutoCommit(false);
            buyerDaoImpl.insertBuyer(buyer, connection, preparedStatement);

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JdbcUtils.release(connection, preparedStatement);
        }
        return buyer;
    }

    /**
     * @param id, connection, preparedStatement
     * @return domain.Buyer
     * @Title: queryBuyerById
     * @Description: 通过ID查询买家信息表
     * @author caoxin
     * @date 2018/1/19
     * @throw SQLException
     */
    @Override
    public Buyer queryBuyerById(Long id) {
        BuyerDaoImpl buyerDaoImpl = new BuyerDaoImpl();
        ResultSet resultSet = null;
        Buyer buyerForOperation = null;
        //连接数据库
        Connection connection = JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement = null;
        //按传入的买家id查询buyer表
        try {
            buyerForOperation = buyerDaoImpl.queryBuyerById(id, connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(connection, preparedStatement, resultSet);
        }
        return buyerForOperation;
    }

    /**
     * @param stringBuyerMap, connection, preparedStatement
     * @return java.util.List<domain.Buyer>
     * @Title: queryBuyerByCondition
     * @Description: 通过特定条件查询买家表
     * @author caoxin
     * @date 2018/1/19
     * @throw SQLException
     */

    @Override
    public List<Buyer> queryBuyerByCondition(Map<String, String> stringBuyerMap) {
        List<Buyer> buyersForOperation = null;
        ResultSet resultSet = null;
        BuyerDaoImpl buyerDaoImpl = new BuyerDaoImpl();
        //连接数据库
        Connection connection = JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement = null;
        //根据条件查询buyer表并返回查询结果
        try {
            buyersForOperation = buyerDaoImpl.queryBuyerByCondition(stringBuyerMap, connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(connection, preparedStatement, resultSet);
        }
        return buyersForOperation;
    }

    /**
     * @param stringBuyerMap, connection, preparedStatement
     * @return java.util.List<domain.Buyer>
     * @Title: queryBuyerByCondition
     * @Description: 通过特定条件查询买家表并分页显示
     * @author caoxin
     * @date 2018/1/19
     * @throw SQLException
     */
    @Override
    public List<Buyer> queryBuyerByCondition(Map<String, String> stringBuyerMap, Integer startRow, Integer size) {
        List<Buyer> buyersForOperation = null;
        ResultSet resultSet = null;
        BuyerDaoImpl buyerDaoImpl = new BuyerDaoImpl();
        //连接数据库
        Connection connection = JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement = null;
        //根据条件查询buyer表，并将结果分页显示
        try {
            buyersForOperation = buyerDaoImpl.queryBuyerByCondition(stringBuyerMap, startRow, size, connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(connection, preparedStatement, resultSet);
        }
        return buyersForOperation;
    }
}
