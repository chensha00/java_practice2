package service;

import common.util.DateTransferUtil;
import common.util.JdbcUtils;
import common.util.OrderState;
import dao.BuyerDaoImpl;
import domain.*;
import exception.BuyException;
import exception.FetchException;

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
public class BuyerServiceImpl implements BuyerService {
    /**
     * 购买商品的清单(商品及数量)
     */
    private Map<GoodsSellerRelation,Double> goodsList=new HashMap<GoodsSellerRelation,Double>();
    /**
     *消费生成的订单详情
     */
    private OrderDetail orderDetail=null;
    /**
     *消费生成的订单详情
     */
    private Order order=null;
    /**
     * 订单详情表接口类
     */
    private OrderDetailServiceImpl orderDetailServiceImpl=new OrderDetailServiceImpl();
    /**
     * 订单流水表接口类
     */
    private OrderStatementServiceImpl orderStatementServiceImpl=new OrderStatementServiceImpl();
    /**
     * 订单表接口类
     */
    private OrderServiceImpl orderServiceImpl=new OrderServiceImpl();
    /**
     * 买家表接口类
     */
    private BuyerDaoImpl buyerDaoImpl=new BuyerDaoImpl();
    /**
     *查询结果集
     */
    ResultSet resultSet=null;
    /**
     * 买家信息
     */
    Buyer buyerForOperation=null;
    /**
     *买家信息集合
     */
    private List<Buyer> buyersForOperation=null;
    /**
     *受影响的行
     */
    private Integer operationRows=null;
    /**
     * @Title: getGoodsList
     * @Description: 获取购买意向清单 添加需要购买的商品到客户账户中
     * @author caoxin
     * @date 2018/1/18
     * @return java.util.Map<domain.GoodsSellerRelation,java.lang.Double>
     */
    public Map<GoodsSellerRelation,Double> getGoodsList() {
        return goodsList;
    }
    /**
     * @Title: setBuyList
     * @Description: 设置购买清单
     * @author caoxin
     * @date 2018/1/18
     * @param goodsList
     */

    public void setGoodsList(Map<GoodsSellerRelation,Double> goodsList) {
        this.goodsList = goodsList;
    }

    /**
     * @Title: bill
     * @Description: 计算订单支付金额
     * @author caoxin
     * @date 2018/1/16
     * @param goodsList
     * @return sum 账单总金额
     */
    @Override
    public Double bill(Map<GoodsSellerRelation,Double> goodsList){
        Double sum=0.0;
        Set<GoodsSellerRelation> goodsSet=goodsList.keySet();
        for(GoodsSellerRelation goods:goodsSet){
            sum=goods.getPrice().doubleValue()*goodsList.get(goods).doubleValue()+sum.doubleValue();
        }

        return sum;
    }
    /**
     * @Title: buy
     * @Description: 买商品
     * @author caoxin
     * @date 2018/1/16
     * @param bill 购买金额 buyNum 购买数量 goods 商品 seller 卖家 \
     */
    @Override
    public OrderDetail buy(Double bill, Goods goods, Seller seller, Buyer buyer)throws BuyException {

        //在订单表数据库中生成一条订单信息
        Connection connection=JdbcUtils.getConnection();
        PreparedStatement preparedStatement=null;
        try{
            order=new Order(1l,buyer.getId(),bill,false,OrderState.UNPAID.getStateNum(),UUID.randomUUID().toString(),new Date(),DateTransferUtil.stringToDate("2018-02-01 18:12:33"));
            orderDetail = new OrderDetail(1l,buyer.getId(),seller.getId(),goods.getId(),order.getId(),bill,false, OrderState.UNPAID.getStateNum(),order.getNumber(),order.getCreationTime(), DateTransferUtil.stringToDate("2018-02-01 18:12:33"));
            connection.setAutoCommit(false);
            //向订单及订单详情表插入数据
            orderServiceImpl.insertOrder(order);
            orderDetailServiceImpl.insertOrder(orderDetail);
        }catch (SQLException e){
            try{
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(connection,preparedStatement);
        }
        //支付该订单
        if(buyer.getSaving().doubleValue()>=bill.doubleValue()){
            System.out.println(buyer.getName()+"在网店购买！");
            pay(buyer,order);
        }else{
            //修改订单相关状态
            order.setIsSuccess(false);
            order.setOrderStatus(OrderState.ABORTION.getStateNum());
            order.setFinishTime(new Date());
            //修改订单详情相关状态
            orderDetail.setIsSuccess(false);
            orderDetail.setOrderStatus(OrderState.ABORTION.getStateNum());
            orderDetail.setFinishTime(new Date());
            try {
                orderServiceImpl.updateOrder(order);
                orderDetailServiceImpl.updateOrder(orderDetail);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(buyer.getName()+":账单总额大于账户余额，交易失败！");
            throw new BuyException(buyer.getName()+":账单总额大于账户余额，交易失败！");
        }
        return orderDetail;
    }
    /**
     * @Title: pay
     * @Description: 付钱
     * @author caoxin
     * @date 2018/1/16
     * @param buyer 商品 order 订单
     * @throws exception.BuyException
     */
    @Override
    public void pay(Buyer buyer,Order order)throws BuyException {
        if(buyer.getSaving().doubleValue()>=order.getAmount().doubleValue()){
            //订单及订单详情的状态修改为已支付
            order.setOrderStatus(OrderState.PAID.getStateNum());
            orderDetail.setOrderStatus(OrderState.PAID.getStateNum());
            Connection connection=JdbcUtils.getConnection();
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //更新订单表
            try {
                orderServiceImpl.updateOrder(order);
                System.out.println(buyer.getName()+"使用移动支付方式支付！本次需要支付"+order.getAmount().doubleValue()+"元");
                //修改商品账户余额
                buyer.setSaving(buyer.getSaving().doubleValue()-order.getAmount().doubleValue());
                connection=JdbcUtils.getConnection();
                connection.setAutoCommit(false);
                //更新买家的存款金额
                updateBuyer(buyer);
                System.out.println("账户当前余额为"+buyer.getSaving().doubleValue());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            System.out.println(buyer.getName()+":账单总额大于账户余额，交易失败！");
            throw new BuyException(buyer.getName()+":账单总额大于账户余额，交易失败！");
        }
    }
    /**
     * @Title: fetchGoods
     * @Description: 收货
     * @author caoxin
     * @date 2018/1/16
     * @param goodsState 商品状态 order 订单 receiptDate 收货时间
     * @throws exception.FetchException
     */
    @Override
    public void fetchGoods(boolean goodsState, OrderDetail orderDetail, Date fetchDate,Buyer buyer)throws FetchException {
        //连接数据库
        Connection connection=JdbcUtils.getConnection();
        PreparedStatement preparedStatement=null;
        //订单流水对象
        OrderStatement os=new OrderStatement(1l,orderDetail.getBuyerId(),orderDetail.getSellerId(),orderDetail.getId(),orderDetail.getAmount(),"OS"+UUID.randomUUID().toString(),fetchDate);
        //设置订单，详情及流水的完成日期
        order.setFinishTime(fetchDate);
        orderDetail.setFinishTime(fetchDate);
        os.setFinishTime(fetchDate);
        //判定商品是否完好
        if(goodsState){
            //商品完好，设定订单成功，设置完成日期，订单状态为完成
            System.out.println(buyer.getName() + "通过快递收到商品!");
            order.setIsSuccess(true);
            order.setOrderStatus(OrderState.FINISH.getStateNum());
            //修改订单详情
            orderDetail.setIsSuccess(true);
            orderDetail.setOrderStatus(OrderState.FINISH.getStateNum());

            //更新订单表
            try {
                connection.setAutoCommit(false);
                orderServiceImpl.updateOrder(order);
                orderDetailServiceImpl.updateOrder(orderDetail);
                orderStatementServiceImpl.insertOrderStatement(os);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("订单完成!");
        }else{
            //否则，设定订单失败，订单状态为支付失败
            order.setIsSuccess(false);
            order.setOrderStatus(OrderState.ABORTION.getStateNum());
            orderDetail.setIsSuccess(false);
            orderDetail.setOrderStatus(OrderState.ABORTION.getStateNum());
            try {
                orderServiceImpl.updateOrder(order);
                orderDetailServiceImpl.updateOrder(orderDetail);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(buyer.getName()+"收货失败！交易结束，订单作废！");
            throw new FetchException(buyer.getName()+"收货失败！交易结束，订单作废！");
        }
    }
    /**
     * @Title: getBuyerDaoImpl
     * @Description: 获取买家信息表实现类对象
     * @author caoxin
     * @date 2018/1/25
     * @return dao.BuyerDaoImpl
     */

    public BuyerDaoImpl getBuyerDaoImpl() {
        return buyerDaoImpl;
    }
    /**
     * @Title: setBuyerDaoImpl
     * @Description: 设置买家表接口实现类对象
     * @author caoxin
     * @date 2018/1/25
     * @param buyerDaoImpl
     */

    public void setBuyerDaoImpl(BuyerDaoImpl buyerDaoImpl) {
        this.buyerDaoImpl = buyerDaoImpl;
    }
    /**
     * @Title: getOrderServiceImpl
     * @Description: 获取订单服务实现类
     * @author caoxin
     * @date 2018/1/25
     * @return service.OrderServiceImpl
     */

    public OrderServiceImpl getOrderServiceImpl() {
        return orderServiceImpl;
    }
    /**
     * @Title: setOrderServiceImpl
     * @Description: 设置订单服务实现类
     * @author caoxin
     * @date 2018/1/25
     * @param orderServiceImpl
     */

    public void setOrderServiceImpl(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }
    /**
     * @Title: getOrder
     * @Description: 获取买家的交易订单
     * @author caoxin
     * @date 2018/1/25
     * @return domain.Order
     */

    public Order getOrder() {
        return order;
    }
    /**
     * @Title: setOrder
     * @Description: 设置买家交易订单
     * @author caoxin
     * @date 2018/1/25
     * @param order
     */

    public void setOrder(Order order) {
        this.order = order;
    }
    /**
     * @Title: getOrderDetail
     * @Description: 获取买家的订单详情
     * @author caoxin
     * @date 2018/1/25
     * @return domain.OrderDetail
     */

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }
    /**
     * @Title: setOrderDetail
     * @Description: 设置买家的交易订单详情
     * @author caoxin
     * @date 2018/1/25
     * @param orderDetail
     */

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
    /**
     * @Title: getOrderDetailServiceImpl
     * @Description: 获取订单详情服务实现类对象
     * @author caoxin
     * @date 2018/1/25
     * @return service.OrderDetailServiceImpl
     */

    public OrderDetailServiceImpl getOrderDetailServiceImpl() {
        return orderDetailServiceImpl;
    }
    /**
     * @Title: setOrderDetailServiceImpl
     * @Description: 设置订单详情服务实现类对象
     * @author caoxin
     * @date 2018/1/25
     * @param orderDetailServiceImpl
     */

    public void setOrderDetailServiceImpl(OrderDetailServiceImpl orderDetailServiceImpl) {
        this.orderDetailServiceImpl = orderDetailServiceImpl;
    }

    /**
     * @Title: deleteBuyerById
     * @Description: 通过买家ID删除
     * @author caoxin
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer deleteBuyerById(Long id) {
        //连接数据库
        Connection connection=JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement=null;

        //根据传入的id在buyer表中删除对应的数据并返回受影响的行数
        try {
            //关闭事务自动提交
            connection.setAutoCommit(false);
            operationRows=buyerDaoImpl.deleteBuyerById(id,connection,preparedStatement);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            JdbcUtils.release(connection,preparedStatement);
        }
        return operationRows;
    }
    /**
     * @Title: deleteBuyerByIds
     * @Description: 通过ID批量删除买家
     * @author caoxin
     * @date 2018/1/19
     * @param  ids, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer deleteBuyerByIds(Long[] ids){
        //连接数据库
        Connection connection=JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement=null;
        //根据传入的id数组进行删除操作
        try {
            //关闭事务自动提交
            connection.setAutoCommit(false);
            operationRows=buyerDaoImpl.deleteBuyerByIds(ids,connection,preparedStatement);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            JdbcUtils.release(connection,preparedStatement);
        }
        return operationRows;
    }
    /**
     * @Title: updateBuyer
     * @Description: 修改买家信息
     * @author caoxin
     * @date 2018/1/19
     * @param buyer, connection, preparedStatement
     * @return domain.Buyer
     * @throw SQLException
     */
    @Override
    public Buyer updateBuyer(Buyer buyer){
        //连接数据库
        Connection connection=JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement=null;
        //根据传入的用户进行更新
        try {
            //关闭事务自动提交
            connection.setAutoCommit(false);
            buyerDaoImpl.updateBuyer(buyer,connection,preparedStatement);

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            JdbcUtils.release(connection,preparedStatement);
        }
        return buyer;
    }
    /**
     * @Title: insertBuyer
     * @Description: 添加买家信息
     * @author caoxin
     * @date 2018/1/19
     * @param buyer, connection, preparedStatement
     * @return domain.Buyer
     * @throw SQLException
     */
    @Override
    public Buyer insertBuyer(Buyer buyer){
        //连接数据库
        Connection connection=JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement=null;
        //插入传入的买家信息
        try {
            connection.setAutoCommit(false);
            buyerDaoImpl.insertBuyer(buyer,connection,preparedStatement);

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            JdbcUtils.release(connection,preparedStatement);
        }
        return buyer;
    }

    /**
     * @Title: queryBuyerById
     * @Description: 通过ID查询买家信息表
     * @author caoxin
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return domain.Buyer
     * @throw SQLException
     */
    @Override
    public Buyer queryBuyerById(Long id){
        //连接数据库
        Connection connection=JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement=null;
        //按传入的买家id查询buyer表
        try {
            buyerForOperation=buyerDaoImpl.queryBuyerById(id,connection,preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcUtils.release(connection,preparedStatement,resultSet);
        }
        return buyerForOperation;
    }
    /**
     * @Title: queryBuyerByCondition
     * @Description: 通过特定条件查询买家表
     * @author caoxin
     * @date 2018/1/19
     * @param stringBuyerMap, connection, preparedStatement
     * @return java.util.List<domain.Buyer>
     * @throw SQLException
     */

    @Override
    public List<Buyer> queryBuyerByCondition(Map<String, String> stringBuyerMap){
        //连接数据库
        Connection connection=JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement=null;
        //根据条件查询buyer表并返回查询结果
        try {
            buyersForOperation=buyerDaoImpl.queryBuyerByCondition(stringBuyerMap,connection,preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcUtils.release(connection,preparedStatement,resultSet);
        }
        return  buyersForOperation;
    }
    /**
     * @Title: queryBuyerByCondition
     * @Description: 通过特定条件查询买家表并分页显示
     * @author caoxin
     * @date 2018/1/19
     * @param stringBuyerMap, connection, preparedStatement
     * @return java.util.List<domain.Buyer>
     * @throw SQLException
     */
    @Override
    public List<Buyer> queryBuyerByCondition(Map<String, String> stringBuyerMap, Integer startRow, Integer size){
        //连接数据库
        Connection connection=JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement=null;
        //根据条件查询buyer表，并将结果分页显示
        try {
            buyersForOperation=buyerDaoImpl.queryBuyerByCondition(stringBuyerMap,startRow,size,connection,preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcUtils.release(connection,preparedStatement,resultSet);
        }
        return buyersForOperation;
    }
}
