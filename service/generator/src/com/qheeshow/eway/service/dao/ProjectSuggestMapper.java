package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.ProjectSuggest;
import com.qheeshow.eway.service.model.ProjectSuggestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectSuggestMapper {
    int countByExample(ProjectSuggestExample example);

    int deleteByExample(ProjectSuggestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectSuggest record);

    int insertSelective(ProjectSuggest record);

    List<ProjectSuggest> selectByExample(ProjectSuggestExample example);

    ProjectSuggest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectSuggest record, @Param("example") ProjectSuggestExample example);

    int updateByExample(@Param("record") ProjectSuggest record, @Param("example") ProjectSuggestExample example);

    int updateByPrimaryKeySelective(ProjectSuggest record);

    int updateByPrimaryKey(ProjectSuggest record);
}