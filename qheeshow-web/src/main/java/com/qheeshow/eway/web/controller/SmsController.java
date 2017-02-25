package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.common.exception.SendSMSException;
import com.qheeshow.eway.common.util.SmsSender;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.UserService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/sms")
public class SmsController extends BaseController {
	
	@Autowired
    private UserService userService;

    @RequestMapping(value = "/get/{phone}", method = RequestMethod.GET)
    @ResponseBody
    public String get(@PathVariable String phone,String type, HttpServletRequest request) {
    	User user = new User();
    	user.setMobile(phone);
        Result<Boolean> result = new Result<>();
        boolean flag = true;
        if("regist".equals(type)){
        	flag = userService.isRegist(user);
        }
    	if(flag){
            result.set("发送成功", true);
            HttpSession session = request.getSession();
            if (session.getAttribute(session.getId() + "_" + phone) != null) {
                long lastTime = (Long) session.getAttribute(session.getId() + "_" + phone);
                if (System.currentTimeMillis() - lastTime < 30 * 1000) {
                    result.setData(false);
                    result.setMessage("请在30秒后再获取验证码");
                    return result.toString();
                }
            }
            try {
                String smsCode = SmsSender.send(phone);
                session.setAttribute(session.getId() + "_" + phone, System.currentTimeMillis());
                session.setAttribute(type + "smsCode", smsCode);
                System.out.println("验证码："+smsCode);
            } catch (SendSMSException e) {
                LOGGER.error("获取短信验证码失败");
                result.setData(false);
                result.setMessage("发送失败");
            }
    	}else{
    		result.setMessage("该手机号已被注册");
    	}
        return result.toString();
    }

}
