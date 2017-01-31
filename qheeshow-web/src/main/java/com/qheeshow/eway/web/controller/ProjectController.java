package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    /**
     * 保存项目基本信息
     *
     * @param project
     * @return
     */
    @RequestMapping("/base/save")
    @ResponseBody
    public String saveBase(Project project) {

        LOGGER.debug("保存项目基本信息");

        Result<Integer> result = new Result<>();

        projectService.save(project);
        result.setData(project.getId());

        return result.toString();
    }

}
