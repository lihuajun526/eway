package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.ActivityMapper;
import com.qheeshow.eway.service.model.Activity;
import com.qheeshow.eway.service.model.ActivitySign;
import com.qheeshow.eway.service.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2017/3/24.
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> listByCodition(Activity activity, Integer pageIndex, Integer pageSize) {
        activity.setPageSize(pageSize);
        activity.setStartRow((pageIndex - 1) * activity.getPageSize());
        return activityMapper.listByCondition(activity);
    }

    @Override
    public Activity get(Integer id) {
        return activityMapper.selectByPrimaryKey(id);
    }
}
