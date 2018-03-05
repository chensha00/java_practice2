package com.zyht.action;

import com.opensymphony.xwork2.ActionSupport;
import com.zyht.domain.Seller;
import com.zyht.service.SellerService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
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
@Action("Seller")
@Results({
        @Result(name = "showSeller",location = "/seller.jsp")
})
public class SellerAction extends ActionSupport {
    //    卖家ID
    private Long sellerId;
    //   存放seller信息
    private Long[] ids;

    List<Seller> sellerList=null;
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    SellerService sellerService = (SellerService)context.getBean("sellerService");

    public String showSeller(){
//        判断传入的卖家ID是否为空，并将其放入map中
        Map<String, Object> stringObjectMap = new HashMap<String, Object>();
        if(sellerId!=null){
            stringObjectMap.put("`SELLER_ID`",sellerId);
            sellerList=sellerService.querySellerByCondition(stringObjectMap);
            return "showShopCar";
        }else {
            return null;
        }

    }

    public String deleteSeller(){
        if (ids!=null){
            sellerService.deleteSellerByIds(ids) ;
            return "deleteSeller";
        }
        return null;
    }

    public String updateSeller(){
        if (sellerId!=null){
            Seller seller=new Seller();
            sellerService.updateSeller(seller);
            return "updateShopCar";
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