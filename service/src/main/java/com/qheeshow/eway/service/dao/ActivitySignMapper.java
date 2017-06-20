package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.ActivitySign;
import com.qheeshow.eway.service.model.ActivitySignExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivitySignMapper {
    int countByExample(ActivitySignExample example);

    int deleteByExample(ActivitySignExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivitySign record);

    int insertSelective(ActivitySign record);

    List<ActivitySign> selectByExample(ActivitySignExample example);

    ActivitySign selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivitySign record, @Param("example") ActivitySignExample example);

    int updateByExample(@Param("record") ActivitySign record, @Param("example") ActivitySignExample example);

    int updateByPrimaryKeySelective(ActivitySign record);

    int updateByPrimaryKey(ActivitySign record);

    List<ActivitySign> listByActivityAndPage(ActivitySign activitySign);

    int countByActivityAndPage(ActivitySign activitySign);
}