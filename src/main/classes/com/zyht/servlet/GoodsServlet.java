package com.zyht.servlet;/********************************************************************
 /**
 * @Project: jsp_web
 * @Package java.servlet
 * @author guohongjin
 * @date 2018/1/30 22:20
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */
import com.sun.xml.internal.ws.api.client.SelectOptimalEncodingFeature;
import com.zyht.domain.*;
import com.zyht.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

/**
 * @author guoxin
 * @ClassName GoodsServlet
 * @Description 类描述
 * @date 2018/1/30
 */
@Repository
public class GoodsServlet extends HttpServlet {


    /**
     * @ClassName doGet
     * @Description 重写doGet方法
     * @author guoxin
     * @date 2018-01-30
     * @param request, response
     * @throw IOException, ServletException
     */

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        request.setCharacterEncoding("utf-8");
        response.setContentType("goods_seller_relation/html;charset=utf-8");
      //  String buyerid=request.getParameter("buyerid");                                                                //接受上一个页面传递来的id
        String buyerid ="1";                                                                                            //测试
        Long id=Long.parseLong(buyerid);                                                                                //把buyerid转化成Long类型用id接收
        Map<String,String> stringStringMap=new HashMap<String ,String>();
        stringStringMap.put("`ID`",buyerid);
        List<GoodsSellerRelation> goodsSellerRelationList=null;                                                         //  定义一个接受goods数据的对象
        Seller seller =null;                                                                                            //定义一个接受卖家数据的对象
        Buyer buyer=null;
        GoodsSellerRelationServiceImpl goodsSellerRelationService=(GoodsSellerRelationServiceImpl)applicationContext.getBean("GoodsServiceRelationService");
        goodsSellerRelationList= (List<GoodsSellerRelation>) goodsSellerRelationService.queryGoodsSellerRelationByCondition(stringStringMap);                               //通过卖家 id查找获取商品的信息
        BuyerServiceImpl buyerServiceImpl=(BuyerServiceImpl)applicationContext.getBean("BuyerService");
        buyer =buyerServiceImpl.queryBuyerById(id);                                                                     //查询id，传递给页面
        request.setAttribute("buyer",buyer);
        SellerServiceImpl sellerServiceImpl =(SellerServiceImpl)applicationContext.getBean("SellerService");
        System.out.println(goodsSellerRelationList.get(0).getSellerId());
        seller= sellerServiceImpl.querySellerById(goodsSellerRelationList.get(0).getSellerId());                        //通过卖家 id查找获取卖家的信息取出卖家的名字
        request.setAttribute("seller",seller);
        request.setAttribute("goodsSellerRelation", goodsSellerRelationList);                                            //传递信息到页面
        request.getRequestDispatcher("/jsp/goods_seller_relation.jsp").forward(request, response);
    }

    /**
     * @ClassName doPost
     * @Description 重写doPost方法
     * @author guoxin
     * @date 2018-01-30
     * @param request, response
     * @throw IOException, ServletException
    */

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
       doGet(request,response);
    }
}