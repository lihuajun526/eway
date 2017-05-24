package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.common.bean.wechat.pay.ResultOrder;
import com.qheeshow.eway.common.bean.wechat.pay.exception.OrderWechatException;
import com.qheeshow.eway.common.exception.CommonException;
import com.qheeshow.eway.common.exception.RequestException;
import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.dao.GoodsMapper;
import com.qheeshow.eway.service.dao.OrderDetailMapper;
import com.qheeshow.eway.service.dao.OrderMapper;
import com.qheeshow.eway.service.dao.UserMapper;
import com.qheeshow.eway.service.model.*;
import com.qheeshow.eway.service.service.OrderService;
import com.qheeshow.eway.service.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 2017/2/26.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PayService payService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public Map<String, String> place(Integer userid, Integer projectid, String orderStr, String payType) throws OrderWechatException {

        Map<String, String> map = new HashMap<>();

        if (orderStr.indexOf("#") != -1) {
            orderStr = orderStr.substring(0, orderStr.length() - 1);
        }
        BigDecimal total = new BigDecimal(0);
        String[] orders = orderStr.split("#");
        StringBuffer title = new StringBuffer();
        for (String str : orders) {
            String[] temp = str.split("_");
            Integer goodsid = Integer.valueOf(temp[0]);
            Integer count = Integer.valueOf(temp[1]);
            title.append(goodsMapper.selectByPrimaryKey(goodsid).getTitle());
            if (count > 1)
                title.append("(" + count + "份)");
            title.append(" ");
            total = total.add(goodsMapper.selectByPrimaryKey(goodsid).getPrice().multiply(new BigDecimal(count)));
        }
        //保存订单
        Order order = new Order();
        order.setTitle(title.toString());
        order.setProjectid(projectid);
        order.setStatus(1);
        order.setFlag(0);
        order.setUserid(userid);
        order.setOrderNo(StrUtil.getOrderno());
        order.setPrice(total);
        Date begin = new Date();
        order.setCreateTime(begin);
        orderMapper.insert(order);
        map.put("orderid", String.valueOf(order.getId()));

        User loginUser = userMapper.selectByPrimaryKey(userid);
        Integer callTime = loginUser.getCallTime();
        //保存订单明细
        BigDecimal totalPrice = new BigDecimal(0);
        for (String str : orders) {
            String[] temp = str.split("_");
            Integer goodsid = Integer.valueOf(temp[0]);
            Integer count = Integer.valueOf(temp[1]);
            if (goodsid.intValue() == 1) {
                callTime += 45 * count;
            } else {
                callTime += 30 * count;
            }
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderid(order.getId());
            orderDetail.setGoodsid(goodsid);
            orderDetail.setPrice(goodsMapper.selectByPrimaryKey(goodsid).getPrice());
            totalPrice = totalPrice.add(orderDetail.getPrice().multiply(new BigDecimal(count)));
            orderDetail.setCount(count);
            orderDetailMapper.insert(orderDetail);
        }
        //更新通话时间
        User user = new User();
        user.setId(userid);
        user.setCallTime(callTime);
        userMapper.updateByPrimaryKeySelective(user);
        //统一下单
        String qrcode = null;
        if (payType.equalsIgnoreCase("WECHAT")) {
            OrderWechat orderWechat = new OrderWechat();
            orderWechat.setDescription(title.toString());
            orderWechat.setOrderno(order.getOrderNo());
            orderWechat.setTotalFee(String.valueOf(totalPrice.multiply(new BigDecimal(100)).intValue()));
            orderWechat.setTradeType("NATIVE");
            try {
                ResultOrder resultOrder = payService.order(orderWechat);
                qrcode = resultOrder.getCode_url();
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("error:", e);
                throw new OrderWechatException();
            } catch (RequestException e) {
                LOGGER.error("error:", e);
                throw new OrderWechatException();
            } catch (CommonException e) {
                LOGGER.error("error code:{}", e.getCode());
                throw new OrderWechatException();
            }
        } else if (payType.equalsIgnoreCase("ALIPAY")) {

        }
        //生成支付二维码
        try {
            map.put("qrcode", payService.createWechatORCode(qrcode, "qrcode"));
        } catch (Exception e) {
            LOGGER.error("error:", e);
            throw new OrderWechatException();
        }
        return map;
    }

    @Override
    public Order get(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public Order getByOrder(String orderno) {

        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNoEqualTo(orderno);

        List<Order> list = orderMapper.selectByExample(example);

        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    @Override
    public void save(Order order) {
        if (order.getId() == null) {
            orderMapper.insert(order);
        } else {
            orderMapper.updateByPrimaryKeySelective(order);
        }
    }
}
