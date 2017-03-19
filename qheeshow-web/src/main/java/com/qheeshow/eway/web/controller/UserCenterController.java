package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.*;
import com.qheeshow.eway.service.service.ProjectFollowService;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/center")
public class UserCenterController extends BaseController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectFollowService projectFollowService;

    @RequestMapping("/index")
    public String index() {
        return "center/center";
    }

    @RequestMapping("/project/{type}/{pageSize}/{pageIndex}")
    public ModelAndView projectMgr(HttpSession session, @PathVariable Integer type, @PathVariable Integer pageSize, @PathVariable Integer pageIndex) {

        User loginUser = (User) session.getAttribute("loginUser");
        List<Project> projects = null;
        ModelAndView modelAndView = new ModelAndView();
        if (loginUser.getRoleid() < 4) {//创业者
            modelAndView.setViewName("center/user_project_list");
            projects = projectService.listByUser(loginUser.getId());
            for (Project project : projects) {
                project.setForcus(projectFollowService.list(project.getId()).size());
            }
        } else {//投资人
            modelAndView.setViewName("center/investor_project_list");
            Integer count = 0;
            Map<String, Object> map = new HashMap<>();
            if (type.intValue() == 1) {//平台推荐的项目
                ProjectSuggest projectSuggest = new ProjectSuggest();
                projectSuggest.setInvestorid(loginUser.getId());
                projectSuggest.setPageSize(pageSize);
                projectSuggest.setStartRow((pageIndex - 1) * pageSize);
                map = projectService.listSuggest(projectSuggest);
                projects = (List<Project>) map.get("projects");
            } else if (type.intValue() == 2) {//关注的项目
                ProjectFollow projectFollow = new ProjectFollow();
                projectFollow.setUserid(loginUser.getId());
                projectFollow.setPageSize(pageSize);
                projectFollow.setStartRow((pageIndex - 1) * pageSize);
                map = projectService.listFollow(projectFollow);
                projects = (List<Project>) map.get("projects");
            } else if (type.intValue() == 3) {//金融顾问
                ProjectAdviser projectAdviser = new ProjectAdviser();
                projectAdviser.setUserid(loginUser.getId());
                projectAdviser.setPageSize(pageSize);
                projectAdviser.setStartRow((pageIndex - 1) * pageSize);
                map = projectService.listAdviser(projectAdviser);
                projects = (List<Project>) map.get("projects");
            }
            count = (Integer) map.get("count");
            modelAndView.addObject("pageSize", pageSize);
            modelAndView.addObject("pageIndex", pageIndex);
            modelAndView.addObject("pageCount", count % pageSize == 0 ? count / pageSize : (count / pageSize + 1));
        }
        modelAndView.addObject("projects", projects == null ? new ArrayList<>() : projects);
        return modelAndView;
    }
}
