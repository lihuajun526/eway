package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.BindMap;

import java.util.List;

/**
 * Created by lihuajun on 2017/3/25.
 */
public interface BindMapService {

    BindMap getByBindId(String bindId);

    void save(BindMap bindMap);

    List<BindMap> listBindRecord(BindMap bindMap);
}
