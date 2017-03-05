package com.qheeshow.eway.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qheeshow.eway.common.web.HaResponse;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.UserService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ResponseBody
    public String regist(User user, String smsCode, String rePassword, HttpServletRequest request) {
        Result<Boolean> result = new Result<>();
        result.set("注册成功", true);

        if (StringUtils.isEmpty(user.getName())) {
            result.set("真实姓名不能为空", false);
            return result.toString();
        }
        if (StringUtils.isEmpty(user.getMobile())) {
            result.set("手机号不能为空", false);
            return result.toString();
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            result.set("邮箱不能为空", false);
            return result.toString();
        }
        if (StringUtils.isEmpty(smsCode)) {
            result.set("短信验证码不能为空", false);
            return result.toString();
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            result.set("密码不能为空", false);
            return result.toString();
        }
        if (user.getRoleid() == null) {
            result.set("身份不能为空", false);
            return result.toString();
        }
        if (!user.getPassword().equals(rePassword)) {
            result.set("两次密码输入不一致", false);
            return result.toString();
        }
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("regist_smsCode") == null) {
            result.set("短信验证码错误", false);
            return result.toString();
        }
        String sSmsCode = (String) httpSession.getAttribute("regist_smsCode");
        if (sSmsCode == null || !sSmsCode.equals(smsCode)) {
            result.set("短信验证码错误", false);
            return result.toString();
        }
        if (userService.isRegist(user)) {
            result.set("该手机号码已被注册", false);
            return result.toString();
        }
        userService.regist(user);
        LOGGER.debug("用户ID={}", user.getId());
        httpSession.setAttribute("loginUser", user);
        return result.toString();
    }


    /**
     * @param session
     * @param user
     * @return
     * @Title: SaveAddress
     * @Description: 登录并将当前用户相关信息放入缓存
     * @author yue
     * @date 2017年2月25日 下午2:14:12
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public HaResponse login(HttpSession session, User user) {
        List<User> users = userService.login(user);
        if (users.size() > 0) {
            user = users.get(0);
            session.setAttribute("userId", user.getId());
            session.setAttribute("nickname", user.getNickname());
            session.setAttribute("roleid", user.getRoleid());
            user.setPassword("");
            return HaResponse.sussess(user);
        } else {
            return HaResponse.fail("用户名密码错误");
        }
    }

    /**
     * @param session
     * @param user
     * @param smsCode
     * @return
     * @Title: changePassword
     * @Description: 修改密码
     * @author yue
     * @date 2017年2月25日 下午3:48:11
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    @ResponseBody
    public HaResponse changePassword(HttpSession session, User user, String smsCode) {
        if (smsCode.equals((String) session.getAttribute("regist" + "smsCode"))) {
            userService.changePassword(user);
            return HaResponse.sussess();
        } else {
            return HaResponse.fail("短信验证码错误");
        }
    }

    @RequestMapping(value = "/password/reset")
    @ResponseBody
    public HaResponse resetPassword(HttpSession session, User user, String smsCode, String rePassword) {

        Object object = session.getAttribute("reset" + "smsCode");
        if (StringUtils.isEmpty(user.getMobile()))
            return HaResponse.fail("手机号不能为空");
        if (StringUtils.isEmpty(smsCode))
            return HaResponse.fail("短信验证码不能为空");
        if (StringUtils.isEmpty(user.getPassword()))
            return HaResponse.fail("新密码不能为空");
        if (!user.getPassword().equals(rePassword))
            return HaResponse.fail("两次密码不匹配");
        if (object == null)
            return HaResponse.fail("请先获取短信验证码");
        if (!smsCode.equals((String) object)) {
            return HaResponse.fail("短信验证码错误");
        } else {
            userService.changePassword(user);
            return HaResponse.sussess();
        }
    }
    
    /**
	 * 
	 * @Title: reLogin
	 * @Description: 未登录时，请求需登录才可访问的请求时统一转向该接口返回
	 * @author yue
	 * @date 2017年3月5日11:35:34
	 * @return
	 */
	@RequestMapping(value="/reLogin")
    @ResponseBody
	public HaResponse reLogin(){
		return HaResponse.fail("-1");
	}

}
