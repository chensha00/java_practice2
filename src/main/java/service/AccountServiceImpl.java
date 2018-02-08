package service;

import common.util.JdbcConnectionUtils;
import common.util.JdbcUtils;
import dao.AccountDao;
import dao.AccountDaoImpl;
import domain.Account;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author renxu
 * @ClassName AccountServiceImpI
 * @Description 类描述
 * @date 2018/1/20
 */
public class AccountServiceImpl implements AccountService {
    //定义一个Dao层的对象
    static AccountDao accountDao = new AccountDaoImpl();
    /**
     * @Title: longinUser
     * @Description: 账户登录
     * @author renxu
     * @date 2018/1/20
     * @param account 账户对象
     * @return respond 显示给用户成功或失败
     */
    public String logInUser(Account account){
        Connection connection = JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement = null;
        String respond = null;//返回给用户的字符串
        try {
            //建立事物
            connection.setAutoCommit(false);
           // Boolean true-登录成功，false-登录失败
            Boolean result = accountDao.logInUser(account, connection, preparedStatement);
            if(result){
                respond = "登录成功";
            }else {
                respond = "用户名或密码不匹配，登录失败！";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();//出现异常，事物回滚
        }finally {
            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.release(connection,preparedStatement);//关闭连接
            return respond;//返回结果给用户
        }
    }

    /**
     * @Title: registerUser
     * @Description: 账户注册
     * @author renxu
     * @date 2018/1/20
     * @param account 账户对象
     * @return  respond 显示给用户成功或失败
     */
    public String registerUser(Account account){
        Connection connection = JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement = null;
        String respond = null;//返回给用户的字符串
        try {
            //建立事物
            connection.setAutoCommit(false);
           // Integer 1-注册成功 0-注册失败
            Integer result = accountDao.registerUser(account, connection, preparedStatement);
            System.out.println(result);
            if(result == 0){
                respond = "注册失败！";
            }else {
                respond = "注册成功！";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();//出现异常，事物回滚
        }finally {
            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.release(connection,preparedStatement);//关闭连接
            return respond;//返回结果给用户
        }
    }
    /**
     * @Title: modifyUser
     * @Description: 账户修改
     * @author renxu
     * @date 2018/1/20
     * @param account 账户对象
     * @return respond 显示给用户成功或失败
     */
    public String modifyUser(Account account){
        Connection connection = JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement = null;
        String respond = null;//返回给用户的字符串
        try {
            //建立事物
            connection.setAutoCommit(false);
            // Integer 1-修改成功 0-修改失败
            Integer result = accountDao.modifyUser(account, connection, preparedStatement);
            if(result == 0){
                respond = "修改失败！";
            }else {
                respond = "修改成功！";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();//出现异常，事物回滚
        }finally {
            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.release(connection,preparedStatement);//关闭连接
            return respond;//返回结果给用户
        }
    }
    /**
     * @Title: cancelUser
     * @Description: 账户注销
     * @author renxu
     * @date 2018/1/20
     * @param account 账户对象
     * @return  respond 显示给用户成功或失败
     */
    public String cancelUser(Account account){
        Connection connection = JdbcConnectionUtils.getConnection();
        PreparedStatement preparedStatement = null;
        String respond = null;//返回给用户的字符串
        try {
            //建立事物
            connection.setAutoCommit(false);
            // Integer 1-注销成功 0-注销失败
            Integer result = accountDao.cancelUser(account, connection, preparedStatement);
            if(result == 0){
                respond = "注销失败！";
            }else {
                respond = "注销成功！";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();//出现异常，事物回滚
        }finally {
            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.release(connection,preparedStatement);//关闭连接
            return respond;//返回结果给用户
        }
    }
}
