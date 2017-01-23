package com.qheeshow.eway.backstage.controller;

import com.alibaba.fastjson.JSON;
import com.qheeshow.eway.backstage.base.BaseController;
import com.qheeshow.eway.backstage.base.ResultDg;
import com.qheeshow.eway.service.model.Goods;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/classinfo")
public class ClassinfoController extends BaseController {

    @RequestMapping("/list")
    @ResponseBody
    public String list(Integer pid) {



        return JSON.toJSONString(null);
    }

}
