package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.common.bean.wechat.pay.ResultOrder;
import com.qheeshow.eway.common.bean.wechat.pay.exception.OrderWechatException;
import com.qheeshow.eway.common.exception.CommonException;
import com.qheeshow.eway.common.exception.RequestException;
import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.dao.*;
import com.qheeshow.eway.service.model.*;
import com.qheeshow.eway.service.service.ActivitySignService;
import com.qheeshow.eway.service.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 2017/3/25.
 */
@Service
public class ActivitySignServiceImpl implements ActivitySignService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivitySignServiceImpl.class);

    @Autowired
    private ActivitySignMapper activitySignMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private PayService payService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(ActivitySign activitySign) {
        return activitySignMapper.insert(activitySign);
    }

    @Override
    public boolean issign(ActivitySign activitySign) {
        ActivitySignExample example = new ActivitySignExample();
        ActivitySignExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(activitySign.getUserid());
        criteria.andActivityIdEqualTo(activitySign.getActivityId());

        List list = activitySignMapper.selectByExample(example);
        if (list != null && list.size() > 0)
            return true;

        return false;
    }

    @Override
    public List<ActivitySign> listByActivity(Integer activityid) {
        ActivitySignExample example = new ActivitySignExample();
        ActivitySignExample.Criteria criteria = example.createCriteria();
        criteria.andActivityIdEqualTo(activityid);

        return activitySignMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public ResultOrder preOrder(ActivitySign activitySign, String payType, String openid) throws OrderWechatException {

        ResultOrder resultOrder = null;
        Activity activity = activityMapper.selectByPrimaryKey(activitySign.getActivityId());
        //创建订单
        Order order = new Order();
        order.setFlag(0);
        order.setPrice(activity.getCost());
        order.setTitle("活动[" + activity.getTitle() + "]报名费");
        order.setStatus(1);
        order.setUserid(activitySign.getUserid());
        order.setOrderNo(StrUtil.getOrderno());
        orderMapper.insert(order);
        //保存订单明细
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCount(1);
        orderDetail.setPrice(activity.getCost());
        orderDetail.setOrderid(order.getId());
        orderDetail.setActivityid(activitySign.getActivityId());
        orderDetailMapper.insert(orderDetail);
        //第三方支付下单
        if (payType.equalsIgnoreCase("WECHAT")) {
            OrderWechat orderWechat = new OrderWechat();
            orderWechat.setDescription(order.getTitle());
            orderWechat.setOrderno(order.getOrderNo());
            orderWechat.setTotalFee(String.valueOf(activity.getCost().multiply(new BigDecimal(100)).intValue()));
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

    @Override
    public Map<String, Object> listByActivityAndPage(ActivitySign activitySign) {
        Map<String, Object> map = new HashMap<>();
        List<ActivitySign> signs = activitySignMapper.listByActivityAndPage(activitySign);

        List<Integer> ids = new ArrayList<>();
        for (ActivitySign sign : signs) {
            ids.add(sign.getUserid());
        }
        if (ids.size() == 0) {
            map.put("signs", new ArrayList<>());
        } else {
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            List<User> users = userMapper.selectByExample(example);
            map.put("users", users);
        }
        map.put("count", activitySignMapper.countByActivityAndPage(activitySign));
        return map;
    }
}
