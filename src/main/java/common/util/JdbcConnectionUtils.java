package common.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author renxu
 * @ClassName JdbcConnectionUtils
 * @Description 数据库连接工具类
 * @date 2018/1/20
 */
public class JdbcConnectionUtils {
    static { //静态调用，保证加载驱动只执行一次
        //1加载驱动程序
        try {
            Class .forName("com.mysql.cj.jdbc.Driver"); //双引号里不能错
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection con = null;
        //2定义数据库的URL
        String   url = "jdbc:mysql://127.0.0.1:3306/service?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
        //3.建立与数据库的连接 ，自己解决异常
        try {
            con = DriverManager.getConnection(url, "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con ;
    }
}