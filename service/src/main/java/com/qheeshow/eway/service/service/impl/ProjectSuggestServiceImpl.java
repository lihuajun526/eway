package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.ProjectSuggestMapper;
import com.qheeshow.eway.service.model.ProjectSuggest;
import com.qheeshow.eway.service.model.ProjectSuggestExample;
import com.qheeshow.eway.service.service.ProjectSuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
