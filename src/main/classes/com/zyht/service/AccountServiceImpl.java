package com.zyht.service;

import com.zyht.common.util.JdbcConnectionUtils;
import com.zyht.common.util.JdbcUtils;
import com.zyht.dao.AccountDao;
import com.zyht.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author renxu
 * @ClassName AccountServiceImpI
 * @Description 账户服务实现类
 * @date 2018/1/20
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    //定义一个Dao层的对象
    @Autowired
    private AccountDao accountDao;
    /**
     * @Title: cancelUser
     * @Description: 账户注销
     * @author renxu
     * @date 2018/1/20
     * @param account 账户对象
     * @return  respond 显示给用户成功或失败
     */
    public int cancelUser(Account account){
//        Connection connection = JdbcConnectionUtils.getConnection();
//        PreparedStatement preparedStatement = null;
//        String respond = null;//返回给用户的字符串
//        Integer result=null;
//        try {
//            //建立事物
//            connection.setAutoCommit(false);
//            // Integer 受影响的行
//            result = accountDao.cancelUser(account, connection, preparedStatement);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            connection.rollback();//出现异常，事物回滚
//        }finally {
//            try {
//                connection.commit();//提交事务
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            JdbcUtils.release(connection,preparedStatement);//关闭连接
//            return result;//返回结果给用户
//        }
        return accountDao.cancelUser(account);
    }
    /**
     * @Title: registerUser
     * @Description: 账户注册
     * @author renxu
     * @date 2018/1/20
     * @param account 账户对象
     * @return  result 返回添加结果对象
     */
    public int registerUser(Account account){
//        Connection connection = JdbcUtils.getConnection();
//        PreparedStatement preparedStatement = null;
//        Account result=null;
//        try {
//            //建立事物
//            connection.setAutoCommit(false);
//            // 将需要添加的账户信息返回给结果
//            result = accountDao.registerUser(account, connection, preparedStatement);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            connection.rollback();//出现异常，事物回滚
//        }finally {
//            try {
//                connection.commit();//提交事务
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            JdbcUtils.release(connection,preparedStatement);//关闭连接
//            return result;//返回结果给用户
//        }
        return accountDao.registerUser(account);
    }
    /**
     * @Title: modifyUser
     * @Description: 账户修改
     * @author renxu
     * @date 2018/1/20
     * @param account 账户对象
     * @return respond 显示给用户成功或失败
     */
    public int modifyUser(Account account){
//        Connection connection = JdbcConnectionUtils.getConnection();
//        PreparedStatement preparedStatement = null;
//        Account result=null;
//        try {
//            //建立事物
//            connection.setAutoCommit(false);
//            // 修改结果返回给result
//            result = accountDao.modifyUser(account, connection, preparedStatement);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            connection.rollback();//出现异常，事物回滚
//        }finally {
//            try {
//                connection.commit();//提交事务
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            JdbcUtils.release(connection,preparedStatement);//关闭连接
//            return result;//返回结果给用户
//        }
        return accountDao.modifyUser(account);
    }
 /**
  * @Title: logInUser
  * @Description: 登录账户
  * @author DengHongbo
  * @date 2018/2/28 15:45
  * @param stringObjectMap
  * @return com.zyht.domain.Account
  */
    public Account logInUser(Map<String,Object> stringObjectMap){
//        Connection connection = JdbcUtils.getConnection();
//        PreparedStatement preparedStatement = null;
//        Account result=null;
//        try {
//            // 登录的用户赋值给结果
//            result = accountDao.logInUser(account, connection, preparedStatement);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            JdbcUtils.release(connection,preparedStatement);//关闭连接
//        }
//        return result;
        return accountDao.logInUser(stringObjectMap);
    }
    @Override
    public List<Account> selectAll() {
        return accountDao.queryAll() ;
    }
    /**
     * @Title: selectOne
     * @Description: 查询单个用户
     * @author DengHongbo
     * @date 2018/2/26 10:18
     * @param id
     * @return com.zyht.domain.Account
     */
    @Override
    public Account selectOne(Long id) {
        return accountDao.queryOne(id);
    }
}
