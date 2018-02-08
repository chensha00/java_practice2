package com.zyht.dao;


import com.zyht.domain.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author denghongbo
 * @InterfaceName AccountDao
 * @Description 账户表Dao类接口
 * @date 2018/1/20
 */
public interface AccountDao {
    /**
     * @Title: deleteAccountById
     * @Description: 注销账户——单个删除数据
     * @author DengHongbo
     * @date 2018/1/19 16:27
     * @param account, connection, preparedStatement
     * @return Integer
     * @throw SQLException
     */
    public Integer cancelUser(Account account, Connection connection, PreparedStatement preparedStatement) throws SQLException;
    /**
     * @Title: insertAccount
     * @Description: 注册账户——插入数据
     * @author DengHongbo
     * @date 2018/1/20 12:32
     * @param account, connection, preparedStatement
     * @return Account
     * @throw SQLException
     */
    public Account registerUser(Account account, Connection connection, PreparedStatement preparedStatement) throws SQLException;
    /**
     * @Title: UpdateAccount
     * @Description: 修改账户——修改数据
     * @author DengHongbo
     * @date 2018/1/19 18:54
     * @param account, connection, preparedStatement
     * @return java.domain.Account
     * @throw SQLException
     */
    public Account modifyUser(Account account, Connection connection, PreparedStatement preparedStatement)throws SQLException;
    /**
     * @Title: logInAccount
     * @Description: 登录账户——查询数据
     * @author DengHongbo
     * @date 2018/1/20 15:58
     * @param account, connection, preparedStatement
     * @return boolean
     * @throw SQLException
     */
    public Account logInUser(Account account, Connection connection, PreparedStatement preparedStatement)throws SQLException;
}
