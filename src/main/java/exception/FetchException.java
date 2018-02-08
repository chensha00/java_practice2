package exception;/********************************************************************/
/**
 * @Project: Java
 * @Package com.zyht.homework
 * @author caoxin
 * @date 2018/1/14 16:04
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

/**
 * @author caoxin
 * @ClassName FetchException
 * @Description 收货异常描述
 * @date 2018/1/14
 */
public class FetchException extends RuntimeException {

    /**
     * @Title: FetchException
     * @Description: 空参数构造器
     * @author caoxin
     * @date 2018/1/14
     */

    public FetchException() {
    }

    /**
     * @Title: FetchException
     * @Description: 收货异常信息构造器
     * @author caoxin
     * @date 2018/1/14
     * @param message
     */

    public FetchException(String message) {
        super(message);
    }
}
