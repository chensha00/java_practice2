package com.zyht.common.util;/********************************************************************/
/**
 * @Project: Java
 * @Package com.zyht.homework
 * @author caoxin
 * @date 2018/1/13 16:00
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

/**
 * @author caoxin
 * @EnumName OrderState
 * @Description 订单状态：0--订单进行中，1--订单已完成 ，2--订单已作废
 * @date 2018/1/13
 */
public enum OrderState {

    // UNPAID 待付， PAID 支付成功 FINISH 已完成  ABORTION 支付失败
    UNPAID((byte)0,"待支付"),PAID((byte)1,"支付成功"),FINISH((byte)2,"已完成"),ABORTION((byte)3,"支付失败");
    /**
     * stateNum 状态代号
     */
    private byte stateNum;
    /**
     * stateStr 状态描述
     */
    private String stateStr;
    /**
     * @Title: OrderState
     * @Description: 构造函数
     * @author caoxin
     * @date 2018/1/13
     * @param  stateNum, stateStr
     * @return
     * @throw Exception
     */
    OrderState(byte stateNum, String stateStr) {
        this.stateNum = stateNum;
        this.stateStr = stateStr;
    }
    /**
     * @Title: getStateNum
     * @Description: 获取状态代号
     * @author caoxin
     * @date 2018/1/13
     * @return int
     */
    public byte getStateNum() {
        return stateNum;
    }

    /**
     * @Title: getStateStr
     * @Description: 获取状态描述
     * @author caoxin
     * @date 2018/1/13
     * @return String
     */
    public String getStateStr() {
        return stateStr;

    }
}
