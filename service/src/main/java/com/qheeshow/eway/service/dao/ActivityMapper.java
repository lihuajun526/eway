package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.Activity;
import com.qheeshow.eway.service.model.ActivityExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ActivityMapper {
    int countByExample(ActivityExample example);

    int deleteByExample(ActivityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    List<Activity> selectByExampleWithBLOBs(ActivityExample example);

    List<Activity> selectByExample(ActivityExample example);

    Activity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Activity record, @Param("example") ActivityExample example);

    int updateByExampleWithBLOBs(@Param("record") Activity record, @Param("example") ActivityExample example);

    int updateByExample(@Param("record") Activity record, @Param("example") ActivityExample example);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKeyWithBLOBs(Activity record);

    int updateByPrimaryKey(Activity record);

    List<Activity> listByCondition(Activity activity);

    List<Activity> latest(Integer num);

    List<Activity> listByClassAndPage(Activity activity);

    Integer countByClassAndPage(Activity activity);
}