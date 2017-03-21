package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.GoodsItemMapper;
import com.qheeshow.eway.service.model.GoodsItem;
import com.qheeshow.eway.service.model.GoodsItemExample;
import com.qheeshow.eway.service.service.GoodsItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by lihuajun on 2017/3/21.
 */
@Service
public class GoodsItemServiceImpl implements GoodsItemService {

    @Autowired
    private GoodsItemMapper goodsItemMapper;

    @Override
    public List<GoodsItem> listByIds(Set<Integer> set) {
        List<Integer> ids = new ArrayList<>();
        for (Integer id : set)
            ids.add(id);
        GoodsItemExample example = new GoodsItemExample();
        GoodsItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        example.setOrderByClause("order_no");
        return goodsItemMapper.selectByExample(example);
    }
}
