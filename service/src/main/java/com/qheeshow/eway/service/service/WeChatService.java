package com.qheeshow.eway.service.service;

import javax.servlet.http.HttpSession;

import com.qheeshow.eway.service.model.User;

public interface WeChatService {

	public User isBand(String code,HttpSession session);
	
	public User regist(HttpSession session,User user);
}
