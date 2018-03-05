package com.zyht.action;/********************************************************************
 /**
 * @Project: jsp_web
 * @Package com.zyht.action
 * @author guohongjin
 * @date 2018/3/2 17:50
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.opensymphony.xwork2.ActionSupport;
import com.zyht.domain.Goods;
import com.zyht.service.GoodsService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guoxin
 * @ClassName GoodsSellerRelationServiceAction
 * @Description 类描述
 * @date 2018/3/2
 */
@Action("Goods")
@Results({
        @Result(name = "goods",location = "/goods_seller_relation.jsp")
})
public class GoodsAction extends ActionSupport {
  public Long goodsId;
  public List<Goods> goodsList;
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    GoodsService goodsService=(GoodsService)context.getBean("GoodsServiceImpl");

    /**
     * @ClassName goodsShow
     * @Description 查询商品
     * @author guoxin
     * @date 2018-03-05
    */

    public  String goodsShow(){
        Map<String, Object> stringObjectMap = new HashMap<String, Object>();
        if(goodsId!=null){
            stringObjectMap.put("`GOODS_ID`",goodsId);
        }
        goodsList=goodsService.queryGoodsByCondition(stringObjectMap, 0, 5);
        return "goods";
    }

    /**
     * @ClassName goodsAdd
     * @Description 添加商品
     * @author guoxin
     * @date 2018-03-05
    */

   public String goodsAdd(){
       Goods goods =new Goods();
       int newGoods=goodsService.insertGoods(goods);
       return "Goods";
   }

    /**
     * @ClassName goodsUpdate
     * @Description 更新物品
     * @author guoxin
     * @date 2018-03-05
    */

    public String goodsUpdate(){
        Goods goods =new Goods();
        Map<String, Object> stringObjectMap = new HashMap<String, Object>();
        if(goodsId!=null){
            stringObjectMap.put("`GOODS_ID`",goodsId);
        }
        int newGoods=goodsService.updateGoods(goods);
        return "goods";
    }

    /**
     * @ClassName goodsDelete
     * @Description 删除物品
     * @author guoxin
     * @date 2018-03-05
    */

    public String goodsDelete(){
        Map<String, Object> stringObjectMap = new HashMap<String, Object>();
        if(goodsId!=null){
            stringObjectMap.put("`GOODS_ID`",goodsId);
        }
        goodsService.deleteGoodsById(goodsId);
        return "goods";
    }
    public String goodsDeletes(){
        Long []ids = null;
        Map<String, Object> stringObjectMap = new HashMap<String, Object>();
        if(goodsId!=null){
            stringObjectMap.put("`GOODS_ID`",goodsId);
        }
        goodsService.deleteGoodsByIds(ids);
        return "goods";
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        goodsId = goodsId;
    }
}

