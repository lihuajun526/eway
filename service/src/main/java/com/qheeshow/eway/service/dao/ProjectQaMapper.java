package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.ProjectQa;
import com.qheeshow.eway.service.model.ProjectQaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectQaMapper {
    int countByExample(ProjectQaExample example);

    int deleteByExample(ProjectQaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectQa record);

    int insertSelective(ProjectQa record);

    List<ProjectQa> selectByExample(ProjectQaExample example);

    ProjectQa selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectQa record, @Param("example") ProjectQaExample example);

    int updateByExample(@Param("record") ProjectQa record, @Param("example") ProjectQaExample example);

    int updateByPrimaryKeySelective(ProjectQa record);

    int updateByPrimaryKey(ProjectQa record);

    List<ProjectQa> listByPage(ProjectQa commonQa);

    int countByPage(ProjectQa commonQa);

    List<ProjectQa> listQByProject(Integer projectid);
}