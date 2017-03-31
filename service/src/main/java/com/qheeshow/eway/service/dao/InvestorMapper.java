package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.InvestorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InvestorMapper {
    int countByExample(InvestorExample example);

    int deleteByExample(InvestorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Investor record);

    int insertSelective(Investor record);

    List<Investor> selectByExample(InvestorExample example);

    Investor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Investor record, @Param("example") InvestorExample example);

    int updateByExample(@Param("record") Investor record, @Param("example") InvestorExample example);

    int updateByPrimaryKeySelective(Investor record);

    int updateByPrimaryKey(Investor record);

    List<Investor> listSuggest(Integer projectid);

    List<Investor> listByCondition(Investor investor);

    List<Investor> getCount(Investor investor);

    List<Investor> bestInvestor(Integer num);
}