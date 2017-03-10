package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.ProjectAdviser;
import com.qheeshow.eway.service.model.ProjectAdviserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectAdviserMapper {
    int countByExample(ProjectAdviserExample example);

    int deleteByExample(ProjectAdviserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectAdviser record);

    int insertSelective(ProjectAdviser record);

    List<ProjectAdviser> selectByExample(ProjectAdviserExample example);

    ProjectAdviser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectAdviser record, @Param("example") ProjectAdviserExample example);

    int updateByExample(@Param("record") ProjectAdviser record, @Param("example") ProjectAdviserExample example);

    int updateByPrimaryKeySelective(ProjectAdviser record);

    int updateByPrimaryKey(ProjectAdviser record);
}