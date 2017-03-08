package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.Project;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface ProjectService {

    void save(Project project);

    List<Project> listByStatus(Integer status);

    Project get(Integer id);

    List<Project> listByCondition(Integer type, Integer areaid,
            Integer financingLimit, Integer industry, Integer pageIndex);

    List<Project> search(String keyword);

}
