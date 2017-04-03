package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.common.web.HaResponse;
import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.InvestorFollow;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.model.Xwcmclassinfo;
import com.qheeshow.eway.service.service.InvestorFollowService;
import com.qheeshow.eway.service.service.InvestorService;
import com.qheeshow.eway.service.service.XwcmclassinfoService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    private InvestorFollowService investorFollowService;

    @RequestMapping("/{id}/add/edit/1/auth")
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

    @RequestMapping("/{id}/add/edit/2/auth")
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
    @RequestMapping(value = "/base/save/authj")
    @ResponseBody
    public String saveBase(Investor investor, HttpSession session) {
        Result<Integer> result = new Result<>();
        User loginUser = (User) session.getAttribute("loginUser");
        investor.setUserid(loginUser.getId());

        boolean isFirst = true;
        StringBuffer citys = new StringBuffer();
        for (String cityid : investor.getCityId().split("#")) {
            if (StringUtils.isEmpty(cityid))
                continue;
            if (isFirst) {
                isFirst = false;
                citys.append(xwcmclassinfoService.get(Integer.parseInt(cityid)).getCname());
                continue;
            }
            citys.append("#").append(xwcmclassinfoService.get(Integer.parseInt(cityid)).getCname());
        }
        isFirst = true;
        StringBuffer stages = new StringBuffer();
        for (String stageid : investor.getStageId().split("#")) {
            if (StringUtils.isEmpty(stageid))
                continue;
            if (isFirst) {
                isFirst = false;
                stages.append(xwcmclassinfoService.get(Integer.parseInt(stageid)).getCname());
                continue;
            }
            stages.append("#").append(xwcmclassinfoService.get(Integer.parseInt(stageid)).getCname());
        }
        isFirst = true;
        StringBuffer industrys = new StringBuffer();
        for (String industryid : investor.getIndustryId().split("#")) {
            if (StringUtils.isEmpty(industryid))
                continue;
            if (isFirst) {
                isFirst = false;
                industrys.append(xwcmclassinfoService.get(Integer.parseInt(industryid)).getCname());
                continue;
            }
            industrys.append("#").append(xwcmclassinfoService.get(Integer.parseInt(industryid)).getCname());
        }
        String singlePrice = xwcmclassinfoService.get(investor.getSinglePriceId()).getCname();
        String style = xwcmclassinfoService.get(investor.getStyleId()).getCname();

        investor.setCityName(citys.toString());
        investor.setStageName(stages.toString());
        investor.setIndustryName(industrys.toString());
        investor.setSinglePrice(singlePrice);
        investor.setStyle(style);

        investorService.save(investor);
        result.setData(investor.getId());
        return result.toString();
    }

    @RequestMapping(value = "/auth/save/authj")
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
        int stageRootid = Config.getInt("classinfo.rootid.stage");
        int industryRootid = Config.getInt("classinfo.rootid.industry");

        List<Xwcmclassinfo> industrys = xwcmclassinfoService.listByRoot(industryRootid);
        List<Xwcmclassinfo> areas = xwcmclassinfoService.listByRoot(areaRootid);
        List<Xwcmclassinfo> stages = xwcmclassinfoService.listByRoot(stageRootid);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("investor/investor_list");
        modelAndView.addObject("areas", areas);
        modelAndView.addObject("stages", stages);
        modelAndView.addObject("industrys", industrys);

        return modelAndView;
    }

    @RequestMapping("/{id}")
    public ModelAndView get(@PathVariable Integer id) {
        Investor investor = investorService.get(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("investor/investor_detail");
        modelAndView.addObject("investor", investor);
        return modelAndView;
    }

    @RequestMapping("/follow/{investorid}")
    @ResponseBody
    public String follow(@PathVariable Integer investorid, HttpSession session) {

        Result<Boolean> result = new Result<>();
        result.setData(false);
        Object o = session.getAttribute("loginUser");
        if (o == null)
            return result.toString();
        User loginUser = (User) o;
        InvestorFollow investorFollow = new InvestorFollow();
        investorFollow.setUserid(loginUser.getId());
        investorFollow.setInvestorid(investorid);
        investorFollowService.save(investorFollow);

        result.setData(true);
        return result.toString();
    }

    @RequestMapping("/isfollow/{investorid}")
    @ResponseBody
    public String isfollow(@PathVariable Integer investorid, HttpSession session) {

        Result<Boolean> result = new Result<>();
        result.setData(false);
        Object o = session.getAttribute("loginUser");
        if (o == null)
            return result.toString();
        User loginUser = (User) o;
        InvestorFollow investorFollow = new InvestorFollow();
        investorFollow.setUserid(loginUser.getId());
        investorFollow.setInvestorid(investorid);
        Boolean isFollow = investorFollowService.isFollow(investorFollow);
        result.setData(isFollow);
        return result.toString();
    }

}
