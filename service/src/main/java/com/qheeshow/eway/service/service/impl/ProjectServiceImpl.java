package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.ProjectMapper;
import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
        List<Project> list = new ArrayList<>();

        Project project1 = new Project();
        project1.setId(1);
        project1.setTitle("积分生活1");
        project1.setUsername("lihuajun1");
        project1.setCreateTime(new Date());

        Project project2 = new Project();
        project2.setId(2);
        project2.setTitle("积分生活2");
        project2.setUsername("lihuajun2");
        project2.setCreateTime(new Date());

        Project project3 = new Project();
        project3.setId(3);
        project3.setTitle("积分生活3");
        project3.setUsername("lihuajun3");
        project3.setCreateTime(new Date());

        list.add(project1);
        list.add(project2);
        list.add(project3);

        return list;
    }

    @Override public Project get(Integer id) {
        Project project1 = new Project();
        project1.setId(1);
        project1.setBp("http://www.baidu.com");
        project1.setBpName("计划书");
        project1.setLogo("https://www.baidu.com/img/baidu_jgylogo3.gif");
        project1.setPercent(12);
        project1.setReferee("介绍人");
        project1.setTitle("积分生活1");
        project1.setIndustry(1);
        project1.setArea(1);
        project1.setFinancingLimit(1);
        project1.setUsername("lihuajun1");
        project1.setCreateTime(new Date());
        project1.setDescription("desc");
        project1.setVideoLink("http://www.caitu99.com");
        project1.setProLink("http://www.baidu.com");
        return project1;
    }

    @Override public List<Project> listByCondition(Integer type, Integer areaid, Integer financingLimit, Integer industry,
            String keyword, Integer pageIndex) {
        // TODO: 17-2-7 分页
        Integer pageSize;
        return new ArrayList<Project>();
    }
}
