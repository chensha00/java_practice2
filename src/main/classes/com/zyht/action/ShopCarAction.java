package com.zyht.action;

import com.opensymphony.xwork2.ActionSupport;
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
 * @author wangchuan
 * @Description ShopCarAction
 * @Date 2018/3/2
 */
@Action("ShopCarAction")
@Results({
        @Result(name = "showShopCar", location = "/shop_car.jsp"),
        @Result(name = "addShopCar", location = "/shop_car.jsp"),
        @Result(name = "deleteShopCar", location = "/shop_car.jsp"),
        @Result(name = "updateShopCar", location = "/shop_car.jsp"),
})

public class ShopCarAction extends ActionSupport {

    private Long shopCarId;

    private Long[] ids;

    List<ShopCar> shopCarList=null;

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    ShopCarService shopCarService = (ShopCarService) context.getBean("shopCarService");
    /**
     * @Title: showShopCar
     * @Description: 展示购物车
     * @author wangchuan
     * @date 2018/3/5
     */
    public String showShopCar(){

        Map<String, Object> stringObjectMap = new HashMap<String, Object>();
        if(shopCarId!=null){
            stringObjectMap.put("`SHOP_CAR_ID`",shopCarId);
            shopCarList=shopCarService.queryShopCarByCondition(stringObjectMap,0,5);
            return "showShopCar";
        }else {
            return null;
        }

    }
    /**
     * @Title: addShopCar
     * @Description: 添加购物车
     * @author wangchuan
     * @date 2018/3/5
     */
    public String addShopCar(){
        if (shopCarId==null){
            ShopCar shopCar=new ShopCar();
            shopCarService.insertShopCar(shopCar);
            return "addShopCar";
        }
        return null;
    }
    /**
     * @Title: deleteShopCar
     * @Description: 删除购物车
     * @author wangchuan
     * @date 2018/3/5
     */
    public String deleteShopCar(){
        if (ids!=null){
            shopCarService.deleteShopCarByIds(ids) ;
            return "deleteShopCar";
        }
        return null;
    }
    /**
     * @Title: updateShopCar
     * @Description: 更新购物车
     * @author wangchuan
     * @date 2018/3/5
     */
    public String updateShopCar(){
        if (shopCarId!=null){
            ShopCar shopCar=new ShopCar();
            shopCarService.updateShopCar(shopCar);
            return "updateShopCar";
        }
        return null;
    }
    /**
     * @Title: getShopCarId
     * @Description: 获取shopCar的id
     * @author wangchuan
     * @date 2018/3/5
     */
    public Long getShopCarId() {
        return shopCarId;
    }
    /**
     * @Title: setShopCarId
     * @Description: 设置shopCar的id
     * @author wangchuan
     * @date 2018/3/5
     * @param shopCarId
     */
    public void setShopCarId(Long shopCarId) {
        this.shopCarId = shopCarId;
    }
    /**
     * @Title: getIds
     * @Description: 获取ids
     * @author wangchuan
     * @date 2018/3/5
     */
    public Long[] getIds() {
        return ids;
    }
    /**
     * @Title: setIds
     * @Description: 设置ids
     * @author wangchuan
     * @date 2018/3/5
     * @param ids
     */
    public void setIds(Long[] ids) {
        this.ids = ids;
    }
}
