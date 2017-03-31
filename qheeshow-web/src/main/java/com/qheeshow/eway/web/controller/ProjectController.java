package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.service.model.*;
import com.qheeshow.eway.service.service.CommonQaService;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.service.service.TeamMemberService;
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
    @Autowired
    private TeamMemberService teamMemberService;
    @Autowired
    private CommonQaService commonQaService;

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
        //项目标签
        int classinfo_rootid_tag = Config.getInt("classinfo.rootid.tag");

        List<Xwcmclassinfo> industrys = xwcmclassinfoService.listByRoot(classinfo_rootid_industry);
        List<Xwcmclassinfo> areas = xwcmclassinfoService.listByRoot(classinfo_rootid_area);
        List<Xwcmclassinfo> financingLimits = xwcmclassinfoService.listByRoot(classinfo_rootid_financing_limit);
        List<Xwcmclassinfo> stages = xwcmclassinfoService.listByRoot(classinfo_rootid_stage);
        List<Xwcmclassinfo> tags = xwcmclassinfoService.listByRoot(classinfo_rootid_tag);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("industrys", industrys);
        modelAndView.addObject("areas", areas);
        modelAndView.addObject("financingLimits", financingLimits);
        modelAndView.addObject("stages", stages);
        modelAndView.addObject("tags", tags);
        modelAndView.addObject("project", projectService.get(id));
        modelAndView.setViewName("/project/project_add_edit_one");

        return modelAndView;
    }

    @RequestMapping("/{id}/add/edit/2/auth")
    public ModelAndView addOrEditTwo(@PathVariable Integer id) {

        List<TeamMember> members = teamMemberService.listByProject(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("project", projectService.get(id));
        modelAndView.addObject("members", members);
        modelAndView.setViewName("/project/project_add_edit_two");

        return modelAndView;
    }

    @RequestMapping("/{id}/add/edit/3/auth")
    public ModelAndView addOrEditThree(@PathVariable Integer id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("project", projectService.get(id));
        modelAndView.setViewName("/project/project_add_edit_three");

        return modelAndView;
    }

    /**
     * 保存基本信息
     *
     * @param project
     * @return
     */
    @RequestMapping("/base/save/authj")
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
        if (StringUtils.isEmpty(project.getTags())) {
            result.setMessage("请选择项目标签");
            return result.toString();
        }
        project.setTags(project.getTags().substring(0, project.getTags().length() - 1));

        User loginUser = (User) httpSession.getAttribute("loginUser");
        project.setUserid(loginUser.getId());
        project.setUsername(loginUser.getName());
        project.setStatus(1);

        projectService.save(project);
        result.setData(project.getId());
        result.setCode(0);

        return result.toString();
    }

    /**
     * 保存团队信息
     *
     * @param teamMember
     * @return
     */
    @RequestMapping("/team/save/authj")
    @ResponseBody
    public String saveTeam(TeamMember teamMember) {

        LOGGER.debug("保存团队信息");
        Result<Integer> result = new Result<>();

        teamMemberService.save(teamMember);

        result.setData(teamMember.getProjectid());

        return result.toString();
    }

    /**
     * 保存项目信息
     *
     * @param project
     * @return
     */
    @RequestMapping("/financing/save/authj")
    @ResponseBody
    public String saveFinancing(Project project) {

        LOGGER.debug("保存项目信息");
        Result result = new Result<>();

        //project.setId(projectid);

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

        int pageSize = 2;
        int recordCount = 0;

        List<Project> projectList = new ArrayList<>();
        if (StringUtils.isEmpty(keyword)) {
            Map<String, Object> map = projectService.listByCondition(type, areaid, financingLimit, industry, pageIndex, pageSize);
            projectList = (List<Project>) map.get("projects");
            recordCount = (Integer) map.get("count");
        } else
            projectList = projectService.search(keyword);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("project/projects");
        modelAndView.addObject("projects", projectList);
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

    @RequestMapping("/{id}")
    public ModelAndView get(@PathVariable Integer id) {
        Project project = projectService.get(id);
        List<TeamMember> members = teamMemberService.listByProject(id);
        List<CommonQa> commonQas = commonQaService.list();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("project/project_detail");
        modelAndView.addObject("project", project);
        modelAndView.addObject("members", members);
        modelAndView.addObject("commonQas", commonQas);
        return modelAndView;
    }

}
