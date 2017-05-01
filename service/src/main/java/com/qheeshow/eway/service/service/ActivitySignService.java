package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.ActivitySign;

import java.util.List;

/**
 * Created by lihuajun on 2017/3/25.
 */
public interface ActivitySignService {

    int save(ActivitySign activitySign);

    boolean issign(ActivitySign activitySign);

    List<ActivitySign> listByActivity(Integer activityid);

}
