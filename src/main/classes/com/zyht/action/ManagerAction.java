package com.zyht.action;

import com.opensymphony.xwork2.ActionSupport;
import com.zyht.common.util.SpringContextUtil;
import com.zyht.dao.AccountDao;
import com.zyht.domain.Account;
import com.zyht.service.AccountService;
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
    AccountService accountService = (AccountService) SpringContextUtil.getBean("accountService");

    public String showManager() {
        httpServletRequest = ServletActionContext.getRequest();
        Account account = null;
        if (Frozen != null) {
            account = accountService.queryOne(Frozen);
            account.setIsFrozen(true);
            accountService.modifyUser(account);
        }
        if(notFrozen != null){
             account = accountService.queryOne(notFrozen);
            account.setIsFrozen(false);
            accountService.modifyUser(account);
        }
        if (Canceled != null) {
            account = accountService.queryOne(Canceled);
            account.setIsCanceled(true);
            accountService.modifyUser(account);
        }
        if (Delete != null) {
            account = accountService.queryOne(Delete);
            account.setIsDelete(true);
            accountService.modifyUser(account);
        }
        if(notDelete!=null){
            account = accountService.queryOne(notDelete);
            account.setIsDelete(false);
            accountService.modifyUser(account);

        }
        accountList = accountService.queryAll();
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