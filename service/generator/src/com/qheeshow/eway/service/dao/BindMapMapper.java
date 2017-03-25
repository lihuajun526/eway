package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.BindMap;
import com.qheeshow.eway.service.model.BindMapExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BindMapMapper {
    int countByExample(BindMapExample example);

    int deleteByExample(BindMapExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BindMap record);

    int insertSelective(BindMap record);

    List<BindMap> selectByExample(BindMapExample example);

    BindMap selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BindMap record, @Param("example") BindMapExample example);

    int updateByExample(@Param("record") BindMap record, @Param("example") BindMapExample example);

    int updateByPrimaryKeySelective(BindMap record);

    int updateByPrimaryKey(BindMap record);
}