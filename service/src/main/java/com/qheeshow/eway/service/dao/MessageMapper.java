package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.Message;
import com.qheeshow.eway.service.model.MessageExample;

import java.util.List;

import com.qheeshow.eway.service.model.Project;
import org.apache.ibatis.annotations.Param;

public interface MessageMapper {
    int countByExample(MessageExample example);

    int deleteByExample(MessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    List<Message> selectByExample(MessageExample example);

    Message selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    List<Message> listByCondition(Message message);

    List<Message> getCount(Message message);
}