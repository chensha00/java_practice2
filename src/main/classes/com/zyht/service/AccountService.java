package com.zyht.service;


import com.zyht.domain.Account;

import java.util.List;


/**
 * Created by HAN on 2018/1/20.
 */
public interface AccountService {
    /**
     * @Title: longinUser
     * @Description: 账户登录
     * @author renxu
     * @date 2018/1/20
     * @param account 账户对象
     * @return Boolean true-登录成功，false-登录失败
     */
    public Account logInUser(Account account);

    /**
     * @Title: registerUser
     * @Description: 账户注册
     * @author renxu
     * @date 2018/1/20
     * @param account 账户对象
     * @return Integer 1-注册成功 0-注册失败
     */
    public Account registerUser(Account account);
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
     * @Title: cancelUser
     * @Description: 账户注销
     * @author renxu
     * @date 2018/1/20
     * @param account 账户对象
     * @return Integer 1-注销成功 0-注销失败
     */
    public Integer cancelUser(Account account);
    /**
     * @Title: selectAll
     * @Description: 查询所有账户
     * @author DengHongbo
     * @date 2018/2/26 10:16
     * @return java.util.List<com.zyht.domain.Account>
     */
    public List<Account> selectAll();
    /**
     * @Title: selectOne
     * @Description: 查询单个账户
     * @author DengHongbo
     * @date 2018/2/26 10:17
     * @param id
     * @return com.zyht.domain.Account
     */
    public Account selectOne(Long id);
}
