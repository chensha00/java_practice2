package com.zyht.action;

import com.opensymphony.xwork2.ActionSupport;
import com.zyht.dao.SellerDao;
import com.zyht.domain.Seller;
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
    private List<Seller> sellerList;
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    SellerDao sellerDao = (SellerDao)context.getBean("sellerDao");

    public String showSeller(){
//        判断传入的卖家ID是否为空，并将其放入map中
        Map<String, Object> stringMap = new HashMap<String, Object>();
        if(sellerId!=null){
            stringMap.put("SELLER_ID",sellerId);
        }else {

        }
//        根据条件查询订单
//        orderList = orderDao.queryOrderCondition(stringMap);
//        根据条件查询商品
        sellerList = sellerDao.querySellerByCondition(stringMap);

//        在request中存放信息
        return "showSeller";
//        将request转发给页面
    }


    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}