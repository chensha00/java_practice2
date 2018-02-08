package com.zyht.domain;/********************************************************************/
/**
 * @Project: java_practice
 * @Package domain
 * @author caoxin
 * @date 2018/1/20 13:16
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author caoxin
 * @ClassName Account
 * @Description 账户信息实体
 * @date 2018/1/20
 */
@Component
public class Account  {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 账户
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 是否冻结：1-true-是 0-false-否
     */
    private Boolean isFrozen;
    /**
     * 是否注销：1-true-是 0-false-否
     */
    private Boolean isCanceled;
    /**
     * 是否删除：1-true-是 0-false-否
     */
    private Boolean isDelete;
    /**
     * 买家信息外键
     */
    private Long buyerId;
    /**
     * 买家信息外键(对象)
     */
    private Buyer buyer;
    /**
     * 卖家信息外键
     */
    private Long sellerId;
    /**
     * 卖家信息外键（对象）
     */
    private Seller seller;
    /**
     * 注册添加时间 yyyy-MM-dd HH:mm:ss
     */
    private Date addTime;
    /**
     * 账户修改时间 yyyy-MM-dd HH:mm:ss
     */
    private Date updateTime;
    /**
      * @Title: Account
      * @Description: 空参构造器
      * @author caoxin
      * @date 2018/1/20
      */
    public Account() {
    }
    /**
     * @Title: Account
     * @Description: 构造函数
     * @author DengHongbo
     * @date 2018/1/22 10:04
     * @param id, account, password, isFrozen, isCanceled, isDelete, buyerId, sellerId, addTime, updateTime
     * @return
     */
    public Account(Long id, String account, String password, Boolean isFrozen, Boolean isCanceled, Boolean isDelete, Long buyerId, Long sellerId, Date addTime, Date updateTime) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.isFrozen = isFrozen;
        this.isCanceled = isCanceled;
        this.isDelete = isDelete;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    /**
     * @Title: getAccount
     * @Description: 获取账号
     * @author caoxin
     * @date 2018/1/20
     * @return java.lang.String
     */
    public String getAccount() {
        return account;
    }
    /**
     * @Title: setAccount
     * @Description: 设置账号
     * @author caoxin
     * @date 2018/1/20
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }
    /**
     * @Title: getAddTime
     * @Description: 获取添加时间
     * @author caoxin
     * @date 2018/1/20
     * @return java.util.Date
     */
    public Date getAddTime() {
        return addTime;
    }
    /**
     * @Title: setAddTime
     * @Description: 设置添加时间
     * @author caoxin
     * @date 2018/1/20
     * @param addTime
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    /**
     * @Title: getBuyerId
     * @Description: 获取买家信息
     * @author caoxin
     * @date 2018/1/20
     * @return Long
     */
    public Long getBuyerId() {
        return buyerId;
    }
    /**
     * @Title: setBuyerId
     * @Description: 设置买家信息
     * @author caoxin
     * @date 2018/1/20
     * @param buyerId
     */
    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }
    /**
     * @Title: getId
     * @Description: 获取主键
     * @author caoxin
     * @date 2018/1/20
     * @return java.lang.Long
     */
    public Long getId() {
        return id;
    }
    /**
     * @Title: setId
     * @Description: 设置主键
     * @author caoxin
     * @date 2018/1/20
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @Title: getIsCanceled
     * @Description: 获取账户注销状态
     * @author caoxin
     * @date 2018/1/20
     * @return java.lang.Boolean
     */
    public Boolean getIsCanceled() {
        return isCanceled;
    }
    /**
     * @Title: setIsCanceled
     * @Description: 设置账户注销状态
     * @author caoxin
     * @date 2018/1/20
     * @param isCanceled
     */
    public void setIsCanceled(Boolean isCanceled) {
        this.isCanceled = isCanceled;
    }
    /**
     * @Title: getIsDelete
     * @Description: 获取账户删除状态
     * @author caoxin
     * @date 2018/1/20
     * @return java.lang.Boolean
     */
    public Boolean getIsDelete() {
        return isDelete;
    }
    /**
     * @Title: setIsDelete
     * @Description: 设置账户删除状态
     * @author caoxin
     * @date 2018/1/20
     * @param isDelete
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
    /**
     * @Title: getIsFrozen
     * @Description: 获取账户冻结状态
     * @author caoxin
     * @date 2018/1/20
     * @return java.lang.Boolean
     */
    public Boolean getIsFrozen() {
        return isFrozen;
    }
    /**
     * @Title: setIsFrozen
     * @Description: 设置账户冻结状态
     * @author caoxin
     * @date 2018/1/20
     * @param isFrozen
     */
    public void setIsFrozen(Boolean isFrozen) {
        this.isFrozen = isFrozen;
    }
    /**
     * @Title: getPassword
     * @Description: 获取密码
     * @author caoxin
     * @date 2018/1/20
     * @return java.lang.String
     */
    public String getPassword() {
        return password;
    }
    /**
     * @Title: setPassword
     * @Description: 设置密码
     * @author caoxin
     * @date 2018/1/20
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @Title: getSellerId
     * @Description: 获取卖家信息
     * @author caoxin
     * @date 2018/1/20
     * @return Long
     */
    public Long getSellerId() {
        return sellerId;
    }
    /**
     * @Title: setSellerId
     * @Description: 设置卖家信息
     * @author caoxin
     * @date 2018/1/20
     * @param sellerId
     */
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
    /**
     * @Title: getUpdateTime
     * @Description: 获取更新账户时间
     * @author caoxin
     * @date 2018/1/20
     * @return java.util.Date
     */

    public Date getUpdateTime() {
        return updateTime;
    }
    /**
     * @Title: setUpdateTime
     * @Description: 设置账户更新时间
     * @author caoxin
     * @date 2018/1/20
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
     * @Title: getBuyer
     * @Description: 获取买家信息
     * @author caoxin
     * @date 2018/1/20
     * @return com.zyht.domain.Buyer
     */
    public Buyer getBuyer() {
        return buyer;
    }
    /**
     * @Title: setBuyer
     * @Description: 设置买家信息
     * @author caoxin
     * @date 2018/1/20
     * @param buyer
     */
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
    /**
     * @Title: getSeller
     * @Description: 获取卖家信息
     * @author caoxin
     * @date 2018/1/20
     * @return com.zyht.domain.Seller
     */
    public Seller getSeller() {
        return seller;
    }
    /**
     * @Title: setSeller
     * @Description: 设置卖家信息
     * @author caoxin
     * @date 2018/1/20
     * @return com.zyht.domain.Seller
     */
    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account='" + account + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                ", isFrozen=" + isFrozen +
                ", isCanceled=" + isCanceled +
                ", isDelete=" + isDelete +
                ", buyerId=" + buyerId +
                ", sellerId=" + sellerId +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
