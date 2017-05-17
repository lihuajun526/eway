package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.PostRecord;
import com.qheeshow.eway.service.model.PostRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PostRecordMapper {
    int countByExample(PostRecordExample example);

    int deleteByExample(PostRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PostRecord record);

    int insertSelective(PostRecord record);

    List<PostRecord> selectByExample(PostRecordExample example);

    PostRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PostRecord record, @Param("example") PostRecordExample example);

    int updateByExample(@Param("record") PostRecord record, @Param("example") PostRecordExample example);

    int updateByPrimaryKeySelective(PostRecord record);

    int updateByPrimaryKey(PostRecord record);

    List<PostRecord> listByUserAndToday(Integer userid);
}