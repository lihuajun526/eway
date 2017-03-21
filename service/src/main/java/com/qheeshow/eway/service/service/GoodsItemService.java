package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.GoodsItem;

import java.util.List;
import java.util.Set;

/**
 * Created by lihuajun on 2017/3/21.
 */
public interface GoodsItemService {

    List<GoodsItem> listByIds(Set<Integer> ids);

}
