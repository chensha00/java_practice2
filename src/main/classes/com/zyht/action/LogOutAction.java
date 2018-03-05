package com.zyht.action;/********************************************************************/
/**
 * @Project: jspweb
 * @Package com.zyht.action
 * @author caoxin
 * @date 2018/3/5 9:11
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author caoxin
 * @ClassName LogOutAction
 * @Description 注销Action
 * @date 2018/3/5
 */
@Action
@Results({
        @Result(name = "",location = "/jsp/log_in.jsp")
})
public class LogOutAction extends ActionSupport {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    public String logOut(){
        //        获取request,response对象
        request = ServletActionContext.getRequest();
        response = ServletActionContext.getResponse();
        //防止创建session
        HttpSession session=request.getSession(false);
        if(session==null){
            return "logIn";
        }
        //将保存在session中的username和password移除并重定向到log_in.jsp
        session.removeAttribute("username");
        session.removeAttribute("password");
        session.invalidate();
        return "logIn";
    }
}
