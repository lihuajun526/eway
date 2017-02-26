package com.qheeshow.eway.service.service;

import java.util.List;

import com.qheeshow.eway.service.model.Goods;
import com.qheeshow.eway.service.model.GoodsWithBLOBs;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface GoodsService {

    List<Goods> listByType(Integer type);

    List<GoodsWithBLOBs> listAll();

    int save(GoodsWithBLOBs goods);

    int update(GoodsWithBLOBs goods);

    GoodsWithBLOBs selectByPrimaryKey(Integer id);

}
