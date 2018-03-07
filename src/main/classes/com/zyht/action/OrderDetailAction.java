package com.zyht.action;/**
 * Created by HAN on 2018/3/2.
 */

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zyht.base.BaseAction;
import com.zyht.common.util.SpringContextUtil;
import com.zyht.domain.OrderDetail;
import com.zyht.service.OrderDetailService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author renxu
 * @ClassName OrderDetailAction
 * @Description 类描述
 * @date 2018/3/2
 */
@Action("OrderDetail")
/*@Results({
        @Result(name = "orderDetailResult",location = "/jsp/order_detail.jsp")
})*/
public class OrderDetailAction extends ActionSupport implements BaseAction {
    //买家信息外键
    private Long buyerId;
    //卖家信息外键
    private Long sellerId;
    //商品信息外键
    private Long goodsId;
    //存放查询到的订单
    private List<OrderDetail> orderDetailList;
    private HttpServletRequest request;

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }


    OrderDetailService orderDetailService = (OrderDetailService) SpringContextUtil.getBean("orderDetailService");

    /**
     * @Title: orderDetailResult
     * @Description: 查询方法
     * @author renxu
     * @date 2018/3/7
     * @return DETAIL 要跳转的界面
     */
    public String orderDetailResult(){

        //将数据库中的数据查询出来显示到界面
        request = ServletActionContext.getRequest();
        //判断外键信息
        Map<String, Object> stringMap = new HashMap<String,Object>();
        if(buyerId!=null){
            stringMap.put("BUYER_ID",buyerId);
        }else if(sellerId!=null){
            stringMap.put("SELLER_ID",sellerId);
        }if(goodsId!=null){
            stringMap.put("GOODS_ID",goodsId);
        }

        //界面点击发货时，改变支付状态值
        if(request.getParameter("send") != null){
            OrderDetail orderDetail = (OrderDetail) SpringContextUtil.getBean("orderDetail");
            String id = request.getParameter("send");//获取到页面按钮传过来的值
            Long orderId = Long.parseLong(id);
            orderDetail = orderDetailService.queryOrderById(orderId);
            orderDetail.setOrderStatus((byte)3);//将支付状态改为3已完成
            //修改数据库中的数据
            orderDetailService.updateOrder(orderDetail);
        }

        //条件查询
        orderDetailList = orderDetailService.queryOrderByCondition(stringMap);
        request.setAttribute("orderDetailList",orderDetailList);

        return DETAIL;
    }
}
