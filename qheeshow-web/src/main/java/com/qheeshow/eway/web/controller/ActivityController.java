package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.Activity;
import com.qheeshow.eway.service.service.ActivityService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/list/{type}/{pageIndex}/{pageSize}")
    @ResponseBody
    public String list(@PathVariable Integer activityClass, Integer pageIndex, Integer pageSize) {

        Result<List<Activity>> result = new Result<>();

        Activity activity = new Activity();
        activity.setActivityClass(activityClass);
        List<Activity> list = activityService.listByCodition(activity, pageIndex, pageSize);
        result.setData(list);

        return result.toString();
    }

    @RequestMapping("/get/{id}")
    public ModelAndView get(@PathVariable Integer id) {

        Activity activity = activityService.get(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("activity/activity_detail");
        modelAndView.addObject("activity", activity);

        return modelAndView;
    }
}
