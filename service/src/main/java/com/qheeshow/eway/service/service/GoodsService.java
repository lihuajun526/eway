package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.Goods;

import java.util.List;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface GoodsService {

    List<Goods> listByType(Integer type);

    int save(Goods goods);

    int update(Goods goods);

    Goods selectByPrimaryKey(Integer id);

}
