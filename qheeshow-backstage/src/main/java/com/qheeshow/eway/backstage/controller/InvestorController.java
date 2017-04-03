package com.qheeshow.eway.backstage.controller;

import com.alibaba.fastjson.JSON;
import com.qheeshow.eway.backstage.base.BaseController;
import com.qheeshow.eway.backstage.base.Result;
import com.qheeshow.eway.backstage.base.ResultDg;
import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.service.InvestorService;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.service.service.XwcmclassinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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

    @RequestMapping("/list")
    @ResponseBody
    public String listByInvestor(Investor investor, Integer page, Integer rows) {

        ResultDg<List<Investor>> resultDg = new ResultDg<>();

        investor.setPageSize(rows);
        investor.setStartRow(rows * (page - 1));

        Map map = investorService.listByInvestor(investor);

        resultDg.setRows((List<Investor>) map.get("investors"));
        resultDg.setTotal((Integer) map.get("count"));
        return JSON.toJSONString(resultDg);
    }


    @RequestMapping("/{investorid}/{status}")
    @ResponseBody
    public String updateStatus(@PathVariable Integer investorid, @PathVariable Integer status) {

        Result result = new Result();

        investorService.updateStatus(investorid, status);

        return result.toString();

    }

    /**
     * 认证
     * @param investorid
     * @param authStatus
     * @return
     */
    @RequestMapping("/auth/{investorid}/{authStatus}")
    @ResponseBody
    public String updateAuth(@PathVariable Integer investorid, @PathVariable Integer authStatus) {

        Result result = new Result();

        investorService.updateAuth(investorid, authStatus);

        return result.toString();

    }

    /**
     * 推荐
     * @param investorid
     * @param isBest
     * @return
     */
    @RequestMapping("/best/{investorid}/{isBest}")
    @ResponseBody
    public String best(@PathVariable Integer investorid, @PathVariable Integer isBest) {

        Result result = new Result();

        investorService.setBest(investorid, isBest);

        return result.toString();

    }

    /**
     * 签约
     * @param investorid
     * @param isSign
     * @return
     */
    @RequestMapping("/sign/{investorid}/{isSign}")
    @ResponseBody
    public String sign(@PathVariable Integer investorid, @PathVariable Integer isSign) {

        Result result = new Result();

        investorService.setSign(investorid, isSign);

        return result.toString();

    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public String get(@PathVariable Integer id) {

        Result result = new Result();

        Investor investor = investorService.get(id);
        result.setData(investor);

        return result.toString();

    }
}
