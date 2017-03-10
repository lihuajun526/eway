package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.constant.ExceptionTypeEnum;
import com.qheeshow.eway.service.dao.InvestorMapper;
import com.qheeshow.eway.service.dao.ProjectFollowMapper;
import com.qheeshow.eway.service.exception.CommonException;
import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.InvestorExample;
import com.qheeshow.eway.service.model.ProjectFollow;
import com.qheeshow.eway.service.model.ProjectFollowExample;
import com.qheeshow.eway.service.service.ProjectFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 17-2-8.
 */
@Service
public class ProjectFollowServiceImpl implements ProjectFollowService {

    @Autowired
    private ProjectFollowMapper projectFollowMapper;
    @Autowired
    private InvestorMapper investorMapper;

    @Override public void follow(ProjectFollow projectFollow) throws CommonException {

        if (this.isFollow(projectFollow))
            throw new CommonException(ExceptionTypeEnum.Project_Follow_Exist_ERROR);

        projectFollowMapper.insert(projectFollow);
    }

    @Override public void unFollow(ProjectFollow projectFollow) {
        ProjectFollowExample example = new ProjectFollowExample();
        ProjectFollowExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(projectFollow.getUserid());
        criteria.andProjectidEqualTo(projectFollow.getProjectid());
        projectFollowMapper.deleteByExample(example);
    }

    @Override public boolean isFollow(ProjectFollow projectFollow) {
        ProjectFollowExample example = new ProjectFollowExample();
        ProjectFollowExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(projectFollow.getUserid());
        criteria.andProjectidEqualTo(projectFollow.getProjectid());
        List<ProjectFollow> list = projectFollowMapper.selectByExample(example);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    @Override public List<Investor> list(Integer projectid) {
        ProjectFollowExample example = new ProjectFollowExample();
        ProjectFollowExample.Criteria criteria = example.createCriteria();
        criteria.andProjectidEqualTo(projectid);
        List<ProjectFollow> list = projectFollowMapper.selectByExample(example);
        List<Integer> ids = new ArrayList<>();
        for (ProjectFollow projectFollow : list) {
            ids.add(projectFollow.getUserid());
        }
        InvestorExample investorExample = new InvestorExample();
        InvestorExample.Criteria criteria1 = investorExample.createCriteria();
        criteria1.andUserIdIn(ids);
        return investorMapper.selectByExample(investorExample);
    }
}
