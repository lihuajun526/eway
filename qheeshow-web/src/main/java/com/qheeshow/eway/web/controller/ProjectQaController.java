package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.ProjectQa;
import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.ProjectQaService;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.service.service.UserService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/project/qa")
public class ProjectQaController extends BaseController {

    @Autowired
    private ProjectQaService commonQaService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/list/{projectid}/{index}")
    public ModelAndView list(@PathVariable Integer projectid, @PathVariable Integer index) {

        ProjectQa projectQa = new ProjectQa();
        projectQa.setPageSize(6);
        projectQa.setProjectid(projectid);

        Project project = projectService.get(projectid);

        Map map = commonQaService.listByPage(projectQa, index);
        Integer count = (Integer) map.get("count");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("project/project_qa");
        modelAndView.addObject("commonQas", (List<ProjectQa>) map.get("commonQas"));
        modelAndView.addObject("projectid", projectid);
        modelAndView.addObject("userid", project.getUserid());
        modelAndView.addObject("pageIndex", index);
        modelAndView.addObject("count", count);
        modelAndView.addObject("pageCount", count % 6 == 0 ? count / 6 : (count / 6 + 1));

        return modelAndView;
    }

    /**
     * 提问
     *
     * @param projectQa
     * @param session
     * @return
     */
    @RequestMapping("/q/authj")
    @ResponseBody
    public String q(ProjectQa projectQa, HttpSession session) {

        Result result = new Result();

        User loginUser = (User) session.getAttribute("loginUser");

        projectQa.setParentid(0);
        projectQa.setUserid(loginUser.getId());
        projectQa.setName(loginUser.getName());
        projectQa.setPhoto(loginUser.getPhoto());
        projectQa.setStatus(1);

        commonQaService.save(projectQa);

        return result.toString();
    }

    /**
     * 回答
     *
     * @param projectQa
     * @param session
     * @return
     */
    @RequestMapping("/a/authj")
    public String a(ProjectQa projectQa, HttpSession session) {
        Result result = new Result();

        ProjectQa q = commonQaService.get(projectQa.getParentid());
        User qUser = userService.get(projectQa.getqUserid());
        User loginUser = (User) session.getAttribute("loginUser");

        projectQa.setQuestion(q.getContent());
        projectQa.setqName(qUser.getName());
        projectQa.setUserid(loginUser.getId());
        projectQa.setName(loginUser.getName());
        projectQa.setPhoto(loginUser.getPhoto());
        projectQa.setStatus(1);

        commonQaService.save(projectQa);

        return result.toString();
    }

}
