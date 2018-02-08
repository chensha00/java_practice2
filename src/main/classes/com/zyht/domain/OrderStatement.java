package com.zyht.domain;/********************************************************************/
/**
 * @Project: java_practice
 * @Package domain
 * @author caoxin
 * @date 2018/1/20 13:04
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import java.util.Date;

/**
 * @author caoxin
 * @ClassName OrderStatement
 * @Description 订单流水实体
 * @date 2018/1/20
 */
public class OrderStatement {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 商品信息外键
     */
    private Long buyerId;
    /**
     * 商品信息外键（实体）
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
     * 订单详情外键
     */
    private Long orderDetailId;
    /**
     * 订单详情外键（实体）
     */
    private OrderDetail orderDetail;
    /**
     * 订单详情金额
     */
    private Double amount;
    /**
     * 流水编号
     */
    private String number;
    /**
     * 订单完成日期 yyyy-MM-dd HH：mm：ss
     */
    private Date finishTime;
    /**
     * @Title: OrderDetail
     * @Description: 空参数构造函数
     * @author caoxin
     * @date 2018/1/16
     */

    public OrderStatement() {
    }

    /**
     * @Title: OrderDetail
     * @Description: 构造函数
     * @author caoxin
     * @date 2018/1/16
     * @param id, buyerId, sellerId, goodsId, amount, isSuccess, orderStatus, orderNumber, creationDate,finishDate
     */

    public OrderStatement(Long id, Long buyerId, Long sellerId,Long orderDetailId ,Double amount,String number, Date finishTime) {
        this.id=id;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.orderDetailId = orderDetailId;
        this.amount = amount;
        this.number = number;
        this.finishTime=finishTime;
    }
    /**
     * @Title: OrderDetail
     * @Description: 构造函数
     * @author caoxin
     * @date 2018/1/16
     * @param  buyerId, sellerId, goodsId, amount, isSuccess, orderStatus, orderNumber, creationDate,finishDate
     */
    public OrderStatement( Long buyerId, Long sellerId,Long orderDetailId ,Double amount,String number, Date finishTime) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.orderDetailId = orderDetailId;
        this.amount = amount;
        this.number = number;
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
     * @Title: getOrderDetailId
     * @Description: 获取商品信息
     * @author caoxin
     * @date 2018/1/16 18:25
     * @return java.lang.Long
     */
    public Long getOrderDetailId() {
        return orderDetailId;
    }
    /**
     * @Title: setOrderDetailId
     * @Description: 设置商品信息
     * @author caoxin
     * @date 2018/1/16 18:25
     * @param orderDetailId
     */
    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
    /**
     * @Title: getOrderDetail
     * @Description: 获取商品信息
     * @author caoxin
     * @date 2018/1/16 18:25
     * @return OrderDetail
     */
    public OrderDetail getOrderDetail() {
        return orderDetail;
    }
    /**
     * @Title: setOrderDetail
     * @Description: 设置商品信息
     * @author caoxin
     * @date 2018/1/16 18:25
     * @param orderDetail
     */
    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
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
     * @Title: getNumber
     * @Description: 获取流水号
     * @author caoxin
     * @date 2018/1/16 18:26
     * @return java.lang.String
     */
    public String getNumber() {
        return number;
    }
    /**
     * @Title: setNumber
     * @Description: 设置流水号
     * @author caoxin
     * @date 2018/1/16 18:26
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }
    /**
     * @Title: getFinishTime
     * @Description: 获取交易完成时间
     * @author caoxin
     * @date 2018/1/16
     * @return java.util.Date
     */
    public Date getFinishTime() {
        return finishTime;
    }
    /**
     * @Title: setFinishTime
     * @Description: 设置交易完成时间
     * @author caoxin
     * @date 2018/1/16
     * @param  finishTime
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "OrderStatement{" +
                "amount=" + amount +
                ", id=" + id +
                ", buyerId=" + buyerId +
                ", sellerId=" + sellerId +
                ", orderDetailId=" + orderDetailId +
                ", number='" + number + '\'' +
                ", finishTime=" + finishTime +
                '}';
    }
}
