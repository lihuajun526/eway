package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.Activity;
import com.qheeshow.eway.service.model.ActivitySign;

import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 2017/3/24.
 */
public interface ActivityService {

    List<Activity> listByCodition(Activity activity, Integer pageIndex, Integer pageSize);

    List<Activity> listByClass(Integer cls);

    Map<String, Object> listByClassAndPage(Activity activity);

    Activity get(Integer id);

    void save(Activity activity);

    List<Activity> latest();
}
