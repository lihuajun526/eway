package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.ProjectMapper;
import com.qheeshow.eway.service.model.*;
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

        if (project.getId() == null) {
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
    public Map<String, Object> listByStatusAndPage(Project project) {
        Map<String, Object> map = new HashMap<>();
        map.put("projects", projectMapper.listByStatusAndPage(project));
        map.put("count", projectMapper.countByStatusAndPage(project));
        return map;
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

    @Override
    public List<Project> listByUser(Integer userid) {
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userid);

        return projectMapper.selectByExample(example);
    }

    @Override
    public Map<String, Object> listSuggest(ProjectSuggest projectSuggest) {
        Map<String, Object> map = new HashMap<>();
        List<Project> projects = projectMapper.listSuggest(projectSuggest);
        map.put("projects", projects);
        map.put("count", projectMapper.listSuggestCount(projectSuggest).size());
        return map;
    }

    @Override
    public Map<String, Object> listFollow(ProjectFollow projectFollow) {
        Map<String, Object> map = new HashMap<>();
        List<Project> projects = projectMapper.listFollow(projectFollow);
        map.put("projects", projects);
        map.put("count", projectMapper.listFollowCount(projectFollow).size());
        return map;
    }

    @Override
    public Map<String, Object> listAdviser(ProjectAdviser projectAdviser) {
        Map<String, Object> map = new HashMap<>();
        List<Project> projects = projectMapper.listAdviser(projectAdviser);
        map.put("projects", projects);
        map.put("count", projectMapper.listAdviserCount(projectAdviser).size());
        return map;
    }

    @Override
    public void recommendOrNot(Integer status, Integer projectid) {
        Project project = new Project();
        project.setId(projectid);
        project.setRecommend(status);
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public Map<String, Object> listPayProject(Project project) {

        Map<String, Object> map = new HashMap<>();
        List<Project> projects = projectMapper.listPayProject(project);
        map.put("projects", projects);
        map.put("count", projectMapper.countPayProject(project));
        return map;
    }

    @Override
    public List<Project> bestSuggest(Integer num) {
        return projectMapper.bestSuggest(num);
    }

    @Override
    public List<Project> bestCase(Integer num) {
        return projectMapper.bestCase(num);
    }

    @Override
    public void delCase(Integer projectid) {
        Project project = new Project();
        project.setId(projectid);
        project.setIsCase(0);
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public void addCase(Integer projectid) {
        Project project = new Project();
        project.setId(projectid);
        project.setIsCase(1);
        projectMapper.updateByPrimaryKeySelective(project);
    }

}
