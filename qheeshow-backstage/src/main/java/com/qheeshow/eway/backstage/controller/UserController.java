package com.qheeshow.eway.backstage.controller;

import com.qheeshow.eway.backstage.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    /**
     * 根据项目匹配投资人
     * @param projectid
     * @return
     */
    @RequestMapping("/match/{projectid}")
    @ResponseBody
    public String matchByProject(@PathVariable Integer projectid) {



        return "index";
    }

}
