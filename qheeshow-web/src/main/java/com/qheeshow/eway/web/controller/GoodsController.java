package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.Goods;
import com.qheeshow.eway.service.model.OrderDetail;
import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.GoodsService;
import com.qheeshow.eway.service.service.OrderDetailService;
import com.qheeshow.eway.service.service.OrderService;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping("/list/{projectid}")
    public ModelAndView list(@PathVariable Integer projectid, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        List<Project> projects = new ArrayList<>();
        Object o = session.getAttribute("loginUser");
        if (o != null) {
            User loginUser = (User) o;
            projects = projectService.listByUser(loginUser.getId());
        }
        List<Goods> goodses = goodsService.listByStatus(1);
        String buyBtncls1 = "g-purchase2";
        String buyBtncls2 = "g-purchase2";
        String buyBtncls3 = "g-purchase3";
        String buyBtncls4 = "g-purchase3";
        if (o != null) {//已登录
            if (projects.size() == 0) {//未创建项目
                buyBtncls1 = "g-purchase2";
                buyBtncls2 = "g-purchase2";
                buyBtncls3 = "g-purchase2";
                buyBtncls4 = "g-purchase2";
            } else {
                Project project = null;
                if (projectid.intValue() == 0)
                    project = projects.get(0);
                else
                    project = projectService.get(projectid);
                /*if (project.getStatus().intValue() == 1) {//新项目
                    buyBtncls1 = "g-purchase2";
                }*/
                boolean buyed2 = false;
                boolean buyed34 = false;
                List<OrderDetail> orderDetails = orderDetailService.listByProject(project.getId());
                for (OrderDetail orderDetail : orderDetails) {
                    if (orderDetail.getGoodsid().intValue() == 1) {

                    } else if (orderDetail.getGoodsid().intValue() == 2) {
                        buyed2 = true;
                    } else if (orderDetail.getGoodsid().intValue() == 3) {
                        buyed34 = true;
                        buyBtncls1 = "g-purchase";
                        buyBtncls3 = "g-purchase2";
                    } else if (orderDetail.getGoodsid().intValue() == 4) {
                        buyed34 = true;
                        buyBtncls1 = "g-purchase";
                        buyBtncls4 = "g-purchase2";
                    }
                }
                if (buyed34 && !buyed2)
                    buyBtncls2 = "g-purchase3";
            }
        } else {
            buyBtncls1 = "g-purchase";
            buyBtncls2 = "g-purchase3";
        }
        modelAndView.setViewName("goods/goods_list");
        modelAndView.addObject("projectid", projectid);
        modelAndView.addObject("projects", projects);
        modelAndView.addObject("goodses", goodses);
        modelAndView.addObject("buyBtncls1", buyBtncls1);
        modelAndView.addObject("buyBtncls2", buyBtncls2);
        modelAndView.addObject("buyBtncls3", buyBtncls3);
        modelAndView.addObject("buyBtncls4", buyBtncls4);
        return modelAndView;
    }

}
