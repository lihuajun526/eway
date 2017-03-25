package com.qheeshow.eway.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qheeshow.eway.common.page.PageInfo;
import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.common.web.HaResponse;
import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.model.Xwcmclassinfo;
import com.qheeshow.eway.service.service.InvestorService;
import com.qheeshow.eway.service.service.XwcmclassinfoService;
import com.qheeshow.eway.web.base.Result;

@Controller
@RequestMapping("/investor")
public class InvestorController extends BaseController {

    @Autowired
    private InvestorService investorService;
    @Autowired
    private XwcmclassinfoService xwcmclassinfoService;

    @RequestMapping("/{id}/add/edit/1")
    public ModelAndView addOrEditOne(@PathVariable Integer id) {

        //项目所属行业rootid
        int classinfo_rootid_industry = Config.getInt("classinfo.rootid.industry");
        //项目所属地域rootid
        int classinfo_rootid_area = Config.getInt("classinfo.rootid.area");
        //项目融资额度rootid
        int classinfo_rootid_financing_limit = Config.getInt("classinfo.rootid.financing.limit");
        //项目阶段rootid
        int classinfo_rootid_stage = Config.getInt("classinfo.rootid.stage");
        //投资风格
        int classinfo_rootid_style = Config.getInt("classinfo.rootid.style");

        List<Xwcmclassinfo> industrys = xwcmclassinfoService.listByRoot(classinfo_rootid_industry);
        List<Xwcmclassinfo> areas = xwcmclassinfoService.listByRoot(classinfo_rootid_area);
        List<Xwcmclassinfo> financingLimits = xwcmclassinfoService.listByRoot(classinfo_rootid_financing_limit);
        List<Xwcmclassinfo> stages = xwcmclassinfoService.listByRoot(classinfo_rootid_stage);
        List<Xwcmclassinfo> styles = xwcmclassinfoService.listByRoot(classinfo_rootid_style);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("industrys", industrys);
        modelAndView.addObject("areas", areas);
        modelAndView.addObject("financingLimits", financingLimits);
        modelAndView.addObject("stages", stages);
        modelAndView.addObject("styles", styles);
        modelAndView.addObject("investor", id.intValue() == 0 ? null : investorService.get(id));
        modelAndView.setViewName("/investor/investor_add_edit_one");

        return modelAndView;
    }

    @RequestMapping("/{id}/add/edit/2")
    public ModelAndView addOrEditTwo(@PathVariable Integer id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("investor", investorService.get(id));
        modelAndView.setViewName("/investor/investor_add_edit_two");

        return modelAndView;
    }

    /**
     * @param investor
     * @param session
     * @return
     */
    @RequestMapping(value = "/base/save")
    @ResponseBody
    public String saveBase(Investor investor, HttpSession session) {
        Result<Integer> result = new Result<>();
        User loginUser = (User) session.getAttribute("loginUser");
        investor.setUserid(loginUser.getId());
        investorService.save(investor);
        result.setData(investor.getId());
        return result.toString();
    }

    @RequestMapping(value = "/auth/save")
    @ResponseBody
    public String saveAuth(Investor investor) {
        Result result = new Result();
        investorService.save(investor);
        return result.toString();
    }

    /**
     * @param id
     * @return
     * @Title: detail
     * @Description: 根据id获取详情
     * @author yue
     * @date 2017年3月5日 下午2:53:21
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public HaResponse detail(Integer id) {
        Investor investor = investorService.detail(id);
        return HaResponse.sussess(investor);
    }

    /**
     * 根据条件过滤投资人
     *
     * @param cityid
     * @param industryid
     * @param stageid
     * @param pageIndex
     * @param keyword
     * @return
     */
    @RequestMapping("/list/{cityid}/{industryid}/{stageid}/{pageIndex}")
    public ModelAndView listByCondition(@PathVariable String cityid,
                                        @PathVariable String industryid, @PathVariable String stageid,
                                        @PathVariable Integer pageIndex, String keyword) {
        LOGGER.debug("根据条件过滤投资人");
        int pageSize = 2;
        int recordCount = 0;
        List<Investor> investorList = new ArrayList<>();
        if (StringUtils.isEmpty(keyword)) {
            Map<String, Object> map = investorService.listByCondition(cityid, industryid, stageid, pageIndex, pageSize);
            investorList = (List<Investor>) map.get("investors");
            recordCount = (Integer) map.get("count");
        } /*else
            projectList = projectService.search(keyword);*/
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("investor/investors");
        modelAndView.addObject("investors", investorList);
        modelAndView.addObject("pageSize", pageSize);
        modelAndView.addObject("pageIndex", pageIndex);
        modelAndView.addObject("pageCount", recordCount % pageSize == 0 ? recordCount / pageSize : (recordCount / pageSize + 1));
        return modelAndView;
    }

    @RequestMapping("/list")
    public ModelAndView list() {
        LOGGER.debug("根据条件过滤项目");

        int areaRootid = Config.getInt("classinfo.rootid.area");
        int financingLimitRootid = Config.getInt("classinfo.rootid.financing.limit");
        int industryRootid = Config.getInt("classinfo.rootid.industry");

        List<Xwcmclassinfo> industrys = xwcmclassinfoService.listByRoot(industryRootid);
        List<Xwcmclassinfo> areas = xwcmclassinfoService.listByRoot(areaRootid);
        List<Xwcmclassinfo> financingLimits = xwcmclassinfoService.listByRoot(financingLimitRootid);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("project/project_list");
        modelAndView.addObject("areas", areas);
        modelAndView.addObject("financingLimits", financingLimits);
        modelAndView.addObject("industrys", industrys);

        return modelAndView;
    }

}
