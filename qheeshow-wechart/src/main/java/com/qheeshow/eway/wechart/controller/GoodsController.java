package com.qheeshow.eway.wechart.controller;

import com.qheeshow.eway.service.model.Goods;
import com.qheeshow.eway.service.model.OrderDetail;
import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.GoodsService;
import com.qheeshow.eway.service.service.OrderDetailService;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.wechart.base.BaseController;
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
@RequestMapping("/goods/do")
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
        if (o == null) {
            modelAndView.setViewName("pub/404");
            return modelAndView;
        }
        User loginUser = (User) o;
        projects = projectService.listByUser(loginUser.getId());
        List<Goods> goodses = goodsService.listByStatus(1);
        Boolean flag1 = false;
        Boolean flag2 = false;
        Boolean flag3 = false;
        Boolean flag4 = false;

        if (projects.size() == 0) {//未创建项目

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
                } else if (orderDetail.getGoodsid().intValue() == 4) {
                    buyed34 = true;
                }
            }
            if (buyed34)
                flag1 = true;
            flag2 = !buyed2;
            flag3 = flag4 = !buyed34;

        }
        modelAndView.setViewName("goods/goods_list");
        modelAndView.addObject("projectid", projectid);
        modelAndView.addObject("projects", projects);
        modelAndView.addObject("goodses", goodses);
        modelAndView.addObject("flag1", flag1);
        modelAndView.addObject("flag2", flag2);
        modelAndView.addObject("flag3", flag3);
        modelAndView.addObject("flag4", flag4);
        return modelAndView;
    }

    @RequestMapping("/get/{goodsid}/{flag}")
    public ModelAndView get(@PathVariable Integer goodsid, @PathVariable Integer flag) {
        ModelAndView modelAndView = new ModelAndView();

        Goods goods = goodsService.selectByPrimaryKey(goodsid);

        modelAndView.addObject("goods", goods);
        modelAndView.addObject("flag", flag);
        modelAndView.setViewName("goods/t_" + goodsid);
        return modelAndView;
    }

}
