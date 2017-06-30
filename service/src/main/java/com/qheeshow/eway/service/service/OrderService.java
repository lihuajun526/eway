package com.qheeshow.eway.service.service;

import com.qheeshow.eway.common.bean.wechat.pay.exception.OrderWechatException;
import com.qheeshow.eway.service.model.Order;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface OrderService {

    /**
     * 下单
     *
     * @param userid
     * @param projectid
     * @param goodsid
     * @param payType
     * @return
     * @throws UnsupportedEncodingException
     */
    Map<String, String> place(Integer userid, Integer projectid, Integer goodsid, String payType) throws OrderWechatException;

    /**
     * 话费充值
     * @param userid
     * @param goodsid
     * @param payType
     * @return
     * @throws OrderWechatException
     */
    public Map<String, String> recharge(Integer userid, Integer goodsid, String payType) throws OrderWechatException;
    /**
     * 活动报名
     *
     * @param userid
     * @param activityid
     * @param payType
     * @return
     * @throws OrderWechatException
     */
    Map<String, String> signActivity(Integer userid, Integer activityid, String payType) throws OrderWechatException;

    Order get(Integer id);

    Order getByOrder(String orderno);

    void save(Order order);

    List<Order> listByUser(Integer userid);
}
