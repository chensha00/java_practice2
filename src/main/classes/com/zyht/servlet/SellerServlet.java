package com.zyht.servlet;
import com.zyht.domain.GoodsSellerRelation;
import com.zyht.service.GoodsSellerRelationService;
import com.zyht.service.GoodsSellerRelationServiceImpl;

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
        GoodsSellerRelationService goodsSellerRelationService=new GoodsSellerRelationServiceImpl();
        Map<String, String> stringMap = new HashMap<String, String>();
        String str = ""+request.getAttribute("sellerid");
        stringMap.put("SELLER_ID", str);
        List<GoodsSellerRelation> goodsSellerRelationList = null;
        try {
            goodsSellerRelationList = goodsSellerRelationService.queryGoodsSellerRelationByCondition(stringMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        GoodsSellerRelationService goodsSellerRelationService=new GoodsSellerRelationServiceImpl();
        Map<String, String> stringMap = new HashMap<String, String>();
        String str = ""+request.getAttribute("sellerid");
        stringMap.put("SELLER_ID", str);
        List<GoodsSellerRelation> goodsSellerRelationList = null;
        try {
            goodsSellerRelationList = goodsSellerRelationService.queryGoodsSellerRelationByCondition(stringMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("goodsSellerRelationList", goodsSellerRelationList);
        System.out.println(goodsSellerRelationList.get(0).getId());
        request.getRequestDispatcher("jsp/seller.jsp").forward(request, response);

        }
    }

