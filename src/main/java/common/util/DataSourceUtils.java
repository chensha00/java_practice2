package common.util;/********************************************************************
 /**
 * @Project: test_maven
 * @Package jdbc
 * @author guohongjin
 * @date 2017/8/28 22:13
 * @Copyright: 2017 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * @author guohongjin
 * @ClassName DataSourceUtils
 * @Description 类描述
 * @date 2017/8/28
 */
public class DataSourceUtils implements  DataSource {
    //1.创建1个容器用于存储Connection对象
    private static LinkedList<Connection> pool = new LinkedList<Connection>();

    //2.创建5个连接放到容器中去
    static{
        for (int i = 0; i < 5; i++) {
            Connection conn = JdbcUtils.getConnection();
            //放入池子中connection对象已经经过改造了
            ConnectionUtils myconn = new ConnectionUtils(conn, pool);
            pool.add(myconn);
        }
    }

    /**
     * 重写获取连接的方法
     */
    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        //3.使用前先判断
        if(pool.size()==0){
            //4.池子里面没有，我们再创建一些
            for (int i = 0; i < 5; i++) {
                conn = JdbcUtils.getConnection();
                //放入池子中connection对象已经经过改造了
                ConnectionUtils myconn = new ConnectionUtils(conn, pool);
                pool.add(myconn);
            }
        }
        //5.从池子里面获取一个连接对象Connection
        conn = pool.remove(0);
        return conn;
    }

    /**
     * 归还连接对象到连接池中去
     */
    public void backConnection(Connection conn){
        pool.add(conn);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }
}