package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.Activity;
import com.qheeshow.eway.service.model.ActivitySign;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.ActivityService;
import com.qheeshow.eway.service.service.ActivitySignService;
import com.qheeshow.eway.service.service.OrderService;
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
import java.util.Map;

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
    @Autowired
    private OrderService orderService;

    @RequestMapping("/list/{activityClass}/{pageIndex}/{pageSize}")
    @ResponseBody
    public String list(@PathVariable Integer activityClass, @PathVariable Integer pageIndex, @PathVariable Integer pageSize) {

        Result<List<Activity>> result = new Result<>();

        Activity activity = new Activity();
        if (activityClass.intValue() > 0)
            activity.setActivityClass(activityClass);
        List<Activity> list = activityService.listByCodition(activity, pageIndex, pageSize);

        for (Activity acti : list) {
            if (acti.getTitle().length() > 15)
                acti.setTitle(acti.getTitle().substring(0, 15));
            if (acti.getSummary().length() > 20)
                acti.setSummary(acti.getSummary().substring(0, 20));
        }

        result.setData(list);

        return result.toString();
    }

    @RequestMapping("/list")
    public String list() {
        return "activity/activity_list";
    }

    @RequestMapping("/get/{id}")
    public ModelAndView get(@PathVariable Integer id) {

        Activity activity = activityService.get(id);

        List<ActivitySign> list = activitySignService.listByActivity(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("activity/activity_detail");
        modelAndView.addObject("activity", activity);
        modelAndView.addObject("isFull", activity.getLimitNum() > list.size() ? false : true);

        return modelAndView;
    }


    @RequestMapping("/sign/{activityid}/authj")
    @ResponseBody
    public String sign(@PathVariable Integer activityid, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");

        Activity activity = activityService.get(activityid);

        if (activity.getCost().floatValue() > 0) {//收费
            Result<Map<String, String>> result = new Result<>();
            try {
                Map<String, String> map = orderService.signActivity(loginUser.getId(), activityid, "WECHAT");
                result.setCode(2);
                result.setData(map);
            } catch (Exception e) {
                LOGGER.error("报名失败:", e);
                result.setCode(-1);
                result.setMessage("报名失败");
            }
            return result.toString();
        }

        Result<String> result = new Result<>();
        ActivitySign activitySign = new ActivitySign();
        activitySign.setActivityId(activityid);
        activitySign.setStatus(1);
        activitySign.setUserid(loginUser.getId());
        activitySignService.save(activitySign);
        result.setCode(1);
        return result.toString();
    }

    @RequestMapping("/issign/{activityid}")
    @ResponseBody
    public String issign(@PathVariable Integer activityid, HttpSession session) {

        Result<Boolean> result = new Result<>();
        User loginUser = (User) session.getAttribute("loginUser");
        ActivitySign activitySign = new ActivitySign();
        activitySign.setActivityId(activityid);
        activitySign.setUserid(loginUser.getId());
        result.setData(activitySignService.issign(activitySign));
        return result.toString();
    }

}
