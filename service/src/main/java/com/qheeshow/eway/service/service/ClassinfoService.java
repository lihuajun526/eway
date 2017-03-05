package com.qheeshow.eway.service.service;

import java.util.List;
import java.util.Map;

import com.qheeshow.eway.service.model.Classinfo;
import com.qheeshow.eway.service.model.XWClassInfo;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface ClassinfoService {

    List<Classinfo> listByParent(Integer pid);

    List<Classinfo> listByRoot(Integer rootid);

    Classinfo get(Integer id);

    void synData();

    public Map<String,List<XWClassInfo>> getTypeList();
}
