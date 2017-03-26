package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.InvestorFollow;
import com.qheeshow.eway.service.model.InvestorFollowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InvestorFollowMapper {
    int countByExample(InvestorFollowExample example);

    int deleteByExample(InvestorFollowExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InvestorFollow record);

    int insertSelective(InvestorFollow record);

    List<InvestorFollow> selectByExample(InvestorFollowExample example);

    InvestorFollow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InvestorFollow record, @Param("example") InvestorFollowExample example);

    int updateByExample(@Param("record") InvestorFollow record, @Param("example") InvestorFollowExample example);

    int updateByPrimaryKeySelective(InvestorFollow record);

    int updateByPrimaryKey(InvestorFollow record);
}