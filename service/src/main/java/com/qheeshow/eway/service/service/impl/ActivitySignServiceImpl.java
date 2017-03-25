package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.ActivityMapper;
import com.qheeshow.eway.service.dao.ActivitySignMapper;
import com.qheeshow.eway.service.model.ActivitySign;
import com.qheeshow.eway.service.service.ActivitySignService;
import org.springframework.stereotype.Service;

/**
 * Created by lihuajun on 2017/3/25.
 */
@Service
public class ActivitySignServiceImpl implements ActivitySignService {

    private ActivitySignMapper activitySignMapper;

    @Override
    public int save(ActivitySign activitySign) {
        return activitySignMapper.insert(activitySign);
    }
}
