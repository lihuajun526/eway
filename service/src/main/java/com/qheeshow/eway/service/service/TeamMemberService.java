package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.TeamMember;

import java.util.List;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface TeamMemberService {

    void save(TeamMember teamMember);

    void del(Integer id);

    TeamMember get(Integer id);

    List<TeamMember> listByProject(Integer projectid);

}
