package com.zyht.action;

import com.opensymphony.xwork2.ActionSupport;
import com.zyht.common.util.SpringContextUtil;
import com.zyht.dao.AccountDao;
import com.zyht.domain.Account;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author denghongbo
 * @ClassName ManagerAction
 * @Package com.zyht.action
 * @Description 管理员action
 * @date 2018/3/2
 */
@Action("manager")
@Results({
        @Result(name = "showManager",location = "/jsp/manager.jsp")
})
public class ManagerAction extends ActionSupport {
    private Long Frozen;
    private Long notFrozen;
    private Long Canceled;
    private Long Delete;
    private Long notDelete;
    private List<Account> accountList;

    private HttpServletRequest httpServletRequest;

//    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    AccountDao accountDao = (AccountDao) SpringContextUtil.getBean("accountDao");

    public String showManager() {
        httpServletRequest = ServletActionContext.getRequest();
        Account account = null;
        if (Frozen != null) {
            account = accountDao.queryOne(Frozen);
            account.setIsFrozen(true);
            accountDao.modifyUser(account);
        }
        if(notFrozen != null){
             account = accountDao.queryOne(notFrozen);
            account.setIsFrozen(false);
            accountDao.modifyUser(account);
        }
        if (Canceled != null) {
            account = accountDao.queryOne(Canceled);
            account.setIsCanceled(true);
            accountDao.modifyUser(account);
        }
        if (Delete != null) {
            account = accountDao.queryOne(Delete);
            account.setIsDelete(true);
            accountDao.modifyUser(account);
        }
        if(notDelete!=null){
            account = accountDao.queryOne(notDelete);
            account.setIsDelete(false);
            accountDao.modifyUser(account);

        }
        accountList = accountDao.queryAll();
        System.out.println(accountList);
        httpServletRequest.setAttribute("accountList",accountList);
        return "showManager";
    }

    public Long getFrozen() {
        return Frozen;
    }

    public void setFrozen(Long frozen) {
        Frozen = frozen;
    }

    public Long getNotFrozen() {
        return notFrozen;
    }

    public void setNotFrozen(Long notFrozen) {
        this.notFrozen = notFrozen;
    }

    public Long getCanceled() {
        return Canceled;
    }

    public void setCanceled(Long canceled) {
        Canceled = canceled;
    }

    public Long getDelete() {
        return Delete;
    }

    public void setDelete(Long delete) {
        Delete = delete;
    }

    public Long getNotDelete() {
        return notDelete;
    }
    public void setNotDelete(Long notDelete) {
        this.notDelete = notDelete;
    }
    public List<Account> getAccountList() {
        return accountList;
    }
    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
}