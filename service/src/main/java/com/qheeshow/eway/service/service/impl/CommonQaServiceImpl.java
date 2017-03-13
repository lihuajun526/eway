package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.CommonQaMapper;
import com.qheeshow.eway.service.model.CommonQa;
import com.qheeshow.eway.service.model.CommonQaExample;
import com.qheeshow.eway.service.service.CommonQaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 17-3-13.
 */
@Service
public class CommonQaServiceImpl implements CommonQaService {

    @Autowired
    private CommonQaMapper commonQaMapper;

    @Override public List<CommonQa> list() {
        return commonQaMapper.selectByExample(new CommonQaExample());
    }
}
