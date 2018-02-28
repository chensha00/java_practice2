package com.zyht.dao;


import com.zyht.domain.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author denghongbo
 * @InterfaceName AccountDao
 * @Description 账户表Dao类接口
 * @date 2018/1/20
 */
public interface AccountDao {
    /**
     * @Title: cancelUser
     * @Description: 注销账户——单个删除数据
     * @author DengHongbo
     * @date 2018/2/28 14:08
     * @param account
     * @return java.lang.Integer
     * @throw
     */
    public int cancelUser(Account account);
    /**
     * @Title: registerUser
     * @Description: 注册账户——插入数据
     * @author DengHongbo
     * @date 2018/2/28 14:07
     * @param account
     * @return com.zyht.domain.Account
     * @throw
     */
    public int registerUser(Account account);
   /**
    * @Title: modifyUser
    * @Description: 修改账户数据
    * @author DengHongbo
    * @date 2018/2/28 14:06
    * @param account
    * @return int
    * @throw
    */
    public int modifyUser(Account account);
   /**
    * @Title: logInUser
    * @Description: 登录账户
    * @author DengHongbo
    * @date 2018/2/28 15:36
    * @param stringObjectMap
    * @return com.zyht.domain.Account
    * @throw
    */
    public Account logInUser(Map<String,Object> stringObjectMap);

    /**
     * @Title: queryAllAccount
     * @Description: 查询所有账户
     * @author DengHongbo
     * @date 2018/2/22 13:39
     * @return java.util.List<com.zyht.domain.Account>
     */
    public List<Account> queryAll();
    /**
     * @Title: queryOne
     * @Description: 根据ID查询单个用户
     * @author DengHongbo
     * @date 2018/2/26 10:10
     * @param id
     * @return com.zyht.domain.Account
     */
    public Account queryOne(Long id);
}
