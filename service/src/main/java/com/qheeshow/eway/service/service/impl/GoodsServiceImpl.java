package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.GoodsMapper;
import com.qheeshow.eway.service.model.Goods;
import com.qheeshow.eway.service.model.GoodsExample;
import com.qheeshow.eway.service.model.GoodsWithBLOBs;
import com.qheeshow.eway.service.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 17-1-17.
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> listByType(Integer type) {
        return null;
    }

    @Override public List<Goods> listByStatus(Integer status) {

        GoodsExample example = new GoodsExample();
        GoodsExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(status);

        return goodsMapper.selectByExample(example);
    }

    @Override
    public List<Goods> listAll() {
        GoodsExample example = new GoodsExample();
        example.setOrderByClause("order_no");
        List<Goods> goods = goodsMapper.selectByExample(example);
        return goods;
    }

    @Override
    public int save(Goods goods) {
        int result = goodsMapper.insert(goods);
        return result;
    }

    @Override
    public int update(Goods goods) {
        int result = goodsMapper.updateByPrimaryKeySelective(goods);
        return result;
    }

    @Override
    public Goods selectByPrimaryKey(Integer id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        return goods;
    }
}
