package domain;
import java.util.Date;

/**
 * @author caoxin
 * @ClassName Goods
 * @Package domain
 * @Description 商品信息实体
 * @date 2018/1/16
 */
public class Goods {
    /**
     * 主键ID
     */
    private Long id;
    /**
     *商品名
     */
    private String name;
    /**
     * 商品编号
     */
    private String number;
    /**
     * 类型
     */
    private String type;
    /**
     * 单价
     */
    private Double price;
    /**
     * 生产地
     */
    private String addr;
    /**
     * 生产日期 yyyy-MM-dd HH:mm:ss
     */
    private Date makeTime;
    /**
     * 过期日期 yyyy-MM-dd
     */
    private Date expirationTime;

    /**
     * 商品计量单位：千克，件，台……
     */
    private String unit;
    /**
     * @Title: Goods
     * @Goodsiption: 空参数构造函数
     * @author caoxin
     * @date 2018/1/16
     */

    public Goods() {
    }

    /**
     * @Title: Goods
     * @Description: 构造函数
     * @author caoxin
     * @date 2018/1/16
     * @param name, number, type, price, addr, makeTime, expiration, sellerInfoId, unit
     */

    public Goods(Long id, String name, String number, String type, Double price, String addr, Date makeTime, Date expirationTime, String unit) {
        this.id=id;
        this.name = name;
        this.number = number;
        this.type = type;
        this.price = price;
        this.addr = addr;
        this.makeTime = makeTime;
        this.expirationTime = expirationTime;
        this.unit=unit;
    }
    /**
     * @Title: getId
     * @Description: 获取主键
     * @author caoxin
     * @date 2018/1/16 18:39
     * @return java.lang.Long
     */
    public Long getId() {
        return id;
    }
    /**
     * @Title: setId
     * @Description: 设置主键
     * @author caoxin
     * @date 2018/1/16 18:39
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @Title: getName
     * @Description: 获取商品名称
     * @author caoxin
     * @date 2018/1/16 18:41
     * @return java.lang.String
     */
    public String getName() {
        return name;
    }
    /**
     * @Title: setName
     * @Description: 设置商品名称
     * @author caoxin
     * @date 2018/1/16 18:39
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @Title: getNumber
     * @Description: 获取商品编号
     * @author caoxin
     * @date 2018/1/16 18:39
     * @return java.lang.String
     */
    public String getNumber() {
        return number;
    }
    /**
     * @Title: setNumber
     * @Description: 设置商品编号
     * @author caoxin
     * @date 2018/1/16 18:40
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }
    /**
     * @Title: getType
     * @Description: 获取商品类型
     * @author caoxin
     * @date 2018/1/16 18:40
     * @return java.lang.String
     */
    public String getType() {
        return type;
    }
    /**
     * @Title: setType
     * @Description: 设置商品类型
     * @author caoxin
     * @date 2018/1/16 18:40
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @Title: getPrice
     * @Description: 获取价格
     * @author caoxin
     * @date 2018/1/16 18:40
     * @return java.lang.Double
     */
    public Double getPrice() {
        return price;
    }
    /**
     * @Title: setPrice
     * @Description: 设置价格
     * @author caoxin
     * @date 2018/1/16 18:40
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }
    /**
     * @Title: getAddr
     * @Description: 获取生产地
     * @author caoxin
     * @date 2018/1/16 18:40
     * @return java.lang.String
     */
    public String getAddr() {
        return addr;
    }
    /**
     * @Title: setAddr
     * @Description: 设置生产地
     * @author caoxin
     * @date 2018/1/16 18:40
     * @param addr
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }
    /**
     * @Title: getMakeTime
     * @Description: 获取生产日期
     * @author caoxin
     * @date 2018/1/16 18:40
     * @return java.util.Date
     */
    public Date getMakeTime() {
        return makeTime;
    }
    /**
     * @Title: setMakeTime
     * @Description: 设置生产日期
     * @author caoxin
     * @date 2018/1/16 18:40
     * @param makeTime
     */
    public void setMakeTime(Date makeTime) {
        this.makeTime = makeTime;
    }
    /**
     * @Title: getExpirationTime
     * @Description: 获取过期日期
     * @author caoxin
     * @date 2018/1/16 18:40
     * @return java.util.Date
     */
    public Date getExpirationTime() {
        return expirationTime;
    }
    /**
     * @Title: setExpiration
     * @Description: 设置过期日期
     * @author caoxin
     * @date 2018/1/16 18:40
     * @param expirationTime
     */
    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }


    /**
     * @Title: getUnit
     * @Description: 获取商品计量单位
     * @author caoxin
     * @date 2018/1/16
     * @return java.lang.String
     */

    public String getUnit() {
        return unit;
    }
    /**
     * @Title: setUnit
     * @Description: 设置商品计量单位
     * @author caoxin
     * @date 2018/1/16
     * @param unit
     */

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "addr='" + addr + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", makeTime=" + makeTime +
                ", expirationTime=" + expirationTime +
                ", unit='" + unit + '\'' +
                '}';
    }
}