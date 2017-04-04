package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.Activity;
import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.service.ActivityService;
import com.qheeshow.eway.service.service.InvestorService;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.service.service.XwcmclassinfoService;
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
    @Autowired
    private ProjectService projectService;
    @Autowired
    private InvestorService investorService;

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

        List<Activity> list = activityService.latest(3);
        for (Activity activity : list) {
            activity.setTitle(activity.getTitle().length()>13?activity.getTitle().substring(0,13):activity.getTitle());
            activity.setSummary(activity.getSummary().length() > 40 ? activity.getSummary().substring(0, 40) : activity.getSummary());
        }
        result.setData(list);

        return result.toString();
    }

    /**
     * 优秀项目推荐
     *
     * @return
     */
    @RequestMapping("/index/project/best/suggest")
    @ResponseBody
    public String bestSuggest() {
        Result result = new Result();

        List<Project> list = projectService.bestSuggest(6);
        result.setData(list);

        return result.toString();
    }

    /**
     * 优秀案例
     *
     * @return
     */
    @RequestMapping("/index/project/best/case")
    @ResponseBody
    public String bestCase() {
        Result result = new Result();

        List<Project> list = projectService.bestCase(5);
        result.setData(list);

        return result.toString();
    }

    /**
     * 优秀投资人
     *
     * @return
     */
    @RequestMapping("/index/investor/best")
    @ResponseBody
    public String bestInvestor() {
        Result result = new Result();

        List<Investor> list = investorService.bestInvestor(9);
        for (Investor investor : list) {
            investor.setFirstCity(investor.getCityName().split("#")[0]);
        }
        result.setData(list);

        return result.toString();
    }
}
