package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.Goods;
import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.GoodsService;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/list")
    public ModelAndView list(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User loginUser = (User) session.getAttribute("loginUser");
        List<Project> projects = projectService.listByUser(loginUser.getId());
        List<Goods> goodses = goodsService.listByStatus(1);
        modelAndView.setViewName("goods/goods_list");
        modelAndView.addObject("projects", projects);
        modelAndView.addObject("goodses", goodses);
        return modelAndView;
    }

}
