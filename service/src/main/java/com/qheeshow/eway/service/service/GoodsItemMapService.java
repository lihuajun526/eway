package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.GoodsItemMap;

import java.util.List;

/**
 * Created by lihuajun on 2017/3/21.
 */
public interface GoodsItemMapService {

    List<GoodsItemMap> listByGoods(Integer goodsid);

}
