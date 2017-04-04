package com.qheeshow.eway.backstage.controller;

import com.alibaba.fastjson.JSON;
import com.qheeshow.eway.backstage.base.BaseController;
import com.qheeshow.eway.backstage.base.Result;
import com.qheeshow.eway.backstage.base.ResultDg;
import com.qheeshow.eway.service.model.ProjectAdviser;
import com.qheeshow.eway.service.service.ProjectAdviserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/project/adviser")
public class ProjectAdviserController extends BaseController {

    @Autowired
    private ProjectAdviserService projectAdviserService;

    @RequestMapping("/list/{status}")
    @ResponseBody
    public String list(@PathVariable Integer status, Integer page, Integer rows) {

        ResultDg<List<ProjectAdviser>> resultDg = new ResultDg<>();

        ProjectAdviser projectAdviser = new ProjectAdviser();
        projectAdviser.setStatus(status);
        projectAdviser.setPageSize(rows);
        projectAdviser.setStartRow(rows * (page - 1));

        Map<String, Object> map = projectAdviserService.listByStatusAndPage(projectAdviser);
        resultDg.setRows((List<ProjectAdviser>) map.get("projectAdvisers"));
        resultDg.setTotal((Integer) map.get("count"));

        return JSON.toJSONString(resultDg);
    }

    @RequestMapping("/status/update/{id}/{status}")
    @ResponseBody
    public String updateStatus(@PathVariable Integer id, @PathVariable Integer status) {

        Result result = new Result();

        ProjectAdviser projectAdviser = new ProjectAdviser();
        projectAdviser.setId(id);
        projectAdviser.setStatus(status);
        projectAdviserService.update(projectAdviser);

        return result.toString();
    }
}
