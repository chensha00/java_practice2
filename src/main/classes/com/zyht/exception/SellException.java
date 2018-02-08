package com.zyht.exception;/********************************************************************/
/**
 * @Project: Java
 * @Package com.zyht.homework
 * @author caoxin
 * @date 2018/1/14 11:13
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

/**
 * @author caoxin
 * @ClassName SellException
 * @Description 出售异常
 * @date 2018/1/14
 */
public class SellException extends RuntimeException {
    /**
     * @Title: SellException
     * @Description: 空参数构造器
     * @author caoxin
     * @date 2018/1/14
     */

    public SellException() {
    }
    /**
     * @Title: SellException
     * @Description: 包含异常信息的构造器
     * @author caoxin
     * @date 2018/1/14
     * @param message
     */
    public SellException(String message) {
        super(message);
    }
}
