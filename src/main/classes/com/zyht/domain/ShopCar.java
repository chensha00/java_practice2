package com.zyht.domain;

/**
 * ShopCar
 *
 * @author Administrator
 * @Description
 * @Date 2018/2/26
 */
public class ShopCar {
    private Long id;
    private Long goodsId;
    private Double buyNum;
    private Double buyMoney;
    private String goodsName;

    public ShopCar(Long id, Long goodsId, Double buyNum, Double buyMoney, String goodsName) {
        this.id = id;
        this.goodsId = goodsId;
        this.buyNum = buyNum;
        this.buyMoney = buyMoney;
        this.goodsName = goodsName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Double getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Double buyNum) {
        this.buyNum = buyNum;
    }

    public Double getBuyMoney() {
        return buyMoney;
    }

    public void setBuyMoney(Double buyMoney) {
        this.buyMoney = buyMoney;
    }
}
