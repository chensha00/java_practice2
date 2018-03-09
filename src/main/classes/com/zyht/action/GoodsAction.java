package com.zyht.action;/********************************************************************
 /**
 * @Project: jsp_web
 * @Package classes.com.zyht.action
 * @author guohongjin
 * @date 2018/3/2 17:50
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.opensymphony.xwork2.ActionSupport;
import com.zyht.base.BaseAction;
import com.zyht.domain.Goods;
import com.zyht.service.GoodsService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
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
//@Results({
//        @Result(name = "goods",location = "/goods_seller_relation.jsp")
//})
public class GoodsAction extends ActionSupport implements BaseAction{
  public Long goodsId;
  public List<Goods> goodsList;
    private HttpServletRequest request;
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    GoodsService goodsService=(GoodsService)context.getBean("goodsService");

    /**
     * @ClassName goodsShows
     * @Description 查询商品
     * @author guoxin
     * @date 2018-03-05
    */
    public  String goodsShows(){
        request = ServletActionContext.getRequest();
        Long [] ids=null;
        Map<String, Object> stringObjectMap = new HashMap<String, Object>();
        if(ids!=null){
            stringObjectMap.put("`GOODS_ID`",goodsId);
        }
        goodsList=goodsService.queryGoodsByCondition(stringObjectMap, 0, 5);
        request.setAttribute("goodsAll",stringObjectMap);
        return RELATION;
    }
    /**
     * @ClassName goodsShow
     * @Description 查询单个商品
     * @author guoxin
     * @date 2018-03-07
    */
    public  String goodsShow(){
        request = ServletActionContext.getRequest();
        Goods goods=new Goods();
        if(goodsId!=null)
            goods=goodsService.queryGoodsById(goodsId);
        request.setAttribute("goods",goods);
        return RELATION;
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
       return JUMP;
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
        return JUMP;
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
        return JUMP;
    }
    /**
     * @ClassName  goodsDeletes
     * @Description 批量删除
     * @author guoxin
     * @date 2018-03-06
    */
    public String goodsDeletes(){
        Long []ids=null ;
        Map<String, Object> stringObjectMap = new HashMap<String, Object>();
        if(goodsId!=null){
            stringObjectMap.put("`GOODS_ID`", goodsId);
        }
        goodsService.deleteGoodsByIds(ids);
        return JUMP;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        goodsId = goodsId;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}

