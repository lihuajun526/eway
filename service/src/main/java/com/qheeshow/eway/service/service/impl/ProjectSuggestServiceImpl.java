package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.ProjectSuggestMapper;
import com.qheeshow.eway.service.model.ProjectSuggest;
import com.qheeshow.eway.service.model.ProjectSuggestExample;
import com.qheeshow.eway.service.service.ProjectSuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 2017/3/19.
 */
@Service
public class ProjectSuggestServiceImpl implements ProjectSuggestService {

    @Autowired
    private ProjectSuggestMapper projectSuggestMapper;

    @Override
    public void del(ProjectSuggest projectSuggest) {
        ProjectSuggestExample example = new ProjectSuggestExample();
        ProjectSuggestExample.Criteria criteria = example.createCriteria();

        criteria.andProjectidEqualTo(projectSuggest.getProjectid());
        criteria.andInvestoridEqualTo(projectSuggest.getInvestorid());

        ProjectSuggest projectSuggest1 = new ProjectSuggest();
        projectSuggest1.setStatus(1);

        projectSuggestMapper.updateByExampleSelective(projectSuggest1, example);
    }

    @Override
    public void save(ProjectSuggest projectSuggest) {
        if (projectSuggest.getId() == null) {
            projectSuggestMapper.insert(projectSuggest);
        } else {
            projectSuggestMapper.updateByPrimaryKeySelective(projectSuggest);
        }
    }

    @Override
    public void addSuggest(Integer projectid, String sIds) {
        List<Integer> ids = new ArrayList<>();
        for (String id : sIds.split("#"))
            ids.add(Integer.valueOf(id));
        ProjectSuggestExample example = new ProjectSuggestExample();
        ProjectSuggestExample.Criteria criteria = example.createCriteria();
        criteria.andProjectidEqualTo(projectid);
        criteria.andInvestoridIn(ids);
        List<ProjectSuggest> list = projectSuggestMapper.selectByExample(example);

        for (String id : sIds.split("#")) {
            Integer investorid = Integer.valueOf(id);
            boolean flag = true;
            for (ProjectSuggest projectSuggest : list) {
                if (projectSuggest.getInvestorid().intValue() == investorid.intValue() && projectSuggest.getStatus().intValue() == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ProjectSuggest projectSuggest = new ProjectSuggest();
                projectSuggest.setInvestorid(investorid);
                projectSuggest.setProjectid(projectid);
                projectSuggest.setStatus(0);
                this.save(projectSuggest);
            }
        }

    }
}
