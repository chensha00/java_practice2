package com.zyht.servlet;/********************************************************************/
/**
 * @Project: jspweb
 * @Package com.zyht.servlet
 * @author caoxin
 * @date 2018/2/4 15:10
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author caoxin
 * @ClassName LogOutServlet
 * @Description 退出登录Servlet
 * @date 2018/2/4
 */
public class LogOutServlet extends HttpServlet {

    /**
     * @Title: doGet
     * @Description: 重写doGet方法
     * @author caoxin
     * @date 2018/2/4
     * @param request, response
     * @throw IOException,ServletException
     */

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        doPost(request,response);
    }

    /**
     * @Title: doPost
     * @Description: 重写doPost方法
     * @author caoxin
     * @date 2018/2/4
     * @param request, response
     * @throw IOException,ServletException
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        //防止创建session
        HttpSession session=request.getSession(false);
        if(session==null){
            response.sendRedirect("jsp/log_in.jsp");
            return;
        }
        //将保存在session中的username和password移除并重定向到log_in.jsp
        session.removeAttribute("username");
        session.removeAttribute("password");
        session.invalidate();
        response.sendRedirect("jsp/log_in.jsp");
    }
}
