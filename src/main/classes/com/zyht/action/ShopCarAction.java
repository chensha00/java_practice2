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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@Action
//@Results({
//        @Result(name = "showShopCar", location = "/shop_car.jsp"),
//        @Result(name = "addShopCar", location = "/shop_car.jsp"),
//        @Result(name = "deleteShopCar", location = "/shop_car.jsp"),
//        @Result(name = "updateShopCar", location = "/shop_car.jsp"),
//})
public class ShopCarAction extends ActionSupport implements BaseAction{

    private Long accountId;

    private Long shopCarId;

    private Long[] ids;

    List<ShopCar> shopCarList=null;

    private HttpServletRequest request;

    private HttpServletResponse response;

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    ShopCarService shopCarService = (ShopCarService) context.getBean("shopCarService");
    /**
     * @Title: showShopCar
     * @Description: 展示购物车
     * @author wangchuan
     * @date 2018/3/8
     */
    public String showShopCar(){

        Map<String, Object> stringObjectMap = new HashMap<String, Object>();
        if(accountId!=null){
            stringObjectMap.put("`ACCOUNT_ID`",accountId);
            shopCarList=shopCarService.queryShopCarByCondition(stringObjectMap);
            request.setAttribute("shopCarList",shopCarList);
            return SHOP_CAR;
        }else {
            return null;
        }

    }
    /**
     * @Title: addShopCar
     * @Description: 添加购物车
     * @author wangchuan
     * @date 2018/3/8
     */
    public String addShopCar(){
        if (shopCarId==null){
            ShopCar shopCar=new ShopCar();
            shopCarService.insertShopCar(shopCar);
            return SHOP_CAR;
        }
        return null;
    }
    /**
     * @Title: deleteShopCar
     * @Description: 删除购物车
     * @author wangchuan
     * @date 2018/3/8
     */
    public String deleteShopCar(){
        if (ids!=null){
            shopCarService.deleteShopCarByIds(ids) ;
            return SHOP_CAR;
        }
        return null;
    }
    /**
     * @Title: updateShopCar
     * @Description: 修改购物车
     * @author wangchuan
     * @date 2018/3/8
     */
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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public List<ShopCar> getShopCarList() {
        return shopCarList;
    }

    public void setShopCarList(List<ShopCar> shopCarList) {
        this.shopCarList = shopCarList;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
