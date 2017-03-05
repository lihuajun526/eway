package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.TeamMember;
import com.qheeshow.eway.service.model.TeamMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeamMemberMapper {
    int countByExample(TeamMemberExample example);

    int deleteByExample(TeamMemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TeamMember record);

    int insertSelective(TeamMember record);

    List<TeamMember> selectByExample(TeamMemberExample example);

    TeamMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TeamMember record, @Param("example") TeamMemberExample example);

    int updateByExample(@Param("record") TeamMember record, @Param("example") TeamMemberExample example);

    int updateByPrimaryKeySelective(TeamMember record);

    int updateByPrimaryKey(TeamMember record);
}