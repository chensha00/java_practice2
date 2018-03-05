package com.zyht.service;


import com.zyht.domain.Account;

import java.util.List;
import java.util.Map;


/**
 * Created by HAN on 2018/1/20.
 */
public interface AccountService {
    /**
     * @Title: cancelUser
     * @Description: 账户注销
     * @author renxu
     * @date 2018/1/20
     * @param account 账户对象
     * @return Integer 1-注销成功 0-注销失败
     */
    public int cancelUser(Account account);
    /**
     * @Title: registerUser
     * @Description: 账户注册
     * @author renxu
     * @date 2018/1/20
     * @param account 账户对象
     * @return Integer 1-注册成功 0-注册失败
     */
    public int registerUser(Account account);
    /**
     * @Title: modifyUser
     * @Description: 账户修改
     * @author renxu
     * @date 2018/1/20
     * @param account 账户对象
     * @return Integer 1-修改成功 0-修改失败
     */
    public int modifyUser(Account account);
  /**
   * @Title: logInUser
   * @Description: 账户登录
   * @author DengHongbo
   * @date 2018/3/5 10:00
   * @param stringObjectMap String表示表字段，Object表示值
   * @return com.zyht.domain.Account
   */
    public Account logInUser(Map<String,Object> stringObjectMap);
    /**
     * @Title: queryAll
     * @Description: 查询所有账户
     * @author DengHongbo
     * @date 2018/2/26 10:16
     * @return java.util.List<com.zyht.domain.Account>
     */
    public List<Account> queryAll();
    /**
     * @Title: queryOne
     * @Description: 查询单个账户
     * @author DengHongbo
     * @date 2018/2/26 10:17
     * @param id
     * @return com.zyht.domain.Account
     */
    public Account queryOne(Long id);
}
