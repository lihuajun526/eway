package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.*;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ProjectMapper {
    int countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Project record);

    int insertSelective(Project record);

    List<Project> selectByExampleWithBLOBs(ProjectExample example);

    List<Project> selectByExample(ProjectExample example);

    Project selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExampleWithBLOBs(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKeyWithBLOBs(Project record);

    int updateByPrimaryKey(Project record);

    List<Project> listByCondition(Project project);

    List<Project> getCount(Project project);

    List<Project> listSuggest(ProjectSuggest projectSuggest);

    List<Project> listSuggestCount(ProjectSuggest projectSuggest);

    List<Project> listFollow(ProjectFollow projectFollow);

    List<Project> listFollowCount(ProjectFollow projectFollow);

    List<Project> listAdviser(ProjectAdviser projectAdviser);

    List<Project> listAdviserCount(ProjectAdviser projectAdviser);

    List<Project> listPayProject(Project project);

    int countPayProject(Project project);

    List<Project> listByStatusAndPage(Project project);

    int countByStatusAndPage(Project project);
}