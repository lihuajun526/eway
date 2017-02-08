package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.ProjectQa;

import java.util.List;

/**
 * Created by lihuajun on 17-2-8.
 */
public interface ProjectQaService{

    void save(ProjectQa projectQa);

    List<ProjectQa> listByStatus(Integer status);

}
