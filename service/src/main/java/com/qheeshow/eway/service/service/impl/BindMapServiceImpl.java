package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.BindMapMapper;
import com.qheeshow.eway.service.model.BindMap;
import com.qheeshow.eway.service.model.BindMapExample;
import com.qheeshow.eway.service.service.BindMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2017/3/25.
 */
@Service
public class BindMapServiceImpl implements BindMapService {

    @Autowired
    private BindMapMapper bindMapMapper;

    @Override
    public BindMap getByBindId(String bindId) {

        BindMapExample example = new BindMapExample();
        BindMapExample.Criteria criteria = example.createCriteria();
        criteria.andBindIdEqualTo(bindId);

        List<BindMap> list = bindMapMapper.selectByExample(example);
        if (list.size() == 0)
            return null;
        return list.get(0);
    }

    @Override
    public void save(BindMap bindMap) {
        bindMapMapper.insert(bindMap);
    }
}
