package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.ProjectFollow;
import com.qheeshow.eway.service.model.ProjectFollowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectFollowMapper {
    int countByExample(ProjectFollowExample example);

    int deleteByExample(ProjectFollowExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectFollow record);

    int insertSelective(ProjectFollow record);

    List<ProjectFollow> selectByExample(ProjectFollowExample example);

    ProjectFollow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectFollow record, @Param("example") ProjectFollowExample example);

    int updateByExample(@Param("record") ProjectFollow record, @Param("example") ProjectFollowExample example);

    int updateByPrimaryKeySelective(ProjectFollow record);

    int updateByPrimaryKey(ProjectFollow record);
}