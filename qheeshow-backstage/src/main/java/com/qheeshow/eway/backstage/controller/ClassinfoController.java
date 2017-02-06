package com.qheeshow.eway.backstage.controller;

import com.qheeshow.eway.backstage.base.BaseController;
import com.qheeshow.eway.backstage.base.Result;
import com.qheeshow.eway.service.model.Classinfo;
import com.qheeshow.eway.service.service.ClassinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("classinfo")
public class ClassinfoController extends BaseController {

    @Autowired
    private ClassinfoService classinfoService;

    /**
     * 通过rootid获得所有分类法
     * @param rootid
     * @return
     */
    @RequestMapping(value = "/list/root/{rootid}", method = RequestMethod.GET)
    @ResponseBody
    public String listByRoot(@PathVariable Integer rootid) {

        LOGGER.debug("rooid=" + rootid);

        Result<List<Classinfo>> result = new Result<>();

        List<Classinfo> list = classinfoService.listByRoot(rootid);
        result.setData(list);

        return result.toString();
    }

    /**
     * 通过id获得分类法
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String get(@PathVariable Integer id) {

        LOGGER.debug("classinfoid=" + id);

        Result<Classinfo> result = new Result<>();

        Classinfo classinfo = classinfoService.get(id);
        result.setData(classinfo);

        return result.toString();
    }

}
