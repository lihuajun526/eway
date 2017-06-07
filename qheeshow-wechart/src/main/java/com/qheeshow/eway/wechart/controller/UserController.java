package com.qheeshow.eway.wechart.controller;

import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.UserService;
import com.qheeshow.eway.wechart.base.BaseController;
import com.qheeshow.eway.wechart.base.Result;
import com.qheeshow.eway.wechart.base.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 完善个人信息
     *
     * @return
     */
    @RequestMapping("/appendj")
    @ResponseBody
    public String appendj() {

        Result<Tip> result = new Result<>();
        result.setCode(-1);

        Tip tip = new Tip();
        tip.setLink("/user/append.jsp");
        tip.setAction("去完善");

        result.setMessage("请先完善您的个人信息");
        result.setData(tip);

        return result.toString();
    }

    @RequestMapping("/append")
    @ResponseBody
    public String append(User user, String smsCode, HttpSession session) {

        Result<Tip> result = new Result<>();
        Tip tip = new Tip();
        result.setData(tip);

        if (StringUtils.isEmpty(user.getName())) {
            result.setMessage("对不起，真实姓名不能为空");
            return result.toString();
        }
        if (StringUtils.isEmpty(user.getMobile())) {
            result.setMessage("对不起，手机号不能为空");
            return result.toString();
        }
        if (StringUtils.isEmpty(smsCode)) {
            result.setMessage("对不起，手机验证码不能为空");
            return result.toString();
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            result.setMessage("对不起，邮箱不能为空");
            return result.toString();
        }
        Object o = session.getAttribute(user.getMobile() + "_append_smsCode");
        if (o == null) {
            result.setMessage("对不起，手机验证码错误");
            return result.toString();
        }
        String smsCodeS = (String) o;
        if (!smsCode.equals(smsCodeS)) {
            result.setMessage("对不起，手机验证码错误");
            return result.toString();
        }
        User loginUser = (User) session.getAttribute("loginUser");
        loginUser.setName(user.getName());
        loginUser.setMobile(user.getMobile());
        loginUser.setEmail(user.getEmail());

        userService.update(loginUser);

        tip.setLink("window.history.go(-1);");
        tip.setAction("返回");
        result.setMessage("恭喜，您的个人信息补存成功");

        return result.toString();
    }
}
