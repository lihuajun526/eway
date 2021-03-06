package com.qheeshow.eway.wechart.controller;

import com.qheeshow.eway.common.constant.ExceptionTypeEnum;
import com.qheeshow.eway.common.exception.CommonException;
import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.service.model.*;
import com.qheeshow.eway.service.service.*;
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
import java.util.HashMap;
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
    @Autowired
    private ProjectFollowService projectFollowService;
    @Autowired
    private ProjectAdviserService projectAdviserService;
    @Autowired
    private XwcmclassinfoService xwcmclassinfoService;
    @Autowired
    private ProjectQaService projectQaService;
    @Autowired
    private UserService userService;

    /**
     * 根据条件过滤项目
     *
     * @param pageIndex
     * @param session
     * @return
     */
    @RequestMapping("/list/{pageIndex}")
    @ResponseBody
    public String listByCondition(
            @PathVariable Integer pageIndex, HttpSession session) {

        LOGGER.debug("根据条件过滤项目");


        Result<List<Project>> result = new Result<>();

        int pageSize = 10;

        Integer type = session.getAttribute("type") == null ? 0 : (Integer) session.getAttribute("type");
        Integer areaid = session.getAttribute("area") == null ? 0 : (Integer) session.getAttribute("area");
        Integer financingLimit = session.getAttribute("financingLimit") == null ? 0 : (Integer) session.getAttribute("financingLimit");
        Integer industry = session.getAttribute("industry") == null ? 0 : (Integer) session.getAttribute("industry");
        String keyword = session.getAttribute("keyword") == null ? "" : (String) session.getAttribute("keyword");

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
            tip.setLink(Config.get("app.path") + "/investor/investor_auth");
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

    @RequestMapping("/follow/{projectid}/v_authj")
    @ResponseBody
    public String follow(@PathVariable Integer projectid, HttpSession session) {

        Result<Tip> result = new Result<>();
        Tip tip = new Tip();
        result.setData(tip);

        User loginUser = (User) session.getAttribute("loginUser");
        ProjectFollow projectFollow = new ProjectFollow();
        projectFollow.setProjectid(projectid);
        projectFollow.setUserid(loginUser.getId());
        try {
            projectFollowService.follow(projectFollow);
        } catch (CommonException e) {
            LOGGER.error("error:", e);
            result.setMessage("关注失败");
            return result.toString();
        }
        result.setMessage("关注成功");
        return result.toString();
    }

    @RequestMapping("/unfollow/{projectid}/v_authj")
    @ResponseBody
    public String unFollow(@PathVariable Integer projectid, HttpSession session) {

        Result<Tip> result = new Result<>();
        Tip tip = new Tip();
        result.setData(tip);

        User loginUser = (User) session.getAttribute("loginUser");
        ProjectFollow projectFollow = new ProjectFollow();
        projectFollow.setProjectid(projectid);
        projectFollow.setUserid(loginUser.getId());
        projectFollowService.unFollow(projectFollow);

        result.setMessage("取消关注成功");
        return result.toString();
    }

    @RequestMapping("/isfollow/{projectid}")
    @ResponseBody
    public String isFollow(@PathVariable Integer projectid, HttpSession session) {
        Result<Boolean> result = new Result();
        if (session.getAttribute("loginUser") == null) {
            result.setData(false);
            return result.toString();
        }
        User loginUser = (User) session.getAttribute("loginUser");
        ProjectFollow projectFollow = new ProjectFollow();
        projectFollow.setProjectid(projectid);
        projectFollow.setUserid(loginUser.getId());
        boolean isFollow = projectFollowService.isFollow(projectFollow);
        result.setData(isFollow);
        return result.toString();
    }

    @RequestMapping("/adviser/apply/{projectid}/v_authj")
    @ResponseBody
    public String apply(@PathVariable Integer projectid, HttpSession session) {

        Result<Tip> result = new Result<>();
        Tip tip = new Tip();
        result.setData(tip);

        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser.getRoleid().intValue() < 30 || loginUser.getRoleid() >= 40) {
            result.setMessage("对不起，您不是投资人，不能申请");
            return result.toString();
        }
        try {
            projectAdviserService.apply(projectid, loginUser.getId());
        } catch (CommonException e) {
            if (e.getCode().equals(ExceptionTypeEnum.Adviser_Info_Not_Full.getCode())) {
                result.setMessage("您尚未填写个人详细信息，请在电脑端补充完善（网址：http://www.qheeshow.com）");
                return result.toString();
            }
            if (e.getCode().equals(ExceptionTypeEnum.Investor_Not_Auth_ERROR.getCode())) {
                result.setMessage("对不起，您的投资人身份尚未认证");
                tip.setAction("去认证");
                tip.setLink(Config.get("app.path") + "/investor/investor_auth");
                return result.toString();
            }
            if (e.getCode().equals(ExceptionTypeEnum.Project_Adviser_Apply_Exist_ERROR.getCode())) {
                result.setMessage("您已申请，不能重复申请");
                return result.toString();
            }
            if (e.getCode().equals(ExceptionTypeEnum.Project_Adviser_Full_ERROR.getCode())) {
                result.setMessage("对不起，顾问人数已满");
                return result.toString();
            }
        }
        result.setMessage("申请成功");
        tip.setAction("关闭");
        return result.toString();
    }

    @RequestMapping("/condition/list")
    public ModelAndView listCondition() {

        //项目所属行业rootid
        int classinfo_rootid_industry = Config.getInt("classinfo.rootid.industry");
        //项目所属地域rootid
        int classinfo_rootid_area = Config.getInt("classinfo.rootid.area");
        //项目融资额度rootid
        int classinfo_rootid_financing_limit = Config.getInt("classinfo.rootid.financing.limit");

        List<Xwcmclassinfo> industrys = xwcmclassinfoService.listByRoot(classinfo_rootid_industry);
        List<Xwcmclassinfo> areas = xwcmclassinfoService.listByRoot(classinfo_rootid_area);
        List<Xwcmclassinfo> financingLimits = xwcmclassinfoService.listByRoot(classinfo_rootid_financing_limit);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("industrys", industrys);
        modelAndView.addObject("areas", areas);
        modelAndView.addObject("financingLimits", financingLimits);
        modelAndView.setViewName("/project/conditions");

        return modelAndView;
    }

    @RequestMapping("/condition/set/{type}/{area}/{financingLimit}/{industry}")
    @ResponseBody
    public String setCondition(@PathVariable Integer type, @PathVariable Integer area, @PathVariable Integer financingLimit, @PathVariable Integer industry, String keyword, HttpSession session) {

        Result result = new Result();

        session.setAttribute("type", type);
        session.setAttribute("area", area);
        session.setAttribute("financingLimit", financingLimit);
        session.setAttribute("industry", industry);
        session.setAttribute("keyword", keyword);

        return result.toString();
    }

    @RequestMapping("/qa/list/{projectid}")
    public ModelAndView listQa(@PathVariable Integer projectid) {

        List<ProjectQa> qs = projectQaService.listQByProject(projectid);

        List<Integer> ids = new ArrayList<>();
        for (ProjectQa projectQa : qs) {
            ids.add(projectQa.getId());
        }

        List<ProjectQa> as = ids.size() > 0 ? projectQaService.listA(ids) : new ArrayList<>();
        Map<Integer, ProjectQa> aMap = new HashMap<>();
        for (ProjectQa projectQa : as) {
            aMap.put(projectQa.getParentid(), projectQa);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("aMap", aMap);
        modelAndView.addObject("qs", qs);
        Project project = projectService.get(projectid);
        modelAndView.addObject("cruser", project == null ? 0 : project.getUserid());
        modelAndView.setViewName("/project/qa_list");

        return modelAndView;
    }

    /**
     * 提问
     *
     * @param projectid
     * @param content
     * @param session
     * @return
     */
    @RequestMapping("/q/{projectid}/v_authj")
    @ResponseBody
    public String q(@PathVariable Integer projectid, String content, HttpSession session) {

        Result<Tip> result = new Result<>();
        Tip tip = new Tip();
        result.setData(tip);
        result.setCode(-1);
        result.setMessage("提问失败");

        User loginUser = (User) session.getAttribute("loginUser");

        ProjectQa projectQa = new ProjectQa();
        projectQa.setParentid(0);
        projectQa.setProjectid(projectid);
        projectQa.setContent(content);
        projectQa.setUserid(loginUser.getId());
        projectQa.setName(StringUtils.isEmpty(loginUser.getName()) ? loginUser.getNickname() : loginUser.getName());
        if (!StringUtils.isEmpty(loginUser.getPhoto()))
            projectQa.setPhoto(loginUser.getPhoto());
        else if (!StringUtils.isEmpty(loginUser.getHeadimgurl()))
            projectQa.setPhoto(loginUser.getHeadimgurl());
        else
            projectQa.setPhoto(Config.get("app.path") + "/images/df.jpg");
        projectQa.setStatus(1);

        projectQaService.save(projectQa);

        result.setCode(0);
        result.setMessage("提问成功");
        return result.toString();
    }

    /**
     * 回复
     *
     * @param projectid
     * @param quserid
     * @param qid
     * @param content
     * @param session
     * @return
     */
    @RequestMapping("/a/{projectid}/{quserid}/{qid}/v_authj")
    @ResponseBody
    public String a(@PathVariable Integer projectid, @PathVariable Integer quserid, @PathVariable Integer qid, String content, HttpSession session) {

        Result<Tip> result = new Result<>();
        Tip tip = new Tip();
        result.setData(tip);
        result.setCode(-1);
        result.setMessage("回复失败");

        User loginUser = (User) session.getAttribute("loginUser");
        ProjectQa q = projectQaService.get(qid);
        User qUser = userService.get(quserid);

        ProjectQa projectQa = new ProjectQa();
        projectQa.setParentid(qid);
        projectQa.setProjectid(projectid);
        projectQa.setQuestion(q.getContent());
        projectQa.setContent(content);
        projectQa.setqUserid(qUser.getId());
        projectQa.setqName(StringUtils.isEmpty(qUser.getName()) ? qUser.getNickname() : qUser.getName());
        projectQa.setUserid(loginUser.getId());
        projectQa.setName(StringUtils.isEmpty(loginUser.getName()) ? loginUser.getNickname() : loginUser.getName());

        if (!StringUtils.isEmpty(loginUser.getPhoto()))
            projectQa.setPhoto(loginUser.getPhoto());
        else if (!StringUtils.isEmpty(loginUser.getHeadimgurl()))
            projectQa.setPhoto(loginUser.getHeadimgurl());
        else
            projectQa.setPhoto(Config.get("app.path") + "/images/df.jpg");
        projectQa.setStatus(1);

        projectQaService.save(projectQa);

        result.setCode(0);
        result.setMessage("回复成功");
        return result.toString();
    }

    /**
     * 获得登录用户创建的项目
     *
     * @param session
     * @return
     */
    @RequestMapping("/list/mypros/v_authj")
    @ResponseBody
    public String listMyProject(HttpSession session) {

        Result<Tip> result = new Result<>();
        Tip tip = new Tip();
        result.setData(tip);

        User loginUser = (User) session.getAttribute("loginUser");
        List<Project> list = projectService.listByUser(loginUser.getId());
        if (list.size() == 0) {
            result.setMessage("您尚未创建项目，请在电脑端登录梧桐e路进行创建（网址：http://www.qheeshow.com）");
            result.setCode(-2);
            return result.toString();
        }

        for (Project project : list) {
            if (project.getTitle().length() > 5) {
                project.setTitle(project.getTitle().substring(0, 4) + "...");
            }
        }

        tip.setData(list);
        return result.toString();
    }

}
