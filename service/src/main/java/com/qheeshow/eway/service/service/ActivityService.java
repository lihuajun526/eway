package com.qheeshow.eway.service.service;

import com.google.zxing.WriterException;
import com.qheeshow.eway.service.model.Activity;
import com.qheeshow.eway.service.model.ActivitySign;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 2017/3/24.
 */
public interface ActivityService {

    List<Activity> listByCodition(Activity activity, Integer pageIndex, Integer pageSize);

    List<Activity> listByClass(Integer cls);

    Map<String, Object> listByClassAndPage(Activity activity);

    List<Activity> listByPage(Activity activity);

    Activity get(Integer id);

    void save(Activity activity) throws IOException, WriterException;

    List<Activity> latest(Integer num);

}
