package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.model.Goods;
import com.qheeshow.eway.service.service.GoodsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 17-1-17.
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Override public List<Goods> listByType(Integer type) {
        return null;
    }

    @Override public List<Goods> listAll() {
        return null;
    }

    @Override public int save(Goods goods) {
        return 0;
    }

    @Override public int update(Goods goods) {
        return 0;
    }

    @Override public Goods selectByPrimaryKey(Integer id) {
        return null;
    }
}
