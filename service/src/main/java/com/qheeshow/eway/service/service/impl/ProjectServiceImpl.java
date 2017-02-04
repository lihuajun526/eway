package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.service.ProjectService;
import org.springframework.stereotype.Service;

/**
 * Created by lihuajun on 17-1-17.
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
    @Override public void save(Project project) {

        if (project.getId() == null) {
            project.setStatus(0);
            //save
        } else {
            //update
        }

    }
}
