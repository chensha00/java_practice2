package com.zyht.domain;

/**
 * ShopCar
 *
 * @author Administrator
 * @Description
 * @Date 2018/2/26
 */
public class ShopCar {
    /**
     * 主键
     */
    private Long id;
    /**
     * 商品主键
     */
    private Long goodsId;
    /**
     * 关联外键实体goods
     */
    private Goods goods;
    /**
     * 购买数量
     */
    private Double buyNum;
    /**
     * 商品价格
     */
    private Double goodsPrice;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 买家主键
     */
    private Long buyerId;
    /**
     * 关联外键实体buyer
     */
    private Buyer buyer;
    /**
     * @Title: ShopCar
     * @Description:
     * @author caoxin
     * @date 2018/2/26
     * @param id,goodsId, buyNum, goodsPrice, goodsName, buyerId，goods，buyer
     */
    public ShopCar(Buyer buyer, Long buyerId, Long id, Long goodsId, Goods goods, Double buyNum, Double goodsPrice, String goodsName) {
        this.buyer = buyer;
        this.buyerId = buyerId;
        this.id = id;
        this.goodsId = goodsId;
        this.goods = goods;
        this.buyNum = buyNum;
        this.goodsPrice = goodsPrice;
        this.goodsName = goodsName;
    }

    /**
     * @Title: ShopCar
     * @Description: 空参数构造函数
     * @author wangchuan
     * @date 2018/2/26
     */
    public ShopCar() {
    }
    /**
     * @Title: getId
     * @Description: 获取id
     * @author wangchuan
     * @date 2018/2/26
     */
    public Long getId() {
        return id;
    }
    /**
     * @Title: setId
     * @Description: 设置id
     * @author wangchuan
     * @date 2018/2/26
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @Title: getGoodsId
     * @Description: 获取goodsId
     * @author wangchuan
     * @date 2018/2/26
     */
    public Long getGoodsId() {
        return goodsId;
    }
    /**
     * @Title: setGoodsId
     * @Description: 设置goodsId
     * @author wangchuan
     * @date 2018/2/26
     * @param goodsId
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    /**
     * @Title: getBuyNum
     * @Description: 获取buyNum
     * @author wangchuan
     * @date 2018/2/26
     */
    public Double getBuyNum() {
        return buyNum;
    }
    /**
     * @Title: setBuyNum
     * @Description: 设置buyNum
     * @author wangchuan
     * @date 2018/2/26
     * @param buyNum
     */
    public void setBuyNum(Double buyNum) {
        this.buyNum = buyNum;
    }
    /**
     * @Title: getGoodsPrice
     * @Description: 获取goodsPrice
     * @author wangchuan
     * @date 2018/2/26
     */
    public Double getGoodsPrice() {
        return goodsPrice;
    }
    /**
     * @Title: setGoodsPrice
     * @Description: 设置goodsPrice
     * @author wangchuan
     * @date 2018/2/26
     * @param goodsPrice
     */
    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
    /**
     * @Title: getGoodsName
     * @Description: 获取goodsName
     * @author wangchuan
     * @date 2018/2/26
     */
    public String getGoodsName() {
        return goodsName;
    }
    /**
     * @Title: setGoodsName
     * @Description: 设置goodsName
     * @author wangchuan
     * @date 2018/2/26
     * @param goodsName
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    /**
     * @Title: getBuyerId
     * @Description: 获取buyerId
     * @author wangchuan
     * @date 2018/2/26
     */
    public Long getBuyerId() {
        return buyerId;
    }
    /**
     * @Title: setBuyerId
     * @Description: 设置buyerId
     * @author wangchuan
     * @date 2018/2/26
     * @param buyerId
     */
    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }
    /**
     * @Title: getGoods
     * @Description: 获取goods
     * @author wangchuan
     * @date 2018/2/26
     */
    public Goods getGoods() {
        return goods;
    }
    /**
     * @Title: setGoods
     * @Description: 设置goods
     * @author wangchuan
     * @date 2018/2/26
     * @param goods
     */
    public void setGoods(Goods goods) {
        this.goods = goods;
    }
    /**
     * @Title: getBuyer
     * @Description: 获取buyer
     * @author wangchuan
     * @date 2018/2/26
     */
    public Buyer getBuyer() {
        return buyer;
    }
    /**
     * @Title: setBuyer
     * @Description: 设置buyer
     * @author wangchuan
     * @date 2018/2/26
     * @param buyer
     */
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
    /**
     * @Title: toString
     * @Description: toString方法
     * @author wangchuan
     * @date 2018/2/26
     */
    @Override
    public String toString() {
        return "ShopCar{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", goods=" + goods +
                ", buyNum=" + buyNum +
                ", goodsPrice=" + goodsPrice +
                ", goodsName='" + goodsName + '\'' +
                ", buyerId=" + buyerId +
                ", buyer=" + buyer +
                '}';
    }
}
