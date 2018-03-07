package com.zyht.action;

import com.opensymphony.xwork2.ActionSupport;
import com.zyht.base.BaseAction;
import com.zyht.domain.Seller;
import com.zyht.service.SellerService;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/2.
 */

/**
 * @author chendong
 * @Title: SellerAction
 * @Description: 卖家Action
 * @date 2018/3/2
 */
@Action
//@Results({
//
//        @Result(name = "showSeller",location = "/jsp/seller.jsp")
//})
public class SellerAction extends ActionSupport implements BaseAction{
    //    卖家ID
    private Long sellerId;
    //   存放seller信息

    List<Seller>sellerList=null;
    private Long[] ids;
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    SellerService sellerService = (SellerService)context.getBean("sellerService");

    public String showSeller(){
        //        判断传入的卖家ID是否为空，并将其放入map中
        Map<String, Object> stringMap = new HashMap<String, Object>();
        System.out.println(sellerId);
        if(sellerId!=null){
            stringMap.put("`SELLER_ID`",sellerId);
            sellerList=sellerService.querySellerByCondition(stringMap);
            System.out.println(sellerList.get(0).getName());
            return "showSeller";
        }else {
            return null;
        }

    }

    public String deleteShopCar(){
        if (ids!=null){
            sellerService.deleteSellerByIds(ids) ;
            return "deleteGoodsDelete";
        }
        return null;
    }

    public String updateShopCar(){
        if (sellerId!=null){

            Seller seller=new Seller();
            sellerService.updateSeller(seller);
            return "updatePrice";
        }
        return null;
    }


    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }
}