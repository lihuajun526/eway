package com.qheeshow.eway.backstage.controller;

import com.alibaba.fastjson.JSON;
import com.qheeshow.eway.backstage.base.BaseController;
import com.qheeshow.eway.backstage.base.Result;
import com.qheeshow.eway.backstage.base.ResultDg;
import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.model.ProjectSuggest;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.service.service.ProjectSuggestService;
import com.qheeshow.eway.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    private UserService userService;
    @Autowired
    private ProjectSuggestService projectSuggestService;

    @RequestMapping("/list/{status}")
    @ResponseBody
    public String list(@PathVariable Integer status) {
        ResultDg<List<Project>> resultDg = new ResultDg<>();

        List<Project> list = projectService.listByStatus(status);
        resultDg.setTotal(list == null ? 0 : list.size());
        resultDg.setRows(list);

        return JSON.toJSONString(resultDg);
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public String get(@PathVariable Integer id) {
        Result<Project> result = new Result<>();

        Project project = projectService.get(id);
        User user = userService.get(project.getUserid());
        project.setMobile(user.getMobile());

        result.setData(project);

        return result.toString();
    }

    /**
     * 更新状态
     *
     * @param id
     * @return
     */
    @RequestMapping("/{id}/{status}")
    @ResponseBody
    public String updateStatus(@PathVariable Integer id, @PathVariable Integer status) {
        Result result = new Result();

        Project project = new Project();
        project.setId(id);
        project.setStatus(status);

        projectService.save(project);

        return result.toString();
    }

    /**
     * 保存基本信息
     *
     * @param project
     * @return
     */
    @RequestMapping("/base/save")
    @ResponseBody
    public String saveBase(Project project, HttpServletRequest request) {

        LOGGER.debug("保存项目基本信息");
        Result<Integer> result = new Result<>();

        projectService.save(project);
        result.setData(project.getId());
        result.setCode(0);

        return result.toString();
    }

    /**
     * 保存基本信息
     *
     * @param project
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public String save(Project project) {

        LOGGER.debug("保存项目信息");
        Result result = new Result();

        User user = userService.getByMobile(project.getMobile());
        if (user == null) {
            result.set(-1, "该手机号码不存在");
            return result.toString();
        }
        project.setUserid(user.getId());
        project.setUsername(user.getName());
        if (!StringUtils.isEmpty(project.getTags())) {
            if (project.getTags().indexOf("#") != -1) {
                project.setTags(project.getTags().substring(0, project.getTags().length() - 1));
            }
        }
        projectService.save(project);

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

    @RequestMapping("/investor/suggest/{projectid}")
    @ResponseBody
    public String suggestInvestor(@PathVariable Integer projectid, String ids) {

        Result result = new Result<>();

        if (projectid == null || projectid.intValue() == 0 || StringUtils.isEmpty(ids)) {
            result.set(-1, "项目id为空或投资人为空");
            return result.toString();
        }
        projectSuggestService.addSuggest(projectid, ids);
        return result.toString();
    }

}
