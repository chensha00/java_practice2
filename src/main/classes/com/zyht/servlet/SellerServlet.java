package com.zyht.servlet;
import com.zyht.domain.GoodsSellerRelation;
import com.zyht.service.GoodsSellerRelationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/31.
 */
public class SellerServlet extends HttpServlet {
    /**
     * @param request, response
     * @Title: doGet
     * @Description: 重写doGet方法
     * @author chendong
     * @date 2018/1/28
     * @throw IOException, ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("applicationContext.xml");
        GoodsSellerRelationService goodsSellerRelationService=(GoodsSellerRelationService) applicationContext.getBean("goodsSellerRelationService");

        Map<String, Object> stringMap = new HashMap<String, Object>();
        String str = ""+request.getAttribute("sellerid");
        stringMap.put("SELLER_ID", str);
        List<GoodsSellerRelation> goodsSellerRelationList = null;
        goodsSellerRelationList = goodsSellerRelationService.queryGoodsSellerRelationByCondition(stringMap);
        request.setAttribute("goodsSellerRelationList", goodsSellerRelationList);
        request.getRequestDispatcher("jsp/seller.jsp").forward(request, response);
    }
    /**
     * @param request, response
     * @Title: doPost
     * @Description: 重写doPost方法
     * @author chendong
     * @date 2018/1/28
     * @throw IOException, ServletException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
        }
    }

