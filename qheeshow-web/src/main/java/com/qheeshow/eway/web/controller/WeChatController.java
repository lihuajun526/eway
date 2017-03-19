package com.qheeshow.eway.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qheeshow.eway.common.web.HaResponse;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.WeChatService;

@Controller
@RequestMapping("/weChat")
public class WeChatController {
	
	@Autowired
    private WeChatService weChatService;

	/**
	 * 
	 * @Title: isBand
	 * @Description: 判断该微信用户是否已经绑定平台账号
	 * @author yue
	 * @date 2017年3月19日 下午12:13:49
	 * @param session
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/isBand")
	@ResponseBody
	public HaResponse isBand(HttpSession session,String code) {
		User user = weChatService.isBand(code,session);
		return HaResponse.sussess(user);
	}
	
	/**
	 * 
	 * @Title: regist
	 * @Description: 注册平台账号并绑定微信账号相关信息
	 * @author yue
	 * @date 2017年3月19日 下午12:14:11
	 * @param session
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/regist")
    @ResponseBody
    public HaResponse regist(HttpSession session,User user,String smsCode) {
		System.out.println(session.getAttribute("regist_smsCode"));
		if(session.getAttribute("regist_smsCode") != null && smsCode.equals(session.getAttribute("regist_smsCode").toString())){
			user = weChatService.regist(session,user);
			return HaResponse.sussess(user);
		}else{
			return HaResponse.fail();
		}
	}
	
}
