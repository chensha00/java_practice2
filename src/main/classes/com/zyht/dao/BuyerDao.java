package com.zyht.dao;/********************************************************************/
/**
 * @Project: java_practice
 * @Package dao
 * @author caoxin
 * @date 2018/1/19 16:38
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.zyht.domain.Account;
import com.zyht.domain.Buyer;

import java.util.List;
import java.util.Map;

/**
 * @author caoxin
 * @InterfaceName BuyerDao
 * @Description 买家信息表访问接口
 * @date 2018/1/19
 */
public interface BuyerDao {
    /**
     * @Title: deleteBuyerById
     * @Description: 通过买家ID删除
     * @author caoxin
     * @date 2018/1/19
     * @param id
     * @return int
     */
    public int deleteBuyerById(Long id);

    /**
     * @Title: deleteBuyerByIds
     * @Description: 通过ID批量删除买家
     * @author caoxin
     * @date 2018/1/19
     * @param  ids
     * @return int
     */
    public int deleteBuyerByIds(Long[] ids);
    /**
     * @Title: updateBuyer
     * @Description: 添加或者修改买家信息
     * @author caoxin
     * @date 2018/1/19
     * @param buyer
     * @return int
     */

    public int updateBuyer(Buyer buyer);
    /**
     * @Title: insertOrUpdateBuyer
     * @Description: 添加或者修改买家信息
     * @author caoxin
     * @date 2018/1/19
     * @param buyer
     * @return int
     */
    public int insertBuyer(Buyer buyer);
    /**
     * @Title: queryBuyerById
     * @Description: 通过ID查询买家信息表
     * @author caoxin
     * @date 2018/1/19
     * @param id
     * @return domain.Buyer
     */

    public Buyer queryBuyerById(Long id);
    /**
     * @Title: queryBuyerAccountById
     * @Description: 通过ID查询买家信息表
     * @author caoxin
     * @date 2018/1/19
     * @param id
     * @return domain.Account
     */

    public Account queryBuyerAccountById(Long id);
    /**
     * @Title: queryBuyerByCondition
     * @Description: 通过条件查询买家信息
     * @author caoxin
     * @date 2018/1/19
     * @param stringBuyerMap
     * @return java.util.List<domain.Buyer>
     */
    public List<Buyer> queryBuyerByCondition(Map<String,Object> stringBuyerMap);
    /**
     * @Title: findBuyerByCondition
     * @Description: 通过条件批量查询买家信息
     * @author caoxin
     * @date 2018/1/19
     * @param stringBuyerMap
     * @return java.util.List<domain.Buyer>
     */
    public List<Buyer> queryBuyerByConditionPage(Map<String,Object> stringBuyerMap);
}
