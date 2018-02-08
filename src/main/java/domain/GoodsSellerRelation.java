package domain;/********************************************************************/
/**
 * @Project: java_practice
 * @Package domain
 * @author caoxin
 * @date 2018/1/25 13:02
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

/**
 * @author caoxin
 * @ClassName GoodsSellerRelation
 * @Description 商品卖家关系
 * @date 2018/1/25
 */
public class GoodsSellerRelation {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 卖家商品库存
     */
    private Double inventory;
    /**
     * 商品出售单价
     */
    private Double price;
    /**
     * 商品信息外键
     */
    private Long goodsId;
    /**
     * 商品信息外键(实体)
     */
    private Goods goods;
    /**
     * 发布商铺,卖家信息外键
     */
    private Long sellerId;
    /**
     * 发布商铺,卖家信息外键(实体)
     */
    private Seller seller;

    /**
     * @Title: Goods
     * @Goodsiption: 空参数构造函数
     * @author caoxin
     * @date 2018/1/16
     */
    public GoodsSellerRelation() {
    }
    /**
      * @Title: GoodsSellerRelation
      * @Description:  设置商品与出售卖家及商品库存及出售单价构造器
      * @author caoxin
      * @date 2018/1/25
      * @param goodsId, id, inventory, price, sellerId
      */

    public GoodsSellerRelation(Long goodsId, Long id, Double inventory, Double price,  Long sellerId,String name) {
        this.goodsId = goodsId;
        this.id = id;
        this.inventory = inventory;
        this.price = price;
        this.sellerId = sellerId;
        this.name=name;
    }

    @Override
    public String toString() {
        return "GoodsSellerRelation{" +
                "goods=" + goods +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", inventory=" + inventory +
                ", price=" + price +
                ", goodsId=" + goodsId +
                ", sellerId=" + sellerId +
                ", seller=" + seller +
                '}';
    }

    /**
     * @Title: getGoods
     * @Description: 获取商品信息
     * @author caoxin
     * @date 2018/1/25
     * @return domain.Goods
     */

    public Goods getGoods() {
        return goods;
    }
    /**
     * @Title: setGoods
     * @Description: 设置商品信息
     * @author caoxin
     * @date 2018/1/25
     * @param goods
     */

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
    /**
     * @Title: getGoodsId
     * @Description: 获取商品信息id
     * @author caoxin
     * @date 2018/1/25
     * @return java.lang.Long
     */

    public Long getGoodsId() {
        return goodsId;
    }
    /**
     * @Title: setGoodsId
     * @Description: 设置商品信息id
     * @author caoxin
     * @date 2018/1/25
     * @param goodsId
     */

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    /**
     * @Title: getId
     * @Description: 获取主键ID
     * @author caoxin
     * @date 2018/1/25
     * @return java.lang.Long
     */
    public Long getId() {
        return id;
    }
    /**
     * @Title: setId
     * @Description: 设置主键ID
     * @author caoxin
     * @date 2018/1/25
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @Title: getInventory
     * @Description: 获取商家出售商品库存
     * @author caoxin
     * @date 2018/1/25
     * @return java.lang.Double
     */

    public Double getInventory() {
        return inventory;
    }
    /**
     * @Title: setInventory
     * @Description: 设置商家出售商品库存
     * @author caoxin
     * @date 2018/1/25
     * @param inventory
     */
    public void setInventory(Double inventory) {
        this.inventory = inventory;
    }
    /**
     * @Title: getPrice
     * @Description: 获取商家出售商品单价
     * @author caoxin
     * @date 2018/1/25
     * @return java.lang.Double
     */

    public Double getPrice() {
        return price;
    }
    /**
     * @Title: setPrice
     * @Description: 设置商家出售商品单价
     * @author caoxin
     * @date 2018/1/25
     * @param price
     */

    public void setPrice(Double price) {
        this.price = price;
    }
    /**
     * @Title: getSeller
     * @Description: 获取卖家信息
     * @author caoxin
     * @date 2018/1/25
     * @return domain.Seller
     */

    public Seller getSeller() {
        return seller;
    }
    /**
     * @Title: setSeller
     * @Description: 设置卖家信息
     * @author caoxin
     * @date 2018/1/25
     * @param seller
     */

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
    /**
     * @Title: getSellerId
     * @Description: 获取卖家id
     * @author caoxin
     * @date 2018/1/25
     * @return java.lang.Long
     */

    public Long getSellerId() {
        return sellerId;
    }
    /**
     * @Title: setSellerId
     * @Description: 设置卖家id
     * @author caoxin
     * @date 2018/1/25
     * @param sellerId
     */

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
    /**
     * @Title: getName
     * @Description: 获取商品名称
     * @author caoxin
     * @date 2018/1/27
     * @return java.lang.String
     */
    public String getName() {
        return name;
    }
    /**
     * @Title: setName
     * @Description: 设置商品名称
     * @author caoxin
     * @date 2018/1/27
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }
}
