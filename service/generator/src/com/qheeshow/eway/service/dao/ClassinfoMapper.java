package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.Classinfo;
import com.qheeshow.eway.service.model.ClassinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassinfoMapper {
    int countByExample(ClassinfoExample example);

    int deleteByExample(ClassinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Classinfo record);

    int insertSelective(Classinfo record);

    List<Classinfo> selectByExample(ClassinfoExample example);

    Classinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Classinfo record, @Param("example") ClassinfoExample example);

    int updateByExample(@Param("record") Classinfo record, @Param("example") ClassinfoExample example);

    int updateByPrimaryKeySelective(Classinfo record);

    int updateByPrimaryKey(Classinfo record);
}