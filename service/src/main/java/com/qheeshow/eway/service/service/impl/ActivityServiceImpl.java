package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.ActivityMapper;
import com.qheeshow.eway.service.model.Activity;
import com.qheeshow.eway.service.model.ActivityExample;
import com.qheeshow.eway.service.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Activity> listByClass(Integer cls) {

        List<Integer> list = new ArrayList<>();
        list.add(30);

        ActivityExample example = new ActivityExample();
        ActivityExample.Criteria criteria = example.createCriteria();
        criteria.andActivityClassEqualTo(cls);
        criteria.andDocStatusNotIn(list);
        criteria.andIsHeadEqualTo(0);

        return activityMapper.selectByExample(example);
    }

    @Override
    public Activity get(Integer id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(Activity activity) {
        if (activity.getId() == null) {
            activityMapper.insert(activity);
        } else {
            activityMapper.updateByPrimaryKeySelective(activity);
        }
    }

    /**
     * 首页最新活动
     * @return
     */
    @Override
    public List<Activity> latest() {
        return activityMapper.latest();
    }

}
