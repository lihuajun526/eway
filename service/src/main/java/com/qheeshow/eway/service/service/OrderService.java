package com.qheeshow.eway.service.service;

import com.qheeshow.eway.common.bean.wechat.pay.exception.OrderWechatException;

import java.io.UnsupportedEncodingException;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface OrderService {

    /**
     * 下单
     * @param userid
     * @param projectid
     * @param orderStr
     * @param payType
     * @return
     * @throws UnsupportedEncodingException
     */
    String place(Integer userid, Integer projectid, String orderStr, String payType) throws OrderWechatException;


}
