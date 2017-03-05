package com.qheeshow.eway.service.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qheeshow.eway.service.dao.InvestorMapper;
import com.qheeshow.eway.service.model.InvestorExample;
import com.qheeshow.eway.service.model.InvestorWithBLOBs;
import com.qheeshow.eway.service.service.InvestorService;

@Service
public class InvestorServiceImpl implements InvestorService{
	
	@Autowired
	InvestorMapper investorMapper;
	
	@Override
	public void submit(InvestorWithBLOBs investor){
		investor.setCreateTime(new Date());
		investorMapper.insertSelective(investor);
	}
	
	@Override
	public void update(InvestorWithBLOBs investor){
		investorMapper.updateByPrimaryKeySelective(investor);
	}
	
	@Override
	public InvestorWithBLOBs detail(Integer id){
		InvestorWithBLOBs investor = investorMapper.selectByPrimaryKey(id);
		return investor;
	}
	
	@Override
	public List<InvestorWithBLOBs> list(InvestorWithBLOBs investor,HttpSession session){
		Integer status = investor.getStatus();
		InvestorExample example = new InvestorExample();
		InvestorExample.Criteria criteria = example.createCriteria();
		//列表查看权限控制
		if(status != null){
			if((status == 1 && session.getAttribute("roleid").toString().equals("2")) || status == 2 || status == 3){
				criteria.andStatusEqualTo(investor.getStatus());
			}else{
				criteria.andStatusEqualTo(-1);
			}
		}else{
			if(session.getAttribute("roleid").toString().equals("2")){
				criteria.andStatusEqualTo(-1);
			}
		}
		example.setOrderByClause("create_time");
		List<InvestorWithBLOBs> investors = investorMapper.selectByExampleWithBLOBs(example);
		return investors;
	}

}
