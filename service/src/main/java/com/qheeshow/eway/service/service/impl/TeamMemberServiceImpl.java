package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.TeamMemberMapper;
import com.qheeshow.eway.service.model.TeamMember;
import com.qheeshow.eway.service.model.TeamMemberExample;
import com.qheeshow.eway.service.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 17-2-4.
 */
@Service
public class TeamMemberServiceImpl implements TeamMemberService {

    @Autowired
    private TeamMemberMapper teamMemberMapper;

    @Override
    public void save(TeamMember teamMember) {
        if (teamMember.getId().intValue() == 0)
            teamMemberMapper.insert(teamMember);
        else
            teamMemberMapper.updateByPrimaryKeySelective(teamMember);
    }

    @Override
    public void del(Integer id) {
        teamMemberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public TeamMember get(Integer id) {


        return teamMemberMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TeamMember> listByProject(Integer projectid) {
        TeamMemberExample teamMemberExample = new TeamMemberExample();
        TeamMemberExample.Criteria criteria = teamMemberExample.createCriteria();
        criteria.andProjectidEqualTo(projectid);
        return teamMemberMapper.selectByExample(teamMemberExample);
    }
}
