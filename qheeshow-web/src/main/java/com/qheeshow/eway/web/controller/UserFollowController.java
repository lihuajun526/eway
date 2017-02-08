package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.exception.CommonException;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.UserFollowService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/user")
public class UserFollowController extends BaseController {

    @Autowired
    private UserFollowService userFollowService;

    @RequestMapping("/follow/{userid}/{followid}")
    @ResponseBody
    public String follow(@PathVariable Integer userid, @PathVariable Integer followid) {

        Result result = new Result();

        try {
            userFollowService.follow(userid, followid);
        } catch (CommonException e) {
            result.set(-1, e.getDesc());
            return result.toString();
        }

        return result.toString();
    }

    @RequestMapping("/unfollow/{userid}/{followid}")
    @ResponseBody
    public String unFollow(@PathVariable Integer userid, @PathVariable Integer followid) {

        Result result = new Result();

        userFollowService.unFollow(userid, followid);

        return result.toString();
    }

    @RequestMapping("/isfollow/{userid}/{followid}")
    @ResponseBody
    public String isFollow(@PathVariable Integer userid, @PathVariable Integer followid) {

        Result result = new Result();

        userFollowService.isFollow(userid, followid);

        return result.toString();
    }

    @RequestMapping("/list/{userid}")
    @ResponseBody
    public String list(@PathVariable Integer userid) {

        Result<List<User>> result = new Result<>();

        List<User> list = userFollowService.list(userid);
        result.setData(list);

        return result.toString();
    }

}
