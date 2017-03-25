package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.Activity;
import com.qheeshow.eway.service.model.ActivitySign;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.ActivityService;
import com.qheeshow.eway.service.service.ActivitySignService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivitySignService activitySignService;

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


    @RequestMapping("/sign/{activityid}")
    @ResponseBody
    public String sign(@PathVariable Integer activityid, HttpSession session) {

        Result<String> result = new Result<>();
        Object o = session.getAttribute("loginUser");
        if (o == null) {//未登录
            result.setMessage("微信端扫码报名，敬请期待");
            return result.toString();
        }
        User loginUser = (User) o;
        Activity activity = activityService.get(activityid);
        if (activity.getCost().intValue() > 0) {
            result.setMessage("微信端扫支付报名费，敬请期待");
            return result.toString();
        }
        ActivitySign activitySign = new ActivitySign();
        activitySign.setActivityId(activityid);
        activitySign.setStatus(1);
        activitySign.setUserid(loginUser.getId());
        activitySignService.save(activitySign);
        result.setCode(1);
        return result.toString();
    }
}
