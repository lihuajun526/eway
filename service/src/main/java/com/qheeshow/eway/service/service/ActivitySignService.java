package com.qheeshow.eway.service.service;

import com.qheeshow.eway.common.bean.wechat.pay.ResultOrder;
import com.qheeshow.eway.common.bean.wechat.pay.exception.OrderWechatException;
import com.qheeshow.eway.service.model.ActivitySign;

import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 2017/3/25.
 */
public interface ActivitySignService {

    int save(ActivitySign activitySign);

    boolean issign(ActivitySign activitySign);

    List<ActivitySign> listByActivity(Integer activityid);

    ResultOrder preOrder(ActivitySign activitySign, String payType, String openid) throws OrderWechatException;

    Map<String, Object> listByActivityAndPage(ActivitySign activitySign);

}
