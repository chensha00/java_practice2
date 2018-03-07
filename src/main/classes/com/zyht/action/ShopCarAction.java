package com.zyht.action;

import com.opensymphony.xwork2.ActionSupport;
import com.zyht.base.Base;
import com.zyht.base.BaseAction;
import com.zyht.domain.ShopCar;
import com.zyht.service.ShopCarService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ShopCarAction
 *
 * @author Administrator
 * @Description
 * @Date 2018/3/2
 */
@Action("ShopCarAction")
//@Results({
//        @Result(name = "showShopCar", location = "/shop_car.jsp"),
//        @Result(name = "addShopCar", location = "/shop_car.jsp"),
//        @Result(name = "deleteShopCar", location = "/shop_car.jsp"),
//        @Result(name = "updateShopCar", location = "/shop_car.jsp"),
//})
public class ShopCarAction extends ActionSupport implements BaseAction{

    private Long shopCarId;

    private Long[] ids;

    List<ShopCar> shopCarList=null;

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    ShopCarService shopCarService = (ShopCarService) context.getBean("shopCarService");

    public String showShopCar(){

        Map<String, Object> stringObjectMap = new HashMap<String, Object>();
        if(shopCarId!=null){
            stringObjectMap.put("`SHOP_CAR_ID`",shopCarId);
            shopCarList=shopCarService.queryShopCarByCondition(stringObjectMap,0,5);
            return SHOP_CAR;
        }else {
            return null;
        }

    }

    public String addShopCar(){
        if (shopCarId==null){
            ShopCar shopCar=new ShopCar();
            shopCarService.insertShopCar(shopCar);
            return SHOP_CAR;
        }
        return null;
    }

    public String deleteShopCar(){
        if (ids!=null){
            shopCarService.deleteShopCarByIds(ids) ;
            return SHOP_CAR;
        }
        return null;
    }

    public String updateShopCar(){
        if (shopCarId!=null){
            ShopCar shopCar=new ShopCar();
            shopCarService.updateShopCar(shopCar);
            return SHOP_CAR;
        }
        return null;
    }

    public Long getShopCarId() {
        return shopCarId;
    }

    public void setShopCarId(Long shopCarId) {
        this.shopCarId = shopCarId;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }
}
