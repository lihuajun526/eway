package com.qheeshow.eway.wechart.controller;

import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.model.ProjectQa;
import com.qheeshow.eway.service.model.TeamMember;
import com.qheeshow.eway.service.service.ProjectQaService;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.service.service.TeamMemberService;
import com.qheeshow.eway.service.service.XwcmclassinfoService;
import com.qheeshow.eway.wechart.base.BaseController;
import com.qheeshow.eway.wechart.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/project/do")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private XwcmclassinfoService xwcmclassinfoService;
    @Autowired
    private TeamMemberService teamMemberService;
    @Autowired
    private ProjectQaService commonQaService;

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
    @ResponseBody
    public String listByCondition(@PathVariable Integer type, @PathVariable Integer areaid,
                                  @PathVariable Integer financingLimit, @PathVariable Integer industry,
                                  @PathVariable Integer pageIndex, String keyword) {

        LOGGER.debug("根据条件过滤项目");

        Result<List<Project>> result = new Result<>();

        int pageSize = 10;

        List<Project> projectList = new ArrayList<>();
        Map<String, Object> map = projectService.listByCondition(type, areaid, financingLimit, industry, pageIndex, pageSize, keyword);
        projectList = (List<Project>) map.get("projects");

        result.setData(projectList);

        return result.toString();
    }

    @RequestMapping("/{id}")
    public ModelAndView get(@PathVariable Integer id) {
        Project project = projectService.get(id);
        List<TeamMember> members = teamMemberService.listByProject(id);
        List<ProjectQa> projectQas = commonQaService.list();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("project/project_detail");
        modelAndView.addObject("project", project);
        modelAndView.addObject("members", members);
        modelAndView.addObject("commonQas", projectQas);
        return modelAndView;
    }

}
