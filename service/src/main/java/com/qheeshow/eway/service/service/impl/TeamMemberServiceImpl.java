package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.model.TeamMember;
import com.qheeshow.eway.service.service.TeamMemberService;
import org.springframework.stereotype.Service;

/**
 * Created by lihuajun on 17-2-4.
 */
@Service
public class TeamMemberServiceImpl implements TeamMemberService {
    @Override public void save(TeamMember teamMember) {

    }

    @Override public void del(Integer id) {

    }

    @Override public TeamMember get(Integer id) {
        TeamMember teamMember = new TeamMember();
        teamMember.setId(1);
        teamMember.setIsFounder(1);
        teamMember.setMemberName("李华君");
        teamMember.setPhoto("https://www.baidu.com/img/bd_logo1.png");
        teamMember.setPosition("ceo");
        teamMember.setProjectid(1);
        teamMember.setSummary("简介");

        return teamMember;
    }
}
