package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

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
        result.setCode(-1);

        if (StringUtils.isEmpty(project.getLogo())) {
            result.setMessage("请上传项目logo");
            return result.toString();
        }
        if (StringUtils.isEmpty(project.getTitle())) {
            result.setMessage("项目名称不能为空");
            return result.toString();
        }
        if (StringUtils.isEmpty(project.getSummary())) {
            result.setMessage("项目简介不能为空");
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
        if (project.getPercent() == null) {
            result.setMessage("项目出让比例不能为空");
            return result.toString();
        }
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

}
