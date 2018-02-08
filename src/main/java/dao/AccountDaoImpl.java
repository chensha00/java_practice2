package dao;


import domain.Account;

import java.sql.*;

/**
 * @author denghongbo
 * @ClassName AccountDaoImpl
 * @Package java.dao
 * @Description 账户表Dao类接口实现
 * @date 2018/1/20
 */
public class AccountDaoImpl implements AccountDao {
    /**
     * @Title: cancelAccount
     * @Description: 注销账户
     * @author DengHongbo
     * @date 2018/1/20 14:06
     * @param account, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer cancelUser(Account account, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement=connection.prepareStatement("DELETE FROM account WHERE ID=?");
        preparedStatement.setLong(1,account.getId());
        int rows=preparedStatement.executeUpdate();
        return rows;
    }
    /**
     * @Title: registerAccount
     * @Description: 注册账户
     * @author DengHongbo
     * @date 2018/1/20 14:06
     * @param account, connection, preparedStatement
     * @return Account
     * @throw SQLException
     */
    @Override
    public Integer registerUser(Account account, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO account(ID,ACCOUNT,PASSWORD,IS_FROZEN,IS_CANCELED,IS_DELETE,BUYER_ID,SELLER_ID,ADD_TIME,UPDATE_TIME) VALUES(DEFAULT ,?,?,?,?,?,?,?,?,?);");
        preparedStatement.setString(1,account.getAccount());
        preparedStatement.setString(2,account.getPassword());
        preparedStatement.setBoolean(3,account.getIsFrozen());
        preparedStatement.setBoolean(4,account.getIsCanceled());
        preparedStatement.setBoolean(5,account.getIsDelete());
        preparedStatement.setLong(6,account.getBuyerId());
        preparedStatement.setLong( 7,account.getSellerId());
        preparedStatement.setDate(8, new java.sql.Date(account.getAddTime().getTime()));
        preparedStatement.setDate(9, new java.sql.Date(account.getUpdateTime().getTime()));
        int rows = preparedStatement.executeUpdate();
        return rows;
    }
    /**
     * @Title: modifyAccount
     * @Description: 修改账户
     * @author DengHongbo
     * @date 2018/1/20 14:09
     * @param account, connection, preparedStatement
     * @return Account
     * @throw SQLException
     */
    @Override
    public Integer modifyUser(Account account, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE account SET ID=?,ACCOUNT=?,PASSWORD=?,IS_FROZEN=?,IS_CANCELED=?,IS_DELETE=?,BUYER_ID=?,SELLER_ID=?,ADD_TIME=?,UPDATE_TIME=? WHERE ID=?");
        preparedStatement.setLong(1,account.getId());
        preparedStatement.setString(2,account.getAccount());
        preparedStatement.setString(3,account.getPassword());
        preparedStatement.setBoolean(4,account.getIsFrozen());
        preparedStatement.setBoolean(5,account.getIsCanceled());
        preparedStatement.setBoolean(6,account.getIsDelete());
        preparedStatement.setLong(7,account.getBuyerId());
        preparedStatement.setLong(8,account.getSellerId());
        preparedStatement.setDate(9, new java.sql.Date(account.getAddTime().getTime()));
        preparedStatement.setDate(10, new java.sql.Date(account.getUpdateTime().getTime()));
        preparedStatement.setLong(11,account.getId());
        int rows = preparedStatement.executeUpdate();
        return rows;
    }
    /**
     * @Title: longinAccount
     * @Description: 登录账户
     * @author DengHongbo
     * @date 2018/1/20 14:10
     * @param connection, preparedStatement
     * @return Account
     * @throw SQLException
     */
    @Override
    public boolean logInUser(Account account, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = null;
        preparedStatement = connection.prepareStatement("SELECT ID,ACCOUNT,PASSWORD,IS_FROZEN,IS_CANCELED,IS_DELETE,BUYER_ID,SELLER_ID,ADD_TIME,UPDATE_TIME FROM account WHERE ID=? AND ACCOUNT=? AND PASSWORD=?");
        preparedStatement.setLong(1,account.getId());
        preparedStatement.setString(2,account.getAccount());
        preparedStatement.setString(3,account.getPassword());
        resultSet = preparedStatement.executeQuery();
        if(resultSet != null){
            return true;
        }else {
            return false;
        }
    }
}