package com.zyht.servlet;


import com.zyht.domain.ShopCar;
import com.zyht.service.ShopCarServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ShopCarServlet
 *
 * @author wangchuan
 * @Description ShopCarServlet
 * @Date 2018/2/26
 */
public class ShopCarServlet extends HttpServlet {
    /**
     * @Title: doGet
     * @Description: 方法描述
     * @author wangchuan
     * @date 2018/1/30
     * @param req,resp
     * @throws java.io.IOException ServletException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req,resp);
    }
    /**
     * @Title: doPost
     * @Description: 方法描述
     * @author wangchuan
     * @date 2018/1/30
     * @param req,resp
     * @throws IOException ServletException
     */
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        ShopCarServiceImpl shopCarService= (ShopCarServiceImpl) applicationContext.getBean("shopCarService");
        List<ShopCar> shopCarList=null;
        Map<String,Object > stringStringMap=new HashMap<String, Object>();
        String str=req.getParameter("id");
        str="1";
        stringStringMap.put("`ID`",str);
        shopCarList=shopCarService.queryShopCarByCondition(stringStringMap, 0, 5);
        req.setAttribute("shopCarList",shopCarList);
        req.getRequestDispatcher("/jsp/shop_car.jsp").forward(req,resp);
    }
}
