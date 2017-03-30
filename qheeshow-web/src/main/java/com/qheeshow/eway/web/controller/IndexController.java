package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.Activity;
import com.qheeshow.eway.service.service.ActivityService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private ActivityService activityService;


    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 最新活动
     *
     * @return
     */
    @RequestMapping("/index/activity/latest")
    @ResponseBody
    public String latest() {
        Result result = new Result();

        List<Activity> list = activityService.latest();
        result.setData(list);

        return result.toString();
    }


}
