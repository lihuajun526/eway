package com.qheeshow.eway.web.controller;

import com.alibaba.fastjson.JSON;
import com.qheeshow.eway.service.model.ProjectQa;
import com.qheeshow.eway.service.service.ProjectQaService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import com.qheeshow.eway.web.base.ResultDg;
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
@RequestMapping("/project/qa")
public class ProjectQaController extends BaseController {

    @Autowired
    private ProjectQaService projectQaService;

    @RequestMapping("/q")
    @ResponseBody
    public String question(ProjectQa projectQa) {

        Result result = new Result();

        projectQaService.save(projectQa);

        return result.toString();
    }

    @RequestMapping("/a")
    @ResponseBody
    public String answer(ProjectQa projectQa) {
        Result result = new Result();

        projectQaService.save(projectQa);

        return result.toString();
    }

    @RequestMapping("/list/{status}")
    @ResponseBody
    public String answer(@PathVariable Integer status) {

        ResultDg<List<ProjectQa>> resultDg = new ResultDg<>();

        List<ProjectQa> list = projectQaService.listByStatus(status);
        resultDg.setTotal(list == null ? 0 : list.size());
        resultDg.setRows(list);

        return JSON.toJSONString(resultDg);
    }

}
