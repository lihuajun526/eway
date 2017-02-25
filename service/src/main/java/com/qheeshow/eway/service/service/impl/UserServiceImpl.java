package com.qheeshow.eway.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qheeshow.eway.common.util.MD5Util;
import com.qheeshow.eway.service.dao.UserMapper;
import com.qheeshow.eway.service.model.MailBean;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.model.UserExample;
import com.qheeshow.eway.service.service.MailService;
import com.qheeshow.eway.service.service.UserService;

/**
 * Created by lihuajun on 17-1-22.
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	MailService mailService;

	@Override 
	public boolean isRegist(User user){
		UserExample example = new UserExample();
		example.or().andMobileEqualTo(user.getMobile());
		if(user.getUsername() != null){
			example.or().andUsernameEqualTo(user.getUsername());
		}
		List<User> users = userMapper.selectByExample(example);
		if(users.size() > 0){
			return false;
		}else{
			return true;
		}
	}
	
    @Override 
    public boolean regist(User user){
    	if(isRegist(user)){
    		user.setStatus((byte) 1);
        	user.setPassword(MD5Util.MD5(user.getPassword()));
        	user.setLogintimes(0);
        	user.setPrice(0);
        	user.setIsdeleted((byte) 0);
        	user.setPasswordlev(0);
        	user.setSupervisorid(0);
    		userMapper.insert(user);
    		return true;
    	}else{
    		return false;
    	}
    }
    
    @Override 
    public void changePassword(User user){
    	UserExample example = new UserExample();
    	example.createCriteria().andMobileEqualTo(user.getMobile());
    	user.setPassword(MD5Util.MD5(user.getPassword()));
    	userMapper.updateByExampleSelective(user, example);
    }
    
    @Override 
    public List<User> login(User user){
    	
//		MailBean mailInfo = new MailBean();
//		mailInfo.setMailServerHost("smtp.163.com");
//		mailInfo.setUserName("zhuyue_nonglin@163.com");
//		mailInfo.setPassword("yue12580");
//		mailInfo.setFromAddress("zhuyue_nonglin@163.com");
//		mailInfo.setToAddress("1065443674@qq.com");
//		mailInfo.setSubject("你好");
//		mailInfo.setContent("大家好");
//		mailService.sendTextMail(mailInfo);
		
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(user.getUsername());
		criteria.andPasswordEqualTo(MD5Util.MD5(user.getPassword()));
		List<User> users = userMapper.selectByExample(example);
		return users;
	}
    
}
