package com.qheeshow.eway.wechart.controller;

import com.vip.integral.base.BaseController;
import com.vip.integral.base.Result;
import com.operational.platform.dbservice.service.UserService;
import com.operational.platform.dbservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/get")
    public String getUser(User user) {
        Result<User> result = new Result<>();

        result.set(0, userService.getByOpenid(user.getOpenid()));

        return result.toString();
    }

    /**
     * 获得用户的推广记录
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/spreads")
    public String listSpreads(User user) {
        Result<User> result = new Result<>();

        result.set(0, userService.getByOpenid(user.getOpenid()));

        return result.toString();
    }

    @RequestMapping("/member")
    public ModelAndView member(HttpServletRequest request) {

        String openid = (String) request.getSession().getAttribute("openid");

        ModelAndView modelAndView = new ModelAndView("member");
        modelAndView.addObject("user", userService.getByOpenid(openid));
        return modelAndView;
    }

}
