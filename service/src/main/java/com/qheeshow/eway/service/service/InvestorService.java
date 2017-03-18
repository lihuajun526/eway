package com.qheeshow.eway.service.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.qheeshow.eway.service.model.Investor;

public interface InvestorService {

	public void save(Investor investor);
	
	public void update(Investor investor);
	
	public Investor detail(Integer id);
	
	public List<Investor> list(Investor investor,HttpSession session);

	Investor get(Integer id);
	
}
