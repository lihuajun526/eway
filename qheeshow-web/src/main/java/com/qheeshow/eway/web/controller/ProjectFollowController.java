package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.common.exception.CommonException;
import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.ProjectFollow;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.ProjectFollowService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/project")
public class ProjectFollowController extends BaseController {

    @Autowired
    private ProjectFollowService projectFollowService;

    @RequestMapping("/follow/{projectid}")
    @ResponseBody
    public String follow(@PathVariable Integer projectid, HttpSession session) {

        Result result = new Result();
        if (session.getAttribute("loginUser") == null) {
            result.set(-1, "亲爱的用户，请先登录才能添加关注哦！");
            return result.toString();
        }
        User loginUser = (User) session.getAttribute("loginUser");
        ProjectFollow projectFollow = new ProjectFollow();
        projectFollow.setProjectid(projectid);
        projectFollow.setUserid(loginUser.getId());
        try {
            projectFollowService.follow(projectFollow);
        } catch (CommonException e) {
            result.set(-2, e.getDesc());
            return result.toString();
        }
        return result.toString();
    }

    @RequestMapping("/unfollow/{projectid}")
    @ResponseBody
    public String unFollow(@PathVariable Integer projectid, HttpSession session) {

        Result result = new Result();
        User loginUser = (User) session.getAttribute("loginUser");
        ProjectFollow projectFollow = new ProjectFollow();
        projectFollow.setProjectid(projectid);
        projectFollow.setUserid(loginUser.getId());
        projectFollowService.unFollow(projectFollow);

        return result.toString();
    }

    @RequestMapping("/isfollow/{projectid}")
    @ResponseBody
    public String isFollow(@PathVariable Integer projectid, HttpSession session) {
        Result<Boolean> result = new Result();
        if (session.getAttribute("loginUser") == null) {
            result.setData(false);
            return result.toString();
        }
        User loginUser = (User) session.getAttribute("loginUser");
        ProjectFollow projectFollow = new ProjectFollow();
        projectFollow.setProjectid(projectid);
        projectFollow.setUserid(loginUser.getId());
        boolean isFollow = projectFollowService.isFollow(projectFollow);
        result.setData(isFollow);
        return result.toString();
    }

    @RequestMapping("/follow/list/{projectid}")
    @ResponseBody
    public String list(@PathVariable Integer projectid) {

        Result<List<Investor>> result = new Result<>();

        List<Investor> list = projectFollowService.list(projectid);
        result.setData(list);

        return result.toString();
    }

}
