package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.ActivitySignMapper;
import com.qheeshow.eway.service.model.ActivitySign;
import com.qheeshow.eway.service.model.ActivitySignExample;
import com.qheeshow.eway.service.service.ActivitySignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2017/3/25.
 */
@Service
public class ActivitySignServiceImpl implements ActivitySignService {

    @Autowired
    private ActivitySignMapper activitySignMapper;

    @Override
    public int save(ActivitySign activitySign) {
        return activitySignMapper.insert(activitySign);
    }

    @Override
    public boolean issign(ActivitySign activitySign) {
        ActivitySignExample example = new ActivitySignExample();
        ActivitySignExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(activitySign.getUserid());
        criteria.andActivityIdEqualTo(activitySign.getActivityId());

        List list = activitySignMapper.selectByExample(example);
        if (list != null && list.size() > 0)
            return true;

        return false;
    }

    @Override
    public List<ActivitySign> listByActivity(Integer activityid) {
        ActivitySignExample example = new ActivitySignExample();
        ActivitySignExample.Criteria criteria = example.createCriteria();
        criteria.andActivityIdEqualTo(activityid);

        return activitySignMapper.selectByExample(example);
    }
}
