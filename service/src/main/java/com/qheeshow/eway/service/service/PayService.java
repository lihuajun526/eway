package com.qheeshow.eway.service.service;

import com.qheeshow.eway.common.exception.CommonException;
import com.qheeshow.eway.common.exception.RequestException;
import com.qheeshow.eway.service.model.OrderWechat;

import java.io.UnsupportedEncodingException;

/**
 * Created by lihuajun on 2017/4/26.
 */
public interface PayService {

    String createWechatORCode(String url);

    String order(OrderWechat orderWechat) throws UnsupportedEncodingException, RequestException, CommonException;

}
