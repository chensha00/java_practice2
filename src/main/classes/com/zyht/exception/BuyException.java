package com.zyht.exception;/********************************************************************/
/**
 * @Project: Java
 * @Package com.zyht.homework
 * @author caoxin
 * @date 2018/1/14 11:00
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

/**
 * @author caoxin
 * @ClassName BuyException
 * @Description 购买时异常描述
 * @date 2018/1/14
 */
public class BuyException extends RuntimeException{

    /**
     * @Title: BuyException
     * @Description: 空参数构造器
     * @author caoxin
     * @date 2018/1/14
     */

    BuyException(){

    }
    /**
     * @Title: BuyException
     * @Description: 包含异常信息的构造器
     * @author caoxin
     * @date 2018/2018/1/14
     * @param message
     */

    public BuyException(String message) {
        super(message);
    }
}
