package com.qheeshow.eway.service.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.qheeshow.eway.service.model.InvestorWithBLOBs;

public interface InvestorService {

	public void submit(InvestorWithBLOBs investor);
	
	public void update(InvestorWithBLOBs investor);
	
	public InvestorWithBLOBs detail(Integer id);
	
	public List<InvestorWithBLOBs> list(InvestorWithBLOBs investor,HttpSession session);
	
}
