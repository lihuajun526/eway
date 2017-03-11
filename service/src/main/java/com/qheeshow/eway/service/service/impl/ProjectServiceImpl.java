package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.ProjectMapper;
import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.model.ProjectExample;
import com.qheeshow.eway.service.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 17-1-17.
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public void save(Project project) {

        if (project.getId().intValue() == 0) {
            project.setStatus(0);
            projectMapper.insert(project);
        } else {
            projectMapper.updateByPrimaryKeySelective(project);
        }

    }

    @Override
    public List<Project> listByStatus(Integer status) {
        ProjectExample projectExample = new ProjectExample();
        ProjectExample.Criteria criteria = projectExample.createCriteria();
        criteria.andStatusEqualTo(status);
        return projectMapper.selectByExample(projectExample);
    }

    @Override
    public Project get(Integer id) {
        return projectMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> listByCondition(Integer type, Integer areaid, Integer financingLimit, Integer industry,
                                               Integer pageIndex, Integer pageSize) {
        Project project = new Project();
        if (type > 0)
            project.setType(type);
        if (areaid > 0)
            project.setArea(areaid);
        if (financingLimit > 0)
            project.setFinancingLimit(financingLimit);
        if (industry > 0)
            project.setIndustry(industry);
        project.setPageSize(pageSize);
        project.setStartRow((pageIndex - 1) * project.getPageSize());
        Map<String, Object> map = new HashMap<>();
        map.put("projects", projectMapper.listByCondition(project));
        map.put("count", projectMapper.getCount(project).size());
        return map;
    }

    @Override
    public List<Project> search(String keyword) {

        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andTitleLike(keyword);

        return projectMapper.selectByExample(example);
    }
}
