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
import java.util.*;

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

    /**
     * 购买套餐
     *
     * @param userid
     * @param projectid
     * @param goodsid
     * @param payType
     * @return
     * @throws OrderWechatException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public Map<String, String> place(Integer userid, Integer projectid, Integer goodsid, String payType) throws OrderWechatException {

        Map<String, String> map = new HashMap<>();
        Goods goods = goodsMapper.selectByPrimaryKey(goodsid);

        //保存订单
        Order order = new Order();
        order.setTitle(goods.getTitle());
        order.setProjectid(projectid);
        order.setStatus(1);
        order.setFlag(0);
        order.setUserid(userid);
        order.setOrderNo(StrUtil.getOrderno());
        order.setPrice(goods.getPrice());
        Date begin = new Date();
        order.setCreateTime(begin);
        orderMapper.insert(order);
        map.put("orderid", String.valueOf(order.getId()));

        User loginUser = userMapper.selectByPrimaryKey(userid);
        Integer callTime = loginUser.getCallTime();
        //保存订单明细
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderid(order.getId());
        orderDetail.setGoodsid(goodsid);
        orderDetail.setPrice(goodsMapper.selectByPrimaryKey(goodsid).getPrice());
        orderDetail.setCount(1);
        orderDetailMapper.insert(orderDetail);
        //统一下单
        String qrcode = null;
        if (payType.equalsIgnoreCase("WECHAT")) {
            OrderWechat orderWechat = new OrderWechat();
            orderWechat.setDescription(goods.getTitle());
            orderWechat.setOrderno(order.getOrderNo());
            orderWechat.setTotalFee(String.valueOf(goods.getPrice().multiply(new BigDecimal(100)).intValue()));
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

    /**
     * 话费充值
     * @param userid
     * @param goodsid
     * @param payType
     * @return
     * @throws OrderWechatException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public Map<String, String> recharge(Integer userid, Integer goodsid, String payType) throws OrderWechatException {

        Map<String, String> map = new HashMap<>();
        Goods goods = goodsMapper.selectByPrimaryKey(goodsid);
        //保存订单
        Order order = new Order();
        order.setTitle(goods.getTitle());
        order.setProjectid(0);
        order.setStatus(1);
        order.setFlag(0);
        order.setUserid(userid);
        order.setOrderNo(StrUtil.getOrderno());
        order.setPrice(goods.getPrice());
        Date begin = new Date();
        order.setCreateTime(begin);
        orderMapper.insert(order);
        map.put("orderid", String.valueOf(order.getId()));
        //保存订单明细
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderid(order.getId());
        orderDetail.setGoodsid(goodsid);
        orderDetail.setPrice(goods.getPrice());
        orderDetail.setCount(1);
        orderDetailMapper.insert(orderDetail);
        //统一下单
        String qrcode = null;
        if (payType.equalsIgnoreCase("WECHAT")) {
            OrderWechat orderWechat = new OrderWechat();
            orderWechat.setDescription(goods.getTitle());
            orderWechat.setOrderno(order.getOrderNo());
            orderWechat.setTotalFee(String.valueOf(goods.getPrice().multiply(new BigDecimal(100)).intValue()));
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
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public Map<String, String> signActivity(Integer userid, Integer activityid, String payType) throws OrderWechatException {

        Map<String, String> map = new HashMap<>();

        Goods goods = goodsMapper.getByActivity(activityid);

        //保存订单
        Order order = new Order();
        order.setTitle(goods.getTitle());
        order.setStatus(1);
        order.setFlag(0);
        order.setUserid(userid);
        order.setOrderNo(StrUtil.getOrderno());
        order.setPrice(goods.getPrice());
        Date begin = new Date();
        order.setCreateTime(begin);
        orderMapper.insert(order);
        map.put("orderid", String.valueOf(order.getId()));

        //保存订单明细
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderid(order.getId());
        orderDetail.setGoodsid(goods.getId());
        orderDetail.setActivityid(activityid);
        orderDetail.setPrice(goods.getPrice());
        orderDetail.setCount(1);
        orderDetailMapper.insert(orderDetail);

        //统一下单
        String qrcode = null;
        if (payType.equalsIgnoreCase("WECHAT")) {
            OrderWechat orderWechat = new OrderWechat();
            orderWechat.setDescription("活动报名费");
            orderWechat.setOrderno(order.getOrderNo());
            orderWechat.setTotalFee(String.valueOf(goods.getPrice().multiply(new BigDecimal(100)).intValue()));
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

    @Override
    public List<Order> listByUser(Integer userid) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userid);
        List<Integer> status = new ArrayList<>();
        status.add(1);
        status.add(2);
        criteria.andStatusIn(status);
        example.setOrderByClause("create_time desc");
        return orderMapper.selectByExample(example);
    }
}
