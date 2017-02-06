package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
