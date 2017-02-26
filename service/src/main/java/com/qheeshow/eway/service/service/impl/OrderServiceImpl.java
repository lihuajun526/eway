package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.model.Order;
import com.qheeshow.eway.service.model.OrderDetail;
import com.qheeshow.eway.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lihuajun on 2017/2/26.
 */
public class OrderServiceImpl implements OrderService {

    //@Autowired


    @Override
    public void place(Integer projectid, String orderStr) {

        Order order = new Order();
        order.setProjectid(projectid);
        order.setStatus(1);
        //todo保存订单

        String[] orderInfos = orderStr.split("#");

        for(String orderInfo:orderInfos){
            String[]goodsInfo = orderInfo.split("_");
            Integer goodsid = Integer.valueOf(goodsInfo[0]);
            Integer count = Integer.valueOf(goodsInfo[1]);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setCount(count);
            orderDetail.setGoodsid(goodsid);
            orderDetail.setOrderid(order.getId());
            //todo保存订单详情
        }


    }
}
