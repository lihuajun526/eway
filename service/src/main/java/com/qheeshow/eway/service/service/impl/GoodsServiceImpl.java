package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.common.bean.wechat.pay.ResultOrder;
import com.qheeshow.eway.common.bean.wechat.pay.exception.OrderWechatException;
import com.qheeshow.eway.common.exception.CommonException;
import com.qheeshow.eway.common.exception.RequestException;
import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.dao.GoodsMapper;
import com.qheeshow.eway.service.dao.OrderDetailMapper;
import com.qheeshow.eway.service.dao.OrderMapper;
import com.qheeshow.eway.service.model.*;
import com.qheeshow.eway.service.service.GoodsService;
import com.qheeshow.eway.service.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lihuajun on 17-1-17.
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private PayService payService;

    @Override
    public List<Goods> listByType(Integer type) {
        return null;
    }

    @Override
    public List<Goods> listByStatus(Integer status) {

        GoodsExample example = new GoodsExample();
        GoodsExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(status);

        return goodsMapper.selectByExample(example);
    }

    @Override
    public List<Goods> listAll() {
        GoodsExample example = new GoodsExample();
        example.setOrderByClause("order_no");
        List<Goods> goods = goodsMapper.selectByExample(example);
        return goods;
    }

    @Override
    public int save(Goods goods) {
        int result = goodsMapper.insert(goods);
        return result;
    }

    @Override
    public int update(Goods goods) {
        int result = goodsMapper.updateByPrimaryKeySelective(goods);
        return result;
    }

    @Override
    public Goods selectByPrimaryKey(Integer id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        return goods;
    }

    @Override
    public ResultOrder preOrder(Goods goods, String payType, Integer projectid, Integer userid, String openid) throws OrderWechatException {
        ResultOrder resultOrder = null;
        //创建订单
        Order order = new Order();
        order.setFlag(0);
        order.setPrice(goods.getPrice());
        order.setTitle(goods.getTitle());
        order.setStatus(1);
        order.setProjectid(projectid);
        order.setUserid(userid);
        order.setOrderNo(StrUtil.getOrderno());
        orderMapper.insert(order);
        //保存订单明细
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCount(1);
        orderDetail.setPrice(goods.getPrice());
        orderDetail.setOrderid(order.getId());
        orderDetail.setGoodsid(goods.getId());
        orderDetailMapper.insert(orderDetail);
        //第三方支付下单
        if (payType.equalsIgnoreCase("WECHAT")) {
            OrderWechat orderWechat = new OrderWechat();
            orderWechat.setDescription(order.getTitle());
            orderWechat.setOrderno(order.getOrderNo());
            orderWechat.setTotalFee(String.valueOf(goods.getPrice().multiply(new BigDecimal(100)).intValue()));
            orderWechat.setTradeType("JSAPI");
            orderWechat.setOpenid(openid);
            try {
                resultOrder = payService.order(orderWechat);
                resultOrder.setOrderno(order.getOrderNo());
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
        return resultOrder;
    }
}
