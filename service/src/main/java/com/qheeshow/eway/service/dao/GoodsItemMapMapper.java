package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.GoodsItemMap;
import com.qheeshow.eway.service.model.GoodsItemMapExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsItemMapMapper {
    int countByExample(GoodsItemMapExample example);

    int deleteByExample(GoodsItemMapExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsItemMap record);

    int insertSelective(GoodsItemMap record);

    List<GoodsItemMap> selectByExample(GoodsItemMapExample example);

    GoodsItemMap selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsItemMap record, @Param("example") GoodsItemMapExample example);

    int updateByExample(@Param("record") GoodsItemMap record, @Param("example") GoodsItemMapExample example);

    int updateByPrimaryKeySelective(GoodsItemMap record);

    int updateByPrimaryKey(GoodsItemMap record);

    List<GoodsItemMap> listByGoods(Integer goodsid);
}