package com.qheeshow.eway.wechart.controller;

import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.service.model.Xwcmclassinfo;
import com.qheeshow.eway.service.service.XwcmclassinfoService;
import com.qheeshow.eway.wechart.base.BaseController;
import com.qheeshow.eway.wechart.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/classinfo")
public class XwcmclassinfoController extends BaseController {


    @Autowired
    private XwcmclassinfoService xwcmclassinfoService;

    @RequestMapping("/industry/list/{count}")
    @ResponseBody
    public String listByIndustryAndCount(@PathVariable Integer count) {

        Result<List<Xwcmclassinfo>> result = new Result<>();
        List<Xwcmclassinfo> list = xwcmclassinfoService.listByRoot(Config.getInt("classinfo.rootid.industry"));
        result.setData(list.subList(0, count));

        return result.toString();
    }
}
