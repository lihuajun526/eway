package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.BindMap;

/**
 * Created by lihuajun on 2017/3/25.
 */
public interface BindMapService {

    BindMap getByBindId(String bindId);

    void save(BindMap bindMap);

}
