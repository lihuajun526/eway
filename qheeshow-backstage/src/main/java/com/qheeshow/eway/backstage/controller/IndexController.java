package com.qheeshow.eway.backstage.controller;

import com.qheeshow.eway.backstage.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping("/index")
    public String index(HttpSession session) {

        if (session.getAttribute("loginUser") == null)
            return "login";

        return "index";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

}
