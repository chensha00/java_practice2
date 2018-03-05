package com.zyht.base;/********************************************************************/
/**
 * @Project: jspweb
 * @Package com.zyht.base
 * @author caoxin
 * @date 2018/3/5 10:25
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

/**
 * @author caoxin
 * @InterfaceName BaseAction
 * @Description BaseAction接口
 * @date 2018/3/5
 */
public interface BaseAction {
    /**
     * 登录界面
     */
    public static final String LOG_IN="logIn";
    /**
     * 注册界面
     */
    public static final String ENROLL="enroll";
    /**
     * 主界面
     */
    public static final String HOME="home";
    /**
     * 管理员界面
     */
    public static final String MANAGER="manager";
    /**
     * 订单详情界面
     */
    public static final String DETAIL="detail";
    /**
     * 订单信息界面
     */
    public static final String ORDER_INFO="orderInfo";
    /**
     * 订单流水界面
     */
    public static final String STATEMENT="statement";
    /**
     * 跳转界面
     */
    public static final String JUMP="jump";
    /**
     * 购物车界面
     */
    public static final String SHOP_CAR="shopCar";
    /**
     * 支付界面
     */
    public static final String PAY="pay";
    /**
     * 商品界面
     */
    public static final String RELATION="relation";
    /**
     * 卖家商铺管理界面
     */
    public static final String SELLER="seller";
    /**
     * 收货地址界面
     */
    public static final String ADDR="addr";

}