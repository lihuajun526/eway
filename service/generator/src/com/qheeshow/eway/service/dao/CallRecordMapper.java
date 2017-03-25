package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.CallRecord;
import com.qheeshow.eway.service.model.CallRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CallRecordMapper {
    int countByExample(CallRecordExample example);

    int deleteByExample(CallRecordExample example);

    int deleteByPrimaryKey(String uniqueId);

    int insert(CallRecord record);

    int insertSelective(CallRecord record);

    List<CallRecord> selectByExample(CallRecordExample example);

    CallRecord selectByPrimaryKey(String uniqueId);

    int updateByExampleSelective(@Param("record") CallRecord record, @Param("example") CallRecordExample example);

    int updateByExample(@Param("record") CallRecord record, @Param("example") CallRecordExample example);

    int updateByPrimaryKeySelective(CallRecord record);

    int updateByPrimaryKey(CallRecord record);
}