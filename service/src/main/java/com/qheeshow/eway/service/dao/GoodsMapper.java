package com.qheeshow.eway.service.dao;

import com.qheeshow.eway.service.model.Goods;
import com.qheeshow.eway.service.model.GoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.qheeshow.eway.service.model.GoodsWithBLOBs;

public interface GoodsMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods
	 * @mbggenerated  Sun Feb 26 13:17:29 CST 2017
	 */
	int countByExample(GoodsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods
	 * @mbggenerated  Sun Feb 26 13:17:29 CST 2017
	 */
	int deleteByExample(GoodsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods
	 * @mbggenerated  Sun Feb 26 13:17:29 CST 2017
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods
	 * @mbggenerated  Sun Feb 26 13:17:29 CST 2017
	 */
	int insert(GoodsWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods
	 * @mbggenerated  Sun Feb 26 13:17:29 CST 2017
	 */
	int insertSelective(GoodsWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods
	 * @mbggenerated  Sun Feb 26 13:17:29 CST 2017
	 */
	List<GoodsWithBLOBs> selectByExampleWithBLOBs(GoodsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods
	 * @mbggenerated  Sun Feb 26 13:17:29 CST 2017
	 */
	List<Goods> selectByExample(GoodsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods
	 * @mbggenerated  Sun Feb 26 13:17:29 CST 2017
	 */
	GoodsWithBLOBs selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods
	 * @mbggenerated  Sun Feb 26 13:17:29 CST 2017
	 */
	int updateByExampleSelective(@Param("record") GoodsWithBLOBs record, @Param("example") GoodsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods
	 * @mbggenerated  Sun Feb 26 13:17:29 CST 2017
	 */
	int updateByExampleWithBLOBs(@Param("record") GoodsWithBLOBs record, @Param("example") GoodsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods
	 * @mbggenerated  Sun Feb 26 13:17:29 CST 2017
	 */
	int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods
	 * @mbggenerated  Sun Feb 26 13:17:29 CST 2017
	 */
	int updateByPrimaryKeySelective(GoodsWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods
	 * @mbggenerated  Sun Feb 26 13:17:29 CST 2017
	 */
	int updateByPrimaryKeyWithBLOBs(GoodsWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods
	 * @mbggenerated  Sun Feb 26 13:17:29 CST 2017
	 */
	int updateByPrimaryKey(Goods record);
}