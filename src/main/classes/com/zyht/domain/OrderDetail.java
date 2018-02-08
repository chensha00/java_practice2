package com.zyht.domain;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author caoxin
 * @ClassName OrderDetail
 * @Package domain
 * @Description 订单明细实体类
 * @date 2018/1/16
 */
@Component
public class OrderDetail {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 买家信息外键
     */
    private Long buyerId;
    /**
     * 买家信息外键（实体）
     */
    private Buyer buyer;
    /**
     * 卖家信息外键
     */
    private Long sellerId;
    /**
     * 卖家信息外键（实体）
     */
    private Seller seller;
    /**
     * 商品信息外键
     */
    private Long goodsId;
    /**
     * 商品信息外键（实体）
     */
    private Goods goods;
    /**
     * 订单外键
     */
    private Long orderId;
    /**
     * 订单信息外键（实体）
     */
    private Order order;
    /**
     * 订单详情金额
     */
    private Double amount;
    /**
     * 是否交易成功，1-true-是，0-false-否
     */
    private Boolean isSuccess;
    /**
     * 订单状态:1--待支付,2--支付成功,3--已完成，4--支付失败，
     */
    private Byte orderStatus;
    /**
     * 订单编号
     */
    private String orderNumber;
    /**
     * 订单生成日期 yyyy-MM-dd HH：mm：ss
     */
    private Date creationTime;
    /**
     * 订单完成日期 yyyy-MM-dd HH：mm：ss
     */
    private Date finishTime;
    /**
     * 留言
     */
    private String message;
    /**
     * 物流状态：1--未发货，2--已发货，3--派送，4--已收货，5--退货
     */
    private Byte expressStatus;
    /**
     * 买家评价：1--差评，2--一般，3--不错，4--满意，5--非常满意
     */
    private Byte comment;
    /**
     * @Title: OrderDetail
     * @Description: 空参数构造函数
     * @author caoxin
     * @date 2018/1/16
     */

    public OrderDetail() {
    }

    /**
     * @Title: OrderDetail
     * @Description: 构造函数
     * @author caoxin
     * @date 2018/1/16
     * @param id, buyerId, sellerId, goodsId, amount, isSuccess, orderStatus, orderNumber, creationDate,finishDate
     */

    public OrderDetail(Long id, Long buyerId, Long sellerId, Long goodsId,Long orderId, Double amount, Boolean isSuccess, Byte orderStatus, String orderNumber, Date creationTime,Date finishTime) {
        this.id=id;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.goodsId = goodsId;
        this.orderId=orderId;
        this.amount = amount;
        this.isSuccess = isSuccess;
        this.orderStatus = orderStatus;
        this.orderNumber = orderNumber;
        this.creationTime = creationTime;
        this.finishTime=finishTime;
    }
    /**
     * @Title: OrderDetail
     * @Description: 构造函数
     * @author caoxin
     * @date 2018/1/16
     * @param  buyerId, sellerId, goodsId, amount, isSuccess, orderStatus, orderNumber, creationDate,finishDate
     */

    public OrderDetail( Long buyerId, Long sellerId, Long goodsId,Long orderId, Double amount, Boolean isSuccess, Byte orderStatus, String orderNumber, Date creationTime,Date finishTime) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.goodsId = goodsId;
        this.orderId=orderId;
        this.amount = amount;
        this.isSuccess = isSuccess;
        this.orderStatus = orderStatus;
        this.orderNumber = orderNumber;
        this.creationTime = creationTime;
        this.finishTime=finishTime;
    }
    /**
     * @Title: getId
     * @Description: 获取主键ID
     * @author caoxin
     * @date 2018/1/16 18:23
     * @return java.lang.Long
     */
    public Long getId() {
        return id;
    }
    /**
     * @Title: setId
     * @Description: 设置主键ID
     * @author caoxin
     * @date 2018/1/16 18:24
     * @param id
     * @return void
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @Title: getBuyerId
     * @Description: 获取商品信息
     * @author caoxin
     * @date 2018/1/16 18:24
     * @return java.lang.Long
     */
    public Long getBuyerId() {
        return buyerId;
    }
    /**
     * @Title: setBuyerId
     * @Description: 设置商品信息
     * @author caoxin
     * @date 2018/1/16 18:27
     * @param buyerId
     */
    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }
    /**
     * @Title: getBuyer
     * @Description: 获取商品信息
     * @author caoxin
     * @date 2018/1/16 18:24
     * @return Buyer
     */
    public Buyer getBuyer() {
        return buyer;
    }
    /**
     * @Title: setBuyer
     * @Description: 设置商品信息
     * @author caoxin
     * @date 2018/1/16 18:27
     * @param buyer
     */
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
    /**
     * @Title: getSellerId
     * @Description: 获取卖家信息
     * @author caoxin
     * @date 2018/1/16 18:25
     * @return java.lang.Long
     */
    public Long getSellerId() {
        return sellerId;
    }
    /**
     * @Title: setSellerId
     * @Description: 设置卖家信息
     * @author caoxin
     * @date 2018/1/16 18:25
     * @param sellerId
     */
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
    /**
     * @Title: getSeller
     * @Description: 获取卖家信息
     * @author caoxin
     * @date 2018/1/16 18:25
     * @return Seller
     */
    public Seller getSeller() {
        return seller;
    }
    /**
     * @Title: setSeller
     * @Description: 设置卖家信息
     * @author caoxin
     * @date 2018/1/16 18:25
     * @param seller
     */
    public void setSeller(Seller seller) {
        this.seller = seller;
    }
    /**
     * @Title: getGoodsId
     * @Description: 获取商品信息
     * @author caoxin
     * @date 2018/1/16 18:25
     * @return java.lang.Long
     */
    public Long getGoodsId() {
        return goodsId;
    }
    /**
     * @Title: setGoodsId
     * @Description: 设置商品信息
     * @author caoxin
     * @date 2018/1/16 18:25
     * @param goodsId
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    /**
     * @Title: getGoods
     * @Description: 获取商品信息
     * @author caoxin
     * @date 2018/1/16 18:25
     * @return Goods
     */
    public Goods getGoods() {
        return goods;
    }
    /**
     * @Title: setGoodsId
     * @Description: 设置商品信息
     * @author caoxin
     * @date 2018/1/16 18:25
     * @param goods
     */
    public void setGoods(Goods goods) {
        this.goods = goods;
    }
    /**
     * @Title: getAmount
     * @Description: 获取订单详情金额
     * @author caoxin
     * @date 2018/1/16 18:26
     * @return java.lang.Double
     */
    public Double getAmount() {
        return amount;
    }
    /**
     * @Title: setAmount
     * @Description: 设置订单总金额
     * @author caoxin
     * @date 2018/1/16 18:26
     * @param amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    /**
     * @Title: getSuccess
     * @Description: 获取订单是否成功
     * @author caoxin
     * @date 2018/1/16 18:26
     * @return java.lang.Boolean
     */
    public Boolean getIsSuccess() {
        return isSuccess;
    }
    /**
     * @Title: setSuccess
     * @Description: 设置订单是否成功
     * @author caoxin
     * @date 2018/1/16 18:26
     * @param success
     */
    public void setIsSuccess(Boolean success) {
        isSuccess = success;
    }
    /**
     * @Title: getOrderStatus
     * @Description: 获取订单状态
     * @author caoxin
     * @date 2018/1/16 18:26
     * @return java.lang.Byte
     */
    public Byte getOrderStatus() {
        return orderStatus;
    }
    /**
     * @Title: setOrderStatus
     * @Description: 设置订单状态
     * @author caoxin
     * @date 2018/1/16 18:26
     * @param orderStatus
     */
    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }
    /**
     * @Title: getOrderNumber
     * @Description: 获取订单编号
     * @author caoxin
     * @date 2018/1/16 18:26
     * @return java.lang.String
     */
    public String getOrderNumber() {
        return orderNumber;
    }
    /**
     * @Title: setOrderNumber
     * @Description: 设置订单编号
     * @author caoxin
     * @date 2018/1/16 18:26
     * @param orderNumber
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    /**
     * @Title: getCreationTime
     * @Description: 获取创建时间
     * @author caoxin
     * @date 2018/1/16 18:26
     * @return java.util.Date
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * @Title: setCreationTime
     * @Description: 设置创建时间
     * @author caoxin
     * @date 2018/1/16 18:26
     * @param creationTime
     */
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
    /**
     * @Title: getFinishTime
     * @Description:
     * @author caoxin
     * @date 2018/1/16
     * @return java.util.Date
     */
    public Date getFinishTime() {
        return finishTime;
    }
    /**
     * @Title: setFinishTime
     * @Description:
     * @author caoxin
     * @date 2018/1/16
     * @param  finishTime
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
    /**
     * @Title: getOrder
     * @Description: 获取订单信息
     * @author caoxin
     * @date 2018/1/26
     * @return domain.Order
     */

    public Order getOrder() {
        return order;
    }
    /**
     * @Title: setOrder
     * @Description: 设置订单信息
     * @author caoxin
     * @date 2018/1/26
     * @param order
     */

    public void setOrder(Order order) {
        this.order = order;
    }
    /**
     * @Title: getOrderId
     * @Description: 获取订单信息id
     * @author caoxin
     * @date 2018/1/26
     * @return java.lang.Long
     */

    public Long getOrderId() {
        return orderId;
    }
    /**
     * @Title: setOrderId
     * @Description: 设置订单信息id
     * @author caoxin
     * @date 2018/1/26
     * @param orderId
     */

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Byte getComment() {
        return comment;
    }

    public void setComment(Byte comment) {
        this.comment = comment;
    }

    public Byte getExpressStatus() {
        return expressStatus;
    }

    public void setExpressStatus(Byte expressStatus) {
        this.expressStatus = expressStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "amount=" + amount +
                ", id=" + id +
                ", buyerId=" + buyerId +
                ", sellerId=" + sellerId +
                ", goodsId=" + goodsId +
                ", orderId=" + orderId +
                ", isSuccess=" + isSuccess +
                ", orderStatus=" + orderStatus +
                ", orderNumber='" + orderNumber + '\'' +
                ", creationTime=" + creationTime +
                ", finishTime=" + finishTime +
                ", message='" + message + '\'' +
                ", expressStatus=" + expressStatus +
                ", comment=" + comment +
                '}';
    }
}