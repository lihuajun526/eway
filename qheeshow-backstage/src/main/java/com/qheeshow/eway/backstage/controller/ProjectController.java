package com.qheeshow.eway.backstage.controller;

import com.alibaba.fastjson.JSON;
import com.qheeshow.eway.backstage.base.BaseController;
import com.qheeshow.eway.backstage.base.ResultDg;
import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/list/{status}")
    @ResponseBody
    public String list(@PathVariable Integer status){
        ResultDg<List<Project>> resultDg = new ResultDg<>();

        List<Project> list = projectService.listByStatus(null);
        resultDg.setTotal(list == null ? 0 : list.size());
        resultDg.setRows(list);

        return JSON.toJSONString(resultDg);
    }



}
