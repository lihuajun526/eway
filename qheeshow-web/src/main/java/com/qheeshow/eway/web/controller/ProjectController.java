package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.service.model.Classinfo;
import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.model.Xwcmclassinfo;
import com.qheeshow.eway.service.service.ProjectService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;
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

        List<Xwcmclassinfo> industrys = xwcmclassinfoService.listByRoot(classinfo_rootid_industry);
        List<Xwcmclassinfo> areas = xwcmclassinfoService.listByRoot(classinfo_rootid_area);
        List<Xwcmclassinfo> financingLimits = xwcmclassinfoService.listByRoot(classinfo_rootid_financing_limit);
        List<Xwcmclassinfo> stages = xwcmclassinfoService.listByRoot(classinfo_rootid_stage);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("industrys", industrys);
        modelAndView.addObject("areas", areas);
        modelAndView.addObject("financingLimits", financingLimits);
        modelAndView.addObject("stages", stages);
        modelAndView.setViewName("/project/project_add_edit_one");

        return modelAndView;
    }

    /**
     * 保存基本信息
     *
     * @param project
     * @return
     */
    @RequestMapping("/base/save")
    @ResponseBody
    public String saveBase(Project project, HttpSession httpSession) {

        LOGGER.debug("保存项目基本信息");
        Result<Integer> result = new Result<>();
        result.setCode(-1);

        if (StringUtils.isEmpty(project.getLogo())) {
            result.setMessage("请上传项目logo");
            return result.toString();
        }
        if (StringUtils.isEmpty(project.getTitle())) {
            result.setMessage("项目名称不能为空");
            return result.toString();
        }
        if (project.getIndustry() == null || project.getIndustry().intValue() == 0) {
            result.setMessage("项目所属行业不能为空");
            return result.toString();
        }
        if (project.getArea() == null || project.getArea().intValue() == 0) {
            result.setMessage("项目所在城市不能为空");
            return result.toString();
        }
        if (project.getFinancingLimit() == null || project.getFinancingLimit().intValue() == 0) {
            result.setMessage("项目融资规模不能为空");
            return result.toString();
        }

        /*User loginUser = (User) httpSession.getAttribute("loginUser");
        project.setUserid(loginUser.getId());
        project.setUsername(loginUser.getName());*/

        projectService.save(project);
        result.setData(project.getId());
        result.setCode(0);

        return result.toString();
    }

    /**
     * 保存项目信息
     *
     * @param project
     * @return
     */
    @RequestMapping("/info/save/{projectid}")
    @ResponseBody
    public String saveInfo(@PathVariable Integer projectid, Project project) {

        LOGGER.debug("保存项目信息");
        Result result = new Result<>();

        project.setId(projectid);

        projectService.save(project);

        return result.toString();
    }

    /**
     * 根据条件过滤项目
     *
     * @param type
     * @param areaid
     * @param financingLimit
     * @param industry
     * @param pageIndex
     * @param keyword
     * @return
     */
    @RequestMapping("/list/{type}/{areaid}/{financingLimit}/{industry}/{pageIndex}")
    public ModelAndView listByCondition(@PathVariable Integer type, @PathVariable Integer areaid,
            @PathVariable Integer financingLimit, @PathVariable Integer industry,
            @PathVariable Integer pageIndex, String keyword) {

        LOGGER.debug("根据条件过滤项目");

        List<Project> projectList = projectService.listByCondition(type, areaid, financingLimit, industry, keyword, pageIndex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("project/projects");
        modelAndView.addObject("projects", projectList);

        return modelAndView;
    }

    @RequestMapping("/list")
    public ModelAndView list() {

        LOGGER.debug("根据条件过滤项目");

        List<Classinfo> classinfoList = new ArrayList<>();

        int areaRootid = 0;
        int financingLimitRootid = 0;
        int industryRootid = 0;

        List<Classinfo> areas = new ArrayList<>();
        Classinfo area1 = new Classinfo();
        area1.setId(1);
        area1.setName("北京");
        Classinfo area2 = new Classinfo();
        area2.setId(2);
        area2.setName("上海");
        areas.add(area1);
        areas.add(area2);

        List<Classinfo> financingLimits = new ArrayList<>();
        Classinfo financingLimit1 = new Classinfo();
        financingLimit1.setId(1);
        financingLimit1.setName("50-100W");
        Classinfo financingLimit2 = new Classinfo();
        financingLimit2.setId(2);
        financingLimit2.setName("100-200W");
        financingLimits.add(financingLimit1);
        financingLimits.add(financingLimit2);

        List<Classinfo> industrys = new ArrayList<>();
        Classinfo industry1 = new Classinfo();
        industry1.setId(1);
        industry1.setName("互联网金融");
        Classinfo industry2 = new Classinfo();
        industry2.setId(2);
        industry2.setName("消费品");
        industrys.add(industry1);
        industrys.add(industry2);

        for (Classinfo classinfo : classinfoList) {
            if (classinfo.getParentid().intValue() == areaRootid) {
                areas.add(classinfo);
            } else if (classinfo.getParentid().intValue() == financingLimitRootid) {
                financingLimits.add(classinfo);
            } else if (classinfo.getParentid().intValue() == industryRootid) {
                industrys.add(classinfo);
            }
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("project/project_list");
        modelAndView.addObject("areas", areas);
        modelAndView.addObject("financingLimits", financingLimits);
        modelAndView.addObject("industrys", industrys);

        return modelAndView;
    }

}
