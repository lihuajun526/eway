package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.Classinfo;

import java.util.List;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface ClassinfoService {

    List<Classinfo> listByParent(Integer pid);

    List<Classinfo> listByRoot(Integer rootid);

    Classinfo get(Integer id);

    void synData();

}
