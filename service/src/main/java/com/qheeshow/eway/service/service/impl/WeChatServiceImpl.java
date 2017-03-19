package com.qheeshow.eway.service.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.common.util.HttpAgent;
import com.qheeshow.eway.service.dao.UserMapper;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.model.UserExample;
import com.qheeshow.eway.service.service.WeChatService;

@Service
public class WeChatServiceImpl implements WeChatService{
	
	@Autowired
	UserMapper userMapper;

	public User isBand(String code,HttpSession session){
		User user = new User();
		HttpAgent agent = HttpAgent.getInstance();
		Map<String,Object> token = new HashMap<String,Object>();
		Map<String,Object> userInfo = new HashMap<String,Object>();
		try {
			Map<String,String> sendMessage = new HashMap<String,String>();
//			String appid = "";
//			String secret = "";
			String appid = Config.get("wechat.appid");
			String secret = Config.get("wechat.secret");
			sendMessage.put("appid", appid);
			sendMessage.put("secret", secret);
			sendMessage.put("code", code);
			sendMessage.put("grant_type", "authorization_code");
			//根据code获取openid及access_token
			String result  = agent.doGet("https://api.weixin.qq.com/sns/oauth2/access_token",sendMessage , null);
			token = JSON.parseObject(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(token != null && token.get("access_token") != null){
			String access_token = token.get("access_token").toString();
			String openid = token.get("openid").toString();
			UserExample userExample = new UserExample();
			userExample.createCriteria().andOpenidEqualTo(openid);
			List<User> users = userMapper.selectByExample(userExample);
			//判断该openid是否已经绑定平台账号
			if(users != null && users.size() > 0){
	            session.setAttribute("loginUser", user);
				user = users.get(0);
			}else{
				Map<String,String> sendMessage = new HashMap<String,String>();
				sendMessage.put("access_token", access_token);
				sendMessage.put("openid", openid);
				//若该openid未绑定平台账号则获取微信用户详情后返回
				try {
					String result  = agent.doGet("https://api.weixin.qq.com/sns/auth",sendMessage , null);
					userInfo = JSON.parseObject(result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(userInfo != null && userInfo.get("openid") != null){
					String nickname = token.get("nickname").toString();
					user.setOpenid(openid);
					user.setNickname(nickname);
				}else{
					System.out.println("获取用户详情失败");
					return null;
				}
			}
		}else{
			System.out.println("获取token失败");
			return null;
		}
		return user;
	}
	
	public User regist(HttpSession session,User user){
		UserExample example = new UserExample();
		example.createCriteria().andMobileEqualTo(user.getMobile());
		List<User> users = userMapper.selectByExample(example);
		//判断该手机号是否已经注册平台账号
		if(users != null && users.size() > 0){
			//若已注册则获取用户信息后返回
			User record = users.get(0);
			record.setOpenid(user.getOpenid());
			if(record.getNickname() == null){
				record.setNickname(user.getNickname());
			}
			userMapper.updateByPrimaryKeySelective(record);
	        session.setAttribute("loginUser", record);
			return record;
		}else{
			//若未注册则注册后返回
			user.setCreateTime(new Date());
			userMapper.insertSelective(user);
	        session.setAttribute("loginUser", user);
			return user;
		}
	}
}
