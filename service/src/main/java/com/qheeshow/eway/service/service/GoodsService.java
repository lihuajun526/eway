package com.qheeshow.eway.service.service;

import java.util.List;

import com.qheeshow.eway.common.bean.wechat.pay.ResultOrder;
import com.qheeshow.eway.common.bean.wechat.pay.exception.OrderWechatException;
import com.qheeshow.eway.service.model.ActivitySign;
import com.qheeshow.eway.service.model.Goods;
import com.qheeshow.eway.service.model.GoodsWithBLOBs;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface GoodsService {

    List<Goods> listByType(Integer type);

    List<Goods> listByStatus(Integer status);

    List<Goods> listAll();

    int save(Goods goods);

    int update(Goods goods);

    Goods selectByPrimaryKey(Integer id);

    ResultOrder preOrder(String orderStr, String payType, Integer userid, String openid) throws OrderWechatException;

}
