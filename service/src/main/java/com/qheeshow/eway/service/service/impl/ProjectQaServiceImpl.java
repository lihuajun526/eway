package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.ProjectQaMapper;
import com.qheeshow.eway.service.model.ProjectQa;
import com.qheeshow.eway.service.service.ProjectQaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 17-2-8.
 */
@Service
public class ProjectQaServiceImpl implements ProjectQaService {

    @Autowired
    private ProjectQaMapper projectQaMapper;

    @Override public void save(ProjectQa projectQa) {

    }

    @Override public List<ProjectQa> listByStatus(Integer status) {
        return null;
    }
}
