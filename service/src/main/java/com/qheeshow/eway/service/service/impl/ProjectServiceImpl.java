package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.ProjectMapper;
import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.model.ProjectExample;
import com.qheeshow.eway.service.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 17-1-17.
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override public void save(Project project) {

        if (project.getId().intValue() == 0) {
            project.setStatus(0);
            projectMapper.insert(project);
        } else {
            projectMapper.updateByPrimaryKeySelective(project);
        }

    }

    @Override public List<Project> listByStatus(Integer status) {
        ProjectExample projectExample = new ProjectExample();
        ProjectExample.Criteria criteria = projectExample.createCriteria();
        criteria.andStatusEqualTo(status);
        return projectMapper.selectByExample(projectExample);
    }

    @Override public Project get(Integer id) {
        return projectMapper.selectByPrimaryKey(id);
    }

    @Override public List<Project> listByCondition(Integer type, Integer areaid, Integer financingLimit, Integer industry,
            Integer pageIndex) {

        Project project = new Project();
        project.setType(type);
        project.setArea(areaid);
        project.setFinancingLimit(financingLimit);
        project.setIndustry(industry);
        project.setPageSize(20);
        project.setStartRow((pageIndex - 1) * project.getPageSize());

        return projectMapper.listByCondition(project);
    }

    @Override public List<Project> search(String keyword) {

        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andTitleLike(keyword);

        return projectMapper.selectByExample(example);
    }
}
