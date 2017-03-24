package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.Activity;

import java.util.List;

/**
 * Created by lihuajun on 2017/3/24.
 */
public interface ActivityService {

    List<Activity> listByCodition(Activity activity, Integer pageIndex, Integer pageSize);

    Activity get(Integer id);
}
