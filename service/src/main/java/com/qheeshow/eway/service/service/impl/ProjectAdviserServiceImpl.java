package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.common.constant.ExceptionTypeEnum;
import com.qheeshow.eway.common.exception.CommonException;
import com.qheeshow.eway.service.dao.InvestorMapper;
import com.qheeshow.eway.service.dao.ProjectAdviserMapper;
import com.qheeshow.eway.service.model.*;
import com.qheeshow.eway.service.service.ProjectAdviserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by lihuajun on 17-2-8.
 */
@Service
public class ProjectAdviserServiceImpl implements ProjectAdviserService {

    @Autowired
    private ProjectAdviserMapper projectAdviserMapper;
    @Autowired
    private InvestorMapper investorMapper;

    @Override
    public void apply(Integer projectid, Integer userid) throws CommonException {
        ProjectAdviser projectAdviser = new ProjectAdviser();
        projectAdviser.setProjectid(projectid);
        projectAdviser.setUserid(userid);

        InvestorExample investorExample = new InvestorExample();
        InvestorExample.Criteria criteria = investorExample.createCriteria();
        criteria.andUseridEqualTo(userid);
        List<Investor> list = investorMapper.selectByExample(investorExample);
        Investor investor = list.size() > 0 ? list.get(0) : null;
        if (investor == null) {
            throw new CommonException(ExceptionTypeEnum.Adviser_Info_Not_Full);
        }
        if (investor.getAuthStatus().intValue() != 2) {//尚未认证
            throw new CommonException(ExceptionTypeEnum.Investor_Not_Auth_ERROR);
        }
        if (isAdviser(projectAdviser)) {//如果已申请过,则抛异常
            throw new CommonException(ExceptionTypeEnum.Project_Adviser_Apply_Exist_ERROR);
        }
        if (list(projectid).size() >= 10) {//顾问人数已满
            throw new CommonException(ExceptionTypeEnum.Project_Adviser_Full_ERROR);
        }

        projectAdviser.setStatus(1);
        projectAdviserMapper.insert(projectAdviser);

    }

    @Override
    public List<Investor> list(Integer projectid) {

        ProjectAdviserExample projectAdviserExample = new ProjectAdviserExample();
        ProjectAdviserExample.Criteria criteria = projectAdviserExample.createCriteria();
        criteria.andProjectidEqualTo(projectid);
        criteria.andStatusEqualTo(2);
        List<ProjectAdviser> list = projectAdviserMapper.selectByExample(projectAdviserExample);
        List<Integer> ids = new ArrayList<>();
        for (ProjectAdviser projectAdviser : list) {
            ids.add(projectAdviser.getUserid());
        }
        if (ids.size() == 0)
            return new ArrayList<>();
        InvestorExample investorExample = new InvestorExample();
        InvestorExample.Criteria criteria1 = investorExample.createCriteria();
        criteria1.andUseridIn(ids);
        return investorMapper.selectByExample(investorExample);
    }

    @Override
    public List<ProjectAdviser> listByStatus(Integer status) {
        return null;
    }

    @Override
    public Map<String, Object> listByStatusAndPage(ProjectAdviser projectAdviser) {
        Map<String, Object> map = new HashMap<>();
        List<ProjectAdviser> list = projectAdviserMapper.listByStatusAndPage(projectAdviser);
        Integer count = projectAdviserMapper.countByStatusAndPage(projectAdviser);

        map.put("projectAdvisers", list);
        map.put("count", count);

        return map;
    }

    @Override
    public ProjectAdviser getByProjectAndUser(Integer projectid, Integer userid) {
        ProjectAdviserExample projectAdviserExample = new ProjectAdviserExample();
        ProjectAdviserExample.Criteria criteria = projectAdviserExample.createCriteria();
        criteria.andProjectidEqualTo(projectid);
        criteria.andUseridEqualTo(userid);
        List<ProjectAdviser> list = projectAdviserMapper.selectByExample(projectAdviserExample);
        if (list != null && list.size() > 0)
            return list.get(0);
        return null;
    }

    @Override
    public void save(ProjectAdviser projectAdviser) {
        // TODO: 17-2-8 保存和更新
    }

    @Override
    public Boolean isAbleToBeAdviser(ProjectAdviser projectAdviser) {

        if (null != getByProjectAndUser(projectAdviser.getProjectid(), projectAdviser.getUserid()))
            return false;

        // TODO: 17-2-8 该项目有多少顾问
        List<ProjectAdviser> list = null;

        if (list.size() >= 10)
            return false;

        return true;
    }

    @Override
    public Boolean isAdviser(ProjectAdviser projectAdviser) {

        projectAdviser = getByProjectAndUser(projectAdviser.getProjectid(), projectAdviser.getUserid());
        if (projectAdviser == null)
            return false;
        return true;
    }

    @Override
    public void update(ProjectAdviser projectAdviser) {
        projectAdviserMapper.updateByPrimaryKeySelective(projectAdviser);
    }

    @Override
    public List<Investor> listByProject(Integer projectid) {

        ProjectAdviserExample example = new ProjectAdviserExample();
        ProjectAdviserExample.Criteria criteria = example.createCriteria();
        criteria.andProjectidEqualTo(projectid);
        List<ProjectAdviser> list = projectAdviserMapper.selectByExample(example);

        List<Integer> ids = new ArrayList<>();
        for (ProjectAdviser projectAdviser : list)
            ids.add(projectAdviser.getId());

        if (ids.size() == 0)
            return new ArrayList<>();

        InvestorExample example1 = new InvestorExample();
        InvestorExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andIdIn(ids);
        return investorMapper.selectByExample(example1);
    }
}
