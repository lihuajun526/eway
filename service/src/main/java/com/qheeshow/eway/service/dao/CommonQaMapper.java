package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.CommonQa;
import com.qheeshow.eway.service.model.CommonQaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonQaMapper {
    int countByExample(CommonQaExample example);

    int deleteByExample(CommonQaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommonQa record);

    int insertSelective(CommonQa record);

    List<CommonQa> selectByExample(CommonQaExample example);

    CommonQa selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommonQa record, @Param("example") CommonQaExample example);

    int updateByExample(@Param("record") CommonQa record, @Param("example") CommonQaExample example);

    int updateByPrimaryKeySelective(CommonQa record);

    int updateByPrimaryKey(CommonQa record);

    List<CommonQa> listByPage(CommonQa commonQa);

    int countByPage(CommonQa commonQa);
}