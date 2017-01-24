package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.Classinfo;
import com.qheeshow.eway.service.service.ClassinfoService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("classinfo")
public class ClassinfoController extends BaseController {

    @Autowired
    private ClassinfoService classinfoService;

    @RequestMapping(value = "/list/root/{rootid}", method = RequestMethod.GET)
    @ResponseBody
    public String listByRoot(@PathVariable Integer rootid) {

        LOGGER.debug("rooid=" + rootid);

        Result<List<Classinfo>> result = new Result<>();

        List<Classinfo> list = classinfoService.listByRoot(rootid);
        result.setData(list);

        return result.toString();
    }

}
