package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.Xwcmclassinfo;
import com.qheeshow.eway.service.model.XwcmclassinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XwcmclassinfoMapper {
    int countByExample(XwcmclassinfoExample example);

    int deleteByExample(XwcmclassinfoExample example);

    int deleteByPrimaryKey(Integer classinfoid);

    int insert(Xwcmclassinfo record);

    int insertSelective(Xwcmclassinfo record);

    List<Xwcmclassinfo> selectByExample(XwcmclassinfoExample example);

    Xwcmclassinfo selectByPrimaryKey(Integer classinfoid);

    int updateByExampleSelective(@Param("record") Xwcmclassinfo record, @Param("example") XwcmclassinfoExample example);

    int updateByExample(@Param("record") Xwcmclassinfo record, @Param("example") XwcmclassinfoExample example);

    int updateByPrimaryKeySelective(Xwcmclassinfo record);

    int updateByPrimaryKey(Xwcmclassinfo record);
}