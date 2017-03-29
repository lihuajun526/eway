package com.qheeshow.eway.backstage.controller;

import com.alibaba.fastjson.JSON;
import com.qheeshow.eway.backstage.base.BaseController;
import com.qheeshow.eway.backstage.base.ResultDg;
import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.common.web.HaResponse;
import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.model.Xwcmclassinfo;
import com.qheeshow.eway.service.service.InvestorService;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.service.service.XwcmclassinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/investor")
public class InvestorController extends BaseController {

    @Autowired
    private InvestorService investorService;
    @Autowired
    private XwcmclassinfoService xwcmclassinfoService;
    @Autowired
    private ProjectService projectService;


    @RequestMapping("/list/suggest/{projectid}")
    @ResponseBody
    public String listSuggest(@PathVariable Integer projectid) {

        ResultDg<List<Investor>> resultDg = new ResultDg<>();
        //推荐优先级：行业/城市/投资阶段/投资金额
        Investor query = new Investor();
        int max = 20;
        int count = investorService.getCountByCondition(query);
        Project project = projectService.get(projectid);
        if (count > max) {
            query.setIndustryId("#" + project.getIndustry() + "#");
            count = investorService.getCountByCondition(query);
            if (count > max) {
                query.setCityId("#" + project.getArea() + "#");
                count = investorService.getCountByCondition(query);
                if (count > max) {
                    query.setStageId("#" + project.getStage() + "#");
                    count = investorService.getCountByCondition(query);
                    if (count > max) {
                        query.setSinglePriceId(project.getFinancingLimit());
                    }
                }
            }
        }

        List<Investor> list = investorService.listByCondition(query);
        if (list.size() > max) {
            list = list.subList(0, max);
        }
        resultDg.setRows(list);
        resultDg.setTotal(list.size());
        return JSON.toJSONString(resultDg);
    }

}
