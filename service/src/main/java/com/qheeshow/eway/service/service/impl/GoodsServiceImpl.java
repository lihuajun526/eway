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
    public ResultOrder preOrder(String orderStr, String payType, Integer userid, String openid) throws OrderWechatException {
        ResultOrder resultOrder = null;
        //orderStr=goodsid1_count1#goodsid2_count2
        String[] aOrderStr = orderStr.split("#");
        StringBuffer orderTitle = new StringBuffer();
        BigDecimal orderPrice = new BigDecimal(0);
        for (String str : aOrderStr) {
            String[] aTemp = str.split("_");
            Goods goods = goodsMapper.selectByPrimaryKey(Integer.valueOf(aTemp[0]));
            orderTitle.append(goods.getTitle()).append("(").append(aTemp[1]).append("份) ");
            orderPrice.add(goods.getPrice().multiply(new BigDecimal(aTemp[1])));
        }
        //创建订单
        Order order = new Order();
        order.setFlag(0);
        order.setPrice(orderPrice);
        order.setTitle(orderTitle.toString());
        order.setStatus(1);
        order.setUserid(userid);
        order.setOrderNo(StrUtil.getOrderno());
        orderMapper.insert(order);
        //保存订单明细
        for (String str : aOrderStr) {
            String[] aTemp = str.split("_");
            Goods goods = goodsMapper.selectByPrimaryKey(Integer.valueOf(aTemp[0]));
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setCount(Integer.valueOf(aTemp[1]));
            orderDetail.setPrice(goods.getPrice());
            orderDetail.setOrderid(order.getId());
            orderDetail.setGoodsid(goods.getId());
            orderDetailMapper.insert(orderDetail);
        }

        //第三方支付下单
        if (payType.equalsIgnoreCase("WECHAT")) {
            OrderWechat orderWechat = new OrderWechat();
            orderWechat.setDescription(order.getTitle());
            orderWechat.setOrderno(order.getOrderNo());
            orderWechat.setTotalFee(String.valueOf(orderPrice.multiply(new BigDecimal(100)).intValue()));
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
