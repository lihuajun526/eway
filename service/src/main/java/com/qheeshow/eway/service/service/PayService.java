package com.qheeshow.eway.service.service;

import com.google.zxing.WriterException;
import com.qheeshow.eway.common.bean.wechat.pay.ResultOrder;
import com.qheeshow.eway.common.bean.wechat.pay.WechatNotify;
import com.qheeshow.eway.common.bean.wechat.pay.exception.ResponseWechat;
import com.qheeshow.eway.common.exception.CommonException;
import com.qheeshow.eway.common.exception.RequestException;
import com.qheeshow.eway.service.model.Order;
import com.qheeshow.eway.service.model.OrderWechat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by lihuajun on 2017/4/26.
 */
public interface PayService {

    String createWechatORCode(String url, String fileType) throws IOException, WriterException;

    ResultOrder order(OrderWechat orderWechat) throws UnsupportedEncodingException, RequestException, CommonException;

    String order(WechatNotify wechatNotify);

    //提现
    void draw(String openid, String trueName, Integer limit) throws CommonException;

    void saveOrderAndActivitySign(Order order);
}
