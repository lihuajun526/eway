package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.ProjectQa;

import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 17-3-13.
 */
public interface ProjectQaService {

    List<ProjectQa> list();

    Map<String, Object> listByPage(ProjectQa commonQa, Integer index);

    ProjectQa get(Integer id);

    void save(ProjectQa commonQa);

    List<ProjectQa> listQByProject(Integer projectid);

    List<ProjectQa> listA(List<Integer> ids);

}
