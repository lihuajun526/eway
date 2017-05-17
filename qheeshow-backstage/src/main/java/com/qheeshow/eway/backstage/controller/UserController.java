package com.qheeshow.eway.backstage.controller;

import com.qheeshow.eway.backstage.base.Result;
import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.service.model.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qheeshow.eway.backstage.base.BaseController;
import com.qheeshow.eway.service.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    /**
     * 根据项目匹配投资人
     *
     * @param projectid
     * @return
     */
    @RequestMapping("/match/{projectid}")
    @ResponseBody
    public String matchByProject(@PathVariable Integer projectid) {
        return "index";
    }

    /**
     * 登录
     * @param loginUser
     * @param session
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(User loginUser, HttpSession session) {

        Result<Boolean> result = new Result<>();
        result.setData(false);

        if(StringUtils.isEmpty(loginUser.getName()) || StringUtils.isEmpty(loginUser.getPassword())){
            result.setMessage("账号和密码不能为空");
            return result.toString();
        }
        if (!loginUser.getName().equals(Config.get("back.admin.name"))) {
            result.setMessage("对不起，您不是管理员无法登录");
            return result.toString();
        }
        if (!loginUser.getPassword().equals(Config.get("back.admin.password"))) {
            result.setMessage("密码错误");
            return result.toString();
        }

        session.setAttribute("loginUser", loginUser);

        result.setData(true);
        return result.toString();
    }


}
