package com.qheeshow.eway.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.model.Xwcmclassinfo;
import com.qheeshow.eway.service.service.XwcmclassinfoService;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qheeshow.eway.common.web.HaResponse;
import com.qheeshow.eway.service.service.InvestorService;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/investor")
public class InvestorController {

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
        List<Xwcmclassinfo> styles = xwcmclassinfoService.listByRoot(classinfo_rootid_stage);

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
     * @param investor
     * @return
     * @Title: list
     * @Description: 获取列表
     * @author yue
     * @date 2017年3月5日 下午2:53:29
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public HaResponse list(Investor investor, HttpSession session) {
        List<Investor> investors = investorService.list(investor, session);
        return HaResponse.sussess(investors);
    }

}
