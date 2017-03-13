package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.Project;

import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface ProjectService {

    void save(Project project);

    List<Project> listByStatus(Integer status);

    Project get(Integer id);

    Map<String, Object> listByCondition(Integer type, Integer areaid,
            Integer financingLimit, Integer industry, Integer pageIndex, Integer pageSize);

    List<Project> search(String keyword);

    List<Project> listByUser(Integer userid);

}
