package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.GoodsItemMapMapper;
import com.qheeshow.eway.service.model.GoodsItemMap;
import com.qheeshow.eway.service.model.GoodsItemMapExample;
import com.qheeshow.eway.service.service.GoodsItemMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2017/3/21.
 */
@Service
public class GoodsItemMapServiceImpl implements GoodsItemMapService {

    @Autowired
    private GoodsItemMapMapper goodsItemMapMapper;

    @Override
    public List<GoodsItemMap> listByGoods(Integer goodsid) {
        GoodsItemMapExample goodsItemMapExample = new GoodsItemMapExample();
        GoodsItemMapExample.Criteria criteria = goodsItemMapExample.createCriteria();
        criteria.andGoodsidEqualTo(goodsid);
        return goodsItemMapMapper.selectByExample(goodsItemMapExample);
    }
}
