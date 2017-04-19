package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.CommonQa;

import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 17-3-13.
 */
public interface CommonQaService {

    List<CommonQa> list();

    Map<String, Object> listByPage(CommonQa commonQa, Integer index);

    CommonQa get(Integer id);

    void save(CommonQa commonQa);

}
