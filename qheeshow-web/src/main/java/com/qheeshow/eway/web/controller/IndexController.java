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
import org.springframework.util.StringUtils;
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
            //activity.setTitle(activity.getTitle().length() > 13 ? activity.getTitle().substring(0, 13) : activity.getTitle());
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
        boolean isFirst = true;
        for (Investor investor : list) {
            investor.setFirstCity(investor.getCityName().split("#")[0]);
            if (isFirst) {
                isFirst = false;
                continue;
            }
            String row1 = investor.getCompanyName() + " | " + investor.getCompanyRank();
            String row2 = "";
            if (!StringUtils.isEmpty(investor.getCityName())) {
                String[] areas = investor.getCityName().split("#");
                row2 = areas[0] + " | ";
            }
            if (!StringUtils.isEmpty(investor.getIndustryName())) {
                String[] indus = investor.getIndustryName().split("#");
                for (String indu : indus) {
                    row2 += indu + " ";
                }
            }
            row1 = row1.length()>19?row1.substring(0,18)+"...":row1;
            row2 = row2.length()>24?row2.substring(0,23)+"...":row2;
            investor.setRow1(row1);
            investor.setRow2(row2);
        }
        result.setData(list);

        return result.toString();
    }
}
