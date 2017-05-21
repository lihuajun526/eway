package com.qheeshow.eway.wechart.controller;

import com.qheeshow.eway.service.model.*;
import com.qheeshow.eway.service.service.MailService;
import com.qheeshow.eway.service.service.ProjectQaService;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.service.service.TeamMemberService;
import com.qheeshow.eway.wechart.base.BaseController;
import com.qheeshow.eway.wechart.base.Result;
import com.qheeshow.eway.wechart.base.Tip;
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
@RequestMapping("/project/do")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private TeamMemberService teamMemberService;
    @Autowired
    private ProjectQaService commonQaService;
    @Autowired
    private MailService mailService;

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

    @RequestMapping("/bp/download/{projectid}/v_authj")
    @ResponseBody
    public String downloadBp(@PathVariable Integer projectid, HttpSession session) {

        Result<Tip> result = new Result<>();
        Tip tip = new Tip();
        result.setData(tip);
        result.setCode(-1);

        Project project = projectService.get(projectid);
        if (project == null) {
            LOGGER.error("[id={}]的项目不存在", projectid);
            result.setMessage("对不起，项目不存在");
            return result.toString();
        }
        if (StringUtils.isEmpty(project.getBp())) {
            result.setMessage("对不起，该项目没有上传商业计划书");
            return result.toString();
        }
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser.getRoleid().intValue() >= 40 || loginUser.getRoleid().intValue() < 30) {
            result.setMessage("您不是投资人不能下载商业计划书");
            return result.toString();
        }
        if (loginUser.getRoleid().intValue() == 30) {
            result.setMessage("您的投资人身份尚未认证，请登录PC网站在个人中心进行认证");
            tip.setAction("去认证");
            tip.setLink("");
            return result.toString();
        }

        //邮件发送商业计划书
        MailBean mailBean = new MailBean();
        mailBean.setContent("<div><a href='" + project.getBp() + "'>点击下载商业计划书</a></div>");
        mailBean.setToAddress(loginUser.getEmail());
        mailBean.setSubject("\"" + project.getTitle() + "\"项目的商业计划书");
        mailService.sendHtmlMail(mailBean);

        result.setMessage("已将商业计划书发送到您的" + loginUser.getEmail() + "邮箱");
        result.setCode(0);
        return result.toString();
    }

}
