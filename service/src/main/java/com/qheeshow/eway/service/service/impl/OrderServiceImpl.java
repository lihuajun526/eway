package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.dao.GoodsMapper;
import com.qheeshow.eway.service.dao.OrderDetailMapper;
import com.qheeshow.eway.service.dao.OrderMapper;
import com.qheeshow.eway.service.model.Order;
import com.qheeshow.eway.service.model.OrderDetail;
import com.qheeshow.eway.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by lihuajun on 2017/2/26.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public void place(Integer userid, Integer projectid, String orderStr) {
        if (orderStr.indexOf("#") != -1) {
            orderStr = orderStr.substring(0, orderStr.length() - 1);
        }
        BigDecimal total = new BigDecimal(0);
        String[] orders = orderStr.split("#");
        for (String str : orders) {
            String[] temp = str.split("_");
            Integer goodsid = Integer.valueOf(temp[0]);
            Integer count = Integer.valueOf(temp[1]);
            total.add(new BigDecimal(goodsMapper.selectByPrimaryKey(goodsid).getPrice().intValue() * count));
        }
        Order order = new Order();
        order.setProjectid(projectid);
        order.setStatus(1);
        order.setUserid(userid);
        order.setOrderNo(StrUtil.getOrderno());
        order.setPrice(total);
        orderMapper.insert(order);
        //保存订单明细
        for (String str : orders) {
            String[] temp = str.split("_");
            Integer goodsid = Integer.valueOf(temp[0]);
            Integer count = Integer.valueOf(temp[1]);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderid(order.getId());
            orderDetail.setGoodsid(goodsid);
            orderDetail.setPrice(goodsMapper.selectByPrimaryKey(goodsid).getPrice());
            orderDetail.setCount(count);
            orderDetailMapper.insert(orderDetail);
        }
    }
}
