package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.Activity;
import com.qheeshow.eway.service.model.ActivitySign;

import java.util.List;

/**
 * Created by lihuajun on 2017/3/24.
 */
public interface ActivityService {

    List<Activity> listByCodition(Activity activity, Integer pageIndex, Integer pageSize);

    List<Activity> listByClass(Integer cls);

    Activity get(Integer id);

    void save(Activity activity);
}
