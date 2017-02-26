package com.qheeshow.eway.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qheeshow.eway.service.dao.GoodsMapper;
import com.qheeshow.eway.service.model.Goods;
import com.qheeshow.eway.service.model.GoodsExample;
import com.qheeshow.eway.service.model.GoodsWithBLOBs;
import com.qheeshow.eway.service.service.GoodsService;

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

    @Override 
    public List<GoodsWithBLOBs> listAll() {
    	GoodsExample example = new GoodsExample();
    	example.setOrderByClause("order_num");
    	List<GoodsWithBLOBs> goods = goodsMapper.selectByExampleWithBLOBs(example);
        return goods;
    }

    @Override 
    public int save(GoodsWithBLOBs goods) {
    	int result = goodsMapper.insert(goods);
        return result;
    }

    @Override 
    public int update(GoodsWithBLOBs goods) {
    	int result = goodsMapper.updateByPrimaryKeySelective(goods);
        return result;
    }

    @Override 
    public GoodsWithBLOBs selectByPrimaryKey(Integer id) {
    	GoodsWithBLOBs goods = goodsMapper.selectByPrimaryKey(id);
        return goods;
    }
}
