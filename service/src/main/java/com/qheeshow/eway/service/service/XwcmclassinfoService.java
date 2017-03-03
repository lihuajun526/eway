package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.Xwcmclassinfo;

import java.util.List;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface XwcmclassinfoService {

    List<Xwcmclassinfo> listByParent(Integer pid);

    List<Xwcmclassinfo> listByRoot(Integer rootid);

    Xwcmclassinfo get(Integer id);


}
