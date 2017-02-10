package com.qheeshow.eway.backstage.controller;

import com.qheeshow.eway.backstage.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/ueditor")
    public String ueditor(){
        return "ueditor";
    }

}
