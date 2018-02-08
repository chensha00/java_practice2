package domain;/********************************************************************/
/**
 * @Project: java_practice
 * @Package domain
 * @author caoxin
 * @date 2018/1/20 13:07
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import java.util.Date;

/**
 * @author caoxin
 * @ClassName Order
 * @Description 订单信息实体
 * @date 2018/1/20
 */
public class Order {
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
     * 订单总金额
     */
    private Double amount;
    /**
     * 是否交易成功，1-true-是，0-false-否
     */
    private Boolean isSuccess;
    /**
     * 订单状态，1--待支付,2--支付成功,3--已完成，4--支付失败，
     */
    private Byte orderStatus;
    /**
     * 订单编号
     */
    private String number;
    /**
     * 订单生成日期 yyyy-MM-dd HH：mm：ss
     */
    private Date creationTime;
    /**
     * 订单完成日期 yyyy-MM-dd HH：mm：ss
     */
    private Date finishTime;
    /**
     * @Title: Order
     * @Description: 空参数构造函数
     * @author caoxin
     * @date 2018/1/16
     */

    public Order() {
    }

    /**
     * @Title: Order
     * @Description: 构造函数
     * @author caoxin
     * @date 2018/1/16
     * @param id, buyerId, sellerId, goodsId, amount, isSuccess, orderStatus, orderNumber, creationDate,finishDate
     */

    public Order(Long id, Long buyerId, Double amount, Boolean isSuccess, Byte orderStatus, String number, Date creationTime,Date finishTime) {
        this.id=id;
        this.buyerId = buyerId;
        this.amount = amount;
        this.isSuccess = isSuccess;
        this.orderStatus = orderStatus;
        this.number = number;
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
     * @return java.lang.Long
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
    public void setBuyerId(Buyer buyer) {
        this.buyer = buyer;
    }
    /**
     * @Title: getAmount
     * @Description: 获取订单总金额
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
     * @Title: setIsSuccess
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
     * @Title: getNumber
     * @Description: 获取订单编号
     * @author caoxin
     * @date 2018/1/16 18:26
     * @return java.lang.String
     */
    public String getNumber() {
        return number;
    }
    /**
     * @Title: setNumber
     * @Description: 设置订单编号
     * @author caoxin
     * @date 2018/1/16 18:26
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
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

    @Override
    public String toString() {
        return "Order{" +
                "amount=" + amount +
                ", id=" + id +
                ", buyerId=" + buyerId +
                ", isSuccess=" + isSuccess +
                ", orderStatus=" + orderStatus +
                ", orderNumber='" + number + '\'' +
                ", creationTime=" + creationTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
