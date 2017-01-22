package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.exception.UserExistException;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.UserService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String regist(User user, String smsCode, HttpServletRequest request) {
        Result<Boolean> result = new Result<>();
        result.set("注册成功", true);

        if (StringUtils.isEmpty(user.getMobile())) {
            result.set("手机号不能为空", false);
            return result.toString();
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            result.set("邮箱不能为空", false);
            return result.toString();
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            result.set("密码不能为空", false);
            return result.toString();
        }
        if (StringUtils.isEmpty(smsCode)) {
            result.set("短信验证码不能为空", false);
            return result.toString();
        }
        if (user.getRoleid() == null) {
            result.set("身份不能为空", false);
            return result.toString();
        }
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute(httpSession.getId() + "_" + user.getMobile()) == null) {
            result.set("短信验证码错误", false);
            return result.toString();
        }
        String sSmsCode = (String) httpSession.getAttribute("smsCode");
        if (!sSmsCode.equals(smsCode)) {
            result.set("短信验证码错误", false);
            return result.toString();
        }

        try {
            userService.regist(user);
        } catch (UserExistException e) {
            result.set("该手机号码已被注册", false);
            return result.toString();
        }
        httpSession.setAttribute("loginUser", user);
        return result.toString();
    }

}
